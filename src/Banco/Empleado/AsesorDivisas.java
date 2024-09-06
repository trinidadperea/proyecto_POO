package Banco.Empleado;
import java.util.HashMap;
//import java.util.List;
import java.util.Map;
import java.util.Scanner;
import Banco.Cliente.Cliente;

public class AsesorDivisas extends Empleado{
    private Scanner sc;

    //meotodo atender cliente
    @Override
    public void atenderCliente(Cliente cliente){
        System.out.println("El cliente "+cliente.getNombre()+" es atendido por el Asesor de divisas "+get.nombre());
    }

    //metodos asesor de divisas
    public void valorDivisasCompra(Cliente cliente){
        //instancio el diccionario con los valores de las monedas para comprarlas
        Map<String,Double> divisasComprar = new HashMap<>();
        divisasComprar.put("Dolar",1340.0);
        divisasComprar.put("Euro",1580.0);
        divisasComprar.put("Real",255.0);
        divisasComprar.put("Chile",1.53);

        Map<String,Double> monedas = new HashMap<>(divisasComprar);
        int index = 0;
        System.out.println("Precio compra");
        String[] monedasArray = monedas.keySet().toArray(new String[0]);
        for (String moneda : monedasArray){
            System.out.println(index + 1 + ". "+ moneda + " = $" + monedas.get(moneda));
            index++;
        }
        
        String comprar; 
        System.out.println("Desea comprar? (S/N)");
        comprar = sc.nextLine().trim().toUpperCase();


        if (comprar.equals("S")){
            System.out.println("Coloque el numero de la divisa a comprar");
            int nroMoneda = sc.nextInt() - 1;
            sc.nextLine();
            //verifico que sea correcto
            if (nroMoneda >= 0 && nroMoneda < monedasArray.length){
                System.out.println("Coloque en pesos cuanto desea comprar");
                double valorComprar = sc.nextDouble();
                sc.nextLine();

                String monedaElegida = monedasArray[nroMoneda];
                //le agrego al cliente la moneda
                double valorMoneda = monedas.get(monedaElegida);
                double precioFinal = valorComprar / valorMoneda ;
                //divisas.put(monedaElegida , valorMoneda);
                System.out.println("La compra fue realizada con exito: "+precioFinal);
                cliente.registrarDivisas(monedaElegida, precioFinal);

            } else {
                System.out.println("Numero no valido");
            }
        }

    }

    public void valorDivisasVenta(Cliente cliente){
        Map<String,Double> divisasVenta = new HashMap<>();
        divisasVenta.put("Dolar",1300.0);
        divisasVenta.put("Euro",1460.0);
        divisasVenta.put("Real",225.0);
        divisasVenta.put("Chile",1.43);
        //antes de vender debo verificar si tiene
    }

    public double calcularMontoDestino(String monedaDestino, double valorEnPesos){
        //metodo que me duevuelve el cambio de x pesos en la moneda solicitada
        return 0;
    }
    public boolean realizarCambio(String monedaCambio, double montoCambiar){
        return true;
    }

    public AsesorDivisas(String nombre, String apellido, int legajo, double salario, int nroTelefono, String email, Scanner sc) {
        super(nombre, apellido, legajo, salario, nroTelefono, email);
        this.sc = sc;
    }
}
