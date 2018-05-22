package principal;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa

import daos.ConexionDao;
import daos.DepartamentoDao;
import daos.EmpleadoDao;
import objetos.Departamento;
import objetos.Empleado;
import utilidades.Utilidades;

public class Gestor {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConexionDao con= new ConexionDao();
		DepartamentoDao.setConexion(con.conexion);
		EmpleadoDao.setConexion(con.conexion);
		ArrayList<Departamento> aDepartamentos= DepartamentoDao.consultaDepartamentos();
		ArrayList <Empleado> aEmpleados = EmpleadoDao.consultarEmpleados();
		System.out.println("Bienvenido al gestor de la base de datos");
		boolean correcto = false;
		boolean salir = false;
		do{
			System.out.println("0.Salir ");
			System.out.println("1.Gestor de Empleados ");
			System.out.println("2.Gestor de Departamentos ");
			System.out.println("Elija una opcion");
			try{
				Scanner sc = new Scanner(System.in);
				int opcion = sc.nextInt();
				switch(opcion){
				case 0: System.out.println("Adios");
				salir = true;
				break;
				case 1: 
					gestorEmpleados(aEmpleados);
					break;
				case 2:
					gestorDepartamentos(aDepartamentos);
					break;
				default:System.out.println("Opcion incorrecta");
				break;
				}
			}catch(InputMismatchException e){
				System.out.println("Introduzca un numero");
			}
		}	while(!salir);
	}

	//Emple section INC
	public static void gestorEmpleados(ArrayList <Empleado> aEmpleados){
		Utilidades.clear();
		boolean atras = false;
		do{
			System.out.println("Bienvenido al gestor de empleados");
			System.out.println("0. Atras");
			System.out.println("1. Introducir empleados ");
			System.out.println("2. Borrar empleados");
			System.out.println("3. Mostrar empleados");
			System.out.println("4. Modificar empleados");
			System.out.println("5. Consulta empleado por codigo");
			try{
				Scanner sc = new Scanner(System.in);
				int opcion = sc.nextInt();
				switch(opcion){
				case 0: 
					atras = true;
					break;
				case 1: 
					introducirEmpleados(aEmpleados);
					break;
				case 2:
					borrarEmpleados(aEmpleados);
					break;
				case 3: 
					mostrarEmpleados(aEmpleados);
					break;
				case 4: modificarEmpleados(aEmpleados);
				break;
				case 5: consultaEmpleadoPorCodigo();
				break;
				default:System.out.println("Opcion incorrecta");
				break;
				}
			}catch(InputMismatchException e){
				System.out.println("Introduzca un numero");
			}
		}	while(!atras);
	}

	private static void consultaEmpleadoPorCodigo(){
		boolean correcto=false;
		int codigo=0;
		do{
			System.out.println("¿Qué empleado quiere consultar?");
			Scanner sc= new Scanner(System.in);
			try{
				codigo=sc.nextInt();
				correcto=true;
			}catch(InputMismatchException e){
				System.out.println("formato de codigo incorrecto");
			}	
		}while(!correcto);
		Empleado emple=EmpleadoDao.consultaEmpleadoPorNumero(codigo);
		if(emple!=null){
			System.out.println("DATOS DEL EMPLEADO: ");
			System.out.println(emple);
			System.out.println("DATOS DE SU DEPARTAMENTO: ");
			System.out.println(emple.getDepartamento());
		}else{
			System.out.println("El empleado no existe.");
		}

	}















	private static void introducirEmpleados(ArrayList <Empleado> aEmpleados){
		try{
			int numEmple = 0;
			System.out.println("Cuantos empleados desea introducir? ");
			Scanner sc = new Scanner(System.in);
			numEmple = sc.nextInt();
			for(int i = 0; i < numEmple; i++){
				Empleado e = new Empleado();
				System.out.println("-- Empleado numero " + (i+1) +" --");
				e.pedirDatos();
				aEmpleados.add(e);
				EmpleadoDao.insertar(e);
			}
		}catch(InputMismatchException e){
			System.out.println("Intoduzca un numero");
			introducirEmpleados(aEmpleados);
		} catch(NullPointerException ex){
			System.out.println("Ha introducido mal la fecha, intentelo de nuevo");
			introducirEmpleados(aEmpleados);
		}
		System.out.println("Fin Introducir Empleados");
	}
	private static void mostrarEmpleados(ArrayList <Empleado> aEmpleados){
		for(int i=0; i<aEmpleados.size();i++){
			System.out.println(aEmpleados.get(i));
		}
		System.out.println(" -- FIN MUESTRA EMPLEADOS --");
	}
	private static void borrarEmpleados(ArrayList <Empleado> aEmpleados){
		System.out.println();
		System.out.println();
		System.out.println("Bienvenido a Borrar Empleado");
		System.out.println("Que empleado desea borrar?");
		boolean correcto = false;
		int number = 0;
		int intro = 0;
		String emple = null;
		do{
			for(int i = 0; i < aEmpleados.size(); i++){
				System.out.println((i+1) +". "+ aEmpleados.get(i).getNombre());
			}
			try{
				Scanner sc = new Scanner(System.in);
				number = sc.nextInt();
				number--;
				correcto = true;
			}
			catch(InputMismatchException e){
				System.out.println("Error 404");
				System.out.println("Introduzca un numero");
				correcto = false;
			}
		}while(!correcto);
		emple = aEmpleados.get(number).getNombre();
		System.out.println("Se borrara " + aEmpleados.get(number).getNombre() + " esta usted seguro?");
		System.out.println("1. Si");
		System.out.println("2. No");
		try{
			Scanner sc = new Scanner(System.in);
			intro = sc.nextInt();
		}
		catch(InputMismatchException e){
			System.out.println("Error 404");
			System.out.println("Introduzca un numero");
			borrarEmpleados(aEmpleados);
		}
		switch(intro){
		case 1: EmpleadoDao.eliminar(aEmpleados.get(number).getNumero()); 
		System.out.println("Se ha borrado " +emple+" de la lista de empleados" );
		break;
		case 2: borrarEmpleados(aEmpleados);
		break;
		default: System.out.println("wrooong input ");borrarEmpleados(aEmpleados);
		break;
		}
		Utilidades.clear();
	}
	public static void modificarEmpleados(ArrayList<Empleado> aEmpleados){

		System.out.println();
		System.out.println();
		System.out.println("Bienvenido a modificar Empleado");
		System.out.println("Que empleado desea modificar?");
		boolean correcto = false;
		int number = 0;
		int intro = 0;
		String emple = null;
		do{
			for(int i = 0; i < aEmpleados.size(); i++){
				System.out.println((i+1) +". "+ aEmpleados.get(i).getNombre());
			}
			try{
				Scanner sc = new Scanner(System.in);
				number = sc.nextInt();
				number--;
				correcto = true;
			}
			catch(InputMismatchException e){
				System.out.println("Error 404");
				System.out.println("Introduzca un numero");
				correcto = false;
			}
		}while(!correcto);
		emple = aEmpleados.get(number).getNombre();
		System.out.println("Se modificara " + aEmpleados.get(number).getNombre() + " esta usted seguro?");
		System.out.println("1. Si");
		System.out.println("2. No");
		try{
			Scanner sc = new Scanner(System.in);
			intro = sc.nextInt();
		}
		catch(InputMismatchException e){
			System.out.println("Error 404");
			System.out.println("Introduzca un numero");
			modificarEmpleados(aEmpleados);
		}
		aEmpleados.get(number).pedirDatos();
		switch(intro){
		case 1: EmpleadoDao.modificar(aEmpleados.get(number), aEmpleados.get(number).getNumero()); 
		System.out.println("Se ha alterado " +emple+" de la lista de empleados" );
		break;
		case 2: modificarEmpleados(aEmpleados);
		break;
		default: System.out.println("wrooong input ");modificarEmpleados(aEmpleados);
		break;
		}
		Utilidades.clear();
	}
	//Emple section END




	//Depart section INC
	public static void gestorDepartamentos(ArrayList <Departamento> aDepartamentos){
		Utilidades.clear();
		boolean atras = false;
		do{
			System.out.println("Bienvenido al gestor de departamentos");
			System.out.println("0. Atras");
			System.out.println("1. Introducir departamento ");
			System.out.println("2. Borrar departamento");
			System.out.println("3. Mostrar departamentos");
			System.out.println("4. Modificar departamentos");
			try{
				Scanner sc = new Scanner(System.in);
				int opcion = sc.nextInt();
				switch(opcion){
				case 0: 
					atras = true;
					break;
				case 1: 
					introducirDepartamentos(aDepartamentos);
					break;
				case 2:
					borrarDepartamentos(aDepartamentos);
					break;
				case 3: 
					mostrarDepartamentos();
					break;
				case 4:
					modificarDepartamentos(aDepartamentos);
					break;
				default:System.out.println("Opcion incorrecta"); 
				break;
				}
			}catch(InputMismatchException e){
				System.out.println("Introduzca un numero");
			}
		}	while(!atras);
	}

	private static void introducirDepartamentos(ArrayList <Departamento> aDepartamentos){
		try{
			int numDepart = 0;
			System.out.println("Cuantos departamentos desea introducir? ");
			Scanner sc = new Scanner(System.in);
			numDepart = sc.nextInt();
			for(int i = 0; i < numDepart; i++){
				Departamento d = new Departamento();
				System.out.println("-- Departamento numero " + (i+1) +" --");
				d.pedirDatos();
				aDepartamentos.add(d);
				DepartamentoDao.insertar(d);
			}
		}catch(InputMismatchException e){
			System.out.println("Intoduzca un numero");
		} catch(NullPointerException ex){
			System.out.println("Ha introducido mal la fecha, intentelo de nuevo");
			introducirDepartamentos(aDepartamentos);
		}
		System.out.println("Fin Introducir Departamentos");
	}





	private static void mostrarDepartamentos(){
		ArrayList<Departamento> aDepartamentos=DepartamentoDao.consultaDepartamentos();

		for(Departamento d: aDepartamentos){
			System.out.println("DEPARTAMENTO");
			System.out.println(d);
			System.out.println("EMPLEADOS DEL DEPARTAMENTO");
			ArrayList<Empleado> aEmpleados=d.getEmpleados();
			for(Empleado e:aEmpleados){
				System.out.println(e);
			}

		}
	}

	private static void mostrarEmpleados(){
		ArrayList<Empleado> aEmpleados=EmpleadoDao.consultarEmpleados();

		for(Empleado e: aEmpleados){
			System.out.println("EMPLEADO");
			System.out.println(e);
			System.out.println("DEPARTAMENTO DEL EMPLEADO");
			System.out.println(e.getDepartamento());

		}
	}







	private static void borrarDepartamentos(ArrayList <Departamento> aDepartamentos){
		System.out.println();
		System.out.println();
		System.out.println("Bienvenido a Borrar Departamento");
		System.out.println("Que departamento desea borrar?");
		boolean correcto = false;
		int number = 0;
		int intro = 0;
		String depart = null;

		do{
			for(int i = 0; i < aDepartamentos.size(); i++){
				System.out.println((i+1) +". "+ aDepartamentos.get(i).getNombre());
			}
			try{
				Scanner sc = new Scanner(System.in);
				number = sc.nextInt();
				number--;
				correcto = true;
			}
			catch(InputMismatchException e){
				System.out.println("Error 404");
				System.out.println("Introduzca un numero");
				correcto = false;
			}
		}while(!correcto);
		depart = aDepartamentos.get(number).getNombre();
		System.out.println("Se borrara " + aDepartamentos.get(number).getNombre() + " esta usted seguro?");
		System.out.println("1. Si");
		System.out.println("2. No");
		try{
			Scanner sc = new Scanner(System.in);
			intro = sc.nextInt();
		}
		catch(InputMismatchException e){
			System.out.println("Error 404");
			System.out.println("Introduzca un numero");
		}
		switch(intro){
		case 1: DepartamentoDao.eliminar(aDepartamentos.get(number).getNumero()); 
		System.out.println("Se ha borrado " +depart+" de la lista de empleados" );
		break;
		case 2: borrarDepartamentos(aDepartamentos);
		break;
		default: System.out.println("wrooong input ");
		break;
		}
		Utilidades.clear();
	}

	public static void modificarDepartamentos(ArrayList<Departamento> aDepartamentos){

		System.out.println();
		System.out.println();
		System.out.println("Bienvenido a modificar Departamento");
		System.out.println("Que departamento desea modificar?");
		boolean correcto = false;
		int number = 0;
		int intro = 0;
		String depart = null;
		do{
			for(int i = 0; i < aDepartamentos.size(); i++){
				System.out.println((i+1) +". "+ aDepartamentos.get(i).getNombre());
			}
			try{
				Scanner sc = new Scanner(System.in);
				number = sc.nextInt();
				number--;
				correcto = true;
			}
			catch(InputMismatchException e){
				System.out.println("Error 404");
				System.out.println("Introduzca un numero");
				correcto = false;
			}
		}while(!correcto);
		depart = aDepartamentos.get(number).getNombre();
		System.out.println("Se modificara " + aDepartamentos.get(number).getNombre() + " esta usted seguro?");
		System.out.println("1. Si");
		System.out.println("2. No");
		try{
			Scanner sc = new Scanner(System.in);
			intro = sc.nextInt();
		}
		catch(InputMismatchException e){
			System.out.println("Error 404");
			System.out.println("Introduzca un numero");
			modificarDepartamentos(aDepartamentos);
		}
		aDepartamentos.get(number).pedirDatosV2();
		switch(intro){
		case 1: DepartamentoDao.modificar(aDepartamentos.get(number)); 
		System.out.println("Se ha alterado " +depart+" de la lista de departamentos" );
		break;
		case 2: modificarDepartamentos(aDepartamentos);
		break;
		default: System.out.println("wrooong input ");modificarDepartamentos(aDepartamentos);
		break;
		}
		Utilidades.clear();
	}
	//Depart section END
}
