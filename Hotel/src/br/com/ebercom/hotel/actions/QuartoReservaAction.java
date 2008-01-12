package br.com.ebercom.hotel.actions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import br.com.ebercom.hotel.beans.QuartoReserva;
import br.com.ebercom.hotel.DAO.QuartoReservaDAO;

import com.opensymphony.xwork2.ActionSupport;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.Annotations;

public class QuartoReservaAction extends ActionSupport {

	private static final long serialVersionUID = 3770857881051883004L;

	//variaveis que serão utilizadas para retorno pro Flex
	private String contentType;
	private int contentLength;
	private InputStream myStream;
	private XStream xstream = new XStream();
	
	private QuartoReserva quartoReserva;
	private List<QuartoReserva> quartosReserva;
	private String reserva;
	private String quarto;
	
	public String execute(){
		return "list";
	}
	
	public String remove(){
		String saveok;
		QuartoReservaDAO.remove(reserva, quarto);
		saveok = "Quarto excluido da reserva!";
		this.setContentType("application/xml");
		this.setMyStream( new ByteArrayInputStream ( saveok.getBytes() ) );
		this.setContentLength( saveok.getBytes().length );		
		return "success";
	}
	
	public String include(){
		String saveok;
		if (getFieldErrors().isEmpty()){
			QuartoReservaDAO.insert(quartoReserva);
			saveok = "Quarto Incluido!";
			this.setContentType("application/xml");
			this.setMyStream( new ByteArrayInputStream ( saveok.getBytes() ) );
			this.setContentLength( saveok.getBytes().length );
			return "saveOk";
		} else {
			return "input";
		}
	}
	
	public String list(){
		this.setContentType("application/xml");
		quartosReserva = QuartoReservaDAO.getListByReserva( reserva );
		Annotations.configureAliases(xstream, QuartoReserva.class);
		String listaXML = xstream.toXML( quartosReserva );
		this.setMyStream( new ByteArrayInputStream ( listaXML.getBytes() ) );
		this.setContentLength( listaXML.getBytes().length );
		return "success";
	}
	
	//geters and seters
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
	public final QuartoReserva getQuartoReserva() {
		return quartoReserva;
	}
	public final void setQuartoReserva(QuartoReserva quartoReserva) {
		this.quartoReserva = quartoReserva;
	}
	public final List<QuartoReserva> getQuartosReserva() {
		return quartosReserva;
	}
	public final void setQuartosReserva(List<QuartoReserva> quartosReserva) {
		this.quartosReserva = quartosReserva;
	}
	public final String getReserva() {
		return reserva;
	}
	public final void setReserva(String reserva) {
		this.reserva = reserva;
	}

	public final String getQuarto() {
		return quarto;
	}

	public final void setQuarto(String quarto) {
		this.quarto = quarto;
	}
}
