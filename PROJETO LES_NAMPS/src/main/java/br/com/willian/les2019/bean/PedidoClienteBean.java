package br.com.willian.les2019.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.willian.les2019.ecommerce.dao.CupomDAO;
import br.com.willian.les2019.ecommerce.dao.CupomProdutoDAO;
import br.com.willian.les2019.ecommerce.dao.ItemTesteDAO;
import br.com.willian.les2019.ecommerce.dominio.Cupom;
import br.com.willian.les2019.ecommerce.dominio.CupomProduto;
import br.com.willian.les2019.ecommerce.dominio.Item;
import br.com.willian.les2019.ecommerce.dominio.Venda;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PedidoClienteBean implements Serializable {
	private static final Logger Log = Logger.getLogger("PedidoClienteBean");
	private Cupom cupom;
	private List<Cupom> listCupom;
	private Venda vendateste;

	private Item item;

	private List<Item> itens;

	private List<Venda> vendas;

	private CupomProduto cupomProduto;

	public CupomProduto getCupomProduto() {
		return cupomProduto;
	}

	public void setCupomProduto(CupomProduto cupomProduto) {
		this.cupomProduto = cupomProduto;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public static Logger getLog() {
		return Log;
	}

	public List<Venda> getVendas() {
		return vendas;
	}

	public Venda getVendateste() {
		return vendateste;
	}

	public void setVendateste(Venda vendateste) {
		this.vendateste = vendateste;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	public Cupom getCupom() {
		return cupom;
	}

	public void setCupom(Cupom cupom) {
		this.cupom = cupom;
	}

	public List<Cupom> getListCupom() {
		return listCupom;
	}

	public void setListCupom(List<Cupom> listCupom) {
		this.listCupom = listCupom;
	}

	public void trocar(ActionEvent evento) {
		
		Item item = (Item) evento.getComponent().getAttributes().get("itemSelecionado");
		Log.info("Codigo do Item....." + "codigo da Venda:" + item.getVenda().getCodigo() + "Codigo Produto:"
				+ item.getProduto().getCodigo());
		
		CupomDAO cupomDAO = new CupomDAO();
		List<Cupom> cupons = cupomDAO.consultar(item.getVenda());
		
		// Já existe o cupom dessa venda?
		
		if(cupons.isEmpty())
		{
			cupom = new Cupom();
			cupom.setPedido(item.getVenda());
			cupom.setDataCriacao(Calendar.getInstance().getTime());
			cupom.setDataAtualizacao(Calendar.getInstance().getTime());

			cupom.setCodigoCupom(String.format("ABCD%d", item.getVenda().getCodigo()));

			cupom.setMotivo("porque o cliente não gostou");

			cupomDAO.salvar(cupom);
			
		}else
		{
			cupom = cupons.get(0);
			
		}
		
		CupomProdutoDAO cpmDAO = new CupomProdutoDAO();
			cupomProduto = new CupomProduto();
			cupomProduto.setCupom(cupom);
			cupomProduto.setProduto(item.getProduto());
			cupomProduto.setDataAtualizacao(Calendar.getInstance().getTime());
			cupomProduto.setDataCriacao(Calendar.getInstance().getTime());
			cupomProduto.setPrecoparcial(item.getPrecoParcial());
			cupomProduto.setQuantidade(item.getQuantidade());

			cpmDAO.salvar(cupomProduto);
		
	
	}

	public void teste(ActionEvent evento) {

		Log.info("Testando 1,2,3");

	}

	public void devolucao(ActionEvent evento) {
		Venda venda = (Venda) evento.getComponent().getAttributes().get("vendaSelecionado");
		Log.info("Codigo venda....." + "codigo:" + venda.getCodigo());

		cupom = new Cupom();
		cupom.setPedido(venda);
		cupom.setDataCriacao(Calendar.getInstance().getTime());
		cupom.setDataAtualizacao(Calendar.getInstance().getTime());

		cupom.setCodigoCupom(String.format("ABCD%d", venda.getCodigo()));

		cupom.setMotivo("porque o cliente não gostou");

		CupomDAO cupomDAO = new CupomDAO();
		cupomDAO.salvar(cupom);

		ItemTesteDAO itemDAO = new ItemTesteDAO();
		List<Item> listar = itemDAO.consultar(venda);

		Log.info("Codigo....." + "codigo:" + venda.getCodigo() + "listar:" + listar.size());

		// para cada item da lista criar um cupom produto

		/*
		 * *****************************************************************************
		 * ****************
		 */

		// Produto produto = (Produto)
		// evento.getComponent().getAttributes().get("produtoSelecionado");
		// Log.info("Codigo produtos....." + "codigo:" + produto .getCodigo() );

		CupomProdutoDAO cpmDAO = new CupomProdutoDAO();
		for (Item tpItem : listar) {
			cupomProduto = new CupomProduto();
			cupomProduto.setProduto(tpItem.getProduto());
			cupomProduto.setDataAtualizacao(Calendar.getInstance().getTime());
			cupomProduto.setDataCriacao(Calendar.getInstance().getTime());
			cupomProduto.setPrecoparcial(tpItem.getPrecoParcial());
			cupomProduto.setQuantidade(tpItem.getQuantidade());

			cpmDAO.salvar(cupomProduto);
		}

		/*
		 * for (Produto tpmProduto : produtos) { if (tpmProduto.equals(produto)) {
		 * tpmProduto.setQuantidade((short) (tpmProduto.getQuantidade() - 1));
		 * Log.info("Tirando quantidade de produto " + tpmProduto.getDescricao() +
		 * " quantidade: " + tpmProduto.getQuantidade() + "....");
		 */

	}

	public void gerarCupom() {
		// instância um objeto da classe Random usando o construtor padrão
		Random gerador = new Random();

		// imprime sequência de 10 números inteiros aleatórios
		for (int i = 0; i < 10; i++) {
			System.out.println(gerador.longs(6, 1, 1000000));

		}
	}

	@PostConstruct
	public void listaritens() {
		try {
			ItemTesteDAO itemDAO = new ItemTesteDAO();
			itens = itemDAO.listar();
			// Log.info("Codigo do Item....." + "codigo:" + item.getCodigo());
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar as cidades");
			erro.printStackTrace();
		}

	}

	public static void main(String[] args) {
		Random gerador = new Random();

		// imprime sequência de 10 números inteiros aleatórios
		for (int i = 0; i < 10; i++) {
			System.out.println(gerador.longs(6, 1, 1000000));

		}

	}

}
