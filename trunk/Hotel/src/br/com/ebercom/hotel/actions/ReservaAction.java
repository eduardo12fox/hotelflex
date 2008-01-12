package br.com.ebercom.hotel.actions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import br.com.ebercom.hotel.beans.Reserva;
import br.com.ebercom.hotel.DAO.ReservaDAO;

import com.opensymphony.xwork2.ActionSupport;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.Annotations;

import java.util.List;

public class ReservaAction extends ActionSupport {

	private static final long serialVersionUID = 9125287539374146526L;
	
	//parametros para uso na saida para a interface
	private String contentType;
	private int contentLength;
	private InputStream myStream;
	private XStream xstream = new XStream();
	
	//variáveis que serão utilizadas pelo struts
	private Reserva reserva;
	private List<Reserva> reservas;
	private String id;
	private String campo;
	private String valor;
	private String like;
	private String flag;
	
	private String cliente;
	private String dtReserva1;
	private String dtReserva2;
	private String dtPrevEnt1;
	private String dtPrevEnt2;
	private String dtHosp1;
	private String dtHosp2;
	private String flag1;
	private String flag2;
	private String flag3;
	private String flag4;
	private String codigo;
	
	public String execute() { return "list"; }
	
	public String getList(){
		this.setContentType("application/xml");
		reservas = ReservaDAO.getListReservas(cliente, dtReserva1, dtReserva2, dtPrevEnt1, dtPrevEnt2, dtHosp1, dtHosp2, flag1, flag2, flag3, flag4);
		Annotations.configureAliases(xstream, Reserva.class);
		String listaXML = xstream.toXML(reservas);
		this.setMyStream( new ByteArrayInputStream ( listaXML.getBytes() ) );
		this.setContentLength( listaXML.getBytes().length );
		return "success";
	}
	
	public String getLastCod(){
		String last;
		last = ReservaDAO.getLast();
		this.setContentType("application/xml");
		this.setMyStream( new ByteArrayInputStream (last.getBytes()) );
		this.setContentLength( last.getBytes().length );
		return "success";
	}

	public String remove(){
		String setOk;
		ReservaDAO.remove(codigo);
		setOk = "Reserva Abortada!";
		this.setContentType("application/xml");
		this.setMyStream( new ByteArrayInputStream (setOk.getBytes()) );
		this.setContentLength( setOk.getBytes().length );
		return "success";
	}
	
	public String setFlag(){
		String setOk;
		ReservaDAO.updateFlag(id, flag);
		setOk = "Sucesso!";
		this.setContentType("application/xml");
		this.setMyStream( new ByteArrayInputStream (setOk.getBytes()) );
		this.setContentLength( setOk.getBytes().length );
		return "success";
	}
	
	public String save(){
		String saveok;
		if (getFieldErrors().isEmpty()){
			if (nullOrZero(reserva.getCodigo())){
				ReservaDAO.insert(reserva);
				saveok = "Reserva Incluida!";
			} else {
				ReservaDAO.update(reserva);
				saveok = "Reserva Atualizada!";
			}
			this.setContentType("application/xml");
			this.setMyStream( new ByteArrayInputStream ( saveok.getBytes() ) );
			this.setContentLength( saveok.getBytes().length );
			return "saveOk";
		} else {
			saveok = "Erro!";
			this.setContentType("application/xml");
			this.setMyStream( new ByteArrayInputStream ( saveok.getBytes() ) );
			this.setContentLength( saveok.getBytes().length );
			return "input";
		}
		
	}
	
	private boolean nullOrZero(Integer codigo) {
		if (null == codigo) return true;
		return 0 == codigo;
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
	public final Reserva getReserva() {
		return reserva;
	}
	public final void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	public final List<Reserva> getReservas() {
		return reservas;
	}
	public final void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	public final String getId() {
		return id;
	}
	public final void setId(String id) {
		this.id = id;
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
	public final String getLike() {
		return like;
	}
	public final void setLike(String like) {
		this.like = like;
	}

	public final String getCliente() {
		return cliente;
	}

	public final void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public final String getDtReserva1() {
		return dtReserva1;
	}

	public final void setDtReserva1(String dtReserva1) {
		this.dtReserva1 = dtReserva1;
	}

	public final String getDtReserva2() {
		return dtReserva2;
	}

	public final void setDtReserva2(String dtReserva2) {
		this.dtReserva2 = dtReserva2;
	}

	public final String getDtPrevEnt1() {
		return dtPrevEnt1;
	}

	public final void setDtPrevEnt1(String dtPrevEnt1) {
		this.dtPrevEnt1 = dtPrevEnt1;
	}

	public final String getDtPrevEnt2() {
		return dtPrevEnt2;
	}

	public final void setDtPrevEnt2(String dtPrevEnt2) {
		this.dtPrevEnt2 = dtPrevEnt2;
	}

	public final String getDtHosp1() {
		return dtHosp1;
	}

	public final void setDtHosp1(String dtHosp1) {
		this.dtHosp1 = dtHosp1;
	}

	public final String getDtHosp2() {
		return dtHosp2;
	}

	public final void setDtHosp2(String dtHosp2) {
		this.dtHosp2 = dtHosp2;
	}

	public final String getFlag1() {
		return flag1;
	}

	public final void setFlag1(String flag1) {
		this.flag1 = flag1;
	}

	public final String getFlag2() {
		return flag2;
	}

	public final void setFlag2(String flag2) {
		this.flag2 = flag2;
	}

	public final String getFlag3() {
		return flag3;
	}

	public final void setFlag3(String flag3) {
		this.flag3 = flag3;
	}

	public final String getFlag4() {
		return flag4;
	}

	public final void setFlag4(String flag4) {
		this.flag4 = flag4;
	}

	public final String getFlag() {
		return flag;
	}

	public final void setFlag(String flag) {
		this.flag = flag;
	}

	public final String getCodigo() {
		return codigo;
	}

	public final void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
}
