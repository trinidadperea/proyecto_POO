package Banco.Empleado.Transaccion;
import Banco.Cliente.Cliente;

public class Transaccion {
    private double monto;
    private String tipoTransaccion;
    private Cliente cliente;

    //constructor
    public Transaccion(double monto, String tipoTransaccion, Cliente cliente) {
        this.monto = monto;
        this.tipoTransaccion = tipoTransaccion;
        this.cliente = cliente;
    }

    //getters y setters
    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
