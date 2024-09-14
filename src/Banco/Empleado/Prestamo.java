package Banco.Empleado;
import Banco.Cliente.Cliente;

import java.time.LocalDate;

public class Prestamo {

    final private int cuotas = 12; //impuesta por el banco no puede ser cambiada
    private double valorCuotas;
    private int cuotasPagadas;
    private double montoPorPagar;
    private int cuotasAtrasadas;
    private LocalDate ultimaFechaPago;
    private Cliente cliente;
    //para todos los clientes el interes anual va a ser del 60%
    final private double interesAnual = 0.6; 

    
    public Prestamo(double montoPorPagar, Cliente cliente) {
        this.valorCuotas = montoPorPagar / cuotas;
        this.cuotasPagadas = 0;
        this.montoPorPagar = montoPorPagar;
        this.cliente = cliente;
        this.cuotasAtrasadas = 0;
        this.ultimaFechaPago = LocalDate.now();
    }

    public double getInteresAnual(){
        return interesAnual;
    }

    public double getMontoPorPagar() {
        return montoPorPagar;
    }

    public void setMontoPorPagar(double montoPorPagar) {
        this.montoPorPagar = montoPorPagar;
    }

    public int getCuotas() {
        return cuotas;
    }

    public double getValorCuotas() {
        return valorCuotas;
    }

    public void setValorCuotas(double valorCuotas) {
        this.valorCuotas = valorCuotas;
    }

    public int getCuotasPagadas() {
        return cuotasPagadas;
    }

    public void setCuotasPagadas(int cuotasPagadas) {
        this.cuotasPagadas = cuotasPagadas;
    }

    public int getCuotasAtrasadas() {
        return cuotasAtrasadas;
    }

    public void setCuotasAtrasadas(int cuotasAtrasadas) {
        this.cuotasAtrasadas = cuotasAtrasadas;
    }

    public LocalDate getUltimaFechaPago() {
        return ultimaFechaPago;
    }

    public void setUltimaFechaPago(LocalDate ultimaFechaPago) {
        this.ultimaFechaPago = ultimaFechaPago;
    }

}
