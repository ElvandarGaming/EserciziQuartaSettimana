package eserciziConnessioni;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionHandler {
	private Connection con;
	private final String url;

	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	
	public ConnectionHandler(String typeDatabase, String address, String port, String databaseName, String schema,
			String username, String password) {
		url = String.format("jdbc:%s://%s:%s/%s?currentSchema=%s&user=%s&password=%s", typeDatabase, address, port,
				databaseName, schema, username, password);
	}

	public Connection getConnection() throws SQLException {
		if (con == null || con.isClosed()) {
			this.con = DriverManager.getConnection(url);
		}
		return this.con;
	}

	public void closeConnection() throws SQLException {
		if (con != null)
			this.con.close();
		// this.con = null;
	}

	public PreparedStatement getPreparedStatement(String queryTemplate) throws SQLException {
		con = getConnection();
		PreparedStatement prep = con.prepareStatement(queryTemplate);
		return prep;
	}

}
