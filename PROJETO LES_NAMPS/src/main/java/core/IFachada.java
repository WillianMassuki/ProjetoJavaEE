package core;

import br.com.willian.les2019.ecommerce.dominio.Entidade;
import br.com.willian.les2019.ecommerce.dominio.EntidadeDominio;
import core.aplicacao.Resultado;

public interface IFachada {
	

	public Resultado salvar(EntidadeDominio entidade);
	public Resultado excluir(EntidadeDominio entidade);
	public Resultado consultar(EntidadeDominio entidade);
	public Resultado visualizar(EntidadeDominio entidade);
	public Resultado alterar(Entidade entidade);

}
