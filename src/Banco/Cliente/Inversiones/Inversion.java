package Banco.Cliente.Inversiones;
import Banco.Cliente.Cliente;

public  abstract class Inversion implements AccionInversion {
    double monto;
    Cliente cliente;
    public Inversion(double monto, Cliente cliente){
        this.monto = monto;
        this.cliente = cliente;
    }
}
