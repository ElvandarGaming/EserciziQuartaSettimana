package eserciziConnessioni;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StartConnectionHandler {

	public static void main(String[] args){
		Scanner cons = new Scanner(System.in);
		ConnectionHandler x = new ConnectionHandler("postgrsql", "localhost", "5432", "EsercizioGiorno2", "tirocinio", "postgres", "sex");
		//ConnectionHandler x = new ConnectionHandler("jdbc:postgresql://localhost:5432/EsercizioGiorno2", "tirocinio", "postgres", "sex");
		Connection con = x.getConnection();
		PreparedStatement y = x.getPreparedStatement("select cognome from tirocinio.tirocinante a join tirocinio.azienda b on a.id_azienda = b.id where b.nome = ?");
		cons.next();
		try {
			y.setString(1, "Microsoft");
			ResultSet res = y.executeQuery();
			List<String> cognomi = new ArrayList<>();
			while (res.next()) {
				cognomi.add(res.getString("cogome"));
			}
			System.out.println(cognomi.toString());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			x.closeConnection();
		}
		
		//jdbc:postgresql://localhost:5432/EsercizioGiorno2?currentSchema=tirocinio&user=postgres&password=sex

		
		
		
		
		
		
		
	}

}
