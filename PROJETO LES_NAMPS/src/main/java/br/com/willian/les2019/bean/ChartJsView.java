package br.com.willian.les2019.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import br.com.willian.les2019.ecommerce.dao.ItemTesteDAO;
import br.com.willian.les2019.ecommerce.dao.ProdutoDAO;
import br.com.willian.les2019.ecommerce.dao.VendaTesteDAO;
import br.com.willian.les2019.ecommerce.dominio.Item;
import br.com.willian.les2019.ecommerce.dominio.Produto;
import br.com.willian.les2019.ecommerce.dominio.Venda;


@ManagedBean
@ViewScoped
public class ChartJsView implements Serializable {
	private LineChartModel lineModel;
	
	private Venda venda;
	
	private List<Venda> vendas;
	
	// trazer todas as vendas dos ultimos 30 dias
	
	// se exemplo venda for maior do que dia 3105.2019 00 hora, 00 minuto e 00 segundo e menor do que 31.05.2019 23 hr 59 minuto e 59 segundo
	
//	se exemplo venda for maior do que dia 31 05.2019 00 hora, 00 minuto e 00 segundo e menor do que 31.05.2019 23 hr 59 minuto e 59 segundo
	
	
	
	
	
	public Map<String,Map<Integer,Integer>> mapaVendas()
	{
		//ALTERAÇÃO
		Date datainicio = new Date(2019,5,1,0,0,0);// criando o objeto date
		Date datafim = new Date(2019,5,31,23,59,59);//criando o objeto date
		Map<String,Map<Integer,Integer>> mapaDias = new LinkedHashMap<>(); //criando um mapa inteiro inteiro para pegar os dois
		Calendar calInicio = Calendar.getInstance(); //	criando um objeto do tipo calendar	
		Calendar calFim = Calendar.getInstance(); //criando um objeto do tipo calendar
		calFim.setTime(datafim); // setando o avlor da data de fim
		ProdutoDAO produtoDAO = new ProdutoDAO(); // criando um objeto do produtoDAO
		List<String> produtos = new ArrayList<>(); // criando uma list de produtos
		for(Produto p : produtoDAO.listar()) // varrendo a lista de produtos com for
			produtos.add(p.getDescricao()); // adicionando o nome do produto na lista
//		String[] produtos = new String[] {"Produto1", "Produto2", "Produto3"};
		for(String produto : produtos) { // varrendo a lista de produtos
			calInicio.setTime(datainicio); // setando a data de inicio
			Map<Integer,Integer> mapaTmp = new LinkedHashMap<>(); // criando um hashmap temporario
			while (calInicio.before(calFim)) { // enquando a data de inicio for antes da data de fim
				calInicio.add(Calendar.DAY_OF_MONTH, 1); // adicionando o mes e o produto
				mapaTmp.put(calInicio.get(Calendar.DAY_OF_MONTH), 0); // adicionando no mapa temporario a data de incio e o valor do produto
			}
			mapaDias.put(produto, mapaTmp); // adicionando no (hashmap) mapa de dias o produto e o (hashmap) mapatemporario
		}
		VendaTesteDAO vendaDAO = new VendaTesteDAO(); // criando um objeto DAO
		List<Venda> resultado = vendaDAO.consultarintervaloperiodo(datainicio, datafim); // criando uma lista de vendas e passando para essa lista a consulta da data de inicio e data fim
		ItemTesteDAO itemTesteDAO = new ItemTesteDAO(); // criando um objeto de ItemDAO
		for(Venda vt : resultado) { // varrendo a lista de resultados
			Calendar calDataVenda = Calendar.getInstance(); // criando um objeto do tipo calendar
			calDataVenda.setTime(vt.getHorario()); // setando o horario(da tabela venda)
			List<Item> produtosVenda = itemTesteDAO.consultar(vt); // criando uma lista de produtos(da tabela de item) e passando a consulta
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

	
	@PostConstruct
	  public void init() {
	      lineModel = new LineChartModel();
//	      LineChartSeries s = new LineChartSeries();
//	      s.setLabel("");
	      
	      for(Entry<String,Map<Integer,Integer>> entry : mapaVendas().entrySet()) {
	    	  LineChartSeries s = new LineChartSeries();
		      s.setLabel(entry.getKey());
		      for(Entry<Integer,Integer> diasMap: entry.getValue().entrySet()) {
		    	  s.set(diasMap.getKey(),diasMap.getValue());
		      }
		      lineModel.addSeries(s);
	      }
//	      s.set(1, 2);
//	      s.set(2, 4);
//	      s.set(3, 5);
//	      s.set(4, 6);
//	      s.set(5, 7);
//	      s.set(6, 8);
//	      lineModel.addSeries(s);	      
	      
	      lineModel.setLegendPosition("e");
	      Axis y = lineModel.getAxis(AxisType.Y);
	      y.setMin(0);
	      y.setMax(10);
	      y.setTickInterval("1");
	      y.setLabel("Quantidade");

	      Axis x = lineModel.getAxis(AxisType.X);
//	      x.setMin(0);
//	      x.setMax(7);
	      x.setTickInterval("1");
	      x.setLabel("Dias");

	      
	  }

	  public LineChartModel getLineModel() {
	      return lineModel;
	  }
}
