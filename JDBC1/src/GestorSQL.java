import java.sql.*;

public class GestorSQL {

    public GestorSQL() {
    }

    // Consulta genérica 
    public ResultSet consultaSQL(String sql) throws SQLException {
        // Quitamos el try-catch para que el error llegue al main
        Connection conex = GestorConnexions.obtenirConnexio();
        Statement st = conex.createStatement();
        return st.executeQuery(sql);
    }

    // Método para consultar empleados por código usando PreparedStatement
    public ResultSet consultaEmpleadosPorCodigo(int codi) throws SQLException {
        String sql = "SELECT * FROM empleats WHERE codi_emp = ?";
        
        Connection conex = GestorConnexions.obtenirConnexio();
        PreparedStatement ps = conex.prepareStatement(sql);
        
        ps.setInt(1, codi);
        return ps.executeQuery();
    }
    public ResultSet consultaEmpleadosPorCodigoDept(int codi_dept) throws SQLException {
        String sql = "SELECT * FROM empleats WHERE codi_dept = ?";
        
        Connection conex = GestorConnexions.obtenirConnexio();
        PreparedStatement ps = conex.prepareStatement(sql);
        
        ps.setInt(1, codi_dept);
        return ps.executeQuery();
    }

    // Método para departamentos
    public ResultSet consultaDepartamentos(String consulta) throws SQLException {
        Connection conex = GestorConnexions.obtenirConnexio();
        Statement st = conex.createStatement();
        return st.executeQuery(consulta);
    }
    
    public ResultSet consultaCantidadEmpPorDept(int codi_dep) throws SQLException {
    	
    	String sql = "SELECT D.nom, COUNT(*) FROM DEPARTAMENTS D "
    			+ " INNER JOIN EMPLEATS E ON D.codi_dept = E.codi_dept "
    			+ " WHERE D.codi_dept = ? GROUP BY D.nom";
        Connection conex = GestorConnexions.obtenirConnexio();
        PreparedStatement ps = conex.prepareStatement(sql);
        ps.setInt(1, codi_dep);
        return ps.executeQuery();
        
        
    }
}
