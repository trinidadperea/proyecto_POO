package Banco.Empleado;
import Banco.Cliente.Cliente;

public class AsesorFinanciero extends Empleado{
    //Constructor
    public AsesorFinanciero(String nombre, String apellido, int legajo, double salario,int nroTelefono, String email){
        super(nombre, apellido, legajo, salario, nroTelefono, email);
    }

    public String consejoFinanciero(Cliente cliente){
        //devuelvo un consejo
        return "Hola " + cliente.getNombre() + " te recomiendo que ahorres un poco mas";
    }

    //meotodo atender cliente
    @Override
    public void atenderCliente(Cliente cliente){
        System.out.println("El cliente "+cliente.getNombre()+" es atendido por el Asesor Financiero "+get.nombre());
    }

}