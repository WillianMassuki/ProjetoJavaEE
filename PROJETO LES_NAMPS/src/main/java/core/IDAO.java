package core;

import java.util.List;

import br.com.willian.les2019.ecommerce.dominio.Entidade;

public interface IDAO {
	
	public void salvar(Entidade entidade);
	public List<Entidade> listar(String campoOrdenacao);
	public List<Entidade> listar();
	//public Entidade buscar();
	public void excluir(Entidade entidade);
	public void editar(Entidade entidade);
	public void merge(Entidade entidade);
	public void consultar(Entidade entidade);
	public void buscar();
	public void alterar();

}
