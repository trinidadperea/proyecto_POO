package Banco.Cliente;

import Banco.Empleado.Empleado;

public interface CompraVentaDivisas {
    public void venderDivisas(String monedaVender, double montoVender, Empleado empleado);
    public void comprarDivisas(double montoComprar, Empleado empleado);
    public void mostrarDivisasCompradas();
    public void solicitarPrecioDivisas(Empleado empleado);
} 
