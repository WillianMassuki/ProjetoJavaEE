package br.com.willian.les2019.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import br.com.willian.les2019.ecommerce.dao.CupomProdutoDAO;
import br.com.willian.les2019.ecommerce.dominio.CupomProduto;
import br.com.willian.les2019.ecommerce.dominio.Produto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CupomProdutoBean implements Serializable {
	private CupomProduto cupomproduto;
	private List<CupomProduto> cupomsProdutos;
	private Produto produto;
	private List<Produto> produtos;
	public CupomProduto getCupomproduto() {
		return cupomproduto;
	}
	public void setCupomproduto(CupomProduto cupomproduto) {
		this.cupomproduto = cupomproduto;
	}
	public List<CupomProduto> getCupomsProdutos() {
		return cupomsProdutos;
	}
	public void setCupomsProdutos(List<CupomProduto> cupomsProdutos) {
		this.cupomsProdutos = cupomsProdutos;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	@PostConstruct
	public void listar()
	{
		CupomProdutoDAO cpmProduto = new CupomProdutoDAO();
		cupomsProdutos = cpmProduto.listar();
	}
	
	

}
