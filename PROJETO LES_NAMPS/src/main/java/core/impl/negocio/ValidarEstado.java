package core.impl.negocio;

import br.com.willian.les2019.ecommerce.dominio.Entidade;
import br.com.willian.les2019.ecommerce.dominio.Estado;
import core.IStrategy;

public class ValidarEstado implements IStrategy{

	@Override
	public String processar(Entidade entidade) {
	Estado estado = new Estado();
		
		String Sigla = estado.getSigla();
		String Nome = estado.getNome();
		
		if(Sigla == null || Nome == null )
		{
			return "Todos os campos são obrigatoroos";
		}
		
		if(Sigla.trim().equals("")||Nome.trim().equals(""))
		{
			return "Todos os campos são obrigatoroos";
			
		}
		
		return null;
	}

}
