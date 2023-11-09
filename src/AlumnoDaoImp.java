import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDaoImp implements AlumnoDao{

    @Override
    public Alumno get(int id) throws SQLException {
        Connection con=Database.getConnection();
        
        Alumno alumno =null;
        String sql="SELECT id, noCuenta, nombre, apellido, altura, situacionAcademica FROM ingenieria WHERE noCuenta = ?";
        PreparedStatement ps = con.prepareStatement(sql);

		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			int tempid = rs.getInt("id");
			int noCuenta = rs.getInt("noCuenta");
			String nombre = rs.getString("nombre");
			String apellido = rs.getString("apellido");
			double altura = rs.getFloat("altura");
            SituacionAcademica situacionAcademica= SituacionAcademica.valueOf(rs.getString("situacionAcademica"));

			alumno = new Alumno(noCuenta, nombre, apellido, altura, situacionAcademica);
		}

		Database.closeResultSet(rs);
		Database.closePreparedStatement(ps);
		Database.closeConnection(con);


        return alumno;

    }

    @Override
    public List<Alumno> getAll() throws SQLException {
        Connection con = Database.getConnection();
		String sql = "SELECT id, noCuenta, nombre, apellido, altura, situacionAcademica FROM ingenieria";
		
		List<Alumno> alumnos = new ArrayList<>();
		
		Statement stmt = con.createStatement();
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
		    int tempid = rs.getInt("id");
			int noCuenta = rs.getInt("noCuenta");
			String nombre = rs.getString("nombre");
			String apellido = rs.getString("apellido");
			double altura = rs.getFloat("altura");
            SituacionAcademica situacionAcademica= SituacionAcademica.valueOf(rs.getString("situacionAcademica"));
	
			Alumno alumno = new Alumno(noCuenta, nombre, apellido, altura, situacionAcademica);
			
			alumnos.add(alumno);
		}
		
		return alumnos;
    }

    @Override
    public int save(Alumno t) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public int insert(Alumno alumno) throws SQLException {
        Connection con = Database.getConnection();

		String sql = "INSERT INTO ingenieria ( noCuenta, nombre, apellido, altura, situacionAcademica) VALUES (?, ?, ?, ?, ?)";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setInt(1, alumno.getNoCuenta());
		ps.setString(2, alumno.getNombre());
		ps.setString(3, alumno.getApellido());
		ps.setDouble(4, alumno.getAltura());
        ps.setString(5,alumno.getSituacionAcademica().toString());

		int result = ps.executeUpdate();

		Database.closePreparedStatement(ps);
		Database.closeConnection(con);

		return result;
    }

    @Override
    public int update(Alumno t) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public int delete(Alumno t) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
