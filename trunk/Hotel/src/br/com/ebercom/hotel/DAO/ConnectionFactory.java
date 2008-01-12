package br.com.ebercom.hotel.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static Connection conexao;
	
	private ConnectionFactory(){
		
	}

	public static Connection getConnection() throws SQLException{
		
		//usando firebird local
		String driver = "org.firebirdsql.jdbc.FBDriver";
		String banco = "jdbc:firebirdsql:localhost:C:\\Java\\Work\\Hotel\\BD\\SGR.FDB";
		String usuario = "SYSDBA";
		String senha = "masterkey"; 
		
		try{
			Class.forName(driver);
			conexao=null;
			conexao = DriverManager.getConnection(banco, usuario, senha);
			conexao.setAutoCommit(false);
		} catch(SQLException e) {
			e.printStackTrace(); 
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return conexao;
	}
}
