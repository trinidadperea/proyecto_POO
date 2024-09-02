package Banco.Cliente.Inversiones;
import Banco.Cliente.Cliente;

public class InversionPropiedades extends Inversion {
    private String tipoPropiedad;
    private double precioCompra;
    private double precioActual;
    private double metrosCuadrados;
    private double gastosMantenimiento;

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

    public String getTipoPropiedad() {
        return tipoPropiedad;
    }

    public void setTipoPropiedad(String tipoPropiedad) {
        this.tipoPropiedad = tipoPropiedad;
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

    public double getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public void setMetrosCuadrados(double metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }

    public double getGastosMantenimiento() {
        return gastosMantenimiento;
    }

    public void setGastosMantenimiento(double gastosMantenimiento) {
        this.gastosMantenimiento = gastosMantenimiento;
    }

    
}