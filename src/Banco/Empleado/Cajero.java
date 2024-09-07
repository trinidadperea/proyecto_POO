package Banco.Empleado;
import Banco.Cliente.Cliente;
public class Cajero extends Empleado{
    
    //meotodo atender cliente
   
    //constructor
    public Cajero(String nombre, String apellido, int legajo, double salario,
                  int nroTelefono, String email) {
        super(nombre,apellido,legajo,salario,nroTelefono,email);
    }

    //metodos que realiza el cajero
    
    public boolean realizarRetiro(double dineroRetirar, Cliente cliente){
        if (dineroRetirar > cliente.getSaldo()){
            System.out.println("Saldo insuficiente");
            return false;
        } else {
            //le resto el dinero a retirar
            cliente.setSaldo(cliente.getSaldo() - dineroRetirar);
            return true;
        }
    }

    public boolean realizarTransferencia(double dineroTransferir,Cliente cliente1, Cliente cliente2){
        //transferencia de cliente 1 a cliente 2
        //primero verifico si el cliente tiene esa plata para transferir
        if (cliente1.getSaldo() < dineroTransferir){
            System.out.println("Sr/Sra "+cliente1.getNombre()+" "+cliente1.getApellido()+" no puede transferir ese monto");
            return false;
        } else{
            //le aumento ese dinero al cliente 2
            cliente2.setSaldo(cliente2.getSaldo() + dineroTransferir);
            //le resto al cliente 1
            cliente1.setSaldo(cliente1.getSaldo() - dineroTransferir);
            return true;
        }
    }
    
    public boolean realizarDeposito(double dineroDepositar, Cliente cliente){
        //le sumo el deposito al saldo del cliente
        cliente.setSaldo(cliente.getSaldo() + dineroDepositar);
        return true;
    }
    public boolean realizarPrestamoCliente(Cliente cliente,double dineroPrestamo){
        //le depositamos el dinero solicitado a su cuenta
        cliente.setSaldo(cliente.getSaldo() + dineroPrestamo);
        //valor de las cuotas a pagar 
        int cuotas = 12;
        double interesAnual = 0.6;
        //valor de las cuotas a pagar segun la fórmula de amortización para pagos iguales
        double precioCuotas;
        double tasaMensual = (interesAnual)/cuotas;
        double factorPotencia = Math.pow(1 + tasaMensual,cuotas);
        precioCuotas = (dineroPrestamo * tasaMensual * factorPotencia)/(factorPotencia - 1);

        //le sumo el prestamo al saldo del cliente
        cliente.setSaldo(cliente.getSaldo() + dineroPrestamo);
        System.out.println( "El prestamo se ha estructurado en "+cuotas+" cuotas con un valor de $"+precioCuotas+" mensuales");
        return true;
    }
      
}
