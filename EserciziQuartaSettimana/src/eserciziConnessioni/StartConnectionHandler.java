package eserciziConnessioni;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StartConnectionHandler {

	public static void main(String[] args) {

		ConnectionHandler x = null;
		x = new ConnectionHandler("postgresql", "localhost", "5432", "EsercizioGiorno2", "tirocinio", "postgres",
				"sex");
		// ConnectionHandler x = new
		// ConnectionHandler("jdbc:postgresql://localhost:5432/EsercizioGiorno2",
		// "tirocinio", "postgres", "sex");
		try {
			x.getConnection();
			PreparedStatement y = x.getPreparedStatement(
					"select cognome from tirocinio.tirocinante a join tirocinio.azienda b on a.id_azienda = b.id where b.nome = ?");

			try {
				y.setString(1, "Microsoft");
				ResultSet res = y.executeQuery();
				List<String> cognomi = new ArrayList<>();
				while (res.next()) {
					cognomi.add(res.getString("cognome"));
				}
				System.out.println(cognomi.toString());

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				x.closeConnection();
			}
		} catch (SQLException d) {
			System.exit(0);
		}
		
		// jdbc:postgresql://localhost:5432/EsercizioGiorno2?currentSchema=tirocinio&user=postgres&password=sex

	}

}
