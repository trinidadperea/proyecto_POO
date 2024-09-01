package Banco.Empleado.Transaccion;
import Banco.Empleado.Empleado;
import Banco.Cliente.Cliente;

public class TransaccionCliente extends Transaccion{
    private Cliente cliente;

    //constructor
    public TransaccionCliente(Cliente cliente,double monto, String tipoTransaccion,
                              Empleado empleado){
        super(monto,tipoTransaccion,empleado);
        this.cliente = cliente;
    }
    //getter y setter cliente

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    //metodos de transacciones
    public boolean realizarDeposito(double dineroDeposito){
        //realizar metodo
        return true;
    }
    public boolean realizarRetiro(double dineroRetirar){
        //realizar metodo
        return true;
    }
    public boolean realizarTransferencia(double dineroTransferir){
        //realizar metodo
        return true;
    }
    public boolean solicitarPrestamo(double dineroPrestamo){
        //realizar metodo
        return true;
    }
}