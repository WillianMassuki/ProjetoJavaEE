package br.com.willian.les2019.ecommerce.dominio;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Pedido extends GenericDomain {

	@Column(nullable = false, precision = 6, scale = 2)
	private BigDecimal ValorTotal;
	
	@Column(nullable = false, length=100)
	private String descricao;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date DataCriacao;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAtualizacao;
	
	@Column
	private String StatusPedido;
	
	public String getStatusPedido() {
		return StatusPedido;
	}

	public void setStatusPedido(String statusPedido) {
		StatusPedido = statusPedido;
	}

	public BigDecimal getValorTotal() {
		return ValorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		ValorTotal = valorTotal;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
