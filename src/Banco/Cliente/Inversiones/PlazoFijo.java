package Banco.Cliente.Inversiones;

import Banco.Cliente.Cliente;

public class PlazoFijo extends Inversion {
    private double tasaInteresAnual;
    private int plazoDias;

    public PlazoFijo(double tasaInteresAnual, int plazoDias, double monto, Cliente cliente){
        super(monto, cliente);
        this.tasaInteresAnual = tasaInteresAnual;

        this.plazoDias = plazoDias;
    }

    public double calcularRendimientos(){
        return 0;
    }

    public double getTasaInteres() {
        return tasaInteresAnual;
    }

    public void setTasaInteres(double tasaInteres) {
        this.tasaInteresAnual = tasaInteres;
    }

    public int getPlazoDias() {
        return plazoDias;
    }

    public void setPlazoDias(int plazoDias) {
        this.plazoDias = plazoDias;
    }
    
}
