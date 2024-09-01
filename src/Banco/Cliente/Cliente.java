package Banco.Cliente;

import Banco.Cliente.Inversiones.Inversion;
import Banco.AgenteDeBolsa;

public class Cliente implements CompraVentaActivos{
    private int dni;
    private String nombre;
    private double saldo;
    private AgenteDeBolsa agente;

    public void solicitarRetiro(){
        // Solicita un retiro
    }
    public void solicitarDeposito(){
        // Solicita un deposito
    }
    public void solicitarTransferencia(){
        // Solicita una transferencia
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
    
    public Cliente(int dni, String nombre, double saldo, AgenteDeBolsa agente) {
        this.dni = dni;
        this.nombre = nombre;
        this.saldo = saldo;
        this.agente = agente;
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
}