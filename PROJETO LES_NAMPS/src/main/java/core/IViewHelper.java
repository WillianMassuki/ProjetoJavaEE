package core;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.aplicacao.Resultado;

public interface IViewHelper {
	

	public void setView(Resultado resultado, 
			HttpServletRequest request, HttpServletResponse response)throws ServletException;
	
	public void setView(Resultado resultado, ServletConfig config);

}
