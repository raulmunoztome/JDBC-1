import java.sql.*;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {

		String consulta = "SELECT * FROM DEPARTAMENTS";
		GestorSQL gestor = new GestorSQL();

		try (ResultSet resultat = gestor.consultaSQL(consulta)){
			
			while (resultat.next()) {
				System.out.println("Codi departament: " + resultat.getInt(1) + " Nom: " + resultat.getString(2));
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}

		System.out.println("--------------------------------------\n");
		
		consulta = "SELECT * FROM EMPLEATS";
		try (ResultSet resultat = gestor.consultaSQL(consulta)){
			
			while (resultat.next()) {
				System.out.println("codi: " + resultat.getInt(1) + " cognom: " + resultat.getString(2) + " ofici: "
						+ resultat.getString(3) + " salari: " + resultat.getInt(5) + " comissio: " + resultat.getInt(6));
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		System.out.println("--------------------------------------\n");
		
		System.out.println("Indiqui el codi del departament a consultar: ");
		Scanner sc = new Scanner(System.in);
		String codiDep = sc.nextLine();
		
		Integer departament = Integer.parseInt(codiDep);
		try (ResultSet resultat = gestor.consultaCantidadEmpPorDept(departament); 
			     ResultSet res2 = gestor.consultaEmpleadosPorCodigoDept(departament)) {

			    // Primero procesamos el COUNT 
			    if (resultat.next()) {
			        System.out.println(resultat.getString(1) + " : " + resultat.getInt(2) + " empleados");
			    }

			    // Luego procesamos la lista de empleados
			    while (res2.next()) {
			        System.out.println("empleado nº " + res2.getInt(1) + " -->  " + res2.getString(2) + " : " + res2.getString(3));
			    }
			    
			} catch (SQLException | NumberFormatException e) {

			System.out.println(e.getMessage());
		}
				
		System.out.println("--------------------------------------\n");
		
		
		System.out.println("AÑADIR UN NUEVO DEPARTAMENTO:\nIntroduce el código del departamento: ");
		try {
			
			codiDep = sc.nextLine();
			departament = Integer.parseInt(codiDep);
			
		} catch (NumberFormatException e) {
			
			System.out.println("error en el dato introducido: "+e.getMessage());
		}
		System.out.println("Nombre del departamento: ");
		String nomD = sc.nextLine();
		System.out.println("Ciudad donde se ubica el departamento: ");
		String ciud = sc.nextLine();
		try {
			boolean comprobar = gestor.añadirDepartamento(departament, nomD, ciud);
			if(comprobar) System.out.println(departament+" "+nomD+" a sido añadido");
			else System.out.println("Ya existe en la base, no se ha añadido, pruebe otro código");
			
		} catch (SQLException e) {
			System.out.println("Error de SQL: "+ e.getMessage());
		}
		
		System.out.println("--------------------------------------\n");
		
		System.out.println("indique el id del trabajdor a eliminar: ");
		String codigo = sc.nextLine();
		
		try {
			int id = Integer.parseInt(codigo);
			boolean confirma = gestor.eliminarEmpleadosPorCodigo(id);
			if(confirma)System.out.println("Trabajador "+ id+" eliminado");

			
		} catch (NumberFormatException e) {

			System.out.println("error en lo introducido: "+e.getMessage());
		}
		catch(SQLException e) {
			System.out.println("error: "+e.getMessage());
		}
	
		
		
		sc.close();

	}

}
