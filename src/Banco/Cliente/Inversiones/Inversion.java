package Banco.Cliente.Inversiones;
import Banco.Cliente.Cliente;

public  abstract class Inversion implements AccionInversion {
    protected double monto;
    protected Cliente cliente;
    protected String tipoInversion;
    protected String nombre;

    public Inversion(double monto, Cliente cliente, String nombre, String tipoInversion) {
        this.monto = monto;
        this.cliente = cliente;
        this.nombre = nombre;
        this.tipoInversion = tipoInversion;
    }

    @Override
    public double calcularRendimientos() {
        return 0;
    }

    public String getTipoInversion() {
        return tipoInversion;
    }

    public void setTipoInversion(String tipoInversion) {
        this.tipoInversion = tipoInversion;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
}
