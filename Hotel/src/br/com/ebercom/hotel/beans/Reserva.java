package br.com.ebercom.hotel.beans;

import java.util.Date;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Reserva")
public class Reserva {

		private int codigo;
		private Date dtreserva;
		private int cliente;
		private Date dtprevent;
		private Date dtprevsai;
		private Date dtentrada;
		private Date dtsaida;
		private String flag;
		private String nome;
		
		public final int getCodigo() {
			return codigo;
		}
		public final void setCodigo(int codigo) {
			this.codigo = codigo;
		}
		public final Date getDtreserva() {
			return dtreserva;
		}
		public final void setDtreserva(Date dtreserva) {
			this.dtreserva = dtreserva;
		}
		public final int getCliente() {
			return cliente;
		}
		public final void setCliente(int cliente) {
			this.cliente = cliente;
		}
		public final Date getDtprevent() {
			return dtprevent;
		}
		public final void setDtprevent(Date dtprevent) {
			this.dtprevent = dtprevent;
		}
		public final Date getDtprevsai() {
			return dtprevsai;
		}
		public final void setDtprevsai(Date dtprevsai) {
			this.dtprevsai = dtprevsai;
		}
		public final Date getDtentrada() {
			return dtentrada;
		}
		public final void setDtentrada(Date dtentrada) {
			this.dtentrada = dtentrada;
		}
		public final Date getDtsaida() {
			return dtsaida;
		}
		public final void setDtsaida(Date dtsaida) {
			this.dtsaida = dtsaida;
		}
		public final String getFlag() {
			return flag;
		}
		public final void setFlag(String flag) {
			this.flag = flag;
		}
		public final String getNome() {
			return nome;
		}
		public final void setNome(String nome) {
			this.nome = nome;
		}	
}
