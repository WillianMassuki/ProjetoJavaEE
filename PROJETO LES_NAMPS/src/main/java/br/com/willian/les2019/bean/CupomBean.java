package br.com.willian.les2019.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.willian.les2019.ecommerce.dao.CupomDAO;
import br.com.willian.les2019.ecommerce.dao.CupomProdutoDAO;
import br.com.willian.les2019.ecommerce.dominio.Cupom;
import br.com.willian.les2019.ecommerce.dominio.CupomProduto;
import br.com.willian.les2019.ecommerce.dominio.Item;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CupomBean implements Serializable {
	private static final Logger Log = Logger.getLogger("CupomBean");
	private Cupom cupom;
	private List<Cupom> cupoms;
	private CupomProduto cupomproduto;
	private List<CupomProduto> cupomprodutos;
	private List<Item> itens;
	
	public CupomProduto getCupomproduto() {
		return cupomproduto;
	}

	public void setCupomproduto(CupomProduto cupomproduto) {
		this.cupomproduto = cupomproduto;
	}

	public List<CupomProduto> getCupomprodutos() {
		return cupomprodutos;
	}

	public void setCupomprodutos(List<CupomProduto> cupomprodutos) {
		this.cupomprodutos = cupomprodutos;
	}

	public static Logger getLog() {
		return Log;
	}

	public Cupom getCupom() {
		return cupom;
	}

	public void setCupom(Cupom cupom) {
		this.cupom = cupom;
	}

	public List<Cupom> getCupoms() {
		return cupoms;
	}

	public void setCupoms(List<Cupom> cupoms) {
		this.cupoms = cupoms;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
	
	@PostConstruct
	public void listar()
	{
		try {

		CupomDAO cupomDAO = new CupomDAO();
		cupoms = cupomDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar as cidades");
			erro.printStackTrace();
		}
	}
	
	public void atualizarstatus(ActionEvent evento)
	{
		Cupom cupom = (Cupom) evento.getComponent().getAttributes().get("CupomSelecionado");
		Log.info("Status: " + cupom.isAprovado());
		
		if(!cupom.isAprovado())
		{
			cupom.setAprovado(true);
			CupomDAO cupoDAO = new CupomDAO();
			cupoDAO.merge(cupom);
			
			Log.info("Status atualizado" + cupom.isAprovado());
			
		}
		else
		{
			Log.info("Status já Aprovado:" + cupom.isAprovado());
		}
		
	}
	
	public void calcularcupom(ActionEvent evento) {
		Cupom cupom = (Cupom) evento.getComponent().getAttributes().get("CupomSelecionado");
		Log.info("Codigo: " + cupom.getCodigo());
			
		CupomProdutoDAO cupomDAO = new CupomProdutoDAO();
		cupomprodutos = cupomDAO.listar();
		
		for(int posicao = 0; posicao < cupomprodutos.size(); posicao++)
		{
			// SOMAR O TOTAL DE CUPONS
			BigDecimal valortotal = cupom.getPedido().getPrecoTotal();
			Log.info("testando valor total: " + valortotal);
		}
		

	}
	

}
