package Banco.Empleado;
import java.util.HashMap;
//import java.util.List;
import java.util.Map;
import java.util.Scanner;
import Banco.Cliente.Cliente;

public class AsesorDivisas extends Empleado{
    private Scanner sc;
    private HashMap<String,Double> divisas = new HashMap<>();


    public AsesorDivisas(String nombre, String apellido, int legajo, double salario, int nroTelefono, String email) {
        super(nombre, apellido, legajo, salario, nroTelefono, email);
        sc = new Scanner(System.in);
        divisas.put("Dolar", 1300.0);
        divisas.put("Euro", 1400.0);
        divisas.put("Real", 200.0);
        divisas.put("Peso Chileno", 0.1);
    }

    //metodos asesor de divisas
    public void consultaPrecios(){

        System.out.println("Precio compra");
        //muestro las monedas con sus valores al usuario
        
        int index = 1;
        String[] divisasArray = divisas.keySet().toArray(new String[0]);
        for (String moneda : divisasArray) {
            System.out.println(index + ". " + moneda + " - $" + divisas.get(moneda));
            index++;
        }
        return;
    }

    public void compraDivisas(double monto, Cliente cliente){
        System.out.println("Seleccione la moneda que desea comprar: ");
        consultaPrecios();
        int opcion = sc.nextInt();
        
        Map<String,Double> divisasCliente = cliente.getDivisasCompradas();

        switch (opcion) {
            case 1: // Dolar
                double montoCambiadoDolar = monto / divisas.get("Dolar");
                montoCambiadoDolar = (long) (montoCambiadoDolar * 100) / 100.0;
                if (divisasCliente.containsKey("Dolar")) {
                    montoCambiadoDolar += divisasCliente.get("Dolar");
                    divisasCliente.remove("Dolar");
                }
                divisasCliente.put("Dolar", montoCambiadoDolar);
                cliente.setDivisasCompradas(divisasCliente);
                break;
        
            case 2: // Euro
                double montoCambiadoEuro = monto / divisas.get("Euro");
                montoCambiadoEuro = (long) (montoCambiadoEuro * 100) / 100.0;
                if (divisasCliente.containsKey("Euro")) {
                    montoCambiadoEuro += divisasCliente.get("Euro");
                    divisasCliente.remove("Euro");
                }
                divisasCliente.put("Euro", montoCambiadoEuro);
                cliente.setDivisasCompradas(divisasCliente);
                break;
        
            case 3: // Real
                double montoCambiadoReal = monto / divisas.get("Real");
                montoCambiadoReal = (long) (montoCambiadoReal * 100) / 100.0;
                if (divisasCliente.containsKey("Real")) {
                    montoCambiadoReal += divisasCliente.get("Real");
                    divisasCliente.remove("Real");
                }
                divisasCliente.put("Real", montoCambiadoReal);
                cliente.setDivisasCompradas(divisasCliente);
                break;
        
            case 4: // Peso Chileno
                double montoCambiadoPesoChileno = monto / divisas.get("Peso Chileno");
                montoCambiadoPesoChileno = (long) (montoCambiadoPesoChileno * 100) / 100.0;
                if (divisasCliente.containsKey("Peso Chileno")) {
                    montoCambiadoPesoChileno += divisasCliente.get("Peso Chileno");
                    divisasCliente.remove("Peso Chileno");
                }
                divisasCliente.put("Peso Chileno", montoCambiadoPesoChileno);
                cliente.setDivisasCompradas(divisasCliente);
                break;
        
            default:
                System.out.println("Opción no válida.");
                break;
        }
        
    }

    public double calcularMontoDestino(String monedaDestino, double valorEnPesos){
        double montoDestino = valorEnPesos / divisas.get(monedaDestino);
        return montoDestino;
    }
    
    public boolean realizarCambio(double monedaCambio, double montoCambiar){    
        return true;
    }

    public void venderDivisas(String monedaVender, double montoVender, Cliente cliente){
        Map<String,Double> divisasCliente = cliente.getDivisasCompradas();
        if (divisasCliente.containsKey(monedaVender) && divisasCliente.get(monedaVender) >= montoVender){
            double montoPesos = montoVender * divisas.get(monedaVender);
            cliente.setSaldo(cliente.getSaldo() + montoPesos);
            if (divisasCliente.get(monedaVender) == montoVender) {
                divisasCliente.remove(monedaVender);
            }else{
                double montoFinal = divisasCliente.get(monedaVender) - montoVender;
                montoFinal = (long) (montoFinal * 100) / 100.0;
                divisasCliente.put(monedaVender, montoFinal);
            }
            
            cliente.setDivisasCompradas(divisasCliente);
        } else {
            System.out.println("No posee suficiente cantidad de la divisa para realizar la venta.");
        }
    }
    
}
