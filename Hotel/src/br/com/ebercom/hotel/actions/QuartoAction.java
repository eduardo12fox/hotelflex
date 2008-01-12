package br.com.ebercom.hotel.actions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import br.com.ebercom.hotel.DAO.QuartoDAO;
import br.com.ebercom.hotel.beans.Quarto;
import br.com.ebercom.hotel.beans.TipoQuarto;
import br.com.ebercom.hotel.DAO.TipoQuartoDAO;

import com.opensymphony.xwork2.ActionSupport;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.Annotations;

public class QuartoAction extends ActionSupport {

	private static final long serialVersionUID = -6175335753578515828L;
	
	//parametros para uso na saida da action
	private String contentType;
	private int contentLength;
	//definição da saido do stream
	private InputStream myStream;
	private XStream xstream = new XStream();
	
	private Quarto quarto;
	private List<Quarto> quartos;
	private String id;
	private List<TipoQuarto> tiposList;
	private String campo;
	private String valor;
	private Boolean like;
	
	public String execute() { return "list"; }
	
	public String list(){
		//definindo o conteudo que sera retornado para o Flex
		this.setContentType("application/xml");
		//definindo o XML de saida
		quartos = QuartoDAO.getQuartosList();
		Annotations.configureAliases(xstream, Quarto.class);
		String quartosXML = xstream.toXML(quartos);
		this.setMyStream(new ByteArrayInputStream( quartosXML.getBytes() ));
		//definindo o tamanho do contentLength
		this.setContentLength( quartosXML.getBytes().length );
		//retorno para o Struts
		return "success";
	}

	public String listByCampo(){
		this.setContentType( "application/xml" );
		quartos = QuartoDAO.getQuartosByCampo(campo, valor, like);
		Annotations.configureAliases(xstream, Quarto.class);
		String quartosEmXML = xstream.toXML(quartos);
		this.setMyStream( new ByteArrayInputStream( quartosEmXML.getBytes() ));
		this.setContentLength( quartosEmXML.getBytes().length );
		return "success";
	}
	
	public String include(){
		return "include";
	}
	
	public String save(){
		String saveok;
		if (getFieldErrors().isEmpty()){
			if (nullOrZero(quarto.getCodigo())){
				QuartoDAO.insert(quarto);
				saveok = "Quarto Incluido!";
			}else{
				QuartoDAO.update(quarto);
				saveok = "Quarto Atualizado!";
			}
			// setando o stream para o tributo
			this.setMyStream( new ByteArrayInputStream( saveok.getBytes() ) );
			// definindo o tamanho do contentLength
			this.setContentLength( saveok.getBytes().length );				
			return"saveOk";
		}else{
			return"input";
		}
	}
	
	public String edit(){
		quarto = QuartoDAO.getQuartoByID(id);
		return "input";
	}

    private boolean nullOrZero(Integer quartoId) {
        if(null == quartoId) return true;
        return 0 == quartoId;
    }
	
//gets and seters

	public final List<Quarto> getQuartos() {
		quartos = null;
		quartos = QuartoDAO.getQuartosList();
		return quartos;
	}
	public final void setQuartos(List<Quarto> quartos) {
		this.quartos = quartos;
	}

	public final void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public final Quarto getQuarto() {
		return quarto;
	}
	
	public final String getId() {
		return id;
	}

	public final void setId(String id) {
		this.id = id;
	}

	public final List<TipoQuarto> getTiposList() {
		if (tiposList == null)
			tiposList = TipoQuartoDAO.getTipoQuartoList();
		return tiposList;
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

	public final XStream getStream() {
		return xstream;
	}

	public final void setStream(XStream stream) {
		this.xstream = stream;
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

	public final Boolean getLike() {
		return like;
	}

	public final void setLike(Boolean like) {
		this.like = like;
	}
}