package eserciziConnessioni;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionHandler4 {
	private Connection con;
	private String url;
	
	public ConnectionHandler4(String typeDatabase,String address,String port,String databaseName ,String schema,String username,String password ) {
		url = String.format("jdbc:%s://%s:%s/%s?currentSchema=%s&user=%s&password=%s",typeDatabase,address,port,databaseName,schema,username,password);
		System.out.println(url);
	}
	
	public Connection getConnection(){
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver postgreSQL non trovati");
		}
		try {
			this.con = DriverManager.getConnection(this.url);
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Url non corretto, Connessione non stabilita");
		}
		return this.con;
	}
	
	public void closeConnection(){
		try {
			this.con.close();
			this.con = null;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Non è stato possibile chiudere la risorsa.");
		} catch(NullPointerException e) {
			System.out.println("La risorsa non è stata aperta");
		}
	}
	
	public PreparedStatement getPreparedStatement(String queryTemplate ){
		PreparedStatement prep = null;
		try {
			prep = con.prepareStatement(queryTemplate);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connessione chiusa, PreparedStatement non creato");
		}
		return prep;
	}
	
}
