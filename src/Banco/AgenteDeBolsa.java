package Banco;

import java.util.List;
import java.util.Map;

import Banco.Cliente.Cliente;
import Banco.Cliente.Inversiones.Inversion;

public class AgenteDeBolsa {
    private List<Cliente> clientes;
    private Map<Cliente, List<Inversion>> inversionesPorCliente;

    String nombre;

    //Manejara el tema de las inversiones y sus respectivos clientes mediante diccionarios combinados con listas, pudiendo asi agregar y eliminar facilmente las distintas inversiones, aun no visto en clase
    
    public void realizarInversion(Inversion inversion, double monto){
        // Realiza una inversion
    }
    public double venderActivo(Inversion inversion){
        // Vende un activo y devuelve el monto de la venta
        return 0;
    }
    public String mostrarInversiones(){
        //devuelve las inversiones realizadas por el cliente de la forma "Inversion1: monto; Inversion2: monto; ..."
        return "";
    }
}
