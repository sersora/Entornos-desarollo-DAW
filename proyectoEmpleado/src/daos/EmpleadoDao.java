package daos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import objetos.Departamento;
import objetos.Empleado;
import utilidades.Utilidades;
public class EmpleadoDao {
	private static Connection conexion;
	
	public static Connection getConexion() {
		return conexion;
	}
	public static void setConexion(Connection conexion) {
		EmpleadoDao.conexion = conexion;
	}
	public static int insertar(Empleado emple){
		try{
			PreparedStatement stmt = conexion.prepareStatement
				("insert into emple (emp_no, apellido, oficio, dir, "
						+ "salario, comision)" + "values(?,?,?,?,?,?)");
			stmt.setInt(1, emple.getNumero());
			stmt.setString(2, emple.getNombre());
			stmt.setString(3, emple.getOficio());
			stmt.setInt(4, emple.getDireccion());
			//stmt.setTimestamp(5, Utilidades.parsearFechaSQL(emple.getFechaAlta()));
			stmt.setInt(5, emple.getSalario());
			stmt.setInt(6, emple.getComision());
			//Devuelvo el numero de filas que han sido modificadas
			return stmt.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
			System.out.println("Fallo al insertar empleado.");
			return -1;
		}
	}
	
	static public ArrayList<Empleado> consultarEmpleados(){
		ArrayList<Empleado> aEmpleados = new ArrayList<Empleado>();
		try{
			Statement st=conexion.createStatement();
			ResultSet rs=st.executeQuery("SELECT * FROM emple");
			while(rs.next()){
				int deptNo=rs.getInt("dept_no");
				Departamento dep=
						DepartamentoDao.buscarDepartamentoPorCodigo(deptNo);
				Empleado em= new Empleado(rs.getInt("EMP_NO"),
						rs.getString("APELLIDO"),rs.getString("OFICIO"),
						rs.getInt("DIR"),rs.getDate("fecha_alt"),
						rs.getInt("SALARIO"),rs.getInt("COMISION"));
				em.setDepartamento(dep);
				aEmpleados.add(em);
			}
			return aEmpleados;	
		}catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static int eliminar(int id){
		try{
			PreparedStatement stmt=conexion.prepareStatement
					("Delete from emple where emp_no=?");
			stmt.setInt(1, id);
			return stmt.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
			return -1;
		}
	}
	
	public static Empleado consultaEmpleadoPorNumero(int numero){
		try{
			PreparedStatement stmt=conexion.prepareStatement(
					"select * from emple e join depart d on e.dept_no "
					+ "= d.dept_no where emp_no=?");
			stmt.setInt(1, numero);
			ResultSet rs= stmt.executeQuery();
			Empleado em=null;
			while(rs.next()){	
				int dept_no=rs.getInt("dept_no");
				String dnombre=rs.getString("dnombre");
				String loc=rs.getString("loc");
				Departamento dep= new Departamento(dept_no,dnombre,loc);
				em= new Empleado(rs.getInt("EMP_NO"),
						rs.getString("APELLIDO"),
						rs.getString("OFICIO"),
						rs.getInt("DIR"),
						rs.getDate("fecha_alt"),
						rs.getInt("SALARIO"),
						rs.getInt("COMISION"));
				em.setDepartamento(dep);
			}
			return em;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}		
	}
	public static int modificar(Empleado emple, int no){
		try{
			PreparedStatement stmt=conexion.prepareStatement
					("Update emple set  apellido=?, oficio=?, dir=?, "
							+ "fecha_alt=?, salario=?, comision=? where emp_no=?");
			stmt.setString(1, emple.getNombre());
			stmt.setString(2, emple.getOficio());
			stmt.setInt(3, emple.getDireccion());
			stmt.setTimestamp(4, Utilidades.parsearFechaSQL(emple.getFechaAlta()));
			stmt.setInt(5, emple.getSalario());
			stmt.setInt(6, emple.getComision());
			stmt.setInt(7, no);
			return stmt.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
			return -1;
		}
	}
	
	public static ArrayList<Empleado> consultaEmpleadoPorNumerodeDepartamento(int numero){
		try{
			PreparedStatement stmt=conexion.prepareStatement(
					"select * from emple where dept_no=?");
			stmt.setInt(1, numero);
			ResultSet rs= stmt.executeQuery();
			Empleado emple=null;
			ArrayList<Empleado>aEmpleados=new ArrayList<Empleado>();
			while(rs.next()){	
				emple= new Empleado(rs.getInt("EMP_NO"),rs.getString("APELLIDO"),
						rs.getString("OFICIO"),	rs.getInt("DIR"),
						rs.getDate("fecha_alt"),rs.getInt("SALARIO"),
						rs.getInt("COMISION"));
				aEmpleados.add(emple);
			}
			return aEmpleados;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	
	}
	
	
	

}



