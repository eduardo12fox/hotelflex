package br.com.ebercom.hotel.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ebercom.hotel.beans.TipoQuarto;

public class TipoQuartoDAO {

	public static void insert(TipoQuarto TipoQuarto){
		try {
			//Prepara a sql
			String sql = "INSERT INTO TIPODEQUARTO ( DESCRICAO ) VALUES (?)";
			//Pega uma conexão
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			//Preenche os valores dos PreparedStatements
			//stmt.setInt(1, quarto.getCodigo());
			stmt.setString(1, TipoQuarto.getDescricao());
			//Executa o PreparedStatement
			stmt.execute();
			//Fecha a conexao e o PreparedStatement
			stmt.close();
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}	

	public static void update(TipoQuarto TipoQuarto){
		try {
			//Prepara a sql
			String sql = "UPDATE TIPODEQUARTO SET DESCRICAO=? WHERE CODIGO=?";
			//Pega uma conexão
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			//Preenche os valores dos PreparedStatements
			//stmt.setInt(1, quarto.getCodigo());
			stmt.setString(1, TipoQuarto.getDescricao());
			stmt.setInt(2, TipoQuarto.getCodigo());
			//Executa o PreparedStatement
			stmt.execute();
			//Fecha a conexao e o PreparedStatement
			stmt.close();
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}		
	
	public static List<TipoQuarto> getTipoQuartoList(){
		List<TipoQuarto> TipoQuartos = new ArrayList<TipoQuarto>();
		try{
			String sql = "SELECT * FROM TIPODEQUARTO ORDER BY CODIGO";
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet res = stmt.executeQuery();
			while (res.next()){
				TipoQuarto obj = new TipoQuarto();
				obj.setCodigo(res.getInt("Codigo"));
				obj.setDescricao(res.getString("Descricao"));
				TipoQuartos.add(obj);
			}
			stmt.close();
			res.close();
			conn.commit();
		} catch (SQLException e){
			e.printStackTrace();
		}

		return TipoQuartos;		
	}

	public static TipoQuarto getQuartoById(String id) {
		TipoQuarto TipoQuarto = new TipoQuarto();
		try{
			String sql = "SELECT * FROM TIPODEQUARTO WHERE CODIGO=?";
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			ResultSet res = stmt.executeQuery();
			while (res.next()){
				TipoQuarto.setCodigo(res.getInt("Codigo"));
				TipoQuarto.setDescricao(res.getString("Descricao"));
			}
			stmt.close();
			res.close();
			conn.commit();
		} catch (SQLException e){
			e.printStackTrace();
		}

		return TipoQuarto;	
	}
}
