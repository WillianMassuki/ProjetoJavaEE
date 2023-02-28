package br.com.willian.les2019.ecommerce.dominio;

import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Bandeira extends GenericDomain {
	
	private String bandeira;

	public String getBandeira() {
		return bandeira;
	}

	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}
	
	

}
