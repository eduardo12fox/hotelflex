package br.com.ebercom.hotel.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ebercom.hotel.beans.Quarto;


public class QuartoDAO {
	
	public static void insert(Quarto quarto){
		try {
			//Prepara a sql
			String sql = "INSERT INTO QUARTO ( DESCRICAO, NUMERO, TIPO, VALOR) VALUES (?,?,?,?)";
			//Pega uma conexão
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			//Preenche os valores dos PreparedStatements
			//stmt.setInt(1, quarto.getCodigo());
			stmt.setString(1, quarto.getDescricao());
			stmt.setString(2, quarto.getNumero());
			stmt.setInt(3, quarto.getTipo());
			stmt.setFloat(4, quarto.getValor());
			//Executa o PreparedStatement
			stmt.execute();
			//Fecha a conexao e o PreparedStatement
			stmt.close();
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public static List<Quarto> getQuartosByCampo(String campo, String valor, Boolean like){
		List<Quarto> quartos = new ArrayList<Quarto>();
		try{
			String sql = like ? "SELECT * FROM QUARTO WHERE " + campo + " like ? ORDER BY CODIGO" :
								"SELECT * FROM QUARTO WHERE " + campo + "= ? ORDER BY CODIGO";
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setObject(1, like ? valor + "%" : valor);
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				Quarto obj = new Quarto();
				obj.setCodigo(res.getInt("CODIGO"));
				obj.setDescricao(res.getString("DESCRICAO"));
				obj.setNumero(res.getString("NUMERO"));
				obj.setTipo(res.getInt("TIPO"));
				obj.setValor(res.getFloat("VALOR"));
				quartos.add(obj);
			}
			conn.commit();
			stmt.close();
			res.close();
			conn.close();
		} catch (SQLException e ){
			e.printStackTrace();
		}
		return quartos;
	}
	
	public static List<Quarto> getQuartosList(){
		List<Quarto> quartos = new ArrayList<Quarto>();
		try{
			String sql = "SELECT * FROM QUARTO ORDER BY CODIGO";
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet res = stmt.executeQuery();
			while (res.next()){
				Quarto obj = new Quarto();
				obj.setCodigo(res.getInt("Codigo"));
				obj.setDescricao(res.getString("Descricao"));
				obj.setNumero(res.getString("Numero"));
				obj.setTipo(res.getInt("tipo"));
				obj.setValor(res.getFloat("Valor"));
				quartos.add(obj);
			}
			stmt.close();
			res.close();
			conn.commit();
		} catch (SQLException e){
			e.printStackTrace();
		}

		return quartos;		
	}

	public static Quarto getQuartoByID(String ID) {
		Quarto quarto = new Quarto();
		try{
			String sql = "SELECT * FROM QUARTO WHERE CODIGO=?";
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, ID);
			ResultSet res = stmt.executeQuery();
			if (res.next()){
				quarto.setCodigo(res.getInt("Codigo"));
				quarto.setDescricao(res.getString("Descricao"));
				quarto.setNumero(res.getString("Numero"));
				quarto.setTipo(res.getInt("Tipo"));
				quarto.setValor(res.getFloat("Valor"));
			}
			stmt.close();
			res.close();
			conn.commit();
		} catch (SQLException e){
			e.printStackTrace();
		}

		return quarto;	
	}

	public static void update(Quarto quarto) {
		try {
			//Prepara a sql
			String sql = "UPDATE QUARTO SET DESCRICAO= ?, NUMERO=?, TIPO=?, VALOR=? WHERE CODIGO=?";
			//Pega uma conexão
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			//Preenche os valores dos PreparedStatements
			//stmt.setInt(1, quarto.getCodigo());
			stmt.setString(1, quarto.getDescricao());
			stmt.setString(2, quarto.getNumero());
			stmt.setInt(3, quarto.getTipo());
			stmt.setFloat(4, quarto.getValor());
			stmt.setInt(5, quarto.getCodigo());
			//Executa o PreparedStatement
			stmt.execute();
			//Fecha a conexao e o PreparedStatement
			stmt.close();
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}
	
/*	public static void main(String args[]){
		QuartoDAO dao = new QuartoDAO();
		List<Quarto> quartos = dao.getQuartosList();
		for (Quarto quarto : quartos) {
			System.out.println(quarto.getCodQuarto());
		}
	}
	*/
}
