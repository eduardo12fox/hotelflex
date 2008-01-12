package br.com.ebercom.hotel.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import br.com.ebercom.hotel.beans.QuartoReserva;

public class QuartoReservaDAO {
	
	public static List<QuartoReserva> getListByReserva(String reserva){
		List<QuartoReserva> quartosReserva = new ArrayList<QuartoReserva>();
		//String sql = "SELECT * FROM QUARTOSRESERVA WHERE RESERVA=? ORDER BY QUARTO";
		String sql = "SELECT qr.reserva, qr.quarto, qr.valor, qa.descricao FROM quartosreserva qr, quarto qa where qr.quarto = qa.codigo and qr.reserva=? ORDER BY quarto";
		try{
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, reserva);
			ResultSet res = stmt.executeQuery();
			while (res.next()){
				QuartoReserva obj = new QuartoReserva();
				obj.setReserva( res.getInt("RESERVA"));
				obj.setQuarto( res.getInt("QUARTO"));
				obj.setValor(res.getFloat("VALOR"));
				obj.setDescricao(res.getString("DESCRICAO"));
				quartosReserva.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quartosReserva;		
	}

	public static void insert( QuartoReserva quartoreserva){
		String sql = "INSERT INTO QUARTOSRESERVA ( RESERVA, QUARTO, VALOR ) VALUES (?,?,?)";
		try{
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, quartoreserva.getReserva());
			stmt.setInt(2, quartoreserva.getQuarto());
			stmt.setFloat(3, quartoreserva.getValor());
			stmt.execute();
			stmt.close();
			conn.commit();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void remove( String reserva, String quarto){
		String sql = "DELETE FROM QUARTOSRESERVA WHERE RESERVA=? AND QUARTO=?";
		try{
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(reserva));
			stmt.setInt(2, Integer.parseInt(quarto));
			stmt.execute();
			stmt.close();
			conn.commit();	
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
}
