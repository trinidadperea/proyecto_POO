import Banco.AgenteDeBolsa;
import Banco.Cliente.Cliente;
import Banco.Empleado.*;
import Banco.Banco;
 


import java.util.*;


public class Main {
    /* 
    public static void main(String[] args){
        //falta ver el tema de donde creamos y llamamos a cada empleado
        AgenteDeBolsa agente = new AgenteDeBolsa("Raul");
        Cliente cliente = new Cliente(123, "Juan", "Loncharich",10000,agente);
        Cajero cajero = new Cajero("trini","des",123,12444,345,"trrh");
        Gerente gerente = new Gerente("gerente","des",123,12444,345,"trrh");
        AgenteEspecial agenteE = new AgenteEspecial("marti","manzano", 12, 0, 14566,"@email",gerente);
        cliente.solicitarDeposito(10,agenteE);
        cliente.solicitarDeposito(1000,agenteE);
        //gerente.mostrarDineroNoRegistrado();
        //Cliente cliente2 = new Cliente(123, "jose", "Loncharich",10000,agente);
        //cliente2.solicitarDeposito(30,agenteE);
        gerente.mostrarDineroNoRegistrado();
    } 
} */

    public static void main(String[] args){

        HashMap<Integer,String> contraseñas = new HashMap<Integer,String>();
        HashMap<Integer,Cliente> clientes = new HashMap<Integer,Cliente>();

        boolean keep = true;
        //creo al gerente
        final int dniGerente = 9090;
        final String passGerente = "9090";
        Gerente gerente = new Gerente("Martin", "Gonzalez", 0, 0,31234,"martingonzales@gmail.com");
        AgenteEspecial agenteE = new AgenteEspecial("Martina","Manzano", 12, 0, 14566,"@email",gerente);

        //inicializo algunos clientes base
        inicializarCliente(contraseñas,clientes);

        while (keep == true) {
            System.out.println("Bienvenido al banco la familia");
            System.out.println("1. Ingresar");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");
            System.out.println("");
            Scanner sc = new Scanner(System.in);
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("");
                    System.out.print("Ingrese su dni: ");
                    int dni = sc.nextInt();
                    System.out.print("Ingrese su contraseña: ");
                    String pass = sc.nextLine();
                    pass = sc.nextLine();
                    System.out.println("");
                    if (contraseñas.containsKey(dni)) {
                        if (contraseñas.get(dni).equals(pass)) {
                            Cliente cliente = clientes.get(dni); 
                            System.out.println("Bienvenido/a "+cliente.getNombre()+" "+cliente.getApellido());
                            System.out.println("");
                            menuCliente(cliente, clientes,agenteE);
                        } else {
                            System.out.println("Contraseña incorrecta");
                            System.out.println("");
                        }
                    } else if(dni == dniGerente && pass.equals(passGerente)){
                            //es el gerente, por lo tanto se abre otro menu
                            System.out.println("Bienvenido Gerente "+gerente.getNombre());
                            menuGerente(gerente);
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
                    break;
            }
        }
    } 
    //clientes base
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

    public static void menuGerente(Gerente gerente){
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("Que desea hacer: ");
            System.out.println("1. Ver el dinero no registrado de los clientes");
            System.out.println("2. Contratar a un cajero");
            System.out.println("3. Contratar a un asesor financiero");
            System.out.println("4. Salir");
            int opcion = sc.nextInt();
            if (opcion == 4){
                break;
            }
            switch (opcion) {
                case 1:
                    System.out.println(" ");
                    gerente.mostrarDineroNoRegistrado();
                    System.out.println(" ");
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    break;
            }
        }
    }
    public static void menuCliente(Cliente cliente, HashMap<Integer,Cliente> clientes, AgenteEspecial agenteE){
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("1. Realizar transferencia");
            System.out.println("2. Realizar retiro");
            System.out.println("3. Realizar deposito");
            System.out.println("4. Solicitar prestamo");
            System.out.println("5. Consultar sobre divisas");
            System.out.println("6. Consultar sobre acciones, criptomonedas y plazos fijos");
            System.out.println("7. Consultar Saldo");
            System.out.println("8. Cerrar sesión");
            System.out.println(" ");
            int opcion = sc.nextInt();
            if(opcion == 8){
                break;
            }
            //instancio el cajero para probar, pero no quedará asi
            Cajero cajero = new Cajero("juan","des",123,12444,345,"trrh");
            //instancio el asesor de divisas para probar, pero no quedará asi
            AsesorDivisas asesorDivisas = new AsesorDivisas("Martina", "Gonzalez", 1440, 120000, 132445566, "mail");
            //AgenteEspecial agenteE = new AgenteEspecial("marti","manzano", 12, 0, 14566,"@email",gerente);
            switch (opcion) {
                case 1:
                    System.out.println("");
                    System.out.println("Ingrese el monto a transferir");
                    double monto = sc.nextDouble();
                    System.out.println("Ingrese el dni del cliente destino");
                    int dniDestino = sc.nextInt();
                    cliente.solicitarTransferencia(monto, clientes.get(dniDestino), cajero);
                    System.out.println("");
                    break;
                    
                case 2:
                    System.out.println("");
                    System.out.println("Ingrese el monto a retirar");
                    double montoRetirar = sc.nextDouble();
                    cliente.solicitarRetiro(montoRetirar, cajero);
                    System.out.println("");
                    break;
                case 3:
                    System.out.println("");
                    System.out.println("Cliente "+cliente.getNombre()+" justifique de donde viene el dinero");
                    sc.nextLine();
                    String respCliente = sc.nextLine();
                    System.out.println("Ingrese el monto a depositar");
                    double montoDepositar = sc.nextDouble();
                    if (respCliente.equals("")){ //si no conesta nada es plata sucia, lo maneja el agente especial
                        cliente.solicitarDeposito(montoDepositar, agenteE);
                    } else {
                        cliente.solicitarDeposito(montoDepositar, cajero);
                    }
                    System.out.println("");
                    break;
                case 4:
                    System.out.println("");
                    System.out.println("Ingrese el monto a solicitar");
                    double montoSolicitar = sc.nextDouble();
                    cliente.solicitarPrestamo(cliente, montoSolicitar, cajero);
                    System.out.println("");
                    break;
                case 5:
                    System.out.println(""); 
                    System.out.println("1. Consultar precios de divisas");
                    System.out.println("2. Comprar divisas");
                    System.out.println("3. Vender divisas");
                    int opcionDivisas = sc.nextInt();
                    System.out.println("");
                    switch (opcionDivisas) {
                        case 1:
                            System.out.println("");
                            cliente.solicitarPrecioDivisas(asesorDivisas);
                            System.out.println("");
                            break;
                        case 2:
                            System.out.println("");
                            System.out.println("Ingrese el monto a comprar");
                            double montoComprar = sc.nextDouble();
                            cliente.comprarDivisas(montoComprar, asesorDivisas);
                            System.out.println("");
                            break;
                        case 3:
                            System.out.println("");
                            System.out.println("Ingrese el monto a vender");
                            double montoVender = sc.nextDouble();
                            System.out.println("Ingrese la moneda a vender, usted posee: ");
                            cliente.mostrarDivisasCompradas();
                            String moneda = sc.nextLine();
                            moneda = sc.nextLine();
                            cliente.venderDivisas(moneda, montoVender, asesorDivisas);
                            System.out.println("");
                            break;
                        default:
                            break;
                    }
                    break;
                case 6:
                    //consultar precios de acciones y criptomonedas
                    break;
                case 7:
                    //mostrar saldo al cliente
                    System.out.println("");
                    System.out.println("Sr/Sra. "+cliente.getNombre()+" su saldo actual es de: $"+ cliente.getSaldo());
                    System.out.println("");
                    break;
                default:
                    break;
            }
        }
    }

    public static void crearCliente(HashMap<Integer,String> contraseñas, HashMap<Integer,Cliente> clientes){
        Scanner sc = new Scanner(System.in);
        //valido nombre
        String nombre = "";
        while (nombre.isEmpty()){
            System.out.print("Ingrese su nombre: ");
            nombre = sc.nextLine();
        }
        //valido apellido
        String apellido = "";
        while (apellido.isEmpty()){
            System.out.print("Ingrese su apellido: ");
            apellido = sc.nextLine();
        }
        //valido dni
        int dni = 0;
        boolean dniValido = false;
        while (!dniValido){
            System.out.print("Ingrese su dni: ");
            dni = sc.nextInt();
            if (dni > 0){
                dniValido = true;
            }
        }
        //valido contraseña
        String pass = "";
        while (pass.isEmpty()){
            System.out.print("Ingrese su contraseña: ");
            pass = sc.nextLine();
            pass = sc.nextLine();
        }
        System.out.println("");
        Cliente cliente = new Cliente(dni, nombre, apellido, 0, null);

        contraseñas.put(dni, pass);
        clientes.put(dni, cliente);
        return;
    }
}

/* 
    

    

}
*/
/*
  /**
        AgenteDeBolsa agente = new AgenteDeBolsa("Raul");
        Cliente cliente = new Cliente(45448520,"Trinidad","Perea", 10000, agente,null,null,null,null);
        Cajero cajero = new Cajero("juan","des",123,12444,345,"trrh");
        //pruebo el asesor financiero
        AsesorFinanciero empleado = new AsesorFinanciero("Juan","Perez",122 , 120000
        , 6175138,"TRin@");

        //pido consejo
        String consejo;
        consejo = empleado.consejoFinanciero(cliente);
        System.out.println(consejo);
        System.out.println("");

        //pruebo realizar un retiro
        cliente.setCajero(cajero);
        double dineroRetirar;
        dineroRetirar = 10;
        cliente.solicitarRetiro(dineroRetirar);
        //verifico si me resto del saldo
        double saldo;
        saldo = cliente.getSaldo();
        System.out.println(saldo);
        
        System.out.println("");
        
        //pruebo depositar dinero
        double deposito;
        deposito = 20;
        cliente.solicitarDeposito(deposito);
        saldo = cliente.getSaldo();
        System.out.println(saldo);

        System.out.println("");

        //pruebo transferir dinero
        //creo otro cliente
        System.out.println("El dinero del cliente 1 es: "+cliente.getSaldo());
        Cliente cliente2 = new Cliente(34, "Adrian", "Perea", 200, agente, cajero,null,null,null);
        cliente.solicitarTransferencia(300, cliente2);
        double saldo2;
        saldo2 = cliente2.getSaldo();
        double saldo1;
        saldo1 = cliente.getSaldo();
        System.out.println("EL saldo del cliente 2 es: "+saldo2);
        System.out.println("EL saldo del cliente 1 es: "+saldo1);
        System.out.println("");

        //solicito prestamo 
        //creo un cliente nuevo 
        /*  Cliente cliente3 = new Cliente(23234344, "Jose", "Manzano", 230000, agente, null,null,null);
        cliente3.setCajero(cajero);
        Gerente gerente = new Gerente(null,"Martin", "Gonzalez", 1441, 120000, 132445566, "mail");
        cliente3.setGerente(gerente);
        //le voy a colocar que no tiene deudas
        cliente3.solicitarPrestamo(cliente3, 23000);
        //verifico si se me suma el prestamo
        double saldo3 = cliente3.getSaldo();
        System.out.println(saldo3); 

        /* 
        System.out.println(" "); 
        /* 
        //pido copia de seguridad del cliente 3
        Banco banco = new Banco(30,30);
        banco.realizarCopiaDeSeguridad(cliente3);
        banco.realizarCopiaDeSeguridad(cliente); 

        //pruebo inversiones(aun no terminadas)
        //cliente.comprarActivo(5000);

        //cliente solicita el valor de las monedas de cambio
        //creo el objeto asesor
        /* 
        Scanner sc = new Scanner(System.in);
        AsesorDivisas asesorDivisas = new AsesorDivisas("Martina", "Gonzalez", 1440, 120000, 132445566, "mail",sc);
        Cliente cliente4 = new Cliente(23234344, "Jose", "Manzano", 230000, agente, null,null,asesorDivisas,null);
        cliente4.consultarPrecioCompraDivisas();
        cliente4.mostrarDivisasCompradas();
        //verifico que si vuelvo a comprar me lo sume 
        cliente4.consultarPrecioCompraDivisas();
        cliente4.mostrarDivisasCompradas(); 

        //METODO POLIMORFICO CLIENTE EMPLEADO
        //creo cliente
        /*Gerente gerente = new Gerente(null,"Martin", "Gonzalez", 1441, 120000, 132445566, "mail");
        Cajero cajero = new Cajero("juan","des",123,12444,345,"trrh");
        //Map<String,Empleado> map = new HashMap<>();
        //map.put("gerente",gerente);
        Cliente cliente = new Cliente(23234344, "Jose", "Manzano", 230000,null, null,cajero);
        Cliente cliente2 = new Cliente(34, "Adrian", "Perea", 200, null,null,cajero);
        cliente.solicitarTransferencia(30,cliente2);
        
        /* 
        AgenteDeBolsa agente = new AgenteDeBolsa("Raul");
        Cliente trini = new Cliente(45448520,"Trinidad","Perea", 0, agente);
        
        AsesorDivisas asesorDivisas = new AsesorDivisas("Martina", "Gonzalez", 1440, 120000, 132445566, "mail");

        Empleado cajero = new Cajero("juan","des",123,12444,345,"trrh");

        trini.solicitarDeposito(100000, cajero);

        trini.consultarPrecios();
        agente.actualizarValorDeAccionesYCriptos();
        trini.consultarPrecios();**/
 