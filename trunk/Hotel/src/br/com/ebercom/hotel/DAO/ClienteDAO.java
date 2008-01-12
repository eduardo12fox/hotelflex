package br.com.ebercom.hotel.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.ebercom.hotel.beans.Cliente;

public class ClienteDAO {

	public static void insert(Cliente cliente){
		try{
			//Prepara a sql;
			String sql = "INSERT INTO CLIENTE (NOME, CPFCNPJ, LOGRADOURO, CIDADE, EMPRESA, TEL1, TEL2 ) VALUES (?,?,?,?,?,?,?)";
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpfcnpj());
			stmt.setString(3, cliente.getLogradouro());
			stmt.setString(4, cliente.getCidade());
			stmt.setString(5, cliente.getEmpresa());
			stmt.setString(6, cliente.getTel1());
			stmt.setString(7, cliente.getTel2());
			stmt.execute();
			stmt.close();
			conn.commit();
			
		} catch (java.sql.SQLException e){
			e.printStackTrace();
		}
	}

	public static void update(Cliente cliente) {
		try {
			//Prepara a sql
			String sql = "UPDATE CLIENTE SET NOME=?, CPFCNPJ=?, LOGRADOURO=?, CIDADE=?, EMPRESA=?, TEL1=?, TEL2=? WHERE CODIGO=?";
			//Pega uma conexão
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			//Preenche os valores dos PreparedStatements
			//stmt.setInt(1, quarto.getCodigo());
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpfcnpj());
			stmt.setString(3, cliente.getLogradouro());
			stmt.setString(4, cliente.getCidade());
			stmt.setString(5, cliente.getEmpresa());
			stmt.setString(6, cliente.getTel1());
			stmt.setString(7, cliente.getTel2());
			stmt.setInt(8, cliente.getCodigo());
			//Executa o PreparedStatement
			stmt.execute();
			//Fecha a conexao e o PreparedStatement
			stmt.close();
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}
	
	public static List<Cliente> getClientesByCampo(String campo, String valor){
		List<Cliente> clientes = new ArrayList<Cliente>();
		String sql = "";
		try{
			if ((campo.equals("codigo")) || (campo.equals("cpfcnpj")))
				sql = "SELECT * FROM CLIENTE WHERE " + campo + " = ?";
			else
				sql = "SELECT * FROM CLIENTE WHERE " + campo + " like ?";						
			
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			if ((campo.equals("codigo")) || (campo.equals("cpfcnpj")))
				stmt.setObject(1, valor);				
			else
				stmt.setObject(1, valor +"%");
			ResultSet res = stmt.executeQuery();
			while (res.next()){
				Cliente obj = new Cliente();
				obj.setCodigo(res.getInt("Codigo"));
				obj.setNome(res.getString("Nome"));
				obj.setLogradouro(res.getString("Logradouro"));
				obj.setCidade(res.getString("Cidade"));
				obj.setCpfcnpj(res.getString("Cpfcnpj"));
				obj.setEmpresa(res.getString("Empresa"));
				obj.setTel1(res.getString("Tel1"));
				obj.setTel2(res.getString("Tel2"));
				clientes.add(obj);
			}
			stmt.close();
			res.close();
			conn.commit();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return clientes;
	}
	
	public static List<Cliente> getClientesList() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		try{
			String sql = "SELECT * FROM CLIENTE ORDER BY CODIGO";
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet res = stmt.executeQuery();
			while (res.next()){
				Cliente obj = new Cliente();
				obj.setCodigo(res.getInt("Codigo"));
				obj.setNome(res.getString("Nome"));
				obj.setLogradouro(res.getString("logradouro"));
				obj.setCidade(res.getString("Cidade"));
				obj.setCpfcnpj(res.getString("Cpfcnpj"));
				obj.setEmpresa(res.getString("Empresa"));
				obj.setTel1(res.getString("Tel1"));
				obj.setTel2(res.getString("Tel2"));
				clientes.add(obj);
			}
			stmt.close();
			res.close();
			conn.commit();
		} catch (SQLException e){
			e.printStackTrace();
		}

		return clientes;
	}

	public static Cliente getClienteById(String id) {
		Cliente cliente = new Cliente();
		try{
			String sql = "SELECT * FROM CLIENTE WHERE CODIGO=?";
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			ResultSet res = stmt.executeQuery();
			if (res.next()){
				cliente.setCodigo(res.getInt("CODIGO"));
				cliente.setNome(res.getString("NOME"));
				cliente.setCpfcnpj(res.getString("CPFCNPJ"));
				cliente.setLogradouro(res.getString("LOGRADOURO"));
				cliente.setCidade(res.getString("CIDADE"));
				cliente.setEmpresa(res.getString("EMPRESA"));
				cliente.setTel1(res.getString("TEL1"));
				cliente.setTel2(res.getString("TEL2"));
			}
			stmt.close();
			res.close();
			conn.commit();
		} catch (SQLException e){
			e.printStackTrace();
		}

		return cliente;
	}
	
}
