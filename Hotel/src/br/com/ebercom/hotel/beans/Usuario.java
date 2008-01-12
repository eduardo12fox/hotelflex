package br.com.ebercom.hotel.beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Usuario")
public class Usuario {

	private int codigo;
	private String nome;
	private String senha;
	private String nivel;
	
	public final int getCodigo() {
		return codigo;
	}
	public final void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public final String getNome() {
		return nome;
	}
	public final void setNome(String nome) {
		this.nome = nome;
	}
	public final String getSenha() {
		return senha;
	}
	public final void setSenha(String senha) {
		this.senha = senha;
	}
	public final String getNivel() {
		return nivel;
	}
	public final void setNivel(String nivel) {
		this.nivel = nivel;
	}
}
