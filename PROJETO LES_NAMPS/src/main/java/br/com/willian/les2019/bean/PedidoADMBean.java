package br.com.willian.les2019.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.willian.les2019.ecommerce.dao.PedidoAdmDAO;
import br.com.willian.les2019.ecommerce.dao.PedidoDAO;
import br.com.willian.les2019.ecommerce.dao.ProdutoDAO;
import br.com.willian.les2019.ecommerce.dominio.Item;
import br.com.willian.les2019.ecommerce.dominio.Pedido;
import br.com.willian.les2019.ecommerce.dominio.Produto;
import br.com.willian.les2019.ecommerce.dominio.Venda;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class PedidoADMBean implements Serializable {

	private Pedido pedido;

	private Venda venda;

	private List<Pedido> pedidos;

	private List<Produto> produtos;
	private List<Item> itensVenda;

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
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

	public void salvar() {
		try {

			PedidoAdmDAO pedDAO = new PedidoAdmDAO();
			pedDAO.salvar(itensVenda);

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar("descricao");

			itensVenda = new ArrayList<>();

			Messages.addGlobalInfo("Venda realizada com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a venda");
			erro.printStackTrace();
		}
	}

	public void alterarStatus(ActionEvent evento) {
		pedido = (Pedido) evento.getComponent().getAttributes().get("produtoSelecionado");

		if (pedido.getStatusPedido() == "EM PROCESSAMENTO") {
			pedido.setStatusPedido("EM TRANSITO");

			PedidoDAO pedidoDAO = new PedidoDAO();
			pedidoDAO.merge(pedido);

		} else if (pedido.getStatusPedido() == "EM TRANSITO") {
			pedido.setStatusPedido("ENTREGUE");

			PedidoDAO pedidoDAO = new PedidoDAO();
			pedidoDAO.merge(pedido);
			
		}else if (pedido.getStatusPedido() == "ENTREGUE") {
			pedido.setStatusPedido("EM TROCA");

			PedidoDAO pedidoDAO = new PedidoDAO();
			pedidoDAO.merge(pedido);
		} else if (pedido.getStatusPedido() == "EM TROCAEM TROCA") {
			pedido.setStatusPedido("TROCA AUTORIZADA");

			PedidoDAO pedidoDAO = new PedidoDAO();
			pedidoDAO.merge(pedido);
		}

	}
}
