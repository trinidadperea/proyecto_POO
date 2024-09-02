package Banco.Cliente.Inversiones;
import Banco.Cliente.Cliente;

public class InversionCriptoMonedas extends Inversion {
    private String nombre;
    private double cantidad;
    private double volatilidad;
    private double precioCompra;
    private double precioActual;

    public InversionCriptoMonedas(String nombre, double cantidad, double volatilidad, double precioCompra, double precioActual, double monto, Cliente cliente) {
        super(monto, cliente);
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.volatilidad = volatilidad;
        this.precioCompra = precioCompra;
        this.precioActual = precioActual;
    }

    public double calcularRendimientos(){
        return (precioActual - precioCompra) * cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getVolatilidad() {
        return volatilidad;
    }

    public void setVolatilidad(double volatilidad) {
        this.volatilidad = volatilidad;
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