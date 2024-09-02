package Banco;

import java.util.*;

import Banco.Cliente.Cliente;
import Banco.Cliente.Inversiones.Inversion;
import Banco.Cliente.Inversiones.InversionBolsa;
import Banco.Cliente.Inversiones.InversionCriptoMonedas;

public class AgenteDeBolsa {

    private Map<Cliente, List<Inversion>> inversionesPorCliente;
    private String nombre;
    private Scanner sc;

    public AgenteDeBolsa(String nombre) {
        this.nombre = nombre;
        this.inversionesPorCliente = new HashMap<>();
        this.sc = new Scanner(System.in);  // Iniciando el Scanner una vez, reutilizable en toda la clase
    }

    public void realizarInversion(int tipoInversion, double monto, Cliente cliente) {
        switch (tipoInversion) {
            case 1:
                // Fondo de inversión
                break;
            case 2:
                // Acciones
                Map<String, Double> companies = new HashMap<>();
                companies.put("Apple", 150.25);
                companies.put("Microsoft", 305.22);
                companies.put("Amazon", 130.55);
                companies.put("Google", 2800.78);
                companies.put("Facebook (Meta)", 220.45);
                companies.put("Tesla", 690.40);
                companies.put("Visa", 210.55);
                companies.put("Samsung", 80.30);

                // Mostrar las opciones al usuario
                System.out.println("Seleccione la empresa en la que desea invertir: ");
                int index = 0;
                String[] companyArray = companies.keySet().toArray(new String[0]);
                for (String company : companyArray) {
                    System.out.println(index + ". " + company + " - $" + companies.get(company));
                    index++;
                }

                int opcion = sc.nextInt();

                // Validar la opción seleccionada
                if (opcion >= 0 && opcion < companyArray.length) {
                    String selectedCompany = companyArray[opcion];
                    double stockValue = companies.get(selectedCompany);
                    float cantidadAcciones = (float) (monto / stockValue);

                    InversionBolsa inversion = new InversionBolsa(
                            selectedCompany,
                            cantidadAcciones,
                            stockValue,
                            stockValue,
                            monto,
                            cliente
                    );

                    // Agregar la inversión al cliente
                    agregarInversion(cliente, inversion);

                } else {
                    System.out.println("Opción no válida.");
                }
                break;

            case 3:
                // Propiedades
                break;
            case 4:
                // Criptomonedas
                Map<String, Double> criptos = new HashMap<>();
                criptos.put("Bitcoin", 26500.00); // Valores de ejemplo
                criptos.put("Ethereum", 1800.00);
                criptos.put("Ripple", 0.55);
                criptos.put("Litecoin", 90.00);

                // Mostrar las opciones al usuario
                System.out.println("Seleccione la criptomoneda en la que desea invertir: ");
                int index2 = 0;
                String[] criptosArray = criptos.keySet().toArray(new String[0]);
                for (String cripto : criptosArray) {
                    System.out.println(index2 + ". " + cripto + " - $" + criptos.get(cripto));
                    index2++;
                }

                int opcion2 = sc.nextInt();

                // Validar la opción seleccionada
                if (opcion2 >= 0 && opcion2 < criptosArray.length) {
                    String selectedCripto = criptosArray[opcion2];
                    double stockValueCripto = criptos.get(selectedCripto);
                    float cantidadCripto = (float) (monto / stockValueCripto);

                    InversionCriptoMonedas inversionCripto = new InversionCriptoMonedas(
                            selectedCripto,
                            cantidadCripto,
                            0.0,
                            stockValueCripto,
                            stockValueCripto,
                            monto,
                            cliente
                    );

                    // Agregar la inversión al cliente
                    agregarInversion(cliente, inversionCripto);
                } else {
                    System.out.println("Opción no válida.");
                }
                break;
            default:
                System.out.println("Tipo de inversión no válido.");
        }
    }

    public double venderActivo(Inversion inversion) {
        // Vende un activo y devuelve el monto de la venta
        return 0;
    }

    public String mostrarInversiones() {
        return "";
    }

    private void agregarInversion(Cliente cliente, Inversion inversion) {
        // Agrega la inversión al cliente en el diccionario `inversionesPorCliente`
        if (!inversionesPorCliente.containsKey(cliente)) {
            inversionesPorCliente.put(cliente, new ArrayList<>());
        }
        inversionesPorCliente.get(cliente).add(inversion);
    }
}
