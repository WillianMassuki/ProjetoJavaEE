package br.com.willian.les2019.ecommerce.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.willian.les2019.ecommerce.dominio.Administrador;
import br.com.willian.les2019.ecommerce.util.HibernateUtil;

public class AdministradorDAO extends GenericDAO<Administrador> {
	
	public Administrador autenticar(String email, String senha) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try{
			Criteria consulta = sessao.createCriteria(Administrador.class);
			consulta.createAlias("pessoa", "p");
			
			consulta.add(Restrictions.eq("p.email", email));
			
			//SimpleHash hash = new SimpleHash("md5", senha);
			//consulta.add(Restrictions.eq("senha", hash.toHex()));
			
			consulta.add(Restrictions.eq("senha", senha));
			
			Administrador resultado = (Administrador) consulta.uniqueResult();
			
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
}


}
