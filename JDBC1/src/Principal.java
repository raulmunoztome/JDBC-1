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
		
		
		sc.close();

	}

}
