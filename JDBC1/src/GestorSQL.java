import java.sql.*;

public class GestorSQL {

    public GestorSQL() {
    }

    // Consulta genérica 
    public ResultSet consultaSQL(String sql) throws SQLException {
        // Quitamos el try-catch para que el error llegue al main
    	//insegura
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
    
    public boolean añadirDepartamento(int codiD, String nom, String ciutat)throws SQLException {
    	
    	Connection con = GestorConnexions.obtenirConnexio();
    	
    	String sql = "INSERT INTO DEPARTAMENTS VALUES (?,?,?)";
    	PreparedStatement ps = con.prepareStatement(sql);
    	ps.setInt(1, codiD);
    	ps.setString(2, nom);
    	ps.setString(3, ciutat);
    	
    	int numfiles = ps.executeUpdate();
    	if(numfiles == 0)return false;
    	
    	System.out.println("se ha añadido "+numfiles+" registro nuevo en DEPARTAMENTS");
    	return true;
    	
    }
    public boolean eliminarEmpleadosPorCodigo(int codi) throws SQLException {
    	
        String sql = "DELETE FROM EMPLEATS WHERE codi_emp = ?";
        
        Connection conex = GestorConnexions.obtenirConnexio();
        PreparedStatement ps = conex.prepareStatement(sql);
        
        ps.setInt(1, codi);
       int files =  ps.executeUpdate();
       
       if(files > 0)  return true;
       
       System.out.println("no hay trabajador con ese código");
       return false;
    }
}
