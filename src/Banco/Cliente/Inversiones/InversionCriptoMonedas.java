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

}