import Banco.AgenteDeBolsa;
import Banco.Cliente.Cliente;
import Banco.Empleado.*;
import Banco.Banco;
import Banco.Cliente.Inversiones.*;


import java.time.LocalDate;
import java.util.*;
import java.time.temporal.ChronoUnit;


public class Main {

    public static void main(String[] args) {

        HashMap<Integer, String> contraseñas = new HashMap<Integer, String>();
        HashMap<Integer, Cliente> clientes = new HashMap<Integer, Cliente>();
        HashMap<String, Empleado> empleados = new HashMap<String, Empleado>();

        LocalDate fechaActual = LocalDate.now();

        boolean keep = true;
        //creo al gerente
        final int dniGerente = 9090;
        final String passGerente = "9090";

        Cajero cajero = new Cajero("juan", "des", 123, 12444, 345, "trrh");
        AsesorDivisas asesorDivisas = new AsesorDivisas("Martina", "Gonzalez", 1440, 120000, 132445566, "mail");
        Gerente gerente = new Gerente("gerente", "des", 123, 12444, 345, "trrh");
        AgenteEspecial agenteE = new AgenteEspecial("marti", "manzano", 12, 0, 14566, "@email", gerente);
        AgenteDeBolsa agenteBolsa = new AgenteDeBolsa("martin");

        empleados.put("cajero", cajero);
        empleados.put("asesorDivisas", asesorDivisas);
        empleados.put("gerente", gerente);
        empleados.put("agenteE", agenteE);

        int contador = 0;

        inicializarCliente(contraseñas,clientes);

        while (keep) {
            System.out.println("Bienvenido al banco la familia");
            System.out.println("1. Ingresar");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");
            Scanner sc = new Scanner(System.in);
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese su dni");
                    int dni = sc.nextInt();
                    System.out.println("Ingrese su contraseña");
                    String pass = sc.nextLine();
                    pass = sc.nextLine();
                    if (contraseñas.containsKey(dni)) {
                        if (contraseñas.get(dni).equals(pass)) {
                            System.out.println("Bienvenido");
                            Cliente cliente = clientes.get(dni);
                            menuCliente(cliente, clientes, empleados, agenteBolsa, fechaActual);
                            if (contador == 2) {
                                fechaActual = fechaActual.plusMonths(1);
                                contador = 0;
                                System.out.println("Se ha actualizado la fecha");
                            } else {
                                contador++;
                            }
                        } else {
                            System.out.println("Contraseña incorrecta");
                        }
                    } else if (dni == dniGerente && pass.equals(passGerente)) {
                        //es el gerente, por lo tanto se abre otro menu
                        System.out.println("Bienvenido Gerente");
                        menuGerente();
                    } else {
                        System.out.println("Usuario no registrado");
                    }
                    break;
                case 2:
                    crearCliente(contraseñas, clientes);
                    break;
                case 3:
                    keep = false;
                    break;
                default:
                    System.out.println("Opción incorrecta");
                    break;
            }
            // Gerente gerente = new Gerente("Martin", "Sabez", 5675, 100000, 261345438, "matrinSabez@gmail.com");
            // gerente.mostrarDineroNoRegistrado();
        }
    }

    public static void menuGerente() {

    }

    public static void menuCliente(Cliente cliente, HashMap<Integer, Cliente> clientes, HashMap<String, Empleado> empleados, AgenteDeBolsa agenteBolsa, LocalDate fechaActual) {
        Cajero cajero = (Cajero) empleados.get("cajero");
        AsesorDivisas asesorDivisas = (AsesorDivisas) empleados.get("asesorDivisas");
        Gerente gerente = (Gerente) empleados.get("gerente");
        AgenteEspecial agenteE = (AgenteEspecial) empleados.get("agenteE");
        Scanner sc = new Scanner(System.in);
        if (cajero.getPrestamos().containsKey(cliente.getDni()) && cajero.getPrestamos().get(cliente.getDni()).getUltimaFechaPago().isBefore(fechaActual)) {
            Prestamo prestamo = cajero.getPrestamos().get(cliente.getDni());
            LocalDate ultimaFechaPago = prestamo.getUltimaFechaPago();

            long diferenciaMeses = ChronoUnit.MONTHS.between(ultimaFechaPago, fechaActual);

            if (diferenciaMeses == prestamo.getCuotasAtrasadas() + 1) {

                System.out.println("Usted tiene un préstamo pendiente de: $" + prestamo.getMontoPorPagar());
                double cuotaConAtraso = prestamo.getValorCuotas() * (prestamo.getCuotasAtrasadas() + 1);
                System.out.println("Debe pagar una cuota de: $" + cuotaConAtraso);

                if (prestamo.getCuotasAtrasadas() > 0) {
                    System.out.println("Ya que usted tiene " + prestamo.getCuotasAtrasadas() + " cuotas atrasadas.");
                }

                System.out.println("¿Desea pagar la cuota? (S/N)");
                String respuesta = sc.nextLine().toUpperCase();

                if (respuesta.equals("S") && cliente.getSaldo() >= cuotaConAtraso) {
                    cliente.setSaldo(cliente.getSaldo() - cuotaConAtraso); // pagar con la cuota atrasada
                    prestamo.setMontoPorPagar(prestamo.getMontoPorPagar() - cuotaConAtraso); // reducir monto por pagar
                    prestamo.setCuotasPagadas(prestamo.getCuotasPagadas() + 1); // sumar a las cuotas pagadas
                    prestamo.setUltimaFechaPago(fechaActual); // actualizar la fecha del último pago
                    prestamo.setCuotasAtrasadas(0); // reiniciar las cuotas atrasadas

                    if (prestamo.getMontoPorPagar() <= 0) {
                        System.out.println("Usted ha pagado completamente el préstamo.");
                        cajero.getPrestamos().remove(cliente.getDni()); // eliminar el préstamo una vez pagado
                    } else {
                        System.out.println("Usted ha pagado la cuota correspondiente.");
                    }
                } else if (respuesta.equals("S") && cliente.getSaldo() < cuotaConAtraso) {
                    System.out.println("Saldo insuficiente.");
                } else {
                    prestamo.setCuotasAtrasadas(prestamo.getCuotasAtrasadas() + 1);
                    System.out.println("Se ha aumentado el número de cuotas atrasadas.");
                }
            }
        }

        if (agenteBolsa.getInversionesPorCliente().containsKey(cliente)) {
            for (int i = 0; i < agenteBolsa.getInversionesPorCliente().get(cliente).size(); i++) {
                if (agenteBolsa.getInversionesPorCliente().get(cliente).get(i).getTipoInversion().equals("Plazo Fijo")) {
                    PlazoFijo plazoFijo = (PlazoFijo) agenteBolsa.getInversionesPorCliente().get(cliente).get(i);
                    if (plazoFijo.getFechaVencimiento().isBefore(fechaActual)) {
                        double ganancia = plazoFijo.getMonto() * plazoFijo.getTasaInteres();
                        cliente.setSaldo(cliente.getSaldo() + ganancia);
                        System.out.println("Su plazo fijo ha vencido, se le ha depositado la ganancia de: $" + ganancia);
                        agenteBolsa.getInversionesPorCliente().get(cliente).remove(i);
                    }
                }
            }
        }

        while (true) {
            System.out.println("1. Realizar transferencia");
            System.out.println("2. Realizar retiro");
            System.out.println("3. Realizar deposito");
            System.out.println("4. Solicitar prestamo");
            System.out.println("5. Consultar sobre divisas");
            System.out.println("6. Consultar sobre acciones, criptomonedas y plazos fijos");
            System.out.println("7. Consultar Saldo");
            System.out.println("8. Cerrar sesión");
            int opcion = sc.nextInt();
            if (opcion == 8) {
                break;
            }
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el monto a transferir");
                    double monto = sc.nextDouble();
                    System.out.println("Ingrese el dni del cliente destino");
                    int dniDestino = sc.nextInt();
                    cliente.solicitarTransferencia(monto, clientes.get(dniDestino), cajero);
                    break;

                case 2:
                    System.out.println("Ingrese el monto a retirar");
                    double montoRetirar = sc.nextDouble();
                    cliente.solicitarRetiro(montoRetirar, cajero);
                    break;

                case 3:
                    System.out.println("Cliente " + cliente.getNombre() + " justifique de donde viene el dinero");
                    sc.nextLine();
                    String respCliente = sc.nextLine();
                    System.out.println("Ingrese el monto a depositar");
                    double montoDepositar = sc.nextDouble();
                    if (respCliente.equals("")) { //si no conesta nada es plata sucia, lo maneja el agente especial
                        cliente.solicitarDeposito(montoDepositar, agenteE);
                    } else {
                        cliente.solicitarDeposito(montoDepositar, cajero);
                    }
                    break;

                case 4:
                    System.out.println("Ingrese el monto que le gustaría recibir");
                    double montoSolicitar = sc.nextDouble();
                    cliente.solicitarPrestamo(montoSolicitar, gerente, cajero);
                    break;

                case 5:
                    System.out.println("1. Consultar precios de divisas");
                    System.out.println("2. Comprar divisas");
                    System.out.println("3. Vender divisas");
                    System.out.println("4. Consultar divisas compradas");
                    int opcionDivisas = sc.nextInt();
                    switch (opcionDivisas) {
                        case 1:
                            cliente.solicitarPrecioDivisas(asesorDivisas);
                            break;

                        case 2:
                            System.out.println("Ingrese el monto a comprar");
                            double montoComprar = sc.nextDouble();
                            cliente.comprarDivisas(montoComprar, asesorDivisas);
                            break;

                        case 3:
                            System.out.println("Ingrese el monto a vender");
                            double montoVender = sc.nextDouble();
                            System.out.println("Ingrese la moneda a vender, usted posee: ");
                            cliente.mostrarDivisasCompradas();
                            String moneda = sc.nextLine();
                            moneda = sc.nextLine();
                            cliente.venderDivisas(moneda, montoVender, asesorDivisas);
                            break;
                        case 4:
                            cliente.mostrarDivisasCompradas();
                            break;
                        default:
                            System.out.println("Opción incorrecta");
                            break;
                    }
                    break;

                case 6:
                    if (cliente.getAgentedeBolsa() == null) {
                        cliente.setAgentedeBolsa(agenteBolsa);
                    }
                    System.out.println("1. Consultar sobre acciones");
                    System.out.println("2. Consultar sobre criptomonedas");
                    System.out.println("3. Consultar sobre plazos fijos");
                    System.out.println("4. Consultar precios de diferentes activos");
                    System.out.println("5. Consultar sobre mis activos");
                    switch (sc.nextInt()) {
                        case 1:
                            System.out.println("1. Comprar acciones");
                            System.out.println("2. Vender acciones");
                            int opcionAcciones = sc.nextInt();
                            switch (opcionAcciones) {
                                case 1:
                                    System.out.println("Cuanto dinero desea invertir?");
                                    double montoAcciones = sc.nextDouble();
                                    cliente.comprarActivo(montoAcciones, 2);
                                    break;
                                case 2:
                                    cliente.venderActivo(1);
                                    break;
                                default:
                                    System.out.println("Opción incorrecta");
                                    break;
                            }
                            break;
                        case 2:
                            System.out.println("1. Comprar criptomonedas");
                            System.out.println("2. Vender criptomonedas");
                            int opcionCripto = sc.nextInt();
                            switch (opcionCripto) {
                                case 1:
                                    System.out.println("Cuanto dinero desea invertir?");
                                    double montoCripto = sc.nextDouble();
                                    cliente.comprarActivo(montoCripto, 3);
                                    break;
                                case 2:
                                    cliente.venderActivo(2);
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case 3:
                            System.out.println("1. Realizar plazo fijo");
                            System.out.println("2. Consulta rendimiento de plazo fijo");
                            int opcionPlazoFijo = sc.nextInt();
                            switch (opcionPlazoFijo) {
                                case 1:
                                    System.out.println("Cuanto dinero desea invertir?");
                                    double montoPlazoFijo = sc.nextDouble();
                                    cliente.comprarActivo(montoPlazoFijo, 1);
                                    break;
                                case 2:
                                    cliente.consultarGananciasPlazoFijo();
                                    break;
                                default:
                                    System.out.println("Opción incorrecta");
                                    break;
                            }
                            break;
                        case 4:
                            cliente.consultarPrecios();
                            break;
                        case 5:
                            cliente.consultarActivos();
                            break;
                        default:
                            System.out.println("Opción incorrecta");
                            break;
                    }
                    break;
                case 7:
                    //mostrar saldo
                    System.out.println("Sr/Sra. su saldo actual es de: $" + cliente.getSaldo());
                    //gerente.mostrarDineroNoRegistrado(cliente);
                    break;
                default:
                    break;

            }
        }
    }

    public static void inicializarCliente(HashMap<Integer,String> contraseñas,HashMap<Integer,Cliente> clientes){
        AgenteDeBolsa agenteDeBolsa = new AgenteDeBolsa("Maria");
        Cliente cliente1 = new Cliente(12345, "Juan", "Loncharich", 120000, agenteDeBolsa);
        Cliente cliente2 = new Cliente(123123,"Trinidad","Perea",80000, agenteDeBolsa);
        Cliente cliente3 = new Cliente(00,"Pablo", "Vidal",1000000,agenteDeBolsa);
        Cliente cliente4 = new Cliente(11,"Laura","Noussan",2000000,agenteDeBolsa);
        contraseñas.put(cliente1.getDni(),"12345");
        clientes.put(cliente1.getDni(),cliente1);
        contraseñas.put(cliente2.getDni(),"123123");
        clientes.put(cliente2.getDni(),cliente2);
        contraseñas.put(cliente3.getDni(),"00");
        clientes.put(cliente3.getDni(),cliente3);
        contraseñas.put(cliente4.getDni(),"11");
        clientes.put(cliente4.getDni(),cliente4);
        return;
    }

    public static void crearCliente(HashMap<Integer, String> contraseñas, HashMap<Integer, Cliente> clientes) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese su nombre");
        String nombre = sc.nextLine();
        System.out.println("Ingrese su apellido");
        String apellido = sc.nextLine();


        System.out.println("Ingrese su dni");
        int dni = verificar(sc.nextLine());
        if (dni == 0) {
            return;}


        System.out.println("Ingrese su contraseña");
        String pass = sc.nextLine();

        Cliente cliente = new Cliente(dni, nombre, apellido, 0, null);

        contraseñas.put(dni, pass);
        clientes.put(dni, cliente);
        return;
    }

    public static Integer verificar(String input) {
        try {
            int numero = Integer.parseInt(input);
            return numero;  // Si es un entero válido, retorna el valor convertido
        } catch (NumberFormatException e) {
            System.out.println("Debe ingresar un número entero.");
            return 0;  // Retornamos null para indicar un error
        }
    }

}
