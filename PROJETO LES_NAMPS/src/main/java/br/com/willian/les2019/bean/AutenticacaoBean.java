package br.com.willian.les2019.bean;

import java.io.IOException;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import br.com.willian.les2019.ecommerce.dao.UsuarioDAO;
import br.com.willian.les2019.ecommerce.dominio.Pessoa;
import br.com.willian.les2019.ecommerce.dominio.Usuario;

@ManagedBean
@SessionScoped
public class AutenticacaoBean {
	
	private static final Logger Log = Logger.getLogger("AutenticacaoBean");
	private Usuario usuario;
	private Usuario usuarioLogado;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	@PostConstruct
	public void iniciar() {
		usuario = new Usuario();
		usuario.setPessoa(new Pessoa());
	}

	public void autenticar() {
		Log.info("tentando logar com cpf e senha" + "cpf:" + usuario.getPessoa().getCpf() 
				+ "Senha:" + usuario.getSenha());
		
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioLogado = usuarioDAO.autenticar(usuario.getPessoa().getCpf(), usuario.getSenha());
			 
			if(usuarioLogado == null){
				Messages.addGlobalError("CPF e/ou senha incorretos");
				
				Log.info("usuario invalido com cpf e senha......" + "cpf:" + usuario.getPessoa().getCpf() 
						+ "Senha:" + usuario.getSenha());
				
				return;
				
			}
			Log.info("usuario encontrado......" + "cpf:" + usuario.getPessoa().getCpf() 
					+ "Senha:" + usuario.getSenha());
			
			Faces.redirect("./pages/principal.xhtml");
		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError(erro.getMessage());
		}
}

}
