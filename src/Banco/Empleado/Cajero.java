package Banco.Empleado;
import Banco.Cliente.Cliente;

import java.util.HashMap;

public class Cajero extends Empleado{
    private HashMap<Integer, Prestamo> prestamos;
    //meotodo atender cliente
   
    //constructor
    public Cajero(String nombre, String apellido, int legajo, double salario,
                  int nroTelefono, String email) {
        super(nombre,apellido,legajo,salario,nroTelefono,email);
        this.prestamos = new HashMap<>();
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

    public void realizarPrestamoCliente(Cliente cliente,double dineroPrestamo){
        //le depositamos el dinero solicitado a su cuenta
        cliente.setSaldo(cliente.getSaldo() + dineroPrestamo);
        prestamos.put(cliente.getDni(), new Prestamo(dineroPrestamo,cliente));
        return;
    }

    //getter y setter del diccionario prestamos
    public HashMap<Integer, Prestamo> getPrestamos() {
        return prestamos;
    }
    public void setPrestamos(HashMap<Integer, Prestamo> prestamos) {
        this.prestamos = prestamos;
    }
}
