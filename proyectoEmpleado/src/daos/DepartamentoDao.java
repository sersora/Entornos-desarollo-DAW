package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import objetos.Departamento;
import objetos.Empleado;

/**
 * Clase para la gestión de la tabla DEPART
 * @author Javier
 * @see objetos.Departamento
 * 
 */
public class DepartamentoDao {
	private static Connection conexion;
	
	public static Connection getConexion() {
		return conexion;
	}
	public static void setConexion(Connection conexion) {
		DepartamentoDao.conexion = conexion;
	}
	

	/**
	 * Método para insertar un registro en la tabla departamento
	 * @param depart
	 * @return  <ul>
	 * 				<li> -1 : fallo al insertar la linea </li>
	 * 				<li> 0 : no ha insertado nada </li>
	 * 				<li> 1 : ha insertado un registro. </li>
	 * 			</ul>
	 */
	public static int insertar(Departamento depart){
		try{
			PreparedStatement stmt = conexion.prepareStatement
					("insert into depart (dept_no, dnombre, loc) values(?,?,?)");
			stmt.setInt(1, depart.getNumero());
			stmt.setString(2, depart.getNombre());
			stmt.setString(3, depart.getLocalizacion());
			return stmt.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
			return -1;
		}
	}

	
	
	public static Departamento buscarDepartamentoPorCodigo(int codigo){
		Departamento depart = new Departamento();
		try{
			PreparedStatement stmt = conexion.prepareStatement
					("select * from depart where dept_no=?");
			stmt.setInt(1, codigo);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				depart= new Departamento(rs.getInt("dept_no"),
						rs.getString("dnombre"), rs.getString("loc"));						
			}
		}catch (SQLException e){
			System.out.println("No existe el departamento con codigo " + codigo);
			return null;
		}
		return depart;
	}
	
	
	public static int modificar(Departamento depart){
		try{
			PreparedStatement stmt=conexion.prepareStatement("Update depart set loc=?, dnombre=? where dept_no=?");
			stmt.setString(1, depart.getLocalizacion());
			stmt.setString(2, depart.getNombre());
			stmt.setInt(3, depart.getNumero());
			return stmt.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
			return -1;
		}
	}

	public static int eliminar(int id){
		try{
			PreparedStatement stmt=conexion.prepareStatement("Delete from depart where dept_no=?");
			stmt.setInt(1, id);
			return stmt.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
			return -1;
		}
	}
	
	
	
	static public ArrayList<Departamento> consultaDepartamentos(){
		ArrayList<Departamento> aDepartamentos = new ArrayList<Departamento>();
		try{
			Statement st=conexion.createStatement();
			ResultSet rs=st.executeQuery("SELECT * FROM depart");
			while(rs.next()){
				int dept_no=rs.getInt("dept_no");
				ArrayList<Empleado> aEmpleados=
						EmpleadoDao.consultaEmpleadoPorNumerodeDepartamento(dept_no);
				Departamento d= new Departamento(rs.getInt("dept_no"),
												rs.getString("DNOMBRE"),
												rs.getString("LOC"));
				d.setEmpleados(aEmpleados);
				aDepartamentos.add(d);
			}
			return aDepartamentos;	
		}catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	
	
	
	
	
	

	
}

