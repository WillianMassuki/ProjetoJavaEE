package core.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.IViewHelper;
import core.aplicacao.Resultado;
import core.command.AlterarCommand;
import core.command.ConsultarCommand;
import core.command.ExcluirCommand;
import core.command.Icommand;
import core.command.SalvarCommand;

public class Controler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Map<String, Icommand> commands;
	private static IViewHelper vh;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		// Parametros definidos como <init-param> no web.xml
		Enumeration<String> parametros = config.getInitParameterNames();

		while (parametros.hasMoreElements()) {
			String parametro = parametros.nextElement();
			config.getInitParameter(parametro);
			Resultado resultado = doProcess();

			/*
			 * Executa o método setView do view helper específico passando o resultado
			 * da consulta com os dados de domínio que será colocado no contexto para montar 
			 * as combos
			 */
			
			vh.setView(resultado, config);
			
			//vh.setView(resultado, config);
		}	
		
		

	}

	/**
	 * Default constructor.
	 */
	public Controler() {

		/*
		 * Utilizando o command para chamar a fachada e indexando cada command pela
		 * operação garantimos que esta servelt atenderá qualquer operação
		 */
		commands = new HashMap<String, Icommand>();

		commands.put("SALVAR", new SalvarCommand());
		commands.put("EXCLUIR", new ExcluirCommand());

		ConsultarCommand consultarCMD = new ConsultarCommand();
		commands.put("CONSULTAR", consultarCMD);
		commands.put("PREPARAR_PRODUTO", consultarCMD);

		//commands.put("VISUALIZAR", new VisualizarCommand());
		commands.put("ALTERAR", new AlterarCommand());

		new HashMap<String, IViewHelper>();

	}

	/**
	 * TODO Descrição do Método
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcessRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcessRequest(request, response);
	}

	protected void doProcessRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}

	protected Resultado doProcess() throws ServletException {
		
		return null;
	}
}