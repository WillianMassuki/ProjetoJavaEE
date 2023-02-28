package br.com.willian.les2019.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.willian.les2019.ecommerce.dao.AdministradorDAO;
import br.com.willian.les2019.ecommerce.dao.PessoaDAO;
import br.com.willian.les2019.ecommerce.dominio.Administrador;
import br.com.willian.les2019.ecommerce.dominio.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class AdministradorBean implements Serializable {
	
private Administrador adm;
	
	private List<Pessoa> pessoas;
	private List<Administrador> adms;

	public Administrador getAdm() {
		return adm;
	}

	public void setAdm(Administrador adm) {
		this.adm = adm;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Administrador> getAdms() {
		return adms;
	}

	public void setAdms(List<Administrador> adms) {
		this.adms = adms;
	}

	@PostConstruct
	public void listar(){
		try{
			AdministradorDAO admDAO = new AdministradorDAO();
			adms = admDAO.listar();
		}catch(RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os usuários");
			erro.printStackTrace();
		}
	}
	
	public void novo() {
		try {
			adm = new Administrador();

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar criar um novo usuário");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			AdministradorDAO admDAO = new AdministradorDAO();
			admDAO.merge(adm);
			
			adm = new Administrador();
			adms = admDAO.listar();
			
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");
			
			Messages.addGlobalInfo("ADM salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o ADM");
			erro.printStackTrace();
		}
}


}
