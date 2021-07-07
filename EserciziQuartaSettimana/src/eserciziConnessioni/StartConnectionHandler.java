package eserciziConnessioni;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import Prof.ConnectionHandler;

public class StartConnectionHandler {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner cons = new Scanner(System.in);
		ConnectionHandler4 x = new ConnectionHandler4("postgresql", "localhost", "5432", "EsercizioGiorno2", "tirocinio", "postgres", "sex");
		//ConnectionHandler x = new ConnectionHandler("jdbc:postgresql://localhost:5432/EsercizioGiorno2", "tirocinio", "postgres", "sex");
		Connection con = x.getConnection();
		x.getConnection();
		x.getConnection();
		
		//jdbc:postgresql://localhost:5432/EsercizioGiorno2?currentSchema=tirocinio&user=postgres&password=sex
		x.closeConnection();
		cons.next();
		
		
		
		
		
		
		
		
	}

}
