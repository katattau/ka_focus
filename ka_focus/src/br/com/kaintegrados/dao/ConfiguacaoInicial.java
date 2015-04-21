package br.com.kaintegrados.dao;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ConfiguacaoInicial {

	public ConfiguacaoInicial() {
		URL urlBD = getClass().getResource("/br/com/kaintegrados/dao/hibernate_db");
		System.out.println(urlBD.getPath());
	}
	
	public static void main(String[] args) throws SQLException {

		ConnectionFactory con = new ConnectionFactory();
		
		String sql = "CREATE TABLE pessoa ( "
				+ "id INTEGER IDENTITY PRIMARY KEY, "
				+ "nome VARCHAR(100) NOT NULL, "
				+ "email VARCHAR(100) NOT NULL)";

		PreparedStatement pstmt = con.getConnection().prepareStatement(sql);
		pstmt.execute();

		con.close();
		
		System.out.println("Base de dados criada!");
		
		//new ConfiguacaoInicial();
	}

}
