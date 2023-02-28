package br.com.willian.les2019.bean;

import java.io.IOException;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.willian.les2019.ecommerce.dao.AdministradorDAO;
import br.com.willian.les2019.ecommerce.dominio.Administrador;
import br.com.willian.les2019.ecommerce.dominio.Pessoa;

@ManagedBean
@SessionScoped
public class AutenticacaoADMBean {

	private static final Logger Log = Logger.getLogger("AutenticacaoADMBean");
	private Administrador adm;
	private Administrador admLogado;

	public Administrador getAdm() {
		return adm;
	}

	public void setAdm(Administrador adm) {
		this.adm = adm;
	}

	public Administrador getAdmLogado() {
		return admLogado;
	}

	public void setAdmLogado(Administrador admLogado) {
		this.admLogado = admLogado;
	}
	
	@PostConstruct
	public void iniciar() {
		adm = new Administrador();
		adm.setPessoa(new Pessoa());
	}
	
	
	public void autenticar() {
		
		Log.info("tentando logar Como ADM com email e senha......" + "email:" +  adm.getPessoa().getEmail()
				+ "Senha:" + adm.getSenha());
		
		
		try {
			AdministradorDAO admDAO = new AdministradorDAO();
			admLogado = admDAO.autenticar(adm.getPessoa().getEmail(), adm.getSenha());
			
			if(admLogado == null){
				Messages.addGlobalError("Email e/ou senha incorretos");
				Log.info("Erro ao logar Como ADM com email e senha......" + "email:" +  adm.getPessoa().getEmail()
						+ "Senha:" + adm.getSenha());
				return;
			}
			
			Log.info("ADm encontrado....." + "email:" +  adm.getPessoa().getEmail()
					+ "Senha:" + adm.getSenha());
		
			
			Faces.redirect("./pages/principal.xhtml");
		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError(erro.getMessage());
		}
}
 
}
