package Banco.Cliente;

import Banco.Cliente.Inversiones.Inversion;

public interface CompraVentaActivos {
    public void comprarActivo(double monto, int tipoInversion);
    public void venderActivo(int tipoInversion);
    public void consultarActivos();
}