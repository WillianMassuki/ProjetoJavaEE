package br.com.willian.les2019.ecommerce.dominio;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@SuppressWarnings("serial")
@MappedSuperclass //ANOTAÇÕES DO HIBERNATE 
public class GenericDomain implements Serializable {
	
	@Id // ANOTAÇÕES DO HIBERNATE REFERENTE A CHAVE PRIMARIA
	@GeneratedValue(strategy = GenerationType.SEQUENCE) // OU SEQUENCE PARA ORACLE E POSTGRES
	private long codigo;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	
	@Override
	public String toString() {
	    return String.format("%s[codigo=%d]", getClass().getSimpleName(), getCodigo());
	}
	
	

}
