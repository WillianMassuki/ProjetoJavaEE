package br.com.willian.les2019.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class FinalizarCompraBean implements Serializable  {
	

	private static final long serialVersionUID = 1L;

	public String save()
	{

		return "Sucesso.xhtml";
	}
	

}
