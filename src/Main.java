import Banco.Empleado.AsesorFinanciero;
import Banco.Empleado.Empleado;
import Banco.AgenteDeBolsa;
import Banco.Cliente.Cliente;
import Banco.Empleado.*;


public class Main {
    //voy a probar el asesor financiero
    //creo un cliente
    public static void main(String[] args){

        AgenteDeBolsa agente = new AgenteDeBolsa();
        Cliente cliente = new Cliente(45448520,"Trinidad","Perea", 10000, agente,null);
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
        Cliente cliente2 = new Cliente(34, "Adrian", "Perea", 200, agente, cajero);
        cliente.solicitarTransferencia(300, cliente2);
        double saldo2;
        saldo2 = cliente2.getSaldo();
        System.out.println("EL saldo del cliente 2 es: "+saldo2);
    }
}
