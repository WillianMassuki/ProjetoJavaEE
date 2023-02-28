package br.com.willian.les2019.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import br.com.willian.les2019.ecommerce.dao.ItemTesteDAO;
import br.com.willian.les2019.ecommerce.dao.ProdutoDAO;
import br.com.willian.les2019.ecommerce.dao.VendaTesteDAO;
import br.com.willian.les2019.ecommerce.dominio.Item;
import br.com.willian.les2019.ecommerce.dominio.Produto;
import br.com.willian.les2019.ecommerce.dominio.Venda;

@SuppressWarnings("serial")
@ManagedBean
public class Grafico implements Serializable {

	private static final Logger Log = Logger.getLogger("Grafico");
	private LineChartModel lineModel1;
	private LineChartModel lineModel2;
	private LineChartModel zoomModel;
	private CartesianChartModel combinedModel;
	private CartesianChartModel fillToZero;
	private LineChartModel areaModel;

	private Venda venda;

	private List<Venda> vendas;

	@PostConstruct
	public void init() {
		createLineModels();
		createAreaModel();
	}

	public void itemSelect(ItemSelectEvent event) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
				"Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public List<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	public LineChartModel getLineModel1() {
		return lineModel1;
	}

	public LineChartModel getLineModel2() {
		return lineModel2;
	}

	public LineChartModel getZoomModel() {
		return zoomModel;
	}

	public CartesianChartModel getCombinedModel() {
		return combinedModel;
	}

	public CartesianChartModel getAreaModel() {
		return areaModel;
	}

	public CartesianChartModel getFillToZero() {
		return fillToZero;
	}

	private LineChartModel initCategoryModel() {
		String[] meses = new String[] {"Janeiro","Fevereiro","Março","Abril","Maio","Junho","Julho",
				"Agosto","Setembro","Outubro","Novembro","Dezembro"};
		LineChartModel model = new LineChartModel();
		for(Entry<String,Map<Integer,Integer>> entry : mapaVendas().entrySet()) {
			ChartSeries s = new ChartSeries();
			s.setLabel(entry.getKey());			
			for(Entry<Integer,Integer> diasMap: entry.getValue().entrySet()) {
				s.set(meses[diasMap.getKey()-1],diasMap.getValue());
				Log.info(String.format("Produto '%s': Dia[%d] -> Qtde[%d]", entry.getKey(), diasMap.getKey(), diasMap.getValue()));
			}
			model.addSeries(s);
		}
/*		
		ChartSeries boys = new ChartSeries();
		boys.setLabel("Boys");
		boys.set("JAN", 120);
		boys.set("FEV", 100);
		boys.set("MAR", 44);
		boys.set("ABR", 150);
		boys.set("MAI", 25);
		boys.set("JUN", 25);
		boys.set("JUL", 25);
		boys.set("AGO", 25);
		boys.set("SET", 25);
		boys.set("OUT", 25);
		boys.set("NOV", 25);
		boys.set("DEZ", 25);

		ChartSeries girls = new ChartSeries();
		girls.setLabel("Girls");
		girls.set("JAN", 52);
		girls.set("FEV", 60);
		girls.set("MAR", 110);
		girls.set("ABR", 90);
		girls.set("MAI", 120);
		girls.set("JUN", 120);
		girls.set("JUL", 120);
		girls.set("AGO", 120);
		girls.set("SET", 120);
		girls.set("OUT", 120);
		girls.set("NOV", 120);
		girls.set("DEZ", 120);

		model.addSeries(boys);
		model.addSeries(girls);
		*/

		return model;
	}
	
	public Map<String,Map<Integer,Integer>> mapaVendas()
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
			Map<Integer,Integer> mapaTmp = new TreeMap<>(); // criando um hashmap temporario
			while (calIteracao.before(calFim)) { // enquando a data de inicio for antes da data de fim
				calIteracao.add(Calendar.MONTH, 1); // adicionando o mes e o produto
				mapaTmp.put(calIteracao.get(Calendar.MONTH), 0); // adicionando no mapa temporario a data de incio e o valor do produto
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
//			System.out.println("Qtde Produtos: " + produtosVenda.size());
			for(Item it: produtosVenda) { // varrendo a lista de produtos venda
				Map<Integer, Integer> map = mapaDias.get(it.getProduto().getDescricao()); // criando um mapa, aonde vai passando o nome dos produtos
				if (map == null) { // se o mapa for igual a null
					map = new LinkedHashMap<>(); // crio um hashmap
					mapaDias.put(it.getProduto().getDescricao(), map); // setando no mapa de dias os nomes dos produtos
				}
				Integer valor = map.get(calDataVenda.get(Calendar.MONTH)); // passando no valor o mapa da data de venda com os meses
				if (valor == null) { // se valor for igual a null
					map.put(calDataVenda.get(Calendar.MONTH),1); // no map to chamando a data de venda e pegando o mês e o produto
				} else { // senão
					map.put(calDataVenda.get(Calendar.MONTH),(valor +1)); // no map to chamando a data de venda e pegando o mês e o produto
				}																// o valor adicionando mais 1
				
			}
		}
		return mapaDias; // retornando o mapa de dias
		
	}

	private void createLineModels() {
		
		lineModel2 = initLinearModel();
		//lineModel1.setTitle("Linear Chart");
		//lineModel1.setLegendPosition("e");
		Axis yAxis = lineModel2.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(12);

		lineModel2 = initCategoryModel();
		lineModel2.setTitle("PRODUTOS VENDIDO EM UM ANO");
		lineModel2.setLegendPosition("e");
		lineModel2.setShowPointLabels(true);
		lineModel2.getAxes().put(AxisType.X, new CategoryAxis("MESES"));
		yAxis = lineModel2.getAxis(AxisType.Y);
		yAxis.setLabel("QUANTIDADE");
		yAxis.setMin(0);
		yAxis.setMax(200);
	}

	private void createAreaModel() {
		areaModel = new LineChartModel();
		//Log.info("Carregando Mapa de Vendas de Produtos" + mapaVendas().toString());
		/*for(Entry<String,Map<Integer,Integer>> entry : mapaVendas().entrySet()) {
	    	  ChartSeries s = new ChartSeries();
		      s.setLabel(entry.getKey());			
		      for(Entry<Integer,Integer> diasMap: entry.getValue().entrySet()) {
		    	  s.set(diasMap.getKey().toString(),diasMap.getValue());
		    	  Log.info(String.format("Produto '%s': Dia[%d] -> Qtde[%d]", entry.getKey(), diasMap.getKey(), diasMap.getValue()));
		      }
		   	areaModel.addSeries(s);
		      
	      }*/
	      
		

		ChartSeries boys = new ChartSeries();
		//boys.setFill(true);
		boys.setLabel("Boys");
		boys.set("JANEIRO", 120);
		boys.set("FEVEREIRO", 100);
		boys.set("MARÇO", 44);
		boys.set("ABRIL", 150);
		boys.set("MAIO", 25);
		boys.set("JUNHO", 25);
		boys.set("JULHO", 25);
		boys.set("AGOSTO", 25);
		boys.set("SETEMBRO", 25);
		boys.set("OUTUBRO", 25);
		boys.set("NOVEMBRO", 25);
		boys.set("DEZEMBRO", 25);

		ChartSeries girls = new ChartSeries();
		// girls.setFill(true);
		girls.setLabel("Girls");
		girls.set("JANEIRO", 52);
		girls.set("FEVEREIRO", 60);
		girls.set("MARÇO", 110);
		girls.set("ABRIL", 90);
		girls.set("MAIO", 120);
		girls.set("JUNHO", 120);
		girls.set("JULHO", 120);
		girls.set("AGOSTO", 120);
		girls.set("SETEMBRO", 120);
		girls.set("OUTUBRO", 120);
		girls.set("NOVEMBRO", 120);
		girls.set("DEZEMBRO", 120);
		areaModel.addSeries(boys);
		areaModel.addSeries(girls);


		
		areaModel.setTitle("Area Chart");
		areaModel.setLegendPosition("e");
		areaModel.setStacked(true);
		areaModel.setShowPointLabels(true);

		Axis xAxis = new CategoryAxis("MESES");
		areaModel.getAxes().put(AxisType.X, xAxis);
		Axis yAxis = areaModel.getAxis(AxisType.Y);
		yAxis.setLabel("QUANTIDADE");
		yAxis.setMin(0);
		yAxis.setMax(300);
	}

	private LineChartModel initLinearModel() {
		LineChartModel model = new LineChartModel();

		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel("Series 1");

		series1.set(1, 2);
		series1.set(2, 1);
		series1.set(3, 3);
		series1.set(4, 6);
		series1.set(5, 8);

		LineChartSeries series2 = new LineChartSeries();
		series2.setLabel("Series 2");

		series2.set(1, 6);
		series2.set(2, 3);
		series2.set(3, 2);
		series2.set(4, 7);
		series2.set(5, 9);

		model.addSeries(series1);
		model.addSeries(series2);

		return model;
	}

}
