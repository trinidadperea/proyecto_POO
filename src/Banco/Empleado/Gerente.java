package Banco.Empleado;
//clases para usar diccionario en java
import java.util.Map;
import Banco.Cliente.Cliente;

public class Gerente extends Empleado{
    private Map<String,Integer> dineroNoRegistrado;

    //meotodo atender cliente
    @Override
    public void atenderCliente(Cliente cliente){
        System.out.println("El cliente "+cliente.getNombre()+" es atendido por el gerente "+get.nombre());
    } 
    //constructor
    public Gerente(Map<String, Integer> dineroNoRegistrado,String nombre,
                   String apellido, int legajo, double salario,
                   int nroTelefono, String email) {
        super(nombre,apellido,legajo,salario,nroTelefono,email);
        this.dineroNoRegistrado = dineroNoRegistrado;
    }

    public boolean aprobarPrestamo(Cliente cliente, double dineroPrestamo, String deuda){
        double porcentajeSalario = (10 * cliente.getSaldo()) / 100;
        //si el cliente tiene alguna deuda actual no se le realiza el prestamo
        //apruebo el prestamo si este es menor o igual al 10% de su salario
        if (deuda.equals("N") && (dineroPrestamo <= porcentajeSalario) ){
            //prestamo aprobado
            return true;
        } else {
            return false;
        }
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

    //este metodo me dice si el cliente tiene dinero sucio (ver como hacerlo)
    public double dineroNoRastreable(Cliente cliente){
        //realizar metodo
        return 0;
    }
}
