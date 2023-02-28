package core.impl.negocio;

import br.com.willian.les2019.ecommerce.dominio.Entidade;
import br.com.willian.les2019.ecommerce.dominio.Usuario;
import core.IStrategy;

public class ValidarUsuario implements IStrategy {

	@Override
	public String processar(Entidade entidade) {
		Usuario usuario = new Usuario();

		String Senha = usuario.getSenha();
		String NomeUsuario = usuario.getPessoa().getNome();
		String Cpf = usuario.getPessoa().getCpf();
		String Rg = usuario.getPessoa().getRg();
		String Celular = usuario.getPessoa().getCelular();
		String Email = usuario.getPessoa().getEmail();

		if (Senha == null || NomeUsuario == null || Cpf == null || Rg == null || Celular == null
				|| Email == null) {
			return "Todos os campos são obrigatorios";
		}

		if (Senha.trim().equals("") || NomeUsuario.trim().equals("") || Cpf.trim().equals("") || Rg.trim().equals("")
				|| Celular.trim().equals("")
				|| Email.trim().equals("")) {
			return "Todos os campos são obrigatorios";
		}

		return null;
	}

}
