package br.com.willian.les2019.ecommerce.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.willian.les2019.ecommerce.dominio.Usuario;
import br.com.willian.les2019.ecommerce.util.HibernateUtil;

public class UsuarioDAO extends GenericDAO<Usuario> {
	
	public Usuario autenticar(String cpf, String senha) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try{
			Criteria consulta = sessao.createCriteria(Usuario.class);
			consulta.createAlias("pessoa", "p");
			
			consulta.add(Restrictions.eq("p.cpf", cpf));
			
			//SimpleHash hash = new SimpleHash("md5", senha);
			//consulta.add(Restrictions.eq("senha", hash.toHex()));
			
			consulta.add(Restrictions.eq("senha", senha));
			
			Usuario resultado = (Usuario) consulta.uniqueResult();
			
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
}

}
