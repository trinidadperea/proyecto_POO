package Banco.Cliente.Inversiones;
import Banco.Cliente.Cliente;

public  abstract class Inversion implements AccionInversion {
    protected double monto;
    protected Cliente cliente;

    public Inversion(double monto, Cliente cliente){
        this.monto = monto;
        this.cliente = cliente;
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
