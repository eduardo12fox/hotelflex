package br.com.ebercom.hotel.actions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import br.com.ebercom.hotel.beans.Usuario;

import com.opensymphony.xwork2.ActionSupport;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.Annotations;

import br.com.ebercom.hotel.DAO.UsuarioDAO;

public class UsuarioAction extends ActionSupport {

	private static final long serialVersionUID = -8953549372877900110L;
	
	// parametros para uso na saida da action para o Flex
	private String contentType;
	private int contentLength;
	private InputStream myStream;
	private XStream xstream = new XStream();
	
	private Usuario usuario;
	private List<Usuario> usuarios;
	private String id;
	
	public String execute(){
		return "list";
	}
	
	//retorna uma lista dos usuarios cadastrados
	public String list(){
		//definindo o conteudo do retorno
		this.setContentType("application/xml");
		//difinindo o conteudo do xml
		//pegando uma lista dos usuarios
		usuarios = UsuarioDAO.getUsusarioList();
		//configurando as anotations para o xml
		Annotations.configureAliases(xstream , Usuario.class);
		// criando um objeto string que recebera o xml
		String usuariosXML = xstream.toXML(usuarios);
		//setando o stream para o atributo
		this.setMyStream( new ByteArrayInputStream( usuariosXML.getBytes()));
		//definindo o tamanho do retorno
		this.setContentLength( usuariosXML.getBytes().length);
		// retorno para o Struts2
		return "success";
	}
	
	//salva no banco o usuario passado no parametro
	public String save(){
		//variavel que sera alimentada com o retorno para o Flex
		String saveok;
		//verifica se existe algum erro nos campos
		//caso não tenha erros faz a gravação
		if(getFieldErrors().isEmpty()){
			//Faz uma verificacao se o codigo passado no parametro é vazio
			if (nullOrZero(usuario.getCodigo())){
				//caso o atributo do objeto usuario seja 0 ou nulo ele incluira um novo usuario
				UsuarioDAO.insert(usuario);
				//alimenta a variavel de retorno
				saveok = "Usuário Incluido!";
			} else {
				//caso o atributo codigo do objeto usuario não seja nulo nem 0 ele ira atualizar o usuario
				UsuarioDAO.update(usuario);
				//alimenta a variavel de retorno
				saveok = "Usuário Atualizado!";
			}
			// setando o stream para o atributo
			this.setMyStream( new ByteArrayInputStream( saveok.getBytes() ) );
			// difinindo o tamanho do retorno
			this.setContentLength( saveok.getBytes().length);
			//retorno para o Struts
			return "saveOk";
		} else {
			//caso exista erros
			// alimenta a variavel de retorno
			saveok = "Falha na gravação!";
			// setando o stream para o atributo
			this.setMyStream( new ByteArrayInputStream( saveok.getBytes() ) );
			// difinindo o tamanho do retorno
			this.setContentLength( saveok.getBytes().length);			
			//retorne input para o struts
			return "input";
		}
	}
	
	//metodo que verificara se o atributo codigo do objeto usuario é nulo ou zero
	private boolean nullOrZero(Integer codigo) {
		// se o parametro passado for nulo retorna verdadeiro
		if (null == codigo) return true;
		// retorna verdadeiro se o parametro for 0
		return 0 == codigo;
	}

	// geters and setters
	public final String getContentType() {
		return contentType;
	}

	public final void setContentType(String contntType) {
		this.contentType = contntType;
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

	public final Usuario getUsuario() {
		return usuario;
	}

	public final void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public final List<Usuario> getUsuarios() {
		return usuarios;
	}

	public final void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public final String getId() {
		return id;
	}

	public final void setId(String id) {
		this.id = id;
	}
	
}