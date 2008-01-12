package br.com.ebercom.hotel.actions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.thoughtworks.xstream.XStream;

import br.com.ebercom.hotel.beans.Usuario;
import br.com.ebercom.hotel.DAO.UsuarioDAO;

public class LoginAction {

	// parametros para uso na saida da action para o Flex
	private String contentType;
	private int contentLength;
	private InputStream myStream;
	private XStream xstream = new XStream();	
	
	private Usuario usuario;	

	public String checkLogin(){
		String retorno;
		this.setContentType("application/xml");
		Usuario user = UsuarioDAO.getUsuarioByName(usuario.getNome());
		if (user.getNome() != null){
			if (user.getNome().equals(usuario.getNome()) && user.getSenha().equals(usuario.getSenha()) ){
				retorno = "valid";
				this.setMyStream( new ByteArrayInputStream (retorno.getBytes()));
				this.setContentLength(retorno.getBytes().length);
				return "valid";
			}
		}
		retorno = "invalid";
		this.setMyStream( new ByteArrayInputStream (retorno.getBytes()));
		this.setContentLength(retorno.getBytes().length);			
		return "invalid";
	}

	public final Usuario getUsuario() {
		return usuario;
	}

	public final void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public final XStream getXstream() {
		return xstream;
	}

	public final void setXstream(XStream xstream) {
		this.xstream = xstream;
	}	
}
