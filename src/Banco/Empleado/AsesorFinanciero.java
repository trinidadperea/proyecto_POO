package Banco.Empleado;
import Banco.Cliente.Cliente;

public class AsesorFinanciero extends Empleado{
    //Constructor
    public AsesorFinanciero(String nombre, String apellido, int legajo, double salario,int nroTelefono, String email){
        super(nombre, apellido, legajo, salario, nroTelefono, email);
    }
    
    /**
     * Metodo que le solicita un consejo al cliente
     * @param cliente cliente al cual se le realiza el consejo
     */
    public String consejoFinanciero(Cliente cliente){
        //devuelvo un consejo
        return "Hola " + cliente.getNombre() + " te recomiendo que ahorres un poco mas";
    }
}