package database;
import java.sql.*;

public class Database {

	private Connection con;
	
	private String query="";
	
	private Statement stm;
	
	private ResultSet results;
	
	public Database() throws SQLException {
		
		con = DriverManager.getConnection(
				"jdbc:mariadb://ns2.firmen-schulungen.info:3306/admin_kursverwaltung002",
				"admin_alex002",
				"z5qeB57?6"
				);
	}
	
	private void executeUpdate() throws SQLException {
		
		// Diese Methode ist fuer Datenmanipulationen an der DB zustaendig
		stm = con.createStatement();
		// Bei Inserts wollen wir ausserdem die automatisch generierte ID bekommen
		stm.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
		results = stm.getGeneratedKeys();
	}
	
	private void executeSQL() throws SQLException {
		
		// Diese Methode wird fuer SELECT Anweisungen verwendent
		stm = con.createStatement();
		results = stm.executeQuery(query);
	}
	
	public void setSelectStatement(String sql) throws SQLException {
		
		query = sql;
		executeSQL();
	}
	
	public void setSQLUpdate(String sql) throws SQLException {
		
		query = sql;
		executeUpdate();
	}
	
	public ResultSet getSelectResults() {
		
		return results;
	}
	
	public int getInsertID() throws SQLException {
		
		results.first();
		return results.getInt(1);
	}
	
	public void close() {
		
		// Wir setellen sicher, dass alle drei Beatandteile geschlossen werden
		// auch wenn einer aus der Gruppe bereits leer/abgebrochen sein sollte
		// Deshalb verwenden wir drei einzelne Exception-Handling
		// Auf einer besondere Fehlerbehandlung koennen wir zudem verzichten
		// Denn eine geschlossene Verbindung ist zu diesem Zeitpunkt kein
		// Fehler
		
		try {
			results.close();
		} catch (SQLException e) {}
		
		try {
			stm.close();
		} catch (SQLException e) {}
		
		try {
			con.close();
		} catch (SQLException e) {}
		
	}
}
