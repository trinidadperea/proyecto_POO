package Banco.Cliente;

import Banco.Cliente.Inversiones.Inversion;

public interface CompraVentaActivos {
    public void comprarActivo(Inversion inversion, double monto);
    public void venderActivo(Inversion inversion);
    public void consultarActivos();
}