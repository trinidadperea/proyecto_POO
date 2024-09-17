package Banco;

import java.util.*;

import Banco.Cliente.Cliente;
import Banco.Cliente.Inversiones.Inversion;
import Banco.Cliente.Inversiones.InversionBolsa;
import Banco.Cliente.Inversiones.InversionCriptoMonedas;
import Banco.Cliente.Inversiones.PlazoFijo;
import java.time.LocalDate;

public class AgenteDeBolsa {

    private Map<Cliente, List<Inversion>> inversionesPorCliente;
    private String nombre;
    private Scanner sc;

    private HashMap<String, Double> companies;
    private Map<String, Double> criptos;

    public AgenteDeBolsa(String nombre) {
        this.nombre = nombre;
        this.inversionesPorCliente = new HashMap<>();
        this.sc = new Scanner(System.in);  // Iniciando el Scanner una vez, reutilizable en toda la clase
        companies = new HashMap<>(); //diccionario de empresas
        companies.put("Apple", 150.25);
        companies.put("Microsoft", 305.22);
        companies.put("Amazon", 130.55);
        companies.put("Google", 2800.78);
        companies.put("Facebook (Meta)", 220.45);
        companies.put("Tesla", 690.40);
        companies.put("Visa", 210.55);
        companies.put("Samsung", 80.30);
        criptos = new HashMap<>(); //diccionario de criptomonedas
        criptos.put("Bitcoin", 26500.00);
        criptos.put("Ethereum", 1800.00);
        criptos.put("Ripple", 0.55);
        criptos.put("Litecoin", 90.00);
    }

    /**
     * Metodo que realiza una inversion al cliente
     * @param tipoInversion 1. Plazo fijo, 2. Acciones, 3.Criptomonedas
     * @param monto monto a realizar
     * @param cliente cliente que realiza la inversion
     */
    public void realizarInversion(int tipoInversion, double monto, Cliente cliente) {
        switch (tipoInversion) {
            case 1:
                if(inversionesPorCliente.containsKey(cliente) == false){
                    inversionesPorCliente.put(cliente, new ArrayList<>());
                }
                for (int i = 0; i < inversionesPorCliente.get(cliente).size(); i++) {
                    if (inversionesPorCliente.get(cliente).get(i).getTipoInversion() == "Plazo Fijo") {
                        System.out.println("El cliente ya tiene un plazo fijo activo, no puede tener mas de uno");
                        return;
                    }
                }
                // Plazo Fijo
                System.out.println("Ingrese la cantidad de meses para el plazo fijo: ");
                int meses = 0;
                while (true) {
                    try {
                        meses = sc.nextInt();
                        if (meses <= 0) {
                            System.out.println("La cantidad de meses debe ser mayor que cero.");
                        } else {
                            break; 
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Ingrese un numero entero.");
                        sc.next(); 
                    }
                }
                float interes = meses * 1.15f;

                LocalDate fechaVencimiento = LocalDate.now().plusMonths(meses);

                PlazoFijo plazoFijo = new PlazoFijo(cliente, monto, interes, "activo", fechaVencimiento, meses);

                // Agregar la inversión al cliente
                agregarInversion(cliente, plazoFijo);

                break;
            case 2: // Acciones
                // Mostrar las opciones al usuario
                System.out.println("Seleccione la empresa en la que desea invertir: ");
                int index = 0;
                String[] companyArray = companies.keySet().toArray(new String[0]);
                for (String company : companyArray) {
                    System.out.println(index + 1 + ". " + company + " - $" + companies.get(company));
                    index++;
                }
                
                //verifico la opcion ingresada
                int opcion = verificarOpcion(9);
                opcion -= 1;

                // Validar la opción seleccionada
                if (opcion >= 0 && opcion < companyArray.length) {
                    String selectedCompany = companyArray[opcion];
                    double stockValue = companies.get(selectedCompany);
                    float cantidadAcciones = (float) (monto / stockValue);

                    InversionBolsa inversion = new InversionBolsa(
                            selectedCompany,
                            cantidadAcciones,
                            stockValue,
                            stockValue,
                            monto,
                            cliente,
                            selectedCompany
                    );


                    // Agregar la inversión al cliente
                    agregarInversion(cliente, inversion);

                } else {
                    System.out.println("Opción no válida.");
                }
                break;

            case 3: // Criptomonedas                
                // Mostrar las opciones al usuario
                System.out.println("Seleccione la criptomoneda en la que desea invertir: ");
                int index2 = 0;
                String[] criptosArray = criptos.keySet().toArray(new String[0]);
                for (String cripto : criptosArray) {
                    System.out.println(index2 + 1 + ". " + cripto + " - $" + criptos.get(cripto));
                    index2++;
                }

                int opcion2 = verificarOpcion(4);
                opcion2 -= 1;

                // Validar la opción seleccionada
                if (opcion2 >= 0 && opcion2 < criptosArray.length) {
                    String selectedCripto = criptosArray[opcion2];
                    double stockValueCripto = criptos.get(selectedCripto);
                    float cantidadCripto = (float) (monto / stockValueCripto);

                    InversionCriptoMonedas inversionCripto = new InversionCriptoMonedas(
                            selectedCripto,
                            cantidadCripto,
                            0.0,
                            stockValueCripto,
                            stockValueCripto,
                            monto,
                            cliente
                    );

                    // Agregar la inversión al cliente
                    agregarInversion(cliente, inversionCripto);
                } else {
                    System.out.println("Opción no válida.");
                }
                break;
            default:
                System.out.println("Tipo de inversión no válido.");
        }
    }

    /**
     * Metodo que es utiliado en la simulacion para realizar una inversion random
     * @param tipoInversion 1. Plazo fijo, 2. Acciones, 3.Criptomonedas
     * @param monto monto a realizar, que es generado de forma random
     * @param cliente cliente que realiza la inversion, que es generado de forma random
     */
    public void realizarInversionRandom(int tipoInversion, double monto, Cliente cliente) {
        Random random = new Random();
        switch (tipoInversion) {
            case 1:
                if(inversionesPorCliente.containsKey(cliente) == false){
                    inversionesPorCliente.put(cliente, new ArrayList<>());
                }
                for (int i = 0; i < inversionesPorCliente.get(cliente).size(); i++) {
                    if (inversionesPorCliente.get(cliente).get(i).getTipoInversion() == "Plazo Fijo") {
                        System.out.println("El cliente ya tiene un plazo fijo activo, no puede tener mas de uno");
                        return;
                    }
                }
                // Plazo Fijo
                int meses = random.nextInt(1,12);
                System.out.println("La cantidad de meses en la que se realizara el plazo ijo es: "+meses);
                
                float interes = meses * 1.15f;

                LocalDate fechaVencimiento = LocalDate.now().plusMonths(meses);

                PlazoFijo plazoFijo = new PlazoFijo(cliente, monto, interes, "activo", fechaVencimiento, meses);

                // Agregar la inversión al cliente
                agregarInversion(cliente, plazoFijo);

                break;
            case 2: // Acciones
                // Mostrar las opciones al usuario
                int index = 0;
                String[] companyArray = companies.keySet().toArray(new String[0]);
                for (String company : companyArray) {
                    System.out.println(index + 1 + ". " + company + " - $" + companies.get(company));
                    index++;
                }
                
                //opcion se genera de forma random
                int opcion = random.nextInt(1,8);
                System.out.println("La empresa en la que va a invertir es: "+companyArray[opcion]);
                opcion -= 1;

                // Validar la opción seleccionada
                if (opcion >= 0 && opcion < companyArray.length) {
                    String selectedCompany = companyArray[opcion];
                    double stockValue = companies.get(selectedCompany);
                    float cantidadAcciones = (float) (monto / stockValue);

                    InversionBolsa inversion = new InversionBolsa(
                            selectedCompany,
                            cantidadAcciones,
                            stockValue,
                            stockValue,
                            monto,
                            cliente,
                            selectedCompany
                    );


                    // Agregar la inversión al cliente
                    agregarInversion(cliente, inversion);

                } else {
                    System.out.println("Opción no válida.");
                }
                break;

            case 3: // Criptomonedas                
                // Mostrar las opciones al usuario
                int index2 = 0;
                String[] criptosArray = criptos.keySet().toArray(new String[0]);
                for (String cripto : criptosArray) {
                    System.out.println(index2 + 1 + ". " + cripto + " - $" + criptos.get(cripto));
                    index2++;
                }

                int opcion2 = random.nextInt(1,4);
                System.out.println("La criptomoneda que va a comprar es: "+criptosArray[opcion2]);
                opcion2 -= 1;

                // Validar la opción seleccionada
                if (opcion2 >= 0 && opcion2 < criptosArray.length) {
                    String selectedCripto = criptosArray[opcion2];
                    double stockValueCripto = criptos.get(selectedCripto);
                    float cantidadCripto = (float) (monto / stockValueCripto);

                    InversionCriptoMonedas inversionCripto = new InversionCriptoMonedas(
                            selectedCripto,
                            cantidadCripto,
                            0.0,
                            stockValueCripto,
                            stockValueCripto,
                            monto,
                            cliente
                    );

                    // Agregar la inversión al cliente
                    agregarInversion(cliente, inversionCripto);
                } else {
                    System.out.println("Opción no válida.");
                }
                break;
            default:
                System.out.println("Tipo de inversión no válido.");
        }
    }

    /**
     * Metodo que si tiene algun activo, te permite venderlo
     * @param tipoInversion 1. Plazo fijo, 2. Acciones, 3.Criptomonedas
     * @param cliente cliente a vender su activo
     * @return 
     */
    public double venderActivo(int tipoInversion, Cliente cliente) {
        // Buscar las inversiones del cliente desde el HashMap
        List<Inversion> inversionesCliente = inversionesPorCliente.get(cliente);
    
        // Verificar si el cliente tiene inversiones
        if (inversionesCliente == null || inversionesCliente.isEmpty()) {
            System.out.println("El cliente no tiene inversiones.");
            return 0;
        }
    
        switch (tipoInversion) {
            case 1: // Acciones
                // Filtrar las inversiones que son de tipo 'InversionBolsa'
                List<InversionBolsa> acciones = new ArrayList<>();
                for (Inversion inv : inversionesCliente) {
                    if (inv instanceof InversionBolsa) {
                        acciones.add((InversionBolsa) inv);
                    }
                }
    
                if (acciones.isEmpty()) {
                    System.out.println("El cliente no tiene acciones para vender.");
                    break;
                }
    
                // Mostrar las acciones que el cliente puede vender
                System.out.println("Seleccione la acción que desea vender: (elija el respectivo numero de la acción)");
                int index = 0;
                for (InversionBolsa accion : acciones) {
                    System.out.println(index + 1 + ". " + accion.getNombre() + " - Cantidad: " + accion.getCantidadAcciones());
                    index++;
                }

                // Seleccionar la acción
                int opcion = verificarOpcion(acciones.size());
                opcion -= 1;
                if (opcion >= 0 && opcion < acciones.size()) {
                    InversionBolsa accionSeleccionada = acciones.get(opcion);
    
                    // Obtener el valor actual de la acción
                    double valorActual = companies.get(accionSeleccionada.getNombre());
    
                    // Calcular el monto de la venta
                    double montoVenta = accionSeleccionada.getCantidadAcciones() * valorActual;
    
                    // Agregar el monto al saldo del cliente
                    cliente.setSaldo(cliente.getSaldo() + montoVenta);
    
                    // Remover la inversión de las inversiones del cliente
                    inversionesCliente.remove(accionSeleccionada);
    
                    System.out.println("Se han vendido las acciones de " + accionSeleccionada.getNombre() +
                                       " por un total de: $" + montoVenta);
                    return montoVenta;
                } else {
                    System.out.println("Opción no válida.");
                }
                break;
    
            case 2: // Criptomonedas
                // Filtrar las inversiones que son de tipo 'InversionCriptoMonedas'
                List<InversionCriptoMonedas> criptos = new ArrayList<>();
                for (Inversion inv : inversionesCliente) {
                    if (inv instanceof InversionCriptoMonedas) {
                        criptos.add((InversionCriptoMonedas) inv);
                    }
                }
    
                if (criptos.isEmpty()) {
                    System.out.println("El cliente no tiene criptomonedas para vender.");
                    break;
                }
    
                // Mostrar las criptomonedas que el cliente puede vender
                System.out.println("Seleccione la criptomoneda que desea vender:");
                int index2 = 0;
                

                for (int i = 1; i <= criptos.size(); i++) {
                    InversionCriptoMonedas cripto = criptos.get(i);
                    System.out.println(i + ". " + cripto.getNombre() + " - Cantidad: " + cripto.getCantidad());
                }
                
                
                // Seleccionar la criptomoneda
                int opcion2 = verificarOpcion(4);
                opcion2 -= 1;
                if (opcion2 >= 0 && opcion2 < criptos.size()) {
                    InversionCriptoMonedas criptoSeleccionada = criptos.get(opcion2);
    
                    // Obtener el valor actual de la criptomoneda
                    //double valorActualCripto = obtenerValorActualDeCripto(criptoSeleccionada.getNombre());
                    
                    double valorActualCripto = criptos.get(opcion2).getPrecioActual();

                    // Calcular el monto de la venta
                    double montoVentaCripto = criptoSeleccionada.getCantidad() * valorActualCripto;
    
                    // Agregar el monto al saldo del cliente
                    cliente.setSaldo(cliente.getSaldo() + montoVentaCripto);
    
                    // Remover la inversión de las inversiones del cliente
                    inversionesCliente.remove(criptoSeleccionada);
    
                    System.out.println("Se han vendido las criptomonedas de " + criptoSeleccionada.getNombre() +
                                       " por un total de: $" + montoVentaCripto);
                    return montoVentaCripto;
                } else {
                    System.out.println("Opción no válida.");
                }
                break;
    
            default:
                System.out.println("Tipo de inversión no válido.");
        }
        return 0.0;
    }
    
    /**
     * Metodo que muestra los precios de las empresas a realizar una inversion, o de las criptomonedas
     * @return precios
     */
    public String consultaPrecios() {
        String toReturn = "";
        System.out.println("¿Desea ver los precios de:\n1. Empresas\n2. Criptomonedas");
        
        int opcion = sc.nextInt();
        

        switch (opcion) {
            case 1:
                toReturn += "Empresas: \n";
                // Usar Iterator para empresas
                Iterator<Map.Entry<String, Double>> companyIterator = companies.entrySet().iterator();
                while (companyIterator.hasNext()) {
                    Map.Entry<String, Double> entry = companyIterator.next();
                    toReturn += entry.getKey() + " - $" + entry.getValue() + "\n";
                }
                break;
            case 2:
                toReturn += "Criptomonedas: \n";
                // Usar Iterator para criptomonedas
                Iterator<Map.Entry<String, Double>> criptoIterator = criptos.entrySet().iterator();
                while (criptoIterator.hasNext()) {
                    Map.Entry<String, Double> entry = criptoIterator.next();
                    toReturn += entry.getKey() + " - $" + entry.getValue() + "\n";
                }
                break;
            default:
                toReturn += "Opción no válida.";
                break;
        }
        return toReturn;
    }

    /**
     * Metodo utilizado en la simulacion para consultar los precios de las inversiones de forma random
     * @return precios generaods de forma random
     */
    public String consultaPreciosRandom() {
        String toReturn = "";
        Random random = new Random();
        
        int opcion = random.nextInt(1,2);
        

        switch (opcion) {
            case 1:
                System.out.println("Precio empresas");
                toReturn += "Empresas: \n";
                // Usar Iterator para empresas
                Iterator<Map.Entry<String, Double>> companyIterator = companies.entrySet().iterator();
                while (companyIterator.hasNext()) {
                    Map.Entry<String, Double> entry = companyIterator.next();
                    toReturn += entry.getKey() + " - $" + entry.getValue() + "\n";
                }
                break;
            case 2:
                System.out.println("Precio criptomonedas");
                toReturn += "Criptomonedas: \n";
                // Usar Iterator para criptomonedas
                Iterator<Map.Entry<String, Double>> criptoIterator = criptos.entrySet().iterator();
                while (criptoIterator.hasNext()) {
                    Map.Entry<String, Double> entry = criptoIterator.next();
                    toReturn += entry.getKey() + " - $" + entry.getValue() + "\n";
                }
                break;
            default:
                toReturn += "Opción no válida.";
                break;
        }
        return toReturn;
    }

    /**
     * Metodo que muestra el rendimiento del plazo fijo de un cliente
     * @param cliente cliente que posee el plazo fijo
     * @return rendimiento
     */
    public double consultarRendimientosPlazoFijo(Cliente cliente){
        for (int i = 0; i < inversionesPorCliente.get(cliente).size(); i++) {
            if (inversionesPorCliente.get(cliente).get(i).getTipoInversion() == "Plazo Fijo") {
                PlazoFijo plazoFijo = (PlazoFijo) inversionesPorCliente.get(cliente).get(i);
                return plazoFijo.calcularRendimientos();
            }
        }
        System.out.println("El cliente no tiene plazos fijos activos.");
        return 0;
    }

    /**
     * Metodo que actualiza los valores de las inversiones
     */
    public void actualizarPrecios() {
        for (Map.Entry<String, Double> entryCo : companies.entrySet()) {
            double randomValue = 0.95 + (Math.random() * (1.05 - 0.95));
    
            double nuevoValor = entryCo.getValue() * randomValue;
            nuevoValor = (long) (nuevoValor * 100) / 100.0;

            entryCo.setValue(nuevoValor);
        }
        for (Map.Entry<String, Double> entryCr : criptos.entrySet()) {
            double randomValue = 0.95 + (Math.random() * (1.05 - 0.95));
    
            double nuevoValor = entryCr.getValue() * randomValue;
            nuevoValor = (long) (nuevoValor * 100) / 100.0;

            entryCr.setValue(nuevoValor);
        }
    }
    
    /**
     * Metodo que muestra las inverisones que ha realizado un cliente
     * @param cliente cliente que solicita ver sus inversiones
     * @return inversiones del cliente
     */
    public String mostrarInversiones(Cliente cliente) {
        List<Inversion> inversiones = inversionesPorCliente.get(cliente);
        if (inversiones == null || inversiones.isEmpty()) {
            return "El cliente no tiene inversiones.";
        }
        String toReturn = new String();
        toReturn = "";
        for (Inversion inversion : inversiones) {
            toReturn += "Nombre: " + inversion.getNombre() + "\n" +
                              "Tipo: " + inversion.getTipoInversion() + "\n" +
                              "Monto: " + inversion.getMonto() + "\n" + "\n";
                            }
            return toReturn;
    }

    /**
     * Metodo que cuando un cliente realiza una inversion, se la agrega al diccionario de inversiones
     * @param cliente cliente que realiza una inversion
     * @param inversion el tipo de inversion que realiza
     */
    private void agregarInversion(Cliente cliente, Inversion inversion) {
        // Agrega la inversión al cliente en el diccionario `inversionesPorCliente`
        if (!inversionesPorCliente.containsKey(cliente)) {
            inversionesPorCliente.put(cliente, new ArrayList<>());
        }
        inversionesPorCliente.get(cliente).add(inversion);
    }

    public Map<Cliente, List<Inversion>> getInversionesPorCliente() {
        return inversionesPorCliente;
    }

    public void setInversionesPorCliente(Map<Cliente, List<Inversion>> inversionesPorCliente) {
        this.inversionesPorCliente = inversionesPorCliente;
    }

    /**
     * Metodo que verifica que la entrada solicitada sea la esperada, para evitar errores
     * @param valor valor maximo que puede tomar la variable
     * @return opcion
     */
    public int verificarOpcion(int valor){
        Scanner sc = new Scanner(System.in);
        int opcion = -1;
        while (true){
            try {
                opcion = sc.nextInt();
                if (opcion < 1 || opcion > valor) {
                    System.out.println("Opción no válida");
                }  else {
                    return opcion;
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingrese un número entero.");
                sc.next();
            }
        }
    }
}

    
