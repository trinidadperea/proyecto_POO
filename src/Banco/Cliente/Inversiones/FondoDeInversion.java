package Banco.Cliente.Inversiones;
import java.time.LocalDate;
import Banco.Cliente.Cliente;

public class FondoDeInversion extends Inversion{
    
    String nombre;
    LocalDate fechaInicio;
    int plazoMinimoPermanencia;

    public FondoDeInversion(String nombre, LocalDate fechaInicio, int plazoMinimoPermanencia, double monto, Cliente cliente){
        super(monto, cliente);
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.plazoMinimoPermanencia = plazoMinimoPermanencia;
    }

    public double calcularRendimientos(){
        // Calcula los rendimientos del fondo de inversion
        return 0;
    }
}