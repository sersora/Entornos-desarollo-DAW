package objetos;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Departamento {
private int numero;
private String nombre;
private String localizacion;
private ArrayList <Empleado> empleados= new ArrayList <Empleado>();


	public Departamento(){ //Se utiliza para pedir datos 1 a 1
		this.empleados = new ArrayList <Empleado>();
	}
	public Departamento(int numero, String nombre, String localizacion){ // Pedir datos 1 a 1
		this.numero = numero;
		this.nombre = nombre;
		this.localizacion = localizacion;
	}	
	public Departamento(int numero, String nombre, String localizacion, ArrayList<Empleado> empleados){
		this.numero = numero;
		this.nombre = nombre;
		this.localizacion = localizacion;
		this.empleados = empleados;
	}

	
public int getNumero() {
	return numero;
}
public void setNumero(int numero) {
	this.numero = numero;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getLocalizacion() {
	return localizacion;
}
public void setLocalizacion(String localizacion) {
	this.localizacion = localizacion;
}
public ArrayList<Empleado> getEmpleados() {
	return empleados;
}
public void setEmpleados(ArrayList<Empleado> empleados) {
	this.empleados = empleados;
}


@Override
public String toString(){
	return "Departamento [numero= " + this.numero + ", nombre= " +
			this.nombre + ", localizacion= " +this.localizacion + "]";
}

public void pedirDatosV2(){
	boolean correcto=false;
	do{
		try{
			Scanner pk = new Scanner(System.in);
			System.out.println("Introduzca el nombre del departamento ");
			this.nombre = pk.nextLine();
			System.out.println("Introduzca la localizacion del departamento ");
			this.localizacion = pk.nextLine();
			correcto=true;
		} catch(InputMismatchException e){
			System.out.println("Introduzca el valor correctamente");
		}
	}while (!correcto);

}

public void pedirDatos(){
	try{
	System.out.println("Introduzca el numero del departamento ");
	this.numero = new Scanner(System.in).nextInt();
	System.out.println("Introduzca el nombre del departamento ");
	this.nombre = new Scanner(System.in).nextLine();
	System.out.println("Introduzca la localizacion del departamento ");
	this.localizacion = new Scanner(System.in).nextLine();
	} catch(InputMismatchException e){
		System.out.println("Introduzca el valor correctamente");
		}
		
	System.out.println("Fin PedirDatos()");
}

}
