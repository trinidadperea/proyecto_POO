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

    /**
     * Metodo que le aprueba el prestamo solicitado a un cliente si cumple los requisitos necesarios
     * @param cliente cliente que realiza el pedido del prestamo
     * @param dineroPrestamo dinero que solicita que le den como prestamo al cliente
     * @param empleado tipo de empleado encargado de realizar la operacion
     * @return true si se aprobo el prestamo, false sino
     */
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

    /**
     * Metodo que actualiza el diccionario de dinero no registrado de los clientes
     * @param cliente cliente a actualizar el monto
     * @param dinero dinero que se le resta al cliente como dinero sucio 
     */
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

    /**
     * Metodo que muestra las transacciones de un cliente
     * @param cliente cliente del cual se van a ver sus transacciones
     * @param cajero empleado encargado de hacerlo
     */
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
    /**
     * Metodo que si aprueba la transaccion pedida, le suma el dinero de la transaccion pedida al diccionario de dinero sucio
     * @param cliente cliente al que se le suma el dinero
     * @param dinero monto solicitado en la transaccion
     * @return true si se aprobo la transaccion, false sino
     */
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
    /**
     * Metodo que es solo accesible por el gerente, y sirve para ver el diccionario que lleva el registro con el dinero no registrado de los clientes
     * @param clientes
     */
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

    /**
     * Metodo que verifica que el cliente tenga la suficiente plata en blanco para realizar una transaccion
     * @param cliente cliente que quiere realizar la transaccion
     * @param dinero monto a realizar 
     * @return true si la transaccion es valida, false sino
     */
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
