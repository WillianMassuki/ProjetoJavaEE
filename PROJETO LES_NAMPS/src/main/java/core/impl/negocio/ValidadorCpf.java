package core.impl.negocio;

import br.com.willian.les2019.ecommerce.dominio.Cliente;
import br.com.willian.les2019.ecommerce.dominio.Entidade;
import core.IStrategy;

public class ValidadorCpf implements IStrategy {

	@Override
	public String processar(Entidade entidade) {
		if(entidade instanceof Cliente){
			Cliente c = (Cliente)entidade;
			
			if(c.getPessoa().getCpf().length() < 9){
				return "CPF deve conter 14 digitos!";
			}
			
		}else{
			return "CPF n�o pode ser v�lidado, pois entidade n�o � um cliente!";
		}
		return null;
	}

}
