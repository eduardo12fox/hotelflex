package br.com.ebercom.hotel.beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Cliente")
public class Cliente {
	
	private int codigo;
	private String nome;
	private String cpfcnpj;
	private String logradouro;
	private String cidade;
	private String empresa;
	private String tel1;
	private String tel2;
	
	
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
	public final String getCpfcnpj() {
		return cpfcnpj;
	}
	public final void setCpfcnpj(String cpfcnpj) {
		this.cpfcnpj = cpfcnpj;
	}
	public final String getLogradouro() {
		return logradouro;
	}
	public final void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public final String getCidade() {
		return cidade;
	}
	public final void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public final String getEmpresa() {
		return empresa;
	}
	public final void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public final String getTel1() {
		return tel1;
	}
	public final void setTel1(String tel1) {
		this.tel1 = tel1;
	}
	public final String getTel2() {
		return tel2;
	}
	public final void setTel2(String tel2) {
		this.tel2 = tel2;
	}

}
