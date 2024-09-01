package Banco.Empleado;
//clases para usar diccionario en java
import java.util.Map;
import Banco.Cliente.Cliente;

public class Gerente extends Empleado{
    private Map<String,Integer> dineroNoRegistrado;

    //constructor
    public Gerente(Map<String, Integer> dineroNoRegistrado,String nombre,
                   String apellido, int legajo, double salario,
                   int nroTelefono, String email) {
        super(nombre,apellido,legajo,salario,nroTelefono,email);
        this.dineroNoRegistrado = dineroNoRegistrado;
    }

    //getter y setter del diccionario dinero no registrado
    public Map<String, Integer> getDineroNoRegistrado() {
        return dineroNoRegistrado;
    }
    public void setDineroNoRegistrado(Map<String, Integer> dineroNoRegistrado) {
        this.dineroNoRegistrado = dineroNoRegistrado;
    }

    //metodo clase gerente, pedidos por el cliente
    public boolean aprobarTransaccionCliente(Cliente cliente,double dinero){
        //realizar metodo
        return true;
    }
    public boolean aprobarTransferenciaNoRastreable(Cliente cliente, double dinero){
        //realizar metodo
        return true;
    }
    public boolean aprobarPrestamo(Cliente cliente, double dinero){
        //realizar metodo
        return true;
    }
    //este metodo me dice si el cliente tiene dinero sucio (ver como hacerlo)
    public double dineroNoRastreable(Cliente cliente){
        //realizar metodo
        return 0;
    }
}
