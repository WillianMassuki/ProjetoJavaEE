package core.impl.controle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.willian.les2019.ecommerce.dominio.Administrador;
import br.com.willian.les2019.ecommerce.dominio.Entidade;
import br.com.willian.les2019.ecommerce.dominio.EntidadeDominio;
import br.com.willian.les2019.ecommerce.dominio.Estado;
import br.com.willian.les2019.ecommerce.dominio.Fabricante;
import br.com.willian.les2019.ecommerce.dominio.Item;
import br.com.willian.les2019.ecommerce.dominio.Pessoa;
import br.com.willian.les2019.ecommerce.dominio.Produto;
import br.com.willian.les2019.ecommerce.dominio.Usuario;
import br.com.willian.les2019.ecommerce.dominio.Venda;
import core.IDAO;
import core.IFachada;
import core.IStrategy;
import core.aplicacao.Resultado;
import core.impl.negocio.ValidadorCpf;
import core.impl.negocio.ValidarDadosADM;
import core.impl.negocio.ValidarDadosCliente;
import core.impl.negocio.ValidarEstado;
import core.impl.negocio.ValidarFabricante;
import core.impl.negocio.ValidarPessoa;
import core.impl.negocio.ValidarProduto;
import core.impl.negocio.ValidarUsuario;
import core.impl.negocio.ValidarVenda;

public class Fachada implements IFachada {

	/**
	 * Mapa de DAOS, será indexado pelo nome da entidade O valor é uma instância do
	 * DAO para uma dada entidade;
	 */
	private Map<String, IDAO> daos;

	/**
	 * Mapa para conter as regras de negócio de todas operações por entidade; O
	 * valor é um mapa que de regras de negócio indexado pela operação
	 */
	private Map<String, Map<String, List<IStrategy>>> rns;

	private Resultado resultado;

	public Fachada() {
		/* Intânciando o Map de DAOS */
		daos = new HashMap<String, IDAO>();
		/* Intânciando o Map de Regras de Negócio */
		rns = new HashMap<String, Map<String, List<IStrategy>>>();

		/* Criando instâncias dos DAOs a serem utilizados */

		/*
		AdministradorDAO admDAO = new AdministradorDAO();
		CidadeDAO cidadeDAO = new CidadeDAO();
		ClienteDAO clienteDAO = new ClienteDAO();
		EstadoDAO estadoDAO = new EstadoDAO();
		FabricanteDAO fabriDAO = new FabricanteDAO();
		ItemDAO itemDAO = new ItemDAO();
		PedidoDAO pedidoDAO = new PedidoDAO();
		PessoaDAO pessoaDAO = new PessoaDAO();
		ProdutoDAO prodDAO = new ProdutoDAO();
		UsuarioDAO usuDAO = new UsuarioDAO();
		VendaDAO vendaDAO = new VendaDAO();
		*/

		/* Adicionando cada dao no MAP indexando pelo nome da classe */
//		daos.put(Fornecedor.class.getName(), forDAO);
//		daos.put(Administrador.class.getName(), admDAO);

		/* Criando instâncias de regras de negócio a serem utilizados */

		ValidadorCpf vdlCPF = new ValidadorCpf();
		ValidarDadosADM vdlADM = new ValidarDadosADM();
		ValidarDadosCliente vldCliente = new ValidarDadosCliente();
		ValidarEstado vdlEstado = new ValidarEstado();
		ValidarFabricante vdlFabri = new ValidarFabricante();
		ValidarPessoa vdlPessoa = new ValidarPessoa();
		ValidarProduto vdlProduto = new ValidarProduto();
		ValidarUsuario vdlUsuario = new ValidarUsuario();
		ValidarVenda vdlVenda = new ValidarVenda();
		
		/*
		 * Criando uma lista para conter as regras de negócio de fornencedor quando a
		 * operação for salvar
		 */
		List<IStrategy> rnsSalvarPessoa = new ArrayList<IStrategy>();
		/* Adicionando as regras a serem utilizadas na operação salvar do fornecedor */
		rnsSalvarPessoa.add(vdlPessoa);

		/*
		 * Cria o mapa que poderá conter todas as listas de regras de negócio específica
		 * por operação do fornecedor
		 */
		Map<String, List<IStrategy>> rnsPessoa = new HashMap<String, List<IStrategy>>();
		/*
		 * Adiciona a listra de regras na operação salvar no mapa do fornecedor (lista
		 * criada na linha 70)
		 */
		rnsPessoa.put("SALVAR",rnsSalvarPessoa);
		
		
		/*
		 * Adiciona o mapa(criado na linha 79) com as regras indexadas pelas operações
		 * no mapa geral indexado pelo nome da entidade
		 */
		rns.put(Pessoa.class.getName(), rnsPessoa);
		
		
		
	/*------------------------------------------*/

		/*
		 * Criando uma lista para conter as regras de negócio de fornencedor quando a
		 * operação for salvar
		 */
		List<IStrategy> rnsSalvarFabricante = new ArrayList<IStrategy>();
		/* Adicionando as regras a serem utilizadas na operação salvar do fornecedor */
		rnsSalvarFabricante.add(vdlFabri);

		/*
		 * Cria o mapa que poderá conter todas as listas de regras de negócio específica
		 * por operação do fornecedor
		 */
		Map<String, List<IStrategy>> rnsFabricante = new HashMap<String, List<IStrategy>>();
		/*
		 * Adiciona a listra de regras na operação salvar no mapa do fornecedor (lista
		 * criada na linha 70)
		 */
		rnsFabricante.put("SALVAR",rnsSalvarFabricante);
		
		
		/*
		 * Adiciona o mapa(criado na linha 79) com as regras indexadas pelas operações
		 * no mapa geral indexado pelo nome da entidade
		 */
		rns.put(Fabricante.class.getName(), rnsFabricante);
		
		
		/*------------------------------------------------------*/
		

		/*
		 * Criando uma lista para conter as regras de negócio de fornencedor quando a
		 * operação for salvar
		 */
		List<IStrategy> rnsSalvarVenda = new ArrayList<IStrategy>();
		/* Adicionando as regras a serem utilizadas na operação salvar do fornecedor */
		rnsSalvarVenda.add(vdlVenda);

		/*
		 * Cria o mapa que poderá conter todas as listas de regras de negócio específica
		 * por operação do fornecedor
		 */
		Map<String, List<IStrategy>> rnsVenda = new HashMap<String, List<IStrategy>>();
		/*
		 * Adiciona a listra de regras na operação salvar no mapa do fornecedor (lista
		 * criada na linha 70)
		 */
		rnsVenda.put("SALVAR",rnsSalvarVenda);
		
		
		/*
		 * Adiciona o mapa(criado na linha 79) com as regras indexadas pelas operações
		 * no mapa geral indexado pelo nome da entidade
		 */
	rns.put(Venda.class.getName(), (Map<String, List<IStrategy>>) rnsSalvarVenda);
		

	
		/*
		 * Criando uma lista para conter as regras de negócio de fornencedor quando a
		 * operação for salvar
		 */
		List<IStrategy> rnsSalvarEstado = new ArrayList<IStrategy>();
		/* Adicionando as regras a serem utilizadas na operação salvar do fornecedor */
		rnsSalvarEstado.add(vdlEstado);

		/*
		 * Cria o mapa que poderá conter todas as listas de regras de negócio específica
		 * por operação do fornecedor
		 */
		Map<String, List<IStrategy>> rnsEstado = new HashMap<String, List<IStrategy>>();
		/*
		 * Adiciona a listra de regras na operação salvar no mapa do fornecedor (lista
		 * criada na linha 70)
		 */
		rnsEstado.put("SALVAR",rnsSalvarEstado);
		
		
		/*
		 * Adiciona o mapa(criado na linha 79) com as regras indexadas pelas operações
		 * no mapa geral indexado pelo nome da entidade
		 */
		rns.put(Estado.class.getName(), rnsEstado);
		
		/* --------------------------------------------------- */

		
		/*
		 * Criando uma lista para conter as regras de negócio de fornencedor quando a
		 * operação for salvar
		 */
		List<IStrategy> rnsSalvarUsuario = new ArrayList<IStrategy>();
		/* Adicionando as regras a serem utilizadas na operação salvar do fornecedor */
		rnsSalvarUsuario.add(vdlUsuario);

		/*
		 * Cria o mapa que poderá conter todas as listas de regras de negócio específica
		 * por operação do fornecedor
		 */
		Map<String, List<IStrategy>> rnsUsuario = new HashMap<String, List<IStrategy>>();
		/*
		 * Adiciona a listra de regras na operação salvar no mapa do fornecedor (lista
		 * criada na linha 70)
		 */
		rnsUsuario.put("SALVAR", rnsSalvarUsuario);

		/*
		 * Adiciona o mapa(criado na linha 79) com as regras indexadas pelas operações
		 * no mapa geral indexado pelo nome da entidade
		 */
		rns.put(Usuario.class.getName(), rnsUsuario);

		/*
		 * Criando uma lista para conter as regras de negócio de fornencedor quando a
		 * operação for salvar
		 */
		List<IStrategy> rnsSalvarAdministrador = new ArrayList<IStrategy>();
		/* Adicionando as regras a serem utilizadas na operação salvar do fornecedor */
		rnsSalvarAdministrador.add(vdlADM);

		/*
		 * Cria o mapa que poderá conter todas as listas de regras de negócio específica
		 * por operação do fornecedor
		 */
		Map<String, List<IStrategy>> rnsAdminsitrador = new HashMap<String, List<IStrategy>>();
		/*
		 * Adiciona a listra de regras na operação salvar no mapa do fornecedor (lista
		 * criada na linha 70)
		 */
		rnsAdminsitrador.put("SALVAR", rnsSalvarAdministrador);

		/*
		 * Adiciona o mapa(criado na linha 79) com as regras indexadas pelas operações
		 * no mapa geral indexado pelo nome da entidade
		 */
		rns.put(Administrador.class.getName(), rnsAdminsitrador);

		/*
		 * Criando uma lista para conter as regras de negócio de cliente quando a
		 * operação for salvar
		 */
		List<IStrategy> rnsSalvarCliente = new ArrayList<IStrategy>();
		/* Adicionando as regras a serem utilizadas na operação salvar do cliente */
		rnsSalvarCliente.add(vldCliente);
		rnsSalvarCliente.add(vdlCPF);

		/*
		 * Cria o mapa que poderá conter todas as listas de regras de negócio específica
		 * por operação do cliente
		 */
		Map<String, List<IStrategy>> rnsCliente = new HashMap<String, List<IStrategy>>();
		/*
		 * Adiciona a listra de regras na operação salvar no mapa do cliente (lista
		 * criada na linha 93)
		 */
		rnsCliente.put("SALVAR", rnsSalvarCliente);
		/*
		 * Adiciona o mapa(criado na linha 101) com as regras indexadas pelas operações
		 * no mapa geral indexado pelo nome da entidade. Observe que este mapa (rns) é o
		 * mesmo utilizado na linha 88.
		 */
		// rns.put(Cliente.class.getName(), rnsCliente);

		/*
		 * Criando uma lista para conter as regras de negócio de produto quando a
		 * operação for salvar
		 */
		List<IStrategy> rnsSalvarProduto = new ArrayList<IStrategy>();
		/* Adicionando as regras a serem utilizadas na operação salvar do produto */
		rnsSalvarProduto.add(vdlProduto);

		/*
		 * Criando uma lista para conter as regras de negócio de produto quando a
		 * operação for alterar
		 */
		List<IStrategy> rnsAlterarProduto = new ArrayList<IStrategy>();
		/* Adicionando as regras a serem utilizadas na operação alterar do produto */
		rnsAlterarProduto.add(vdlProduto);

		/*
		 * Cria o mapa que poderá conter todas as listas de regras de negócio específica
		 * por operação do produto
		 */
		Map<String, List<IStrategy>> rnsProduto = new HashMap<String, List<IStrategy>>();
		/*
		 * Adiciona a listra de regras na operação salvar no mapa do produto (lista
		 * criada na linha 114)
		 */
		rnsProduto.put("SALVAR", rnsSalvarProduto);
		/*
		 * Adiciona a listra de regras na operação alterar no mapa do produto (lista
		 * criada na linha 122)
		 */
		rnsProduto.put("ALTERAR", rnsAlterarProduto);

		/*
		 * Adiciona o mapa(criado na linha 129) com as regras indexadas pelas operações
		 * no mapa geral indexado pelo nome da entidade. Observe que este mapa (rns) é o
		 * mesmo utilizado na linha 88.
		 */
		rns.put(Produto.class.getName(), rnsProduto);

	}

	private String executarRegras(Entidade entidade, String operacao) {
		String nmClasse = entidade.getClass().getName();
		StringBuilder msg = new StringBuilder();

		Map<String, List<IStrategy>> regrasOperacao = rns.get(nmClasse);

		if (regrasOperacao != null) {
			List<IStrategy> regras = regrasOperacao.get(operacao);

			if (regras != null) {
				 for(IStrategy s: regras){
			String m = s.processar(entidade);

				
				  if(m != null){ msg.append(m); msg.append("\n"); } }
				 
			}

		}

		if (msg.length() > 0)
			return msg.toString();
		else
			return null;
	}



	@Override
	public Resultado salvar(EntidadeDominio entidade) {
		List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
		//EntidadeDominio entidade = null;
		resultado = new Resultado();
		String nmClasse = entidades.getClass().getName();

		String msg = executarRegras(entidade, "SALVAR");

		if (msg == null) {
			IDAO dao = daos.get(nmClasse);
			dao.salvar(entidade);

			entidades.add(0, entidade);
			resultado.setEntidades(entidades);
		} else {
			resultado.setMsg(msg);

		}

		return resultado;
	}


	@Override
	public Resultado excluir(EntidadeDominio entidade) {
		List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
		resultado = new Resultado();
		String nmClasse = entidades.getClass().getName();

		String msg = executarRegras(entidade, "EXCLUIR");

		if (msg == null) {
			IDAO dao = daos.get(nmClasse);
			dao.excluir(entidade);

			entidades.add(entidade);
			resultado.setEntidades(entidades);
		} else {
			resultado.setMsg(msg);

		}

		return resultado;
	}


	@Override
	public Resultado consultar(EntidadeDominio entidade) {
		List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "EXCLUIR");

		if (msg == null) {
			IDAO dao = daos.get(nmClasse);
			dao.buscar();

			entidades.add(entidade);
			resultado.setEntidades(entidades);
		} else {
			resultado.setMsg(msg);

		}

		return resultado;
	}


	@Override
	public Resultado visualizar(EntidadeDominio entidade) {
		resultado = new Resultado();
		resultado.setEntidades(new ArrayList<EntidadeDominio>(1));
		//resultado.getEntidades().add();
		return resultado;
	
	}



	@Override
	public Resultado alterar(Entidade entidade) {
		List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "ALTERAR");

		if (msg == null) {
			IDAO dao = daos.get(nmClasse);
			dao.alterar();
			//entidades.add(0, entidade);
			resultado.setEntidades(entidades);
		} else {
			resultado.setMsg(msg);

		}

		return resultado;
	}

}
