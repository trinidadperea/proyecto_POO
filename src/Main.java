import Banco.Empleado.AsesorFinanciero;
import Banco.Empleado.Empleado;
import Banco.AgenteDeBolsa;
import Banco.Cliente.Cliente;
import Banco.Empleado.*;
import Banco.Banco;


public class Main {
    //voy a probar el asesor financiero
    //creo un cliente
    public static void main(String[] args){

        AgenteDeBolsa agente = new AgenteDeBolsa("Raul");
        Cliente cliente = new Cliente(45448520,"Trinidad","Perea", 10000, agente,null,null);
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
        Cliente cliente2 = new Cliente(34, "Adrian", "Perea", 200, agente, cajero,null);
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
        Cliente cliente3 = new Cliente(23234344, "Jose", "Manzano", 230000, agente, null,null);
        cliente3.setCajero(cajero);
        Gerente gerente = new Gerente(null,"Martin", "Gonzalez", 1441, 120000, 132445566, "mail");
        cliente3.setGerente(gerente);
        //le voy a colocar que no tiene deudas
        cliente3.solicitarPrestamo(cliente3, 23000, "no");
        //verifico si se me suma el prestamo
        double saldo3 = cliente3.getSaldo();
        System.out.println(saldo3);

        System.out.println(" ");

        //pido copia de seguridad del cliente 3
        Banco banco = new Banco(30,30);
        banco.realizarCopiaDeSeguridad(cliente3);
        banco.realizarCopiaDeSeguridad(cliente);

        //pruebo inversiones(aun no terminadas)
        cliente.comprarActivo(5000);
        
    }
}
