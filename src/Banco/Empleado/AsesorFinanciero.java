package Banco.Empleado;

public class AsesorFinanciero extends Empleado{
    //Constructor

    public AsesorFinanciero(String nombre, String apellido, int legajo,
                            double salario, int nroTelefono, String email) {
        super(nombre,apellido,legajo,salario,nroTelefono,email);
    }
    //devuelvo un consejo
    public String consejoFinanciero(){
    }
}
