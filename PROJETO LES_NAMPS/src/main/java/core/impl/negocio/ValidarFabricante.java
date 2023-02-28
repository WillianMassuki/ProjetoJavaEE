package core.impl.negocio;

import br.com.willian.les2019.ecommerce.dominio.Entidade;
import br.com.willian.les2019.ecommerce.dominio.Fabricante;
import core.IStrategy;

public class ValidarFabricante implements IStrategy {

	@Override
	public String processar(Entidade entidade) {
		Fabricante fabricante = new Fabricante();
		
		 String descricao = fabricante.getDescricao();
			
		 if(descricao.trim().equals("") || descricao == null)
		 {
			 return "Todos os campos são obrigatorios";
		 }
			
			return null;
	}

}
