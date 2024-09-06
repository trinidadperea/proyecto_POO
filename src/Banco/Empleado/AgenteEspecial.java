package Banco.Empleado;

public class AgenteEspecial extends Empleado{
    //constructor
    public AgenteEspecial(String nombre, String apellido, int legajo,
                            double salario, int nroTelefono, String email) {
        super(nombre,apellido,legajo,salario,nroTelefono,email);
    }
    //hace el pedido al gerente de las transacciones ilegales
    //hay que ver despeus como lo implemntamos
    public boolean solicitarTransferenciaNoRastreable(Gerente gerente, double dineroSucio){
        return true;
        //realizar metodo
    }
    public boolean solicitarTransaccionNoRastreable(Gerente gerente, double dineroSucio){
        //realizar metodo
        return true;
    }
    //meotodo atender cliente
    @Override
    public void atenderCliente(Cliente cliente){
        System.out.println("El cliente "+cliente.getNombre()+" es atendido por el agente "+get.nombre());
    }

}
