package Banco.Cliente;

import Banco.Cliente.Inversiones.Inversion;
import Banco.AgenteDeBolsa;
import Banco.Empleado.*;

public class Cliente implements CompraVentaActivos{
    private int dni;
    private String nombre;
    private String apellido;
    private double saldo;
    private AgenteDeBolsa agente;
    private Cajero cajero;


    public void solicitarRetiro(double dineroRetirar){    // Solicita un retiro
        boolean solicitud = cajero.realizarRetiro(dineroRetirar,this);
        if (solicitud){
            System.out.println("Retiro realizado con exito");
        } else{
            System.out.println("El retiro no se pudo realizar");
        }
    }
    
    public void solicitarDeposito(double dineroDepositar){
        boolean solicitud = cajero.realizarDeposito(dineroDepositar, this);
        if (solicitud){
            System.out.println("El dinero se deposito con exito");
        } else{
            //deberiamos ver cuando no se puede depositar, si hay algun tope max
            //si quiere depositar mas de x plata, deberia decir de donde la saca, sino es lavado de dinero
            System.out.println("El deposito no pudo realizarse");
            System.out.println("Mostrar de donde viene la plata");
        }
    }

    public void solicitarTransferencia(double dineroTransferir, Cliente clienteDestino){
        // Solicita una transferencia
        boolean solicitud = cajero.realizarTransferencia(dineroTransferir, this, clienteDestino);
        if (solicitud){
            System.out.println("La transferencia se realizo con exito");
        } else{
            System.out.println("Transferencia cancelada");
        }
    }
    public void solicitarPrestamo(){
        // Solicita un prestamo
    }
    public void pagarCuotaPrestamo(){
        // Paga una cuota de un prestamo
    }
    public void cancelarPrestamo(){
        // Cancela un prestamo
    }
    public double mostrarSaldo(){
        return saldo;
    }
    
    public Cliente(int dni, String nombre,String apellido, double saldo, AgenteDeBolsa agente, Cajero cajero) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.saldo = saldo;
        this.agente = agente;
        this.cajero = cajero;
    }

    public void comprarActivo(Inversion inversion, double monto){
        if (this.saldo >= monto) {
            agente.realizarInversion(inversion, monto);
            this.saldo -= monto;
        } else {
            System.out.println("Saldo insuficiente para realizar la inversión.");
        }
    }

    public void venderActivo(Inversion inversion){
        this.saldo += agente.venderActivo(inversion);
        System.out.println("El saldo actual es: " + this.saldo);
        return;
    }

    public void consultarActivos(){
        System.out.println(agente.mostrarInversiones());
        return;
    }
    
    public int getDni() {
        return dni;
    }
    public void setDni(int dni) {
        this.dni = dni;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

    public void setCajero(Cajero cajero){
        this.cajero = cajero;
    }


}