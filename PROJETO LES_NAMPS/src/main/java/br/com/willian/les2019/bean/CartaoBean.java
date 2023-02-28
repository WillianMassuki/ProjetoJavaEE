package br.com.willian.les2019.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.willian.les2019.ecommerce.dao.BandeiraDAO;
import br.com.willian.les2019.ecommerce.dao.CartaoDAO;
import br.com.willian.les2019.ecommerce.dominio.Bandeira;
import br.com.willian.les2019.ecommerce.dominio.Cartao;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CartaoBean implements Serializable {
	private Cartao cartao;
	private List<Cartao> cartoes;
	
	private Bandeira bandeira;
	
	private List<Bandeira> bandeiras;
	
	
	public List<Bandeira> getBandeiras() {
		return bandeiras;
	}
	public void setBandeiras(List<Bandeira> bandeiras) {
		this.bandeiras = bandeiras;
	}
	public Cartao getCartao() {
		return cartao;
	}
	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}
	public List<Cartao> getCartoes() {
		return cartoes;
	}
	public void setCartoes(List<Cartao> cartoes) {
		this.cartoes = cartoes;
	}
	
	@PostConstruct
	public void listar() {
		try {
			CartaoDAO cartaoDAO = new CartaoDAO();
			cartoes = cartaoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os estados");
			erro.printStackTrace();
		}
	}
	
	public void novo() {
		try {
			cartao = new Cartao();

			BandeiraDAO bandeiraDAO = new BandeiraDAO();
			bandeiras = bandeiraDAO.listar();
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao gerar uma nova cidade");
			erro.printStackTrace();
		}
		
		
	}
	
	public void salvar() {
		try {
			CartaoDAO cartaoDAO = new CartaoDAO();
			cartaoDAO.merge(cartao);

			cartao = new Cartao();
			cartoes = cartaoDAO.listar();
			
			BandeiraDAO bandeiraDAO = new BandeiraDAO();
			bandeiras = bandeiraDAO.listar();

			Messages.addGlobalInfo("Estado salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o estado");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento) {
		try {
			cartao = (Cartao) evento.getComponent().getAttributes().get("cartaoSelecionado");

			CartaoDAO cartaoDAO = new CartaoDAO();
			cartaoDAO.excluir(cartao);

			cartoes = cartaoDAO.listar();

			Messages.addGlobalInfo("Estado removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o estado");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		cartao = (Cartao) evento.getComponent().getAttributes().get("cartaoSelecionado");
	}
	public Bandeira getBandeira() {
		return bandeira;
	}
	public void setBandeira(Bandeira bandeira) {
		this.bandeira = bandeira;
	}

	
	

}
