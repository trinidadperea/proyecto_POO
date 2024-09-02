package Banco;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import Banco.Cliente.Cliente;

public class Banco{
    private int cantEmpleados;
    private int cantClientes;
    private static final String archivoClientes = "clientes.txt";

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
    public void realizarCopiaDeSeguridad(Cliente cliente){
        //guardo los datos en un archvio de texto
        try (BufferedWriter guardar = new BufferedWriter(new FileWriter(archivoClientes,true))){
            guardar.write(cliente.toString());
            guardar.newLine();
            System.out.println("Cliente "+cliente.getNombre()+" guardado en archivos");
        } catch(IOException e){
            System.out.println("Error al guardar al cliente en archivos "+ e.getMessage());
        }
    }
    
    public String mostrarCantClientes(){
        return "La cantidad de clientes que tiene el Banco La Familia son: "+cantClientes;
    }
    public String mostrarCantEmpleados(){
        return "La cantidad de clientes que tiene el Banco La Familia son: "+cantEmpleados;
    }
}