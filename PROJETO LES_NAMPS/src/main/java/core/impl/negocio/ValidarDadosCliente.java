package core.impl.negocio;

import br.com.willian.les2019.ecommerce.dominio.Cliente;
import br.com.willian.les2019.ecommerce.dominio.Entidade;
import core.IStrategy;

public class ValidarDadosCliente implements IStrategy {

	@Override
	public String processar(Entidade entidade) {
		Cliente cliente = (Cliente) entidade;
		
		//Date dataCadastro;
		//Boolean liberado;
		String Nome = cliente.getPessoa().getNome();
		String Cpf = cliente.getPessoa().getCpf();
		String RG = cliente.getPessoa().getRg();
	//	String RUA = cliente.getPessoa().getRua();
	//	Short Numero = cliente.getPessoa().getNumero();
	//	String Bairro = cliente.getPessoa().getBairro();
	//	String CEP = cliente.getPessoa().getCep();
	//	String Complemento = cliente.getPessoa().getComplemento(); 
	//	String Telefone = cliente.getPessoa().getTelefone();
		String Celular = cliente.getPessoa().getCelular();
		String Email = cliente.getPessoa().getEmail();
		String NomeCidade = cliente.getPessoa().getCidade().getNome();
		String NomeEstado = cliente.getPessoa().getCidade().getEstado().getNome();
		String Sigla = cliente.getPessoa().getCidade().getEstado().getSigla();
		
		if(Nome == null || Cpf == null || RG == null ||Celular == null || Email == null || NomeCidade == null || 
				NomeEstado == null || Sigla == null)
		{
			return " Todos os campos São de preenchimento obrigatório!";
		}
		
		if(Nome.trim().equals("") || Cpf.trim().equals("") || RG.trim().equals("") 
				||Celular.trim().equals("")||Email.trim().equals("")||
		  NomeCidade.trim().equals("") ||NomeEstado.trim().equals("") || Sigla.trim().equals(""))
		{
			return " Todos os campos São de preenchimento obrigatório!";
		}
		return null;
	}

}
