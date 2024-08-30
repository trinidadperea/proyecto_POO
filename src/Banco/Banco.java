package Banco;
import Banco.Cliente.Cliente;

public class Banco{
    private int cantEmpleados;
    private int cantClientes;

    //contructor

    public Banco(int cantEmpleados, int cantClientes) {
        this.cantEmpleados = cantEmpleados;
        this.cantClientes = cantClientes;
    }
    //getters y setters

    public int getCantEmpleados() {
        return cantEmpleados;
    }

    public void setCantEmpleados(int cantEmpleados) {
        this.cantEmpleados = cantEmpleados;
    }

    public int getCantClientes() {
        return cantClientes;
    }

    public void setCantClientes(int cantClientes) {
        this.cantClientes = cantClientes;
    }

    //metodos del banco
    public String realizarCopiaDeSeguridad(Cliente cliente){
        //realizar metodo
    }
    public String mostrarCantClientes(){
        return "La cantidad de clientes que tiene el Banco La Familia son: "+cantClientes;
    }
    public String mostrarCantEmpleados(){
        return "La cantidad de clientes que tiene el Banco La Familia son: "+cantEmpleados;
    }
}