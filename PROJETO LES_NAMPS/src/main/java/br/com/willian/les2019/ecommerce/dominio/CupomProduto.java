package br.com.willian.les2019.ecommerce.dominio;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class CupomProduto extends GenericDomain {

	@OneToOne
	private Produto produto;
	
	@OneToOne
	private Cupom cupom;
	
	@Column(nullable = false, length = 100)
	private int quantidade;

	@Column(nullable = false, precision = 6, scale = 2)
	private BigDecimal precoparcial;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date DataCriacao;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAtualizacao;
	
	public Cupom getCupom() {
		return cupom;
	}

	public void setCupom(Cupom cupom) {
		this.cupom = cupom;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPrecoparcial() {
		return precoparcial;
	}

	public void setPrecoparcial(BigDecimal precoparcial) {
		this.precoparcial = precoparcial;
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
	

}
