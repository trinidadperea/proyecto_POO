package Banco.Cliente;

import Banco.Cliente.Inversiones.*;
import Banco.AgenteDeBolsa;
import Banco.Empleado.*;
import java.util.*;

public class Cliente {
    private int dni;
    private String nombre;
    private String apellido;
    private double saldo;
    private Map<String,Double> divisasCompradas;
    private Empleado empleado;
    private AgenteDeBolsa agentedeBolsa;

    //metodos que se encarga el agente de bolsa
    public void comprarActivo(double monto){
        System.out.println("Seleccione el tipo de inversion que desea realizar: ");
        System.out.println("1. Fondo de inversion");
        System.out.println("2. Acciones");
        System.out.println("3. Propiedades");
        System.out.println("4. Criptomonedas");
        Scanner sc = new Scanner(System.in);
        int tipoInversion = sc.nextInt();

        if (this.saldo >= monto) {
            agentedeBolsa.realizarInversion(tipoInversion, monto, this);
            this.saldo -= monto;
        } else {
            System.out.println("Saldo insuficiente para realizar la inversión.");
        }
        sc.close();
    }

    public void venderActivo(Inversion inversion){
        this.saldo += agentedeBolsa.venderActivo(inversion);
        System.out.println("El saldo actual es: " + this.saldo);
        return;
    }

    public void consultarActivos(){
        System.out.println(agentedeBolsa.mostrarInversiones());
        return;
    }
    //metodos que se encarga el cajero
    public void solicitarTransferencia(double dineroTransferir, Cliente clienteDestino){
        if (empleado instanceof Cajero){
            boolean solicitud = ((Cajero) empleado).realizarTransferencia(dineroTransferir, this, clienteDestino);
            if (solicitud){
                System.out.println("La transferencia se solicito con exito ");
            } else{
                System.out.println("Transferencia cancelada");
            }
        }
    }

    public void solicitarRetiro(double dineroRetirar){    // Solicita un retiro
        if (empleado instanceof Cajero){
            boolean solicitud = ((Cajero) empleado).realizarRetiro(dineroRetirar,this);
            if (solicitud){
                System.out.println("Retiro realizado con exito");
            } else{
                System.out.println("El retiro no se pudo realizar");
            }
        }
    }
    
    public void solicitarDeposito(double dineroDepositar){
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
    
    public void solicitarPrestamo(Cliente cliente, double dineroPrestamo){
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
    public void venderDivisas(String monedaVender, double montoVender){
        //le vendo al banco las monedas y le sumo el precio en pesos al saldo
    }
 
    public void consultarPrecioCompraDivisas(){
        //llamo a la clase AsesorDivisas, que ahi es donde se actualizan
        //me muestra los precios y si quiero comprar
        ((AsesorDivisas) empleado).valorDivisasCompra(this);
    }

    public void consultarPrecioVentaDivisas(){
        ((AsesorDivisas) empleado).valorDivisasVenta(this);

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
    public void registrarDivisas(String nombreDivisa, double valor){
        divisasCompradas.put(nombreDivisa, divisasCompradas.getOrDefault(nombreDivisa, 0.0) + valor);
    }

    public void mostrarDivisasCompradas(){
        System.out.println("Divisas compradas de la/el Sr/Sra: "+ this.getNombre());
        System.out.println(" ");
        for (Map.Entry<String, Double> entry : divisasCompradas.entrySet()) {
            System.out.println("Divisa: " + entry.getKey() + " - Valor: $" + entry.getValue());
        }
    }

    public Cliente(int dni, String nombre,String apellido, double saldo, Map<String,Double> divisasCompradas, AgenteDeBolsa agenteDeBolsa, Empleado empleado) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.saldo = saldo;
        this.empleado = empleado;
        this.divisasCompradas = new HashMap<>();
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

    @Override
    public String toString(){
        return "Cliente: "+nombre+" "+apellido+"\nDNI: "+dni+"\nSaldo: "+saldo;
    }

}