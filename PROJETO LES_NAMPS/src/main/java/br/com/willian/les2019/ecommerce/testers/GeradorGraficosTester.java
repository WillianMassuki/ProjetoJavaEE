package br.com.willian.les2019.ecommerce.testers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import br.com.willian.les2019.ecommerce.dao.ItemTesteDAO;
import br.com.willian.les2019.ecommerce.dao.ProdutoDAO;
import br.com.willian.les2019.ecommerce.dao.VendaTesteDAO;
import br.com.willian.les2019.ecommerce.dominio.Item;
import br.com.willian.les2019.ecommerce.dominio.Produto;
import br.com.willian.les2019.ecommerce.dominio.Venda;

public class GeradorGraficosTester {
	
	public static void main(String[] args) {
		for(Entry<String,Map<Integer,Integer>> entry : mapaVendas().entrySet()) {
						
			for(Entry<Integer,Integer> diasMap: entry.getValue().entrySet()) {
				System.out.printf("Produto '%s': Dia[%d] -> Qtde[%d]\n", entry.getKey(), diasMap.getKey(), diasMap.getValue());
			}
			
		}
	}
	
	public static Map<String,Map<Integer,Integer>> mapaVendas()
	{
		//ALTERAÇÃO		
		Map<String,Map<Integer,Integer>> mapaDias = new LinkedHashMap<>(); //criando um mapa inteiro inteiro para pegar os dois
		Calendar calInicio = Calendar.getInstance(); //	criando um objeto do tipo calendar
		Calendar calIteracao = Calendar.getInstance(); //	criando um objeto do tipo calendar		
		calInicio.set(2019, 1, 1, 0, 0, 0);
		Calendar calFim = Calendar.getInstance(); //criando um objeto do tipo calendar
		calFim.set(2019, 5, 31, 23, 59, 59);		
		ProdutoDAO produtoDAO = new ProdutoDAO(); // criando um objeto do produtoDAO
		List<String> produtos = new ArrayList<>(); // criando uma list de produtos
		for(Produto p : produtoDAO.listar()) // varrendo a lista de produtos com for
			produtos.add(p.getDescricao()); // adicionando o nome do produto na lista
//		String[] produtos = new String[] {"Produto1", "Produto2", "Produto3"};
		for(String produto : produtos) { // varrendo a lista de produtos
			calIteracao.set(2019, 1, 1, 0, 0, 0);
			Map<Integer,Integer> mapaTmp = new LinkedHashMap<>(); // criando um hashmap temporario
			while (calIteracao.before(calFim)) { // enquando a data de inicio for antes da data de fim
				calIteracao.add(Calendar.DAY_OF_MONTH, 1); // adicionando o mes e o produto
				mapaTmp.put(calIteracao.get(Calendar.DAY_OF_MONTH), 0); // adicionando no mapa temporario a data de incio e o valor do produto
			}
			mapaDias.put(produto, mapaTmp); // adicionando no (hashmap) mapa de dias o produto e o (hashmap) mapatemporario
		}
		VendaTesteDAO vendaDAO = new VendaTesteDAO(); // criando um objeto DAO
		System.out.println(calInicio.getTime());
		System.out.println(calFim.getTime());
		List<Venda> resultado = vendaDAO.consultarintervaloperiodo(calInicio.getTime(), calFim.getTime()); // criando uma lista de vendas e passando para essa lista a consulta da data de inicio e data fim
		ItemTesteDAO itemTesteDAO = new ItemTesteDAO(); // criando um objeto de ItemDAO
		System.out.println("Qtde Vendas: " + resultado.size());
		for(Venda vt : resultado) { // varrendo a lista de resultados
			Calendar calDataVenda = Calendar.getInstance(); // criando um objeto do tipo calendar
			calDataVenda.setTime(vt.getHorario()); // setando o horario(da tabela venda)
			List<Item> produtosVenda = itemTesteDAO.consultar(vt); // criando uma lista de produtos(da tabela de item) e passando a consulta
			System.out.println("Qtde Produtos: " + produtosVenda.size());
			for(Item it: produtosVenda) { // varrendo a lista de produtos venda
				Map<Integer, Integer> map = mapaDias.get(it.getProduto().getDescricao()); // criando um mapa, aonde vai passando o nome dos produtos
				if (map == null) { // se o mapa for igual a null
					map = new LinkedHashMap<>(); // crio um hashmap
					mapaDias.put(it.getProduto().getDescricao(), map); // setando no mapa de dias os nomes dos produtos
				}
				Integer valor = map.get(calDataVenda.get(Calendar.DAY_OF_MONTH)); // passando no valor o mapa da data de venda com os meses
				if (valor == null) { // se valor for igual a null
					map.put(calDataVenda.get(Calendar.DAY_OF_MONTH),1); // no map to chamando a data de venda e pegando o mês e o produto
				} else { // senão
					map.put(calDataVenda.get(Calendar.DAY_OF_MONTH),(valor +1)); // no map to chamando a data de venda e pegando o mês e o produto
				}																// o valor adicionando mais 1
				
			}
		}
		return mapaDias; // retornando o mapa de dias
		
	}

}
