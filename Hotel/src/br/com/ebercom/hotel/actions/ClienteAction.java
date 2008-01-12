package br.com.ebercom.hotel.actions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import br.com.ebercom.hotel.beans.Cliente;
import br.com.ebercom.hotel.DAO.ClienteDAO;

import com.opensymphony.xwork2.ActionSupport;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.Annotations;


public class ClienteAction extends ActionSupport {

	private static final long serialVersionUID = -1958700967025041008L;

	// parametros para uso na saída da action
	private String contentType;
	private int contentLength;
	// definição da saída do stream
	private InputStream myStream;	
	private XStream xstream = new XStream();
	
	private Cliente cliente;
	private List<Cliente> clientes;
	private String id;
	String campo;
	String valor;
	
	public String execute(){
		return "list";
	}
	
	public String list(){
		
		// definindo o tipo de conteúdo
		this.setContentType( "application/xml" );	
		
		// definindo o XML de saida
		clientes = ClienteDAO.getClientesList();
		Annotations.configureAliases(xstream, Cliente.class);
		String contatosEmXML = xstream.toXML(clientes);	
		
		// setando o stream para o tributo
		this.setMyStream( new ByteArrayInputStream( contatosEmXML.getBytes() ) );

		// definindo o tamanho do contentLength
		this.setContentLength( contatosEmXML.getBytes().length );		
		
		return "success";	
	}
	
	public String include(){
		return "include";
	}
	
	public String edit(){
		cliente = ClienteDAO.getClienteById(id);
		return "input";
	}
	
	public String getClienteByCampo(){
		clientes = ClienteDAO.getClientesByCampo(campo, valor);
		Annotations.configureAliases(xstream, Cliente.class);
		String contatosEmXML = xstream.toXML(clientes);
		this.setContentType( "application/xml" );
		this.setMyStream( new ByteArrayInputStream ( contatosEmXML.getBytes() ) );
		this.setContentLength( contatosEmXML.getBytes().length );
		return "success";
	}
	
	public String save(){
		String saveok;
		if (getFieldErrors().isEmpty()){
			if (nullOrZero(cliente.getCodigo())){
				ClienteDAO.insert(cliente);
				saveok = "Cliente Incluido!";
			}else{
				ClienteDAO.update(cliente);
				saveok = "Cliente Atualizado!";
			}
			// setando o stream para o tributo
			this.setMyStream( new ByteArrayInputStream( saveok.getBytes() ) );
			// definindo o tamanho do contentLength
			this.setContentLength( saveok.getBytes().length );					
			return "saveOk";
		}else{
			return "input";
		}
	}
	
	private boolean nullOrZero(Integer clienteId) {
		if (null == clienteId) return true;
		return 0 == clienteId;
	}
	
	public final Cliente getCliente() {
		return cliente;
	}
	public final void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public final List<Cliente> getClientes() {
		clientes = null;
		clientes = ClienteDAO.getClientesList();
		return clientes;
	}
	public final void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	public final String getId() {
		return id;
	}
	public final void setId(String id) {
		this.id = id;
	}

	public final String getContentType() {
		return contentType;
	}

	public final void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public final int getContentLength() {
		return contentLength;
	}

	public final void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}

	public final InputStream getMyStream() {
		return myStream;
	}

	public final void setMyStream(InputStream myStream) {
		this.myStream = myStream;
	}

	public final String getCampo() {
		return campo;
	}

	public final void setCampo(String campo) {
		this.campo = campo;
	}

	public final String getValor() {
		return valor;
	}

	public final void setValor(String valor) {
		this.valor = valor;
	}

}
