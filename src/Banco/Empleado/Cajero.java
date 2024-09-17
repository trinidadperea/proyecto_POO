package Banco.Empleado;

import Banco.Cliente.Cliente;
import Banco.Empleado.Transaccion.Transaccion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cajero extends Empleado {
    private HashMap<Integer, Prestamo> prestamos;
    private HashMap<Integer, List<Transaccion>> transacciones;

    // Constructor
    public Cajero(String nombre, String apellido, int legajo, double salario,
                  int nroTelefono, String email) {
        super(nombre, apellido, legajo, salario, nroTelefono, email);
        this.prestamos = new HashMap<>();
        this.transacciones = new HashMap<>();
    }

    // Métodos que realiza el cajero
    /**
     * Realiza un retiro de dinero de la cuenta de un cliente
     * @param dineroRetirar Monto a retirar
     * @param cliente Cliente al que se le realiza el retiro
     * @return true si se realizó el retiro, false si no se pudo realizar
     */

    public boolean realizarRetiro(double dineroRetirar, Cliente cliente) {
        if (dineroRetirar > cliente.getSaldo()) {
            System.out.println("Saldo insuficiente");
            return false;
        } else {
            cliente.setSaldo(cliente.getSaldo() - dineroRetirar);

            registrarTransaccion(cliente, dineroRetirar, "Retiro");

            return true;
        }
    }
    /**
     * Realiza una transferencia de dinero entre dos clientes
     * @param dineroTransferir Monto a transferir
     * @param cliente1 Cliente que realiza la transferencia
     * @param cliente2 Cliente que recibe la transferencia
     * @return true si se realizó la transferencia, false si no se pudo realizar
     */
    public boolean realizarTransferencia(double dineroTransferir, Cliente cliente1, Cliente cliente2) {
        
        if (cliente1.getSaldo() < dineroTransferir) {
            System.out.println("Sr/Sra " + cliente1.getNombre() + " " + cliente1.getApellido() + " no puede transferir ese monto");
            return false;
        } else {
            cliente2.setSaldo(cliente2.getSaldo() + dineroTransferir);
            cliente1.setSaldo(cliente1.getSaldo() - dineroTransferir);

            registrarTransaccion(cliente1, dineroTransferir, "Transferencia salida");
            registrarTransaccion(cliente2, dineroTransferir, "Transferencia entrada");

            return true;
        }
    }

    /**
     * Metodo que realiza un deposito a un cliente
     * @param dineroDepositar monto que desea depositar en el banco
     * @param cliente cliente que realiza el deposito
     * @return true si se realizó el deposito, false si no se pudo realizar
     */
    public boolean realizarDeposito(double dineroDepositar, Cliente cliente) {
        cliente.setSaldo(cliente.getSaldo() + dineroDepositar);

        registrarTransaccion(cliente, dineroDepositar, "Depósito");

        return true;
    }

    /**
     * Metodo que le realiza al cliente, si no tiene deudas, un prestamo
     * @param cliente cliente al que se le realiza el prestamo
     * @param dineroPrestamo monto del prestamo a realizar
     */
    public void realizarPrestamoCliente(Cliente cliente, double dineroPrestamo) {
        cliente.setSaldo(cliente.getSaldo() + dineroPrestamo);

        prestamos.put(cliente.getDni(), new Prestamo(dineroPrestamo, cliente));

        registrarTransaccion(cliente, dineroPrestamo, "Préstamo");

        return;
    }

    public HashMap<Integer, List<Transaccion>> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(HashMap<Integer, List<Transaccion>> transacciones) {
        this.transacciones = transacciones;
    }

    /**
     * Metodo que agrega las transacciones de un cliente en una lista
     * @param cliente cliente que realizo la transaccion
     * @param monto monto de la transaccion
     * @param tipoTransaccion que transaccion se va realizar
     */
    private void registrarTransaccion(Cliente cliente, double monto, String tipoTransaccion) {
        if (!transacciones.containsKey(cliente.getDni())) {
            transacciones.put(cliente.getDni(), new ArrayList<>());
        }

        List<Transaccion> listaTransacciones = transacciones.get(cliente.getDni());
        listaTransacciones.add(new Transaccion(monto, tipoTransaccion, cliente));
    }

    public HashMap<Integer, Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(HashMap<Integer, Prestamo> prestamos) {
        this.prestamos = prestamos;
    }
}
