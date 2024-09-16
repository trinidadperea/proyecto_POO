import Banco.AgenteDeBolsa;
import Banco.Cliente.Cliente;
import Banco.Empleado.*;
import Banco.Cliente.Inversiones.*;
import Banco.Banco;

import java.time.LocalDate;
import java.util.*;
import java.time.temporal.ChronoUnit;


public class Main {

    public static void main(String[] args) {

        HashMap<Integer, String> contraseñas = new HashMap<Integer, String>();
        HashMap<Integer, Cliente> clientes = new HashMap<Integer, Cliente>();
        HashMap<String, Empleado> empleados = new HashMap<String, Empleado>();

        LocalDate fechaActual = LocalDate.now();

        //creo al gerente
        final int dniGerente = 9090;
        final String passGerente = "9090";
        //para usar el simulador
        final int dniSimular = 100;
        final String passSimular = "100";

        Cajero cajero = new Cajero("juan", "des", 123, 12444, 345, "trrh");
        AsesorDivisas asesorDivisas = new AsesorDivisas("Martina", "des", 123, 12444, 345, "trrh");
        Gerente gerente = new Gerente("gerente", "des", 123, 12444, 345, "trrh");
        AgenteEspecial agenteE = new AgenteEspecial("marti", "manzano", 12, 0, 14566, "@email", gerente);
        AgenteDeBolsa agenteBolsa = new AgenteDeBolsa("martin");
        AsesorFinanciero asesorF = new AsesorFinanciero("Julieta","Enrico",12300,90000,233,"@");

        empleados.put("cajero", cajero);
        empleados.put("asesorDivisas", asesorDivisas);
        empleados.put("gerente", gerente);
        empleados.put("agenteE", agenteE);
        empleados.put("asesorFinanciero",asesorF);

        inicializarCliente(contraseñas,clientes);

        int contador = 0;
        
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Bienvenido al banco la familia");
            System.out.println("1. Ingresar");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");

            int opcion = verificarOpcion(3);
            
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese su dni");
                    int dni = verificarDNI();
                    System.out.println("Ingrese su contraseña");
                    String pass = sc.nextLine();
                    
                    if (dni == dniSimular && pass.equals(passSimular)){
                        Banco banco = new Banco(clientes.size(),empleados.size());
                        simulacion(banco,clientes, empleados);
                        break;
                    }
                    if (contraseñas.containsKey(dni)) {
                        if (contraseñas.get(dni).equals(pass)) {
                            Cliente cliente = clientes.get(dni);
                            if (cliente != null){
                                System.out.println("Bienvenido");
                                menuCliente(cliente, clientes, empleados, agenteBolsa, fechaActual);
                                if (contador == 2) {
                                    fechaActual = fechaActual.plusMonths(1);
                                    agenteBolsa.actualizarPrecios();
                                    asesorDivisas.actualizarDivisas();
                                    contador = 0;
                                    System.out.println("Se ha actualizado la fecha");
                                } else {
                                    contador++;
                                }
                        } else {
                            System.out.println("Contraseña incorrecta");
                        }
                            }
                            
                    } else if (dni == dniGerente && pass.equals(passGerente)) {
                        //es el gerente, por lo tanto se abre otro menu
                        System.out.println("Bienvenido Gerente");
                        menuGerente(gerente, clientes,empleados);
                    } else {
                        System.out.println("Usuario no registrado");
                    }
                    break;
                case 2:
                    crearCliente(contraseñas, clientes);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opción incorrecta");
                    break;
            }

        }  }

    public static void menuGerente(Gerente gerente, HashMap<Integer, Cliente> clientes,HashMap<String, Empleado> empleados) {
        while (true){

            Banco banco = new Banco(empleados.size(), clientes.size());
            Cajero cajero = (Cajero) empleados.get("cajero");
            Scanner sc = new Scanner(System.in);

            System.out.println("Gerente que desea hacer: ");
            System.out.println("1. Ver listado de dinero no registrado en el banco");
            System.out.println("2. Eliminar un cliente del sistema");
            System.out.println("3. Ver listado de clientes en el sistema");
            System.out.println("4. Realizar copia de seguridad");
            System.out.println("5. Ver cantidad de empleados");
            System.out.println("6. Ver cantidad de clientes");
            System.out.println("7. Mostrar transaccion cliente");
            System.out.println("8. Contratar un empleado");
            System.out.println("9. Cerrar sesion");

            int opcion = verificarOpcion(9);
    
            switch (opcion) {
                case 1:
                    gerente.mostrarDineroNoRegistrado(clientes);
                    break;
                case 2:
                    //busco al cliente en el diccioanrio
                    if (!clientes.isEmpty()){
                        System.out.println("Ingrese el dni del cliente a eliminar: ");
                        int dniClienteEliminar = verificarDNI();
                        if (clientes.containsKey(dniClienteEliminar)){
                            //elimino al cliente
                            Cliente clienteEliminado = clientes.remove(dniClienteEliminar);
                            if (clienteEliminado != null){
                                System.out.println("El cliente "+clienteEliminado.getNombre()+" ha sido eliminado del sistema");
                            } else {
                                System.out.println("No se encontro un cliente con el DNI ingresado");
                            }
                        } else {
                            System.out.println("El dni ingresado no existe en el sistema");
                        }
                    } else {
                        System.out.println("No hay clientes para eliminar");
                    }
                    break;
                case 3:
                    if (!clientes.isEmpty()){
                        for (Map.Entry<Integer,Cliente> entry: clientes.entrySet()){
                            Cliente cliente = entry.getValue();
                            System.out.println("Cliente "+cliente.getNombre()+" con DNI: "+cliente.getDni());
                       }
                    } else {
                        System.out.println("No hay clientes ingresados en el sistema");
                    }
                    break;
                case 4:
                    banco.realizarCopiaDeSeguridad(clientes);
                case 5:
                    System.out.println("La cantidad de empleados que hay en el banco es: "+banco.getCantEmpleados());
                    break;
                case 6:
                    System.out.println("La cantidad de clientes que hay en el banco es: "+banco.getCantClientes());
                    break;
                case 7: 
                    //mostrar transaccion
                    System.out.println("Ingrese el dni del cliente: ");
                    int dniCliente = verificarDNI();
                    if (clientes.get(dniCliente) != null){
                        Cliente cliente = clientes.get(dniCliente);
                        gerente.mostrarTransaccionesCLiente(cliente, cajero);
                    }
                case 8: 
                    //contratar un empleado
                    System.out.println("Que empleado desde contratar?");
                    System.out.println("1. Un cajero");
                    System.out.println("2. Un Asesor Financiero");
                    System.out.println("3. Un Agente Especial");
                    System.out.println("4. Un Asesor de divisas");
                    int opcionEmpleado = verificarOpcion(4);
                    
                    //sc.nextLine();
                    //cualquiera sea el empleado le pido lo mismo 
                    System.out.println("Nombre del empleado: ");
                    String nombreE = sc.nextLine();
                    System.out.println("Apellido del empleado: ");
                    String apellidoE = sc.nextLine();
                    System.out.println("Email del empleado: ");
                    String emailE = sc.nextLine();
                    System.out.println("El empleado fue contratado!");
                    break;
                case 9:
                    return;
                default:
                    break;
            }
        }
        
    }

    public static void menuCliente(Cliente cliente, HashMap<Integer, Cliente> clientes, HashMap<String, Empleado> empleados, AgenteDeBolsa agenteBolsa, LocalDate fechaActual) {
        Cajero cajero = (Cajero) empleados.get("cajero");
        AsesorDivisas asesorDivisas = (AsesorDivisas) empleados.get("asesorDivisas");
        Gerente gerente = (Gerente) empleados.get("gerente");
        AgenteEspecial agenteE = (AgenteEspecial) empleados.get("agenteE");
        AsesorFinanciero asesorF = (AsesorFinanciero) empleados.get("asesorFinanciero");
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
            System.out.println("8. Solicitar un consejo");
            System.out.println("9. Cerrar sesión");

            int opcion = verificarOpcion(9);
            
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el monto a transferir");
                    double monto = verificarMonto(); 
                    boolean realizar = gerente.verificarTransaccion(cliente, monto);
                    if (realizar){
                        System.out.println("Ingrese el dni del cliente destino");
                        int dniDestino = verificarDNI();
                        cliente.solicitarTransferencia(monto, clientes.get(dniDestino), cajero);
                    } else {
                        System.out.println("No posee suficiente dinero");
                    }
                    break;

                case 2:
                    System.out.println("Ingrese el monto a retirar");
                    double montoRetirar = verificarMonto();
                    boolean realizarRetiro = gerente.verificarTransaccion(cliente, montoRetirar);
                    if (realizarRetiro){
                        cliente.solicitarRetiro(montoRetirar, cajero);
                    } else {
                        System.out.println("No puede retirar ese monto");
                    }
                    break;

                case 3:
                    System.out.println("Cliente " + cliente.getNombre() + " justifique de donde viene el dinero");
                    String respCliente = sc.nextLine();
                    System.out.println("Ingrese el monto a depositar");
                    double montoDepositar = verificarMonto();
                    if (respCliente.equals("")) { //si no conesta nada es plata sucia, lo maneja el agente especial
                        cliente.solicitarDeposito(montoDepositar, agenteE);
                    } else {
                        cliente.solicitarDeposito(montoDepositar, cajero);
                    }
                    break;

                case 4:
                    System.out.println("Ingrese el monto que le gustaría recibir");
                    double montoSolicitar = verificarMonto();
                    cliente.solicitarPrestamo(montoSolicitar, gerente, cajero);
                    break;

                case 5:
                    System.out.println("1. Consultar precios de divisas");
                    System.out.println("2. Comprar divisas");
                    System.out.println("3. Vender divisas");
                    System.out.println("4. Consultar divisas compradas");

                    int opcionDivisas = verificarOpcion(4);

                    switch (opcionDivisas) {
                        case 1:
                            cliente.solicitarPrecioDivisas(asesorDivisas);
                            break;

                        case 2:
                            System.out.println("Ingrese el monto a comprar");
                            double montoComprar = verificarMonto();
                            cliente.comprarDivisas(montoComprar, asesorDivisas);
                            break;

                        case 3:
                            System.out.println("Ingrese el monto a vender");
                            double montoVender = verificarMonto();
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

                    int opcionBolsa = verificarOpcion(5);
                    switch (opcionBolsa) {
                        case 1:
                            System.out.println("1. Comprar acciones");
                            System.out.println("2. Vender acciones");
                            int opcionAcciones = verificarOpcion(2);
                            switch (opcionAcciones) {
                                case 1:
                                    System.out.println("Cuanto dinero desea invertir?");
                                    double montoAcciones = verificarMonto();
                                    gerente.actualizoDineroNoRegCliente(cliente, montoAcciones); //le lavo el dinero al cliente
                                    cliente.comprarActivo(montoAcciones, 2);
                                    System.out.println("Se realizo con exito la inversion");
                                    break;
                                case 2:
                                    cliente.venderActivo(1);
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case 2:
                            System.out.println("1. Comprar criptomonedas");
                            System.out.println("2. Vender criptomonedas");
                            int opcionCripto = verificarOpcion(2);
                            switch (opcionCripto) {
                                case 1:
                                    System.out.println("Cuanto dinero desea invertir?");
                                    double montoCripto = verificarMonto();
                                    gerente.actualizoDineroNoRegCliente(cliente, montoCripto); //le lavo el dinero al cliente
                                    cliente.comprarActivo(montoCripto, 3);
                                    System.out.println("Se realizo con exito la inversion");
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
                            int opcionPlazoFijo = verificarOpcion(2);
                            switch (opcionPlazoFijo) {
                                case 1:
                                    System.out.println("Cuanto dinero desea invertir?");
                                    double montoPlazoFijo = verificarMonto();
                                    gerente.actualizoDineroNoRegCliente(cliente, montoPlazoFijo); //le lavo el dinero al cliente
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
                    break;
                case 8:
                    System.out.println(asesorF.consejoFinanciero(cliente)); 
                    break;
                case 9:
                    return;
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
        int dni = verificarDNI();
        System.out.println("Ingrese su contraseña");
        String pass = sc.nextLine();
        
        Cliente cliente = new Cliente(dni, nombre, apellido, 0, null);

        contraseñas.put(dni, pass);
        clientes.put(dni, cliente);
        return;
    }

    public static int verificarOpcion(int valor){
        Scanner sc = new Scanner(System.in);
        int opcion = -1;
        while (true){
            try {
                System.out.println("Ingrese una opción: ");
                opcion = sc.nextInt();
                if (opcion < 1 || opcion > valor) {
                    System.out.println("Opción no válida");
                }  else {
                    return opcion;
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingrese un numero entero.");
                sc.next();
            }
        }
    }

    public static int verificarDNI(){
        Scanner sc = new Scanner(System.in);
        int dni = 0;

        while (true) {
            try {
                dni = sc.nextInt(); 
                return dni;
            } catch (InputMismatchException e) {
                System.out.println("DNI inválido");
                sc.next(); 
            }
        }
    }

    public static double verificarMonto(){
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                double monto = sc.nextDouble();
                if (monto <= 0) {
                    System.out.println("El monto debe ser positivo");
                } else {
                    return monto; 
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingrese un número válido.");
                sc.next(); 
            }
        }
    }
    //si ingresa el codigo secreto se realiza la simulacion
    public static void simulacion(Banco banco,HashMap<Integer, Cliente> clientes,HashMap<String, Empleado> empleados){
        Cajero cajero = (Cajero) empleados.get("cajero");
        AsesorDivisas asesorDivisas = (AsesorDivisas) empleados.get("asesorDivisas");
        Gerente gerente = (Gerente) empleados.get("gerente");
        AgenteEspecial agenteE = (AgenteEspecial) empleados.get("agenteE");
        AsesorFinanciero asesorF = (AsesorFinanciero) empleados.get("asesorFinanciero");
        
        Random random = new Random();

        String[] operaciones = {"tranferencia", "retiro", "deposito","divisas","solicitarPrestamo","realizarInversion","consejoFinanciero"};

        //50 movimientos
        for (int i = 0; i < 50; i++){
            System.out.println("");
            Cliente cliente = clienteAleatorio(clientes);
            String operacion = operaciones[random.nextInt(operaciones.length)];
            double monto = random.nextInt(10,100);

            switch (operacion){
                case "transferencia":
                    boolean realizar = gerente.verificarTransaccion(cliente, monto);
                    if (realizar){
                        Cliente clienteDestino = clienteAleatorio(clientes);
                        System.out.println("Se realiza una transferencia de: $"+monto+ " a "+clienteDestino.getNombre());
                        cliente.solicitarTransferencia(monto, clientes.get(clienteDestino.getDni()), cajero);
                        System.out.println("El saldo actual de: "+cliente.getNombre()+" es: $"+cliente.getSaldo());
                    } else {
                        System.out.println("El cliente "+cliente.getNombre()+" no posee suficiente dinero pra realizar la transferencia de: "+monto);
                    }
                    break;
                case "retiro":
                    boolean realizarRetiro = gerente.verificarTransaccion(cliente, monto);
                    if (realizarRetiro){
                        System.out.println("El cliente "+cliente.getNombre()+" realiza un retiro de: $"+monto);
                        cliente.solicitarRetiro(monto, cajero);
                        System.out.println("El saldo actual de: "+cliente.getNombre()+" es: $"+cliente.getSaldo());
                    } else {
                        System.out.println("No puede retirar ese monto");
                    }
                    break;
                case "deposito":
                    String respuestas[] = {"","respode"}; //genero las dos respuestas posibles, 
                    //para que se realize el dinero no rastreable
                    String respCliente = respuestas[random.nextInt(respuestas.length)];
                    System.out.println("El cliente: "+cliente.getNombre()+" realiza un deposito de: "+monto);
                    if (respCliente.equals("")) { //si no conesta nada es plata sucia, lo maneja el agente especial
                        cliente.solicitarDeposito(monto, agenteE);
                        System.out.println("El saldo actual de: "+cliente.getNombre()+" es: $"+cliente.getSaldo());
                    } else {
                        cliente.solicitarDeposito(monto, cajero);
                        System.out.println("El saldo actual de: "+cliente.getNombre()+" es: $"+cliente.getSaldo());
                    }
                    break;
                case "divisas":
                    System.out.println("Seccion divisas");
                    int opcion = random.nextInt(1,3);
                    switch (opcion){
                        case 1:
                            System.out.println("El cliente "+cliente.getNombre()+" consulto el precio de las divisas");
                            cliente.solicitarPrecioDivisas(asesorDivisas);
                            break;
                        case 2: 
                            System.out.println("El cliente "+cliente.getNombre()+ " quiere comprar: $"+monto+" en divisas");
                            cliente.comprarDivisasRandom(monto, asesorDivisas);
                            break;
                        case 3:
                            //divisas compradas cliente
                            cliente.mostrarDivisasCompradas();
                            break;
                    }
                case "solicitarPrestamo":
                    System.out.println("Se realizara un prestamo al cliente "+cliente.getNombre()+" de: $"+monto);
                    cliente.solicitarPrestamo(monto, gerente, cajero);
                    break;
                case "realizarInversion":
                    int inversion = random.nextInt(1,5);
                    switch (inversion){
                        case 1: 
                            //acciones
                            int accion = random.nextInt(1,2);
                            switch (accion) {
                                case 1:
                                int opcionAcciones = random.nextInt(1,2);
                                switch (opcionAcciones) {
                                    case 1:
                                        System.out.println("El cliente "+cliente.getNombre()+" va a invertir: $"+monto);
                                        gerente.actualizoDineroNoRegCliente(cliente, monto); //le lavo el dinero al cliente
                                        cliente.comprarActivoRandom(monto, 2);
                                        System.out.println("Se realizo con exito la inversion");
                                        break;
                                    case 2:
                                        //vendo la accion si hay
                                        cliente.venderActivo(1);
                                        break;
                                    default:
                                        break;
                                }
                            }
                            break;
                        case 2:
                            int opcionCripto = random.nextInt(1,2);
                            switch (opcionCripto) {
                                case 1:
                                    //comprar cripto
                                    gerente.actualizoDineroNoRegCliente(cliente, monto); //le lavo el dinero al cliente
                                    cliente.comprarActivoRandom(monto, 3);
                                    System.out.println("Se realizo con exito la inversion");
                                    break;
                                case 2:
                                    cliente.venderActivo(2);
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case 3:
                            int opcionPlazoFijo = random.nextInt(1,2);
                            switch (opcionPlazoFijo) {
                                case 1:
                                //realizar plazo fijo
                                    gerente.actualizoDineroNoRegCliente(cliente, monto); //le lavo el dinero al cliente
                                    cliente.comprarActivoRandom(monto, 1);
                                    break;
                                case 2:
                                    cliente.consultarGananciasPlazoFijo();
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case 4:
                            System.out.println("El cliente "+cliente.getNombre()+" consulto el precio de los activos");
                            cliente.consultarPreciosRandom();
                            break;
                        case 5:
                            //consulto activos cliente
                            System.out.println("Los activos del cliente: "+cliente.getNombre()+" son:");
                            cliente.consultarActivos();
                            break;
                        default:
                            break;
                    }
                case "consejoFinanciero":    
                    String consejo = asesorF.consejoFinanciero(cliente);
                    System.out.println(consejo);
                    break;
                default:
                    break;    
            }   

        }
    }

    public static Cliente clienteAleatorio(HashMap<Integer,Cliente> clientes){
        List<Cliente> listaClientes = new ArrayList<>(clientes.values());
        Random random = new Random();
        return listaClientes.get(random.nextInt(clientes.size()));
    }

}
