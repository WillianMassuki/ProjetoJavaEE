package br.com.willian.les2019.ecommerce.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.willian.les2019.ecommerce.dominio.Cupom;
import br.com.willian.les2019.ecommerce.dominio.Venda;
import br.com.willian.les2019.ecommerce.util.HibernateUtil;

public class CupomDAO extends GenericDAO<Cupom> {
	
	public List<Cupom> consultar(Venda venda)
	{
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try{
			Criteria consulta = sessao.createCriteria(Cupom.class);
			
			consulta.add(Restrictions.eq("pedido", venda));
			
			List<Cupom> resultado = (List<Cupom>) consulta.list();
			
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	

}
