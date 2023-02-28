package br.com.willian.les2019.ecommerce.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity
public class Cartao extends GenericDomain {
	
	@Column(length = 25)
	private String NomeCartao;
	
	@Column(length = 25)
	private String NumeroCartao;
	
	@Column(length = 25)
	private String CodigoSeguranca;
	
	@OneToOne
	private Bandeira bandeira;
	
	public Bandeira getBandeira() {
		return bandeira;
	}
	public void setBandeira(Bandeira bandeira) {
		this.bandeira = bandeira;
	}
	public String getNomeCartao() {
		return NomeCartao;
	}
	public void setNomeCartao(String nomeCartao) {
		NomeCartao = nomeCartao;
	}
	public String getNumeroCartao() {
		return NumeroCartao;
	}
	public void setNumeroCartao(String numeroCartao) {
		NumeroCartao = numeroCartao;
	}
	public String getCodigoSeguranca() {
		return CodigoSeguranca;
	}
	public void setCodigoSeguranca(String codigoSeguranca) {
		CodigoSeguranca = codigoSeguranca;
	}
	
	
}
