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


    /**
     * Metodo solicitado por el cliente, para que el gerente le apruebe su transaccion no justificada
     * @param cliente cliente que desea meter plata sucia al banco
     * @param dineroSucio monto de plata sucia
     * @return true si se aprueba la trasacicon, sino false
     */
    public boolean solicitarTransaccionNoRastreable(Cliente cliente, double dineroSucio){
        //le paso el dinero al gerente para que lo agregue al diccionario
        if (gerente.aprobarTransaccionCliente(cliente, dineroSucio)) return true;
        return false;
    }


}
