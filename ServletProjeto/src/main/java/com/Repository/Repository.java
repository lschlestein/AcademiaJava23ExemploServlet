package com.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.Model.Pessoa;

public class Repository {
	private String db_url;
	private String db_user;
	private String db_password;

	public Repository(String db_url, String db_user, String db_password) {
		this.db_url = db_url;
		this.db_user = db_user;
		this.db_password = db_password;
	}
	
	public void cadastrarPessoa(Pessoa p) {
		String create = "insert into students (id, name, email, password) values (?, ?,?,?)";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection(db_url, db_user, db_password);
			System.out.println("Conectado ao BD");
			PreparedStatement pst = c.prepareStatement(create);
			pst.setLong(1, p.getId());
			pst.setString(2, p.getNome());
			pst.setString(3, p.getEmail());
			pst.setString(4, p.getPassword());
			// executar a query
			pst.executeUpdate();
			// Encerrar conexão
			c.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public List<Pessoa> consultarPessoas() {
		Pessoa pAux = new Pessoa();
		List<Pessoa> listaPessoas = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection(db_url, db_user, db_password);
			System.out.println("Conectado ao BD");
			PreparedStatement ps = c.prepareStatement("SELECT * FROM students");
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				pAux.setNome(resultSet.getString("name"));
				pAux.setEmail(resultSet.getString("email"));
				pAux.setPassword(resultSet.getString("password"));
				listaPessoas.add(pAux);
				pAux = new Pessoa();
			}
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Falha ao conectar ao MySql" + e.getMessage());
		}
		return listaPessoas;
	}

}
