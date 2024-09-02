package Banco.Cliente.Inversiones;
import java.time.LocalDate;
import Banco.Cliente.Cliente;

public class FondoDeInversion extends Inversion{
    
    private String nombre;
    private LocalDate fechaInicio;
    private int plazoMinimoPermanencia;

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public int getPlazoMinimoPermanencia() {
        return plazoMinimoPermanencia;
    }

    public void setPlazoMinimoPermanencia(int plazoMinimoPermanencia) {
        this.plazoMinimoPermanencia = plazoMinimoPermanencia;
    }

    
}