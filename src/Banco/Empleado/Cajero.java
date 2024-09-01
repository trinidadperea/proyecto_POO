package Banco.Empleado;
import Banco.Cliente.Cliente;
public class Cajero extends Empleado{

    //constructor
    public Cajero(String nombre, String apellido, int legajo, double salario,
                  int nroTelefono, String email) {
        super(nombre,apellido,legajo,salario,nroTelefono,email);
    }

    //metodos que realiza el cajero

    public boolean realizarRetiro(double dineroRetirar, Cliente cliente){
        if (dineroRetirar > cliente.getSaldo()){
            System.out.println("Saldo insuficiente");
            return false;
        } else {
            //le resto el dinero a retirar
            cliente.setSaldo(cliente.getSaldo() - dineroRetirar);
            return true;
        }
    }

    public boolean realizarDeposito(double dineroDepositar, Cliente cliente){
        //le sumo el deposito al saldo del cliente
        cliente.setSaldo(cliente.getSaldo() + dineroDepositar);
        return true;
    }

    public boolean realizarTransferencia(double dineroTransferir,Cliente cliente1, Cliente cliente2){
        //transferencia de cliente 1 a cliente 2
        //primero verifico si el cliente tiene esa plata para transferir
        if (cliente1.getSaldo() < dineroTransferir){
            System.out.println("Cliente "+cliente1.getNombre()+" "+cliente1.getApellido()+" no puede transferir ese monto");
            return false;
        } else{
            //le aumento ese dinero al cliente 2
            cliente2.setSaldo(cliente2.getSaldo() + dineroTransferir);
            return true;
        }
    }

    public boolean realizarPrestamoCliente(Cliente cliente,double dineroPrestamo){
        //realizar metodo
        return true;
    }
}
