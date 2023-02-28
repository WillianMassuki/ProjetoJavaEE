package br.com.willian.les2019.ecommerce.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.willian.les2019.ecommerce.dominio.Cupom;
import br.com.willian.les2019.ecommerce.dominio.Item;
import br.com.willian.les2019.ecommerce.dominio.Produto;
import br.com.willian.les2019.ecommerce.dominio.Venda;
import br.com.willian.les2019.ecommerce.util.HibernateUtil;

public class VendaTesteDAO extends GenericDAO<Venda> {
	public void salvar(Venda venda, List<Item> itensVenda) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession(); // PEGO UM SESSÃO ABERTA
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // INICIAR A TRANSAÇÃO
			
			sessao.save(venda); // MANDAR SALVAR A VENDA

			for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
				Item itemVenda = itensVenda.get(posicao);
				itemVenda.setVenda(venda);
			
				sessao.save(itemVenda);

				Produto produto /*QTD EM PRODUTO == ESTOQUE */= itemVenda.getProduto();// JÁ ITEMVENDA É Q QTD VENDIDA
				// CAPTURAR PRODUTO - TODO ITEM VENDA TEM O SEU PRODUTO
				
				// FOR PARA SALVAR TODOS OS ITENS DA VENDA
				
				int quantidade = produto.getQuantidade() - itemVenda.getQuantidade(); // A QUANTIDADE ESTÁ RECEBENDO O RESULTADO DA OPERAÇÃO
				if (quantidade >= 0) {
					produto.setQuantidade(new Short(quantidade + "")); // O TIPO SHORT NÃO ACEITA INT - POR ISSO TEM QUE CONVERTER PARA STRING
					sessao.update(produto); // ATUALIZANDO A QUANTIDADE DE PRODUTOS NO ESTOQUE
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
	
	public List<Venda> consultarintervaloperiodo(Date inicio, Date fim)
	{
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		

		try{
			Criteria consulta = sessao.createCriteria(Venda.class);
			
			consulta.add(Restrictions.between("horario", inicio, fim));
			
			List<Venda> resultado = (List<Venda>) consulta.list();
			
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
		

}
