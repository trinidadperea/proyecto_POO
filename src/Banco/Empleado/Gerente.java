package Banco.Empleado;
import java.util.*;
import Banco.Cliente.Cliente;
import Banco.Empleado.Transaccion.Transaccion;

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


    public void actualizoDineroNoRegCliente(Cliente cliente, double dinero){
        //verifico que el cliente posee donero no registrado
        if (this.dineroNoRegistrado.containsKey(cliente.getDni())){
            //verifico que tenga al menos el dinero slicitado o mas
            double dineroNoRegCliente = this.dineroNoRegistrado.get(cliente.getDni());
            if (dinero <= dineroNoRegCliente){
                //resto en el dic l dinero no registrado y se la agrego al saldo
                dineroNoRegCliente -= dinero;
                this.dineroNoRegistrado.put(cliente.getDni(), dineroNoRegCliente);
            } else if (dineroNoRegCliente != 0 && dinero > dineroNoRegCliente){
                //limpie toda la plata
                this.dineroNoRegistrado.put(cliente.getDni(),0.0);
            }
        } 
    }

    public void mostrarTransaccionesCLiente(Cliente cliente, Cajero cajero) {
        HashMap<Integer, List<Transaccion>> transacciones = cajero.getTransacciones();

        if (!transacciones.containsKey(cliente.getDni())) {
            System.out.println("No hay transacciones registradas para este cliente.");
            return;
        }

        List<Transaccion> listaTransacciones = transacciones.get(cliente.getDni());

        if (listaTransacciones.isEmpty()) {
            System.out.println("No hay transacciones realizadas.");
        } else {
            System.out.println("Transacciones realizadas: ");
            for (int i = 0; i < listaTransacciones.size(); i++) {
                Transaccion transaccion = listaTransacciones.get(i);
                System.out.println("Transacción " + (i + 1) + ": ");
                System.out.println("Monto: $" + transaccion.getMonto());
                System.out.println("Tipo de transacción: " + transaccion.getTipoTransaccion());
            }
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
        //System.out.println(dineroNoRegistrado.keySet());
        if (this.dineroNoRegistrado.containsKey(cliente.getDni())){
            double dineroNoRegCliente = this.dineroNoRegistrado.get(cliente.getDni());
            dineroNoRegCliente += dinero;
            this.dineroNoRegistrado.put(cliente.getDni(),dineroNoRegCliente);
        } else {
            this.dineroNoRegistrado.put(cliente.getDni(),dinero);
        }
        return true;
    }
    //metodo que solo el gerente puede solicitar
    public void mostrarDineroNoRegistrado(HashMap<Integer, Cliente> clientes){
        if (dineroNoRegistrado.isEmpty()){
            System.out.println("No hay dinero no registrado");
        } else {
            System.out.println("Dinero no registrado: ");
            for (Map.Entry<Integer,Double> entry : dineroNoRegistrado.entrySet()){
                int dniCliente = entry.getKey();
                double dinero = entry.getValue();
                if (clientes.containsKey(dniCliente)){
                    Cliente cliente = clientes.get(dniCliente);
                    System.out.println("Cliente " + cliente.getNombre() + " posee $" + dinero + " de dinero no registrado.");
                } 
            }
        }
    }

    
    public boolean verificarTransaccion(Cliente cliente, double dinero){
        //verifica que la trasferencia se va a realizar con plata limpia
        if (!dineroNoRegistrado.isEmpty()){
            double dineroNoRegCliente = dineroNoRegistrado.getOrDefault(cliente.getDni(),0.0);
            //verifico que tenga la suficiente plata limpia para la transferencia
            if ((cliente.getSaldo() - dineroNoRegCliente) >= dinero){
                return true;
            } else {
                return false;
            }
        } 
        return true;
    }  
}
