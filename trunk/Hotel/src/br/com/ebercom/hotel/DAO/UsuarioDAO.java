package br.com.ebercom.hotel.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.ebercom.hotel.beans.Usuario;
import br.com.ebercom.hotel.DAO.ConnectionFactory;

public class UsuarioDAO {

	public static void insert(Usuario usuario){
		try{
			//prepara sql;
			String sql = "INSERT INTO USUARIOS ( NOME, SENHA, NIVEL) VALUES  (?,?,?)";
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getSenha());
			stmt.setString(3, usuario.getNivel());
			stmt.execute();
			stmt.close();
			conn.commit();
			
		} catch (java.sql.SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void update(Usuario usuario){
		try{
			String sql = "UPDATE USUARIOS SET NOME=?, SENHA=?, NIVEL=? WHERE CODIGO=?";
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getSenha());
			stmt.setString(3, usuario.getNivel());
			stmt.setInt(4, usuario.getCodigo());
			stmt.execute();
			stmt.close();
			conn.commit();
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Usuario> getUsusarioList(){
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try{
			String sql = "SELECT * FROM USUARIOS ORDER BY CODIGO";
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet res = stmt.executeQuery();
			while (res.next()){
				Usuario obj = new Usuario();
				obj.setCodigo(res.getInt("Codigo"));
				obj.setNome(res.getString("Nome"));
				obj.setSenha(res.getString("Senha"));
				obj.setNivel(res.getString("Nivel"));
				usuarios.add(obj);
			}
			stmt.close();
			res.close();
			conn.commit();
		} catch (java.sql.SQLException e){
			e.printStackTrace();
		}
		return usuarios;
	}
	
	public static Usuario getUsuarioById(String id){
		Usuario usuario = new Usuario();
		try{
			String sql = "SELECT * FROM USUARIOS WHERE CODIGO=? ORDER BY CODIGO";
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			ResultSet res = stmt.executeQuery();
			while (res.next()){
				usuario.setCodigo(res.getInt("Codigo"));
				usuario.setNome(res.getString("Nome"));
				usuario.setSenha(res.getString("Senha"));
				usuario.setNivel(res.getString("Nivel"));
			}
			stmt.close();
			res.close();
			conn.commit();
		} catch (java.sql.SQLException e){
			e.printStackTrace();
		}
		return usuario;
	}
	
	public static Usuario getUsuarioByName(String name){
		Usuario usuario = new Usuario();
		try{
			String sql = "SELECT * FROM USUARIOS WHERE NOME=? ORDER BY CODIGO";
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			ResultSet res = stmt.executeQuery();
			while (res.next()){
				usuario.setCodigo(res.getInt("Codigo"));
				usuario.setNome(res.getString("Nome"));
				usuario.setSenha(res.getString("Senha"));
				usuario.setNivel(res.getString("Nivel"));
			}
			stmt.close();
			res.close();
			conn.commit();
		} catch (java.sql.SQLException e){
			e.printStackTrace();
		}
		return usuario;
	}	
}
