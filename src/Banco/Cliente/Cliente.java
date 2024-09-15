package Banco.Cliente;

import Banco.AgenteDeBolsa;
import Banco.Empleado.AsesorDivisas;
import Banco.Empleado.*;
import java.util.*;

public class Cliente implements CompraVentaActivos, CompraVentaDivisas{
    private int dni;
    private String nombre;
    private String apellido;
    private double saldo;
    private Map<String,Double> divisasCompradas;
    private Map<Cliente,Double> dineroNoRegistrado;
    private Empleado empleado;
    private AgenteDeBolsa agentedeBolsa;

    public Cliente(int dni, String nombre,String apellido, double saldo, AgenteDeBolsa agenteDeBolsa) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.saldo = saldo;
        this.divisasCompradas = new HashMap<>();
        this.agentedeBolsa = agenteDeBolsa;
        this.empleado = null;
        this.dineroNoRegistrado = new HashMap<>();
    }
    public Map<Cliente,Double> getDineroNoRegistrado(){
        return dineroNoRegistrado;
    }
    public void setDineroNoRegistrado(Map<Cliente,Double> dineroNoRegistrado){
        this.dineroNoRegistrado = dineroNoRegistrado;
    }

    //metodos que se encarga el agente de bolsa
    public void comprarActivo(double monto, int tipoInversion){

        if (this.saldo >= monto) {
            agentedeBolsa.realizarInversion(tipoInversion, monto, this);
            this.saldo -= monto;
        } else {
            System.out.println("Saldo insuficiente para realizar la inversión.");
        }
    }

    public void venderActivo(int tipoInversion){
        this.saldo += agentedeBolsa.venderActivo(tipoInversion,this);
        System.out.println("El saldo actual es: " + this.saldo);
        return;
    }

    public void consultarActivos(){
        System.out.println(agentedeBolsa.mostrarInversiones(this));
        return;
    }

    public void consultarPrecios(){
        System.out.println(agentedeBolsa.consultaPrecios());
        return;
    }

    public void consultarGananciasPlazoFijo(){
        System.out.println("Usted ha generado: "+agentedeBolsa.consultarRendimientosPlazoFijo(this));
        return;
    }

    //metodos que se encarga el cajero
    public void solicitarTransferencia(double dineroTransferir, Cliente clienteDestino, Empleado empleado){    // Solicita una transferencia
        this.empleado = empleado;
        if (empleado instanceof Cajero && !clienteDestino.equals(this)){
            boolean solicitud = ((Cajero) empleado).realizarTransferencia(dineroTransferir, this, clienteDestino);
            if (solicitud){
                System.out.println("La transferencia se solicito con exito a "+clienteDestino.getNombre()+" "+clienteDestino.getApellido());
            } else{
                System.out.println("Transferencia cancelada");
            }
        }
    }

    public void solicitarRetiro(double dineroRetirar, Empleado empleado){   
        this.empleado = empleado;
        if (empleado instanceof Cajero){
            boolean solicitud = ((Cajero) empleado).realizarRetiro(dineroRetirar,this);
            if (solicitud){
                System.out.println("Retiro realizado con exito");
            }
        }
    }
    
    public void solicitarDeposito(double dineroDepositar, Empleado empleado){    
        this.empleado = empleado;
        //no justifica llamo al asesor especial
        if (empleado instanceof AgenteEspecial){
            boolean solicitud = ((AgenteEspecial) empleado).solicitarTransaccionNoRastreable(this, dineroDepositar);
            if (solicitud){
                System.out.println("Dinero depositado con exito");
            }
        } else if (empleado instanceof Cajero){
            boolean solicitud = ((Cajero) empleado).realizarDeposito(dineroDepositar, this);
            if (solicitud){
                System.out.println("El dinero se deposito con exito");
            } else{
                System.out.println("El deposito no pudo realizarse"); //no deberia pasar
            }
        }
    }
    
    public void solicitarPrestamo(double dineroPrestamo, Empleado empleado1, Empleado empleado2){
        //empleado1 es el gerente y empleado2 es el cajero
        this.empleado = empleado1;
        if (empleado instanceof Gerente){
            boolean solicitud =((Gerente) empleado).aprobarPrestamo(this, dineroPrestamo, empleado2);
            if (solicitud){
                //si el gerente lo aprueba lo realiza el cajero
                this.empleado = empleado2;
                ((Cajero) empleado).realizarPrestamoCliente(this, dineroPrestamo);
                System.out.println("Prestamo realizado");
            } else {
                System.out.println("Prestamo rechazado");
            }
        }
    }

    //metodos que se encarga el asesor de divisas
    public void venderDivisas(String monedaVender, double montoVender, Empleado empleado){
        this.empleado = empleado;
        if (empleado instanceof AsesorDivisas){
            if (divisasCompradas.containsKey(monedaVender) && divisasCompradas.get(monedaVender) >= montoVender){
                ((AsesorDivisas) empleado).venderDivisas(monedaVender, montoVender, this);
                this.saldo += montoVender;
            } else {
                System.out.println("No posee suficiente cantidad de la divisa para realizar la venta.");
            }
        }else{
            System.out.println("Este empleado no puede realizar esta operación");
        }
    }

    public void comprarDivisas(double montoComprar, Empleado empleado){
        this.empleado = empleado;
        if (empleado instanceof AsesorDivisas){
            if (this.saldo >= montoComprar) {
                ((AsesorDivisas) empleado).compraDivisas(montoComprar,this);
                this.saldo -= montoComprar;
            } else {
                System.out.println("Saldo insuficiente para realizar la conversión.");
            }
        }else{
            System.out.println("Este empleado no puede realizar esta operación");
        }
        
    }
 
    public void solicitarPrecioDivisas(Empleado empleado){
        this.empleado = empleado;
        if (empleado instanceof AsesorDivisas){
            ((AsesorDivisas) empleado).consultaPrecios();
        }else{
            System.out.println("Este empleado no puede realizar esta operación");
        }
    }

    public void simularCompra(double monto, String monedaDestino, Empleado empleado){
        if (empleado instanceof AsesorDivisas){
            System.out.println(((AsesorDivisas) empleado).calcularMontoDestino(monedaDestino,monto));
        }else{
            System.out.println("Este empleado no puede realizar esta operación");
        }
    }

    public void mostrarDivisasCompradas(){
        System.out.println("Divisas compradas de la/el Sr/Sra: "+ this.getNombre());
        System.out.println(" ");
        for (Map.Entry<String, Double> entry : divisasCompradas.entrySet()) {
            System.out.println("Divisa: " + entry.getKey() + " - Valor: $" + entry.getValue());
        }
    }

    //metodos del cliente

    public void pagarCuotaPrestamo(){
        // Paga una cuota de un prestamo
    }
    public void cancelarPrestamo(){
        // Cancela un prestamo
    }
    public double mostrarSaldo(){
        return saldo;
    }
    public void registrarDivisas(String nombreDivisa, double valor){
        divisasCompradas.put(nombreDivisa, divisasCompradas.getOrDefault(nombreDivisa, 0.0) + valor);
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

    public Map<String, Double> getDivisasCompradas() {
        return divisasCompradas;
    }

    public void setDivisasCompradas(Map<String, Double> divisasCompradas) {
        this.divisasCompradas = divisasCompradas;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public AgenteDeBolsa getAgentedeBolsa() {
        return agentedeBolsa;
    }

    public void setAgentedeBolsa(AgenteDeBolsa agentedeBolsa) {
        this.agentedeBolsa = agentedeBolsa;
    }

    @Override
    public String toString(){
        return "Cliente: "+nombre+" "+apellido+"\nDNI: "+dni+"\nSaldo: "+saldo;
    }

}