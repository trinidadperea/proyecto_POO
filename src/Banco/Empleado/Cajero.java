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

    public boolean realizarDeposito(double dineroDepositar, Cliente cliente) {
        cliente.setSaldo(cliente.getSaldo() + dineroDepositar);

        registrarTransaccion(cliente, dineroDepositar, "Depósito");

        return true;
    }

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
