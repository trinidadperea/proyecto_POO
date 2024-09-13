package Banco.Empleado;
//clases para usar diccionario en java
import java.util.*;
import Banco.Cliente.Cliente;

public class Gerente extends Empleado{
    private Map<Cliente, Double> dineroNoRegistrado;

    //constructor
    public Gerente(String nombre, String apellido, int legajo, double salario,
                   int nroTelefono, String email) {
        super(nombre,apellido,legajo,salario,nroTelefono,email);
        this.dineroNoRegistrado = new HashMap<>();
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
    public Map<Cliente, Double> getDineroNoRegistrado() {
        return dineroNoRegistrado;
    }
    public void setDineroNoRegistrado(Map<Cliente, Double> dineroNoRegistrado) {
        this.dineroNoRegistrado = dineroNoRegistrado;
    }
    
    //metodo clase gerente, pedidos por el cliente
    public boolean aprobarTransaccionCliente(Cliente cliente,double dinero){
        if (!dineroNoRegistrado.containsKey(cliente)){
            System.out.println("armo el primer cliente");
            dineroNoRegistrado.put(cliente,dinero);
            //mostrarDineroNoRegistrado();
        } else {
            System.out.println("Agrega dinero no registrado");
            double dineroNoRegCliente = dineroNoRegistrado.get(cliente);
            dineroNoRegistrado.put(cliente, dineroNoRegCliente + dinero);
            //mostrarDineroNoRegistrado();
        }
        return true;
    }
    //metodo que solo el gerente puede solicitar
    public void mostrarDineroNoRegistrado(){
        if (dineroNoRegistrado.isEmpty()){
            System.out.println("No hay dinero no registrado");
        } else {
            for (Map.Entry<Cliente,Double> entryCliente: dineroNoRegistrado.entrySet()){
                System.out.println("Cliente "+entryCliente.getKey().getNombre()+" posee $"+entryCliente.getValue()+" de dinero no registrado");
            }
        }
    }

    /* 
    public boolean aprobarTransferenciaNoRastreable(Cliente cliente, double dinero){
        //realizar metodo
        return true;
    }  */
}
