package objetos;

import java.text.ParseException;
import java.util.Date; // Usamos el .util cuando estemos usando java
import java.util.InputMismatchException;
import java.util.Scanner;

import utilidades.Utilidades;


// Usamos el .sql cuando estemos en el paquete dao
//Constructor con todos los atributos suele ser recomendable
public class Empleado {
	private int numero;
	private String nombre;
	private String oficio;
	private int direccion;
	private Date fechaAlta;
	private int salario;
	private int comision;
	private Departamento departamento;

	public Empleado() {
	}
	
	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Empleado(int numero, String nombre, String oficio, int direccion, Date fechaAlta, int salario,
			int comision) {
		super();
		this.numero = numero;
		this.nombre = nombre;
		this.oficio = oficio;
		this.direccion = direccion;
		this.fechaAlta = fechaAlta;
		this.salario = salario;
		this.comision = comision;
	}
	
	public Empleado(int numero, String nombre, String oficio, int direccion, Date fechaAlta, int salario, int comision,
			Departamento departamento) {
		super();
		this.numero = numero;
		this.nombre = nombre;
		this.oficio = oficio;
		this.direccion = direccion;
		this.fechaAlta = fechaAlta;
		this.salario = salario;
		this.comision = comision;
		this.departamento = departamento;
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
	public String getOficio() {
		return oficio;
	}
	public void setOficio(String oficio) {
		this.oficio = oficio;
	}
	public int getDireccion() {
		return direccion;
	}
	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public int getSalario() {
		return salario;
	}
	public void setSalario(int salario) {
		this.salario = salario;
	}
	public int getComision() {
		return comision;
	}
	public void setComision(int comision) {
		this.comision = comision;
	}
	
	public void pedirDatos(){
		try{
		String fecha = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("Por favor introduzca el numero de empleado ");
		this.numero = sc.nextInt();
		Scanner pk = new Scanner(System.in);
		System.out.println("Introduzca el nombre del empleado ");
		this.nombre = pk.nextLine();
		System.out.println("Introduzca el oficio del empleado ");
		this.oficio = pk.nextLine();
		System.out.println("Introduzca el numero de la direccion del empleado ");
		this.direccion = sc.nextInt();
		System.out.println("Introduzca la fecha de ingreso del empleado ");
		System.out.println("Ej. 22/12/1990");
		fecha = pk.nextLine();
		this.fechaAlta = Utilidades.parsearFechaString(fecha);
		System.out.println("Introduzca el salario del empleado ");
		this.salario = sc.nextInt();
		System.out.println("Introduzca la comision ");
		this.comision = sc.nextInt();
		System.out.println("");	
		} catch(InputMismatchException e){
			System.out.println("Introduzca el valor correctamente");
			pedirDatos();
		}
		catch(ParseException ex){
			System.out.println("Fallo al convertir el valor fecha");
		}
		System.out.println("Fin PedirDatos(Empleado)");
	}


	
	
	
	
}
