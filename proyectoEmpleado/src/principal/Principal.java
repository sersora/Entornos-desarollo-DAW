package principal;

import java.util.ArrayList;

import daos.ConexionDao;
import daos.DepartamentoDao;
import daos.EmpleadoDao;
import objetos.Empleado;

public class Principal {

	public static void main(String[] args) {
		ConexionDao con= new ConexionDao();
		DepartamentoDao.setConexion(con.conexion);
		EmpleadoDao.setConexion(con.conexion);
		
		ArrayList<Empleado>arrayEmpleados=EmpleadoDao.consultarEmpleados();
		System.out.println("Hay " + arrayEmpleados.size() + " Empleados.");
	
		
		
		

	}

}
