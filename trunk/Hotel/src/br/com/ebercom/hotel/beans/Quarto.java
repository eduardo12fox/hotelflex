package br.com.ebercom.hotel.beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Quarto")
public class Quarto {

	private int codigo;
	private String descricao;
	private String numero;
	private int tipo;
	private float valor;
	
	
	public final int getCodigo() {
		return codigo;
	}
	public final void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public final String getDescricao() {
		return descricao;
	}
	public final void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public final String getNumero() {
		return numero;
	}
	public final void setNumero(String numero) {
		this.numero = numero;
	}
	public final int getTipo() {
		return tipo;
	}
	public final void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public final float getValor() {
		return valor;
	}
	public final void setValor(float valor) {
		this.valor = valor;
	}

}
