package br.com.willian.les2019.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.willian.les2019.ecommerce.dao.CartaoDAO;
import br.com.willian.les2019.ecommerce.dao.CupomDAO;
import br.com.willian.les2019.ecommerce.dao.CupomProdutoDAO;
import br.com.willian.les2019.ecommerce.dao.ProdutoDAO;
import br.com.willian.les2019.ecommerce.dao.VendaTesteDAO;
import br.com.willian.les2019.ecommerce.dominio.Cartao;
import br.com.willian.les2019.ecommerce.dominio.Cupom;
import br.com.willian.les2019.ecommerce.dominio.CupomProduto;
import br.com.willian.les2019.ecommerce.dominio.Item;
import br.com.willian.les2019.ecommerce.dominio.Produto;
import br.com.willian.les2019.ecommerce.dominio.Venda;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class VendaTesteBean implements Serializable {
	private static final Logger Log = Logger.getLogger("CupomBean");

	private CupomProduto cupomproduto;

	private List<CupomProduto> cuponsprodutos;

	private List<Cartao> cartoes;

	private Cupom cupom;

	private List<Cupom> cupons;

	private Venda venda;

	private List<Produto> produtos;
	private List<Item> itensVenda;

	
	public static Logger getLog() {
		return Log;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Item> getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(List<Item> itensVenda) {
		this.itensVenda = itensVenda;
	}

	public List<Cartao> getCartoes() {
		return cartoes;
	}

	public void setCartoes(List<Cartao> cartoes) {
		this.cartoes = cartoes;
	}

	public Cupom getCupom() {
		return cupom;
	}

	public void setCupom(Cupom cupom) {
		this.cupom = cupom;
	}

	public List<Cupom> getCupons() {
		return cupons;
	}

	public void setCupons(List<Cupom> cupons) {
		this.cupons = cupons;
	}

	public CupomProduto getCupomproduto() {
		return cupomproduto;
	}

	public void setCupomproduto(CupomProduto cupomproduto) {
		this.cupomproduto = cupomproduto;
	}

	public List<CupomProduto> getCuponsprodutos() {
		return cuponsprodutos;
	}

	public void setCuponsprodutos(List<CupomProduto> cuponsprodutos) {
		this.cuponsprodutos = cuponsprodutos;
	}

	@PostConstruct
	public void novo() {
		try {
			venda = new Venda();
			venda.setPrecoTotal(new BigDecimal("0.00"));

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar("descricao");

			itensVenda = new ArrayList<>();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar a tela de vendas");
			erro.printStackTrace();
		}
	}

	public void adicionar(ActionEvent evento) {
		Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

		int achou = -1;
		for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
			if (itensVenda.get(posicao).getProduto().equals(produto)) {
				achou = posicao;
			}
		}

		if (achou < 0) {
			Item itemVenda = new Item();
			itemVenda.setPrecoParcial(produto.getPreco());
			itemVenda.setProduto(produto);
			itemVenda.setQuantidade(new Short("1"));

			itensVenda.add(itemVenda);
		} else {
			Item itemVenda = itensVenda.get(achou);
			itemVenda.setQuantidade(new Short(itemVenda.getQuantidade() + 1 + ""));
			itemVenda.setPrecoParcial(produto.getPreco().multiply(new BigDecimal(itemVenda.getQuantidade())));
		}

		calcular();
	}

	public void remover(ActionEvent evento) {
		Item itemVenda = (Item) evento.getComponent().getAttributes().get("itemSelecionado");

		int achou = -1;
		for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
			if (itensVenda.get(posicao).getProduto().equals(itemVenda.getProduto())) {
				achou = posicao;
			}
		}

		if (achou > -1) {
			itensVenda.remove(achou);
		}

		calcular();
	}

	public void calcular() {
		venda.setPrecoTotal(new BigDecimal("0.00"));

		for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
			Item itemVenda = itensVenda.get(posicao);
			venda.setPrecoTotal(venda.getPrecoTotal().add(itemVenda.getPrecoParcial()));
		}
	}

	public void finalizar() {
		try {
			venda.setHorario(new Date());

			CartaoDAO cartaoDAO = new CartaoDAO();
			cartoes = cartaoDAO.listar();
			
			CupomDAO cupomDAO = new CupomDAO();
			cupons = cupomDAO.listar();
			
			CupomProdutoDAO cupomprodutoDAO = new CupomProdutoDAO();
			cuponsprodutos = cupomprodutoDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar finalizar a venda");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			if (venda.getPrecoTotal().signum() == 0) {
				Messages.addGlobalError("Informe pelo menos um item para a venda");
				return;
			}

			VendaTesteDAO vendaDAO = new VendaTesteDAO();
			vendaDAO.salvar(venda, itensVenda);

			venda = new Venda();
			venda.setPrecoTotal(new BigDecimal("0.00"));

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar("descricao");
			
			
			/*
			 * PedidoDAO pedidoDAO = new PedidoDAO();
			 * pedido.setStatusPedido("EM PROCESSAMENTO"); pedidoDAO.merge(pedido);
			 */

			itensVenda = new ArrayList<>();

			Messages.addGlobalInfo("Venda realizada com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a venda");
			erro.printStackTrace();
		}
	}

	@PostConstruct
	public void listarCartao() {
		CartaoDAO cartaoDAO = new CartaoDAO();
		cartoes = cartaoDAO.listar();
	}

}
