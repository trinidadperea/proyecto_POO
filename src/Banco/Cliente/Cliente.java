package Banco.Cliente;

import Banco.AgenteDeBolsa;
import Banco.Empleado.*;
import java.util.*;

public class Cliente implements CompraVentaActivos, CompraVentaDivisas{
    private int dni;
    private String nombre;
    private String apellido;
    private double saldo;
    private Map<String,Double> divisasCompradas;
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
    }

    //metodos que se encarga el agente de bolsa
    public void comprarActivo(double monto){
        System.out.println("Seleccione el tipo de inversion que desea realizar: ");
        System.out.println("1. Plazo Fijo");
        System.out.println("2. Acciones");
        System.out.println("3. Criptomonedas");
        Scanner sc = new Scanner(System.in);
        int tipoInversion = sc.nextInt();

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

    //metodos que se encarga el cajero
    public void solicitarTransferencia(double dineroTransferir, Cliente clienteDestino, Empleado empleado){    // Solicita una transferencia
        this.empleado = empleado;
        if (empleado instanceof Cajero){
            boolean solicitud = ((Cajero) empleado).realizarTransferencia(dineroTransferir, this, clienteDestino);
            if (solicitud){
                System.out.println("La transferencia se solicito con exito ");
            } else{
                System.out.println("Transferencia cancelada");
            }
        }
    }

    public void solicitarRetiro(double dineroRetirar, Empleado empleado){    // Solicita un retiro
        this.empleado = empleado;
        if (empleado instanceof Cajero){
            boolean solicitud = ((Cajero) empleado).realizarRetiro(dineroRetirar,this);
            if (solicitud){
                System.out.println("Retiro realizado con exito");
            } else{
                System.out.println("El retiro no se pudo realizar");
            }
        }
    }
    
    public void solicitarDeposito(double dineroDepositar, Empleado empleado){    // Solicita un deposito
        this.empleado = empleado;
        if (empleado instanceof Cajero){
            boolean solicitud = ((Cajero) empleado).realizarDeposito(dineroDepositar, this);
            if (solicitud){
                System.out.println("El dinero se deposito con exito");
            } else{
                //deberiamos ver cuando no se puede depositar, si hay algun tope max
                //si quiere depositar mas de x plata, deberia decir de donde la saca, sino es lavado de dinero
                System.out.println("El deposito no pudo realizarse");
                System.out.println("Mostrar de donde viene la plata");
            }
        }
    }
    
    public void solicitarPrestamo(Cliente cliente, double dineroPrestamo, Empleado empleado){
        this.empleado = empleado;
        // Solicita permiso de un prestamo al gerente
        String deuda; 
        Scanner sc = new Scanner(System.in);
        do { //si no tiene deudas se le realizara el prestamo
        System.out.println("Sr/Sra.  "+cliente.getNombre()+" tiene usted alguna deuda? (S/N)");
        deuda = sc.nextLine().trim().toUpperCase(); //paso a mayuscula
        } while (!deuda.equals("N") && !deuda.equals("S"));
        if (empleado instanceof Cajero){
            boolean solicitud =((Cajero) empleado).realizarPrestamoCliente(cliente, dineroPrestamo); 
            if (solicitud){
                //si el gerente lo aprueba lo realiza el cajero
                System.out.println("Hecho");
                //empleado.realizarPrestamoCliente(this, dineroPrestamo);
            } else {
                System.out.println("Prestamo cancelado");
            }
        }
        sc.close();
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
    @Override
    public String toString(){
        return "Cliente: "+nombre+" "+apellido+"\nDNI: "+dni+"\nSaldo: "+saldo;
    }

}