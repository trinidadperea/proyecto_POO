package Banco.Cliente;

public interface CompraVentaDivisas {
    public void venderDivisas(String monedaVender, double montoVender);
    public void comprarDivisas(String monedaComprar, double montoComprar);
    public void solicitarPrecioDivisas();

} 
