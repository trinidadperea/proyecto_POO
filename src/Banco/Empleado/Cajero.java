package Banco.Empleado;
import Banco.Cliente.Cliente;
public class Cajero extends Empleado{
    //constructor

    public Cajero(String nombre, String apellido, int legajo, double salario,
                  int nroTelefono, String email) {
        super(nombre,apellido,legajo,salario,nroTelefono,email);
    }
    //metodos que realiza el cajero
    public boolean realizarRetiro(double dineroRetirar){
        //realizar metodo
    }
    public boolean realizarTransferencia(double dineroTransferir){
        //realizar metodo
    }
    public boolean realizarDeposito(double dineroDepositar){
        //realizar metodo
    }
    public boolean realizarPrestamoCliente(Cliente cliente,double dineroPrestamo){
        //realizar metodo
    }
}
