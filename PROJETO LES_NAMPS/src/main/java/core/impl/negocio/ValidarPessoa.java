package core.impl.negocio;

import br.com.willian.les2019.ecommerce.dominio.Entidade;
import br.com.willian.les2019.ecommerce.dominio.Pessoa;
import core.IStrategy;

public class ValidarPessoa implements IStrategy {

	@Override
	public String processar(Entidade entidade) {
Pessoa pessoa = new Pessoa();
		
		String Nome = pessoa.getNome();
		String Celular = pessoa.getCelular();
		String email = pessoa.getEmail();
		String NomeCidade = pessoa.getCidade().getNome();
		String Sigla = pessoa.getCidade().getEstado().getSigla();
		String NomeEstado = pessoa.getCidade().getEstado().getNome();
		
		if(Nome==null ||Celular == null
			|| email == null || NomeCidade == null ||Sigla== null || NomeEstado == null  )
		{
			return "Todos os campos são obrigatorios";
		}
		
		if(Nome.trim().equals("") || Celular.trim().equals("") || 
				email.trim().equals("") || NomeCidade.trim().equals("") || Sigla.trim().equals("")||
		NomeEstado.trim().equals(""))
		{
			return "Todos os campos são obrigatorios";
		}
				 
		return null;
	}

	

}
