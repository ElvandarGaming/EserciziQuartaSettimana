package eserciziConnessioni;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//import java.util.Scanner;

public class TirocinioDbConnector {

	public List<Object[]> cercaTirocinanti() throws SQLException {
		// Scanner g = new Scanner(System.in);
		ConnectionHandler x = new ConnectionHandler("postgresql", "localhost", "5432", "EsercizioGiorno2", "tirocinio",
				"postgres", "sex");
		List<Object[]> ls = new ArrayList<>();

		try (Connection con = x.getConnection()) {
			Statement stat = con.createStatement();
			String query = "SELECT id,nome,cognome,classe FROM tirocinio.tirocinante";
			ResultSet res = stat.executeQuery(query);
			while (res.next()) {
				Object[] tirocinante = new Object[4];
				tirocinante[0] = res.getString("id");
				tirocinante[1] = res.getString("nome");
				tirocinante[2] = res.getString("cognome");
				tirocinante[3] = res.getString("classe");
				// g.next();
				ls.add(tirocinante);
			}
		}
		x.closeConnection();
		return ls;
	}

	public List<Object[]> cercaTutor(String mat) throws SQLException {
		ConnectionHandler x = new ConnectionHandler("postgresql", "localhost", "5432", "EsercizioGiorno2", "tirocinio",
				"postgres", "sex");
		List<Object[]> ls = new ArrayList<>();
		try (Connection con = x.getConnection()) {
			String query = "SELECT id,nome,cognome,materia FROM tirocinio.tutor WHERE materia LIKE ?";
			PreparedStatement stat = x.getPreparedStatement(query);
			stat.setString(1, mat + "%");
			ResultSet res = stat.executeQuery();
			while (res.next()) {
				Object[] tutor = new Object[4];
				tutor[0] = res.getString("id");
				tutor[1] = res.getString("nome");
				tutor[2] = res.getString("cognome");
				tutor[3] = res.getString("materia");

				ls.add(tutor);
			}

		}
		x.closeConnection();
		return ls;
	}

	public void insertTirocinante(long id, String matricola, String nome, String cognome, String classe, long idazienda,
			long idtutor) throws SQLException {
		ConnectionHandler x = new ConnectionHandler("postgresql", "localhost", "5432", "EsercizioGiorno2", "tirocinio",
				"postgres", "sex");
		try (Connection con = x.getConnection()) {
			String query = "insert into tirocinio.tirocinante (id,matricola,nome,cognome,classe,id_azienda,id_tutor) values (?,?,?,?,?,?,?);";
			PreparedStatement stat = x.getPreparedStatement(query);
			stat.setLong(1, id);
			stat.setString(2, matricola);
			stat.setString(3, nome);
			stat.setString(4, cognome);
			stat.setString(5, classe);
			stat.setLong(6, idazienda);
			stat.setLong(7, idtutor);
			stat.executeUpdate();
		}
		x.closeConnection();
	}

	public void updateTirocinante(long id, String matricola, String nome, String cognome, String classe, long idazienda,
			long idtutor) throws SQLException {
		ConnectionHandler x = new ConnectionHandler("postgresql", "localhost", "5432", "EsercizioGiorno2", "tirocinio",
				"postgres", "sex");
		try (Connection con = x.getConnection()) {
			String query = "UPDATE tirocinio.tirocinante SET matricola = ?,nome = ?,cognome = ?,classe = ?,id_azienda = ?,id_tutor = ? WHERE id=?;";
			PreparedStatement stat = x.getPreparedStatement(query);

			stat.setString(1, matricola);
			stat.setString(2, nome);
			stat.setString(3, cognome);
			stat.setString(4, classe);
			stat.setLong(5, idazienda);
			stat.setLong(6, idtutor);
			stat.setLong(7, id);
			stat.executeUpdate();
		}
		x.closeConnection();
	}

	public void deleteTirocinante(long id) throws SQLException {
		ConnectionHandler x = new ConnectionHandler("postgresql", "localhost", "5432", "EsercizioGiorno2", "tirocinio",
				"postgres", "sex");
		
		try (Connection con = x.getConnection()) {
			String query = "DELETE FROM tirocinio.tirocinante WHERE id=?;";
			PreparedStatement stat = x.getPreparedStatement(query);
			stat.setLong(1, id);

			stat.executeUpdate();
		}
		x.closeConnection();
	}

	public List<Object[]> cercaTirocinantiPerAzienda(String nomeAzienda) throws SQLException {
		ConnectionHandler x = new ConnectionHandler("postgresql", "localhost", "5432", "EsercizioGiorno2", "tirocinio",
				"postgres", "sex");
		List<Object[]> ls = new ArrayList<>();
		String templateQuery = "SELECT t.id,t.matricola, t.nome, t.cognome, t.classe FROM tirocinio.tirocinante t"
				+ /* INNER */" JOIN tirocinio.azienda a ON t.id_azienda=a.id WHERE a.nome = ?";
		try (Connection con = x.getConnection(); PreparedStatement stat = x.getPreparedStatement(templateQuery)) {
			stat.setString(1, nomeAzienda);
			try (ResultSet res = stat.executeQuery();) {
				while (res.next()) {
					Object[] tirocinanti = new Object[4];
					tirocinanti[0] = res.getString("id");
					tirocinanti[1] = res.getString("nome");
					tirocinanti[2] = res.getString("cognome");
					tirocinanti[3] = res.getString("classe");
					ls.add(tirocinanti);
				}
			}
		}
		return ls;

	}

}
