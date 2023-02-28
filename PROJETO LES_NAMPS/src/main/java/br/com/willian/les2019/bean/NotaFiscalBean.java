package br.com.willian.les2019.bean;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.willian.les2019.ecommerce.dao.VendaTesteDAO;
import br.com.willian.les2019.ecommerce.dominio.Item;
import br.com.willian.les2019.ecommerce.dominio.Venda;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class NotaFiscalBean implements Serializable {
	private static final Logger Log = Logger.getLogger("NotaFiscalBean");
	
	private Item itemteste;
	
	private List<Item> item;
	
	private Venda vendateste;
	
	private List<Venda> vendas;
	

	public Venda getVendateste() {
		return vendateste;
	}

	public void setVendateste(Venda vendateste) {
		this.vendateste = vendateste;
	}

	public List<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}
	
	
	
	public Item getItemteste() {
		return itemteste;
	}

	public void setItemteste(Item itemteste) {
		this.itemteste = itemteste;
	}

	@PostConstruct
	public void listar()
	{
		try {
			VendaTesteDAO vndDAO = new VendaTesteDAO();
			
			vendas = vndDAO.listar();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os estados");
			erro.printStackTrace();
		}
	}
	

}
