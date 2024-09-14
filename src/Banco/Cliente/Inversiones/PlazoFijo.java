package Banco.Cliente.Inversiones;
import java.time.LocalDate;
import Banco.Cliente.Cliente;

public class PlazoFijo extends Inversion {
    private float tasaInteres;
    private String estado;
    private LocalDate fechaVencimiento;
    private int duracion;  // en días

    // Constructor
    public PlazoFijo(Cliente cliente, double monto,float tasaInteres, String estado, LocalDate fechaVencimiento, int duracion) {
        super(monto, cliente, "Plazo Fijo", "Plazo Fijo");
        this.tasaInteres = tasaInteres;
        this.estado = estado;
        this.fechaVencimiento = fechaVencimiento;
        this.duracion = duracion;
    }

    public double calcularRendimientos(int diasTranscurridos){
        return monto * tasaInteres * diasTranscurridos;
    }

    public float getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(float tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }


    // Método toString para mostrar los detalles del PlazoFijo
    @Override
    public String toString() {
        return "PlazoFijo{" +
                "tasaInteres=" + tasaInteres +
                ", estado='" + estado + '\'' +
                ", fechaVencimiento=" + fechaVencimiento +
                ", duracion=" + duracion +
                '}';
    }
}