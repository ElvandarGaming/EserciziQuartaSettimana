package Prof;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionHandler {
	public static final String DB_URL = "jdbc:postgresql://localhost:5432/epicode-be";
	
	private final String connectionUrl;
	
	private Connection connection;


	public ConnectionHandler(String dbUrl, String schema, String username, String password) throws ClassNotFoundException {
		this.connectionUrl = dbUrl 
				+ "?currentSchema="+ schema 
				+ "&user="+username
				+ "&password=" + password;
	
		// Occorre conoscere il nome del driver e il suo package
		Class.forName("org.postgresql.Driver");
	
	}
	
	public Connection getConnection() throws SQLException {
		if ( this.connection == null || this.connection.isClosed() ) {
			// Se la connessione non esiste o è chiusa, la crea
			this.connection = DriverManager.getConnection(connectionUrl);
		}
		// Ritorna la connessione
		return this.connection;

	}

	public void closeConnection() throws SQLException {
		if ( this.connection != null && !this.connection.isClosed()) {
			this.connection.close();
			this.connection = null;
		}
	}
	
	public PreparedStatement getPreparedStatement(String query) throws SQLException {
		// Uso il metodo getConnection invece di accedere direttamente per
		// garantire che la connection sia attiva
		Connection conn = getConnection();
		
		PreparedStatement stmnt = conn.prepareStatement(query);
		
		return stmnt;
	}
	

}
