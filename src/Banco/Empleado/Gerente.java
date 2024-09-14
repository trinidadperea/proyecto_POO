package Banco.Empleado;
//clases para usar diccionario en java
import java.util.*;
import Banco.Cliente.Cliente;

public class Gerente extends Empleado{
    private Map<Integer, Double> dineroNoRegistrado;

    //constructor
    public Gerente(String nombre, String apellido, int legajo, double salario,
                   int nroTelefono, String email) {
        super(nombre,apellido,legajo,salario,nroTelefono,email);
        this.dineroNoRegistrado = new HashMap<>();
    }

    public boolean aprobarPrestamo(Cliente cliente, double dineroPrestamo, Empleado empleado){
        double porcentajeSalario = (50 * cliente.getSaldo()) / 100;
        boolean deuda = ((Cajero) empleado).getPrestamos().containsKey(cliente.getDni());
        porcentajeSalario = Math.round(porcentajeSalario * 100.0) / 100.0;
        //si el cliente tiene alguna deuda actual no se le realiza el prestamo
        //apruebo el prestamo si este es menor o igual al 50% de su salario
        if (!deuda && (dineroPrestamo <= porcentajeSalario) ){
            //prestamo aprobado
            return true;
        } else {
            if (deuda){
                System.out.println("El cliente ya tiene una deuda pendiente");
            } else {
                System.out.println("El prestamo solicitado supera el 50% de su salario");
                System.out.println("Podemos ofrecerle como maximo un prestamo de $"+porcentajeSalario);
            }
            return false;
        }
    }

    //getter y setter del diccionario dinero no registrado
    public Map<Integer, Double> getDineroNoRegistrado() {
        return dineroNoRegistrado;
    }
    public void setDineroNoRegistrado(Map<Integer, Double> dineroNoRegistrado) {
        this.dineroNoRegistrado = dineroNoRegistrado;
    }
    
    //metodo clase gerente, pedidos por el cliente
    public boolean aprobarTransaccionCliente(Cliente cliente,double dinero){
        System.out.println(dineroNoRegistrado.keySet());
        if (this.dineroNoRegistrado.containsKey(cliente.getDni())){

            double dineroNoRegCliente = this.dineroNoRegistrado.get(cliente.getDni());
            dineroNoRegCliente += dinero;
            this.dineroNoRegistrado.put(cliente.getDni(),dineroNoRegCliente);

        } else {
            System.out.println("armo el primer cliente");
            this.dineroNoRegistrado.put(cliente.getDni(),dinero);
        }
        return true;
    }
    //metodo que solo el gerente puede solicitar
    public void mostrarDineroNoRegistrado(Cliente cliente){
        if (dineroNoRegistrado.isEmpty()){
            System.out.println("No hay dinero no registrado");
        } else {
            System.out.println("Dinero no registrado: ");
            System.out.println("Cliente "+cliente.getNombre()+" posee $"+dineroNoRegistrado.get(cliente.getDni())+" de dinero no registrado");
        }
    }

    /* 
    public boolean aprobarTransferenciaNoRastreable(Cliente cliente, double dinero){
        //realizar metodo
        return true;
    }  */
}
