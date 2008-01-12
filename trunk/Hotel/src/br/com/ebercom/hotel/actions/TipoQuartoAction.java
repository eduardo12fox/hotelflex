package br.com.ebercom.hotel.actions;

import java.util.List;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import com.opensymphony.xwork2.ActionSupport;

import br.com.ebercom.hotel.DAO.TipoQuartoDAO;
import br.com.ebercom.hotel.beans.TipoQuarto;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.Annotations;

public class TipoQuartoAction extends ActionSupport {

	private static final long serialVersionUID = 4513273998627249521L;

	private String contentType;
	// parametros para uso na saída da action
	private int contentLength;
	// definição da saída do stream
	private InputStream myStream;		
	
	private TipoQuarto tipoquarto;
	private List<TipoQuarto> tipoquartos;
	private String id;
	private XStream xstream = new XStream();
	
	public String execute(){ return "list";	}
	
	public String include(){
		return "include";
	}
	
	public String list() {
		// definindo o tipo de conteudo que ira retornar para o flex
		this.setContentType("application/xml");
		//definindo o XML de saida
		tipoquartos = TipoQuartoDAO.getTipoQuartoList();
		Annotations.configureAliases(xstream, TipoQuarto.class);
		String tiposXML = xstream.toXML(tipoquartos);
		this.setMyStream(new ByteArrayInputStream( tiposXML.getBytes() ));
		this.setContentLength( tiposXML.getBytes().length);
		
		return "success";
	}

	public String edit() {
		tipoquarto = TipoQuartoDAO.getQuartoById(id); 
		return "input";
	}
	
	public String save(){
		String saveOk;
		if (getFieldErrors().isEmpty()){
			if (nullOrZero(tipoquarto.getCodigo())){
				TipoQuartoDAO.insert(tipoquarto);
				saveOk = "Tipo de Quarto Incluido!";
			}else{
				TipoQuartoDAO.update(tipoquarto);
				saveOk = "Tipo de Quarto Atualizado!";
			}
			// setando qual o sera o conteudo retornado
			this.setMyStream( new ByteArrayInputStream( saveOk.getBytes() ) );
			// definindo o tamanho do contentLength
			this.setContentLength( saveOk.getBytes().length );
			return"saveOk";
		}else{
			return"input";
		}		
	}
	
    private boolean nullOrZero(Integer tipoquartoId) {
        if (null == tipoquartoId) return true;
        return 0 == tipoquartoId;
    }	
	
	public final TipoQuarto getTipoquarto() {
		return tipoquarto;
	}
	
	public final void setTipoquarto(TipoQuarto tipoquarto) {
		this.tipoquarto = tipoquarto;
	}
	
	public final List<TipoQuarto> getTipoquartos() {
		tipoquartos = null;
		tipoquartos = TipoQuartoDAO.getTipoQuartoList();
		return tipoquartos;
	}
	
	public final void setTipoquartos(List<TipoQuarto> tipoquartos) {
		this.tipoquartos = tipoquartos;
	}
	
	public final String getId() {
		return id;
	}
	
	public final void setId(String id) {
		this.id = id;
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

	public final String getContentType() {
		return contentType;
	}

	public final void setContentType(String contentType) {
		this.contentType = contentType;
	}
}