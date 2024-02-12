package objetos;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Esta clase representa un departamento en una organización.
 * Contiene información sobre el número, nombre, localización y empleados del departamento.
 * También proporciona métodos para establecer y obtener esta información, así como para
 * solicitar datos al usuario.
 * 
 * @author Armando Xaixo
 */
public class Departamento {
    private int numero;
    private String nombre;
    private String localizacion;
    private ArrayList<Empleado> empleados = new ArrayList<Empleado>();

    /**
     * Constructor por defecto. Crea un departamento sin inicializar sus atributos.
     */
    public Departamento() {
        this.empleados = new ArrayList<Empleado>();
    }

    /**
     * Constructor que recibe el número, nombre y localización del departamento.
     * 
     * @param numero El número del departamento.
     * @param nombre El nombre del departamento.
     * @param localizacion La localización del departamento.
     */
    public Departamento(int numero, String nombre, String localizacion) {
        this.numero = numero;
        this.nombre = nombre;
        this.localizacion = localizacion;
    }

    /**
     * Constructor que recibe el número, nombre, localización y lista de empleados del departamento.
     * 
     * @param numero El número del departamento.
     * @param nombre El nombre del departamento.
     * @param localizacion La localización del departamento.
     * @param empleados La lista de empleados del departamento.
     */
    public Departamento(int numero, String nombre, String localizacion, ArrayList<Empleado> empleados) {
        this.numero = numero;
        this.nombre = nombre;
        this.localizacion = localizacion;
        this.empleados = empleados;
    }

    // Métodos getters y setters
    // ...

    /**
     * Devuelve una representación en cadena de caracteres del objeto Departamento.
     * 
     * @return Una cadena de caracteres que representa el departamento.
     */
    @Override
    public String toString() {
        return "Departamento [numero= " + this.numero + ", nombre= " + this.nombre + ", localizacion= "
                + this.localizacion + "]";
    }

    /**
     * Solicita al usuario que introduzca el nombre y la localización del departamento.
     * 
     * @throws InputMismatchException Si el usuario introduce un valor incorrecto.
     */
    public void pedirDatosV2() {
        boolean correcto = false;
        do {
            try {
                Scanner pk = new Scanner(System.in);
                System.out.println("Introduzca el nombre del departamento ");
                this.nombre = pk.nextLine();
                System.out.println("Introduzca la localizacion del departamento ");
                this.localizacion = pk.nextLine();
                correcto = true;
            } catch (InputMismatchException e) {
                System.out.println("Introduzca el valor correctamente");
            }
        } while (!correcto);
    }

    /**
     * Solicita al usuario que introduzca el número, nombre y localización del departamento.
     * 
     * @throws InputMismatchException Si el usuario introduce un valor incorrecto.
     */
    public void pedirDatos() {
        try {
            System.out.println("Introduzca el numero del departamento ");
            this.numero = new Scanner(System.in).nextInt();
            System.out.println("Introduzca el nombre del departamento ");
            this.nombre = new Scanner(System.in).nextLine();
            System.out.println("Introduzca la localizacion del departamento ");
            this.localizacion = new Scanner(System.in).nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Introduzca el valor correctamente");
        }

        System.out.println("Fin PedirDatos()");
    }
}
