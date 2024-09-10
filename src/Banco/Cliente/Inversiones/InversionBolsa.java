package Banco.Cliente.Inversiones;
import Banco.Cliente.Cliente;

public class InversionBolsa extends Inversion {
    private String accion;
    private float cantidadAcciones;
    private double precioCompra;
    private double precioActual;

    public InversionBolsa(String accion, float cantidadAcciones, double precioCompra, double precioActual, double monto, Cliente cliente, String nombre) {
        super(monto, cliente, nombre, "Bolsa");
        this.accion = accion;
        this.cantidadAcciones = cantidadAcciones;
        this.precioCompra = precioCompra;
        this.precioActual = precioActual;
    }


    public double calcularRendimientos(){
        return (precioActual - precioCompra) * cantidadAcciones;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public float getCantidadAcciones() {
        return cantidadAcciones;
    }

    public void setCantidadAcciones(float cantidadAcciones) {
        this.cantidadAcciones = cantidadAcciones;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioActual() {
        return precioActual;
    }

    public void setPrecioActual(double precioActual) {
        this.precioActual = precioActual;
    }


}