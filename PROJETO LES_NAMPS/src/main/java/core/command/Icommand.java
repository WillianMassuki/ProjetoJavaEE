package core.command;

import br.com.willian.les2019.ecommerce.dominio.EntidadeDominio;
import core.aplicacao.Resultado;

public interface Icommand {
	
	public Resultado execute(EntidadeDominio entidade);

}
