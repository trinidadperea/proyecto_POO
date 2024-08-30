package Banco.Cliente.Inversiones;
import Banco.Cliente.Cliente;

public class InversionPropiedades extends Inversion {
    String tipoPropiedad;
    double precioCompra;
    double precioActual;
    double metrosCuadrados;
    double gastosMantenimiento;

    public InversionPropiedades(String tipoPropiedad, double precioCompra, double precioActual, double metrosCuadrados, double gastosMantenimiento, double monto, Cliente cliente) {
        super(monto, cliente);
        this.tipoPropiedad = tipoPropiedad;
        this.precioCompra = precioCompra;
        this.precioActual = precioActual;
        this.metrosCuadrados = metrosCuadrados;
        this.gastosMantenimiento = gastosMantenimiento;
    }
    
    public double calcularRendimientos(){
        return (precioActual - precioCompra) * metrosCuadrados;
    }
}