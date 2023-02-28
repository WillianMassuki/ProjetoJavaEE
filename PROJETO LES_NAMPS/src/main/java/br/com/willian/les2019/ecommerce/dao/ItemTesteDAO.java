package br.com.willian.les2019.ecommerce.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.willian.les2019.ecommerce.dominio.Item;
import br.com.willian.les2019.ecommerce.dominio.Venda;
import br.com.willian.les2019.ecommerce.util.HibernateUtil;

public class ItemTesteDAO extends GenericDAO<Item>{
	
	public List<Item> consultar(Venda venda)
	{
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try{
			Criteria consulta = sessao.createCriteria(Item.class);
			
			consulta.add(Restrictions.eq("venda", venda));
			
			//consulta.add(Restrictions.eq("senha", senha));
			
			List<Item> resultado = (List<Item>) consulta.list();
			
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
}
	
