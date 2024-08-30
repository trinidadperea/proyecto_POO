package Banco.Cliente.Inversiones;
import Banco.Cliente.Cliente;

public class InversionBolsa extends Inversion {
    String accion;
    float cantidadAcciones;
    double precioCompra;
    double precioActual;
    String sectorEconomico;
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