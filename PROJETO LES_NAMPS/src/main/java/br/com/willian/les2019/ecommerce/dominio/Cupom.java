package br.com.willian.les2019.ecommerce.dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@SuppressWarnings("serial")
@Entity
public class Cupom extends GenericDomain {
	
	@Column(nullable = false)
	private String CodigoCupom;
	
	@OneToOne
	private Venda pedido;
	
	@Column(nullable = false, length=100)
	private String motivo;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date DataCriacao;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAtualizacao;
	
	@Column
	private boolean aprovado;
	
	public boolean isAprovado() {
		return aprovado;
	}

	public void setAprovado(boolean aprovado) {
		this.aprovado = aprovado;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getCodigoCupom() {
		return CodigoCupom;
	}

	public void setCodigoCupom(String codigoCupom) {
		CodigoCupom = codigoCupom;
	}

	public Date getDataCriacao() {
		return DataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		DataCriacao = dataCriacao;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public Venda getPedido() {
		return pedido;
	}

	public void setPedido(Venda pedido) {
		this.pedido = pedido;
	}
	
	
	
	
	
	

}
