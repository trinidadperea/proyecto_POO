package Banco.Empleado;
//import java.util.List;
import java.util.*;

import Banco.Cliente.Cliente;


public class AsesorDivisas extends Empleado{
    private Scanner sc;
    private Map<String,Double> divisas = new HashMap<>();
    public AsesorDivisas(String nombre, String apellido, int legajo, double salario, int nroTelefono, String email) {
        super(nombre, apellido, legajo, salario, nroTelefono, email);
        sc = new Scanner(System.in);
        divisas.put("Dolar", 1300.0);
        divisas.put("Real", 200.0);
        divisas.put("Euro", 1400.0);
        divisas.put("Peso Chileno", 0.1);
    }

    //metodos asesor de divisas

    /**
     * Metodo que muestra el valor de las divisas
     */
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
    /**
     * Metodo que realiza la compra de las divisas
     * @param monto monto que desea comprar la divisa elegida
     * @param cliente cliente que realiza la compra
     * @return
     */
    public void compraDivisas(double monto, Cliente cliente){
        System.out.println("Seleccione la moneda que desea comprar: ");
        consultaPrecios();

        int opcion = verificar(4); // Inicializar como valor inválido
        
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

    /**
     * Metodo que realiza la compra de divisas don una funcion aleatoria, es utilizada en la simulacion
     * @param monto monto aleatorio generado para comprar una divisa
     * @param cliente cliente aleatorio que realiza la compra
     * @return
     */
    public void compraDivisasRandom(double monto, Cliente cliente){
        Random random = new Random();
        System.out.println("Seleccione la moneda que desea comprar: ");
        consultaPrecios();

        int opcion = random.nextInt(1,4); 
        
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

    /**
     * Metodo que me calcula el monto en pesos de una divisa
     * @param monedaDestino moneda que se va a pedir el monto
     * @param valorEnPesos monto en cuanto quedaria en pesos
     * @return
     */
    public double calcularMontoDestino(String monedaDestino, double valorEnPesos){
        double montoDestino = valorEnPesos / divisas.get(monedaDestino);
        return montoDestino;
    }
    
    /**
     * Metodo que actualiza el valor de las divisas
     * @return
     */
    public void actualizarDivisas(){
        for (Map.Entry<String, Double> entry : divisas.entrySet()) {
            double randomValue = 0.95 + (Math.random() * (1.05 - 0.95));

            double nuevoValor = entry.getValue() * randomValue;
            nuevoValor = (long) (nuevoValor * 100) / 100.0;

            entry.setValue(nuevoValor);
        }
    }

    /**
     * Metodo que si el cliente tiene alguna divisa previamente comprada, puede venderlas
     * @param monedaVender moneda que desea vender el cliente
     * @param monto cantidad que desea vender
     * @param cliente cliente que realiza la operacion
     * 
     */
    public void venderDivisas(String monedaVender, double monto, Cliente cliente){
        Map<String,Double> divisasCliente = cliente.getDivisasCompradas();
        double montoVender = monto / divisas.get(monedaVender);
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
    
    /**
     * Metodo que verifica que la entrada pedida en diferentes metodos, sea la entrada esperada
     * @param valor valor maximo que puede tomar la vriable
     */
    public int verificar(int valor){
        Scanner sc = new Scanner(System.in);
        int opcion = -1;
        while (true){
            try {
                opcion = sc.nextInt();
                if (opcion < 1 || opcion > valor) {
                    System.out.println("Opción no válida");
                }  else {
                    return opcion;
                }
            } catch (Exception e) {
                System.out.println("Ingrese un número entero.");
                sc.next();
            }
        }
    }
}
