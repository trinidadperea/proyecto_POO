package Banco.Cliente;

public class Cliente implements CompraVentaActivos{
    int dni;
    String nombre;
    double saldo;
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
    public Cliente(int dni, String nombre, double saldo){
        this.dni = dni;
        this.nombre = nombre;
        this.saldo = saldo;
    }

    public void comprarActivo(){
        // Compra un activo
    }
    public void venderActivo(){
        // Vende un activo
    }
    public void consultarActivos(){
        // Consulta los activos
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