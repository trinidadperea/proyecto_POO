package Banco.Cliente.Inversiones;
import Banco.Cliente.Cliente;

public class InversionBolsa extends Inversion {
    private String accion;
    private float cantidadAcciones;
    private double precioCompra;
    private double precioActual;
    private String sectorEconomico;
    public InversionBolsa(String accion, float cantidadAcciones, double precioCompra, double precioActual, String sectorEconomico, double monto, Cliente cliente) {
        super(monto, cliente);
        this.accion = accion;
        this.cantidadAcciones = cantidadAcciones;
        this.precioCompra = precioCompra;
        this.precioActual = precioActual;
        this.sectorEconomico = sectorEconomico;
    }
    
    public double calcularRendimientos(){
        return (precioActual - precioCompra) * cantidadAcciones;
    }

}