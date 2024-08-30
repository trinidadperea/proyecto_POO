package Banco.Empleado;

public abstract class Empleado {
    protected String nombre;
    protected String apellido;
    protected int legajo;
    protected double salario;
    protected int nroTelefono;
    protected String email;

    //constructor clase absttracta empleado
    public Empleado(String nombre, String apellido, int legajo, double salario,int nroTelefono, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.legajo = legajo;
        this.salario = salario;
        this.nroTelefono = nroTelefono;
        this.email = email;
    }

    //getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getNroTelefono() {
        return nroTelefono;
    }

    public void setNroTelefono(int nroTelefono) {
        this.nroTelefono = nroTelefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
