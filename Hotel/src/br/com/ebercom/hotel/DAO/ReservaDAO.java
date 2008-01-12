package br.com.ebercom.hotel.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import br.com.ebercom.hotel.beans.Reserva;

public class ReservaDAO {

	public static List<Reserva> getListReservas(String cliente, String dtReserva1, String dtReserva2, 
								String dtPrevEnt1, String dtPrevEnt2, String dtHosp1, String dtHosp2, 
								String flag1, String flag2, String flag3, String flag4){
		List<Reserva> reservas = new ArrayList<Reserva>();
		String sql = "SELECT RESERVA.CODIGO, RESERVA.DTRESERVA, RESERVA.CLIENTE, CLIENTE.NOME, RESERVA.DTPREVENT, RESERVA.DTPREVSAI, RESERVA.DTENTRADA, " +
				     "RESERVA.DTSAIDA, RESERVA.FLAG FROM RESERVA LEFT OUTER JOIN CLIENTE ON (RESERVA.CLIENTE = CLIENTE.CODIGO) WHERE ";
		sql = ((cliente==null) || (cliente.equals(""))) ? sql : sql + "RESERVA.CLIENTE= '" + cliente + "' AND ";
		sql = ((dtReserva1==null) || (dtReserva1.equals(""))) ? sql + "RESERVA.DTRESERVA BETWEEN '01.01.1000' AND " : sql + "RESERVA.DTRESERVA BETWEEN '" + converte(dtReserva1) + "' AND ";
		sql = ((dtReserva2==null) || (dtReserva2.equals(""))) ? sql + "'01.01.5000' AND " : sql + "'" + converte(dtReserva2) + "' AND ";
		sql = ((dtPrevEnt1==null) || (dtPrevEnt1.equals(""))) ? sql + "RESERVA.DTPREVENT BETWEEN '01.01.1000' AND " : sql + "RESERVA.DTPREVENT BETWEEN '" + converte(dtPrevEnt1) + "' AND ";
		sql = ((dtPrevEnt2==null) || (dtPrevEnt2.equals(""))) ? sql + "'01.01.5000' AND " : sql + "'" + converte(dtPrevEnt2) + "' AND ";
		if ((dtHosp1==null) || (dtHosp1.equals("")) && (dtHosp2==null) || (dtHosp2.equals(""))){
			//não faça nada
		} else {
			sql = ((dtHosp1==null) || (dtHosp1.equals(""))) ? sql + "RESERVA.DTENTRADA BETWEEN '01.01.1000' AND " : sql + "RESERVA.DTENTRADA BETWEEN '" + converte(dtHosp1) + "' AND ";
			sql = ((dtHosp2==null) || (dtHosp2.equals("")))? sql + "'01.01.5000' AND " : sql + "'" + converte(dtHosp2) + "' AND ";
		}
		sql = sql + "( ";
		sql = (flag1==null) ? sql : sql + "(RESERVA.FLAG= '" + flag1 + "') ";
		if ((flag1!=null) && (flag2!=null))
			sql = sql + "OR ";
		sql = (flag2==null) ? sql : sql + "(RESERVA.FLAG= '" + flag2 + "') ";
		if (((flag1!=null) || (flag2!=null)) && (flag3!=null))
			sql = sql + "OR ";
		sql = (flag3==null) ? sql : sql + "(RESERVA.FLAG= '" + flag3 + "') ";
		if (((flag1!=null) || (flag2!=null) || (flag3!=null)) && (flag4!=null))
			sql = sql + "OR ";		
		sql = (flag4==null) ? sql : sql + "(RESERVA.FLAG= '" + flag4 + "')";
		sql = sql + ") ";
		try{
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet res = stmt.executeQuery();
			while (res.next()){
				Reserva obj = new Reserva();
				obj.setCodigo(res.getInt("CODIGO"));
				obj.setDtreserva( res.getDate("DTRESERVA"));
				obj.setCliente(res.getInt("CLIENTE"));
				obj.setNome(res.getString("NOME"));
				obj.setDtprevent(res.getDate("DTPREVENT"));
				obj.setDtprevsai(res.getDate("DTPREVSAI"));
				obj.setDtentrada(res.getDate("DTENTRADA"));
				obj.setDtsaida(res.getDate("DTSAIDA"));
				obj.setFlag(res.getString("FLAG"));
				reservas.add(obj);
			}
			conn.commit();
			stmt.close();
			res.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return reservas;
	}
	
	public static String getLast(){
		String sql = "SELECT CODIGO FROM RESERVA ORDER BY CODIGO";
		String retorno = "";
		try{
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet res = stmt.executeQuery();
			while (res.next()){
				retorno = res.getString(1);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return retorno;
	}

	public static void remove( String codigo){
		String sql = "DELETE FROM RESERVA WHERE CODIGO=?";
		try{
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(codigo));
			stmt.execute();
			stmt.close();
			conn.commit();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void updateFlag(String id, String flag){
		String sql = "UPDATE RESERVA SET FLAG=? WHERE CODIGO=?";
		try{
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, flag);
			stmt.setString(2, id);
			stmt.execute();
			conn.commit();
			stmt.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}	
	
	public static void update(Reserva reserva){
		String sql = "UPDATE RESERVA SET DTRESERVA=?, CLIENTE=?, DTPREVENT=?, DTPREVSAI=?, DTENTRADA=?, DTSAIDA=?, FLAG=? WHERE CODIGO=?";
		try{
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setDate(1, new java.sql.Date(reserva.getDtreserva().getTime()));
			stmt.setInt(2, reserva.getCliente());
			stmt.setDate(3, new java.sql.Date(reserva.getDtprevent().getTime()));
			stmt.setDate(4, new java.sql.Date(reserva.getDtprevsai().getTime()));
			
			if (reserva.getDtentrada()== null)
				stmt.setDate(5, null);
			else
				stmt.setDate(5, new java.sql.Date(reserva.getDtentrada().getTime()));
			
			if (reserva.getDtsaida()== null)
				stmt.setDate(6, null);
			else		
				stmt.setDate(6, new java.sql.Date(reserva.getDtsaida().getTime()));
			
			stmt.setString(7, reserva.getFlag());
			stmt.setInt(8, reserva.getCodigo());
			stmt.execute();
			conn.commit();
			stmt.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}	
	
	public static void insert(Reserva reserva){
		String sql = "INSERT INTO RESERVA (DTRESERVA, CLIENTE, DTPREVENT, DTPREVSAI, DTENTRADA, DTSAIDA, FLAG) VALUES ( ?,?,?,?,?,?,?)";
		try{
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setDate(1, new java.sql.Date(reserva.getDtreserva().getTime()));
			stmt.setInt(2, reserva.getCliente());
			stmt.setDate(3, new java.sql.Date(reserva.getDtprevent().getTime()));
			stmt.setDate(4, new java.sql.Date(reserva.getDtprevsai().getTime()));
			if (reserva.getDtentrada()== null)
				stmt.setDate(5, null);
			else
				stmt.setDate(5, new java.sql.Date(reserva.getDtentrada().getTime()));
			if (reserva.getDtsaida()== null)
				stmt.setDate(6, null);
			else		
				stmt.setDate(6, new java.sql.Date(reserva.getDtsaida().getTime()));
			
			stmt.setString(7, reserva.getFlag());
			stmt.execute();
			conn.commit();
			stmt.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public static String converte(String data){
		String res="";
		int i;
		for ( i=0; i < data.length() ; i++ ){
			if (data.charAt(i)=='/')
				res = res + ".";
			else
				res = res + data.charAt(i);
		}
		return res;
	}
	
}
