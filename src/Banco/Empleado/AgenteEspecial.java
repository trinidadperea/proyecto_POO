package Banco.Empleado;

import Banco.Cliente.Cliente;
import Banco.Empleado.Empleado;

public class AgenteEspecial extends Empleado{
    private Gerente gerente;
    //constructor
    public AgenteEspecial(String nombre, String apellido, int legajo,
                            double salario, int nroTelefono, String email, Gerente gerente) {
                                
        super(nombre,apellido,legajo,salario,nroTelefono,email);
        this.gerente = gerente;
    }
    /* no realizamos transferencia aca 
    public boolean solicitarTransferenciaNoRastreable(Cliente cliente, double dineroSucio,Cliente clienteDestino){
        return true;
        //realizar metodo
    } */
    //si el cliente quiere realizar un deposito con plata sucia se encarga el cajero 
    public boolean solicitarTransaccionNoRastreable(Cliente cliente, double dineroSucio){
        //le paso el dinero al gerente para que lo agregue al diccionario
        if (gerente.aprobarTransaccionCliente(cliente, dineroSucio)) return true;
        return false;
    }


}
