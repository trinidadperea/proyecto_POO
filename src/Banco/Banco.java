package Banco;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import Banco.Cliente.Cliente;
import java.util.*;

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
    /**
     * Metodo que realiza una copia de seguridad de los clientes que hay en el sistema
     * @param clientes
     */
    public void realizarCopiaDeSeguridad(HashMap<Integer, Cliente> clientes) {
        try  {
            for (Map.Entry<Integer, Cliente> entry : clientes.entrySet()) {
                Cliente cliente = entry.getValue(); 
                System.out.println("Cliente " + cliente.getNombre() + " guardado en archivos.");
            }
        } catch (Exception e) {
            System.out.println("Error al guardar los clientes en archivos: " + e.getMessage());
        }
    }
    
    /**
     * Metodo que devuelve el valor de la cantidad de clientes ingresados en el sistema
     * @return
     */
    public String mostrarCantClientes(){
        return "La cantidad de clientes que tiene el Banco La Familia son: "+cantClientes;
    }
    
    /**
     * Metodo que muestra la cantidad de empleados que posee el banco
     * @return
     */
    public String mostrarCantEmpleados(){
        return "La cantidad de clientes que tiene el Banco La Familia son: "+cantEmpleados;
    }
}