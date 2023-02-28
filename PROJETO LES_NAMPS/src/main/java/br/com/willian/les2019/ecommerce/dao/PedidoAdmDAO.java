package br.com.willian.les2019.ecommerce.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.willian.les2019.ecommerce.dominio.Item;
import br.com.willian.les2019.ecommerce.dominio.Produto;
import br.com.willian.les2019.ecommerce.util.HibernateUtil;

public class PedidoAdmDAO extends GenericDAO<PedidoAdmDAO>{
	
	public void salvar(List<Item> itensVenda) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();

		//	sessao.save(venda);

			for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
				Item itemVenda = itensVenda.get(posicao);
			//	itemVenda.setVenda(venda);

				sessao.save(itemVenda);

				Produto produto = itemVenda.getProduto();
				int quantidade = produto.getQuantidade() + itemVenda.getQuantidade();
				if (quantidade >= 0) {
					produto.setQuantidade(new Short(quantidade + ""));
					sessao.update(produto);
				} else {
					throw new RuntimeException("Quantidade insuficiente em estoque");
				}
			}

			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
}

}
