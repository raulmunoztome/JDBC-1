import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) throws SQLException {

		String consulta = "SELECT * FROM DEPARTAMENTS";
		GestorSQL gestor1 = new GestorSQL();

		ResultSet resultat = gestor.consultaSQL(consulta);

		while (resultat.next()) {
			System.out.println("Codi departament: " + resultat.getInt(1) + " Nom: " + resultat.getString(2));
		}
		System.out.println("--------------------------------------");
		consulta = "SELECT codi_emp, cognom, ofici, salari, comissio FROM EMPLEATS";
		resultat = gestor.consultaSQL(consulta);

		while (resultat.next()) {
			System.out.println("codi: " + resultat.getInt(1) + " cognom: " + resultat.getString(2) + " ofici: "
					+ resultat.getString(3) + " salari: " + resultat.getInt(4) + " comissio: " + resultat.getInt(5));
		}

		String sql = "SELECT CODI_EMP, COGNOM FROM EMPLEATS WHERE CODI_DEPT = ?";
		PreparedStatement sentenciaPreparada = connexio.prepareStatement(sql);
		System.out.println("Indiqui el codi del departament a consultar: ");
		Scanner sc = new Scanner(System.in);
		String codiDep = sc.nextLine();
		Integer departament = Integer.parseInt(codiDep);
		sentenciaPreparada.setInt(1, departament);
		System.out.println("EMPLEATS DEL DEPARTAMENT " + codiDep);
		ResultSet resultat = GestorSQL
		while (resultat.next()) {
			System.out.println("Codi empleat: " + resultat.getInt(1) + " Cognom: " + resultat.getString(2));
		}
		sc.close();

	}

}
