package br.com.ebercom.hotel.beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("TipoQuarto")
public class TipoQuarto {

	private int codigo;
	private String descricao;
	
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
}
