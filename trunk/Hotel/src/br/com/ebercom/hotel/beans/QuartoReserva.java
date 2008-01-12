package br.com.ebercom.hotel.beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("QuartoReserva")
public class QuartoReserva {
	
	private int reserva;
	private int quarto;
	private float valor;
	private String descricao;
	
	public final int getReserva() {
		return reserva;
	}
	public final void setReserva(int reserva) {
		this.reserva = reserva;
	}
	public final int getQuarto() {
		return quarto;
	}
	public final void setQuarto(int quarto) {
		this.quarto = quarto;
	}
	public final float getValor() {
		return valor;
	}
	public final void setValor(float valor) {
		this.valor = valor;
	}
	public final String getDescricao() {
		return descricao;
	}
	public final void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}