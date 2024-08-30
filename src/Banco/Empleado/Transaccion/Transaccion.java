package Banco.Empleado.Transaccion;

public abstract class Transaccion {
    protected double monto;
    protected String tipoTransaccion;
    protected Empleado empleado;

    //constructor
    public Transaccion(double monto, String tipoTransaccion, Empleado empleado) {
        this.monto = monto;
        this.tipoTransaccion = tipoTransaccion;
        this.empleado = empleado;
    }
    //getters y setters
    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
