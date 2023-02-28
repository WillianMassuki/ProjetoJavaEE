package br.com.willian.les2019.ecommerce.testers;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import br.com.willian.les2019.ecommerce.dao.ItemDAO;
import br.com.willian.les2019.ecommerce.dao.ProdutoDAO;
import br.com.willian.les2019.ecommerce.dao.VendaTesteDAO;
import br.com.willian.les2019.ecommerce.dominio.Cartao;
import br.com.willian.les2019.ecommerce.dominio.Item;
import br.com.willian.les2019.ecommerce.dominio.Produto;
import br.com.willian.les2019.ecommerce.dominio.Venda;

public class PopuladorTabelas {

	public static void main(String[] args) {
		Calendar instance = Calendar.getInstance();
		instance.set(2019, 1, 1);
		long comeco = instance.getTimeInMillis();
		long agora = Calendar.getInstance().getTimeInMillis();
		ProdutoDAO produtoDAO = new ProdutoDAO();
		ItemDAO itemDAO = new ItemDAO();
		VendaTesteDAO vendaDAO = new VendaTesteDAO();
		List<Produto> listar = produtoDAO.listar();		
		for (int i = 0 ; i < 100 ; i++) {
			
			long aleatorio = ThreadLocalRandom.current().nextLong(comeco, agora);
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			System.out.println(String.valueOf(i) + " - " +dateFormat.format(aleatorio));
			
			Venda venda = new Venda();
			Date dataAleatoria = new Date(aleatorio);
			venda.setHorario(dataAleatoria);
			Cartao cartao = new Cartao();
			cartao.setCodigo(2);
			venda.setCartao(cartao);
			venda.setPrecoTotal(new BigDecimal(0));
			vendaDAO.salvar(venda);
//			List<Produto> vendaProdutos = new ArrayList<>();
			int qtdeProdutosAPegar = ThreadLocalRandom.current().nextInt(1, listar.size());
			double precoTotal = 0;
			for (int j = 0 ; j <= qtdeProdutosAPegar; j++ ) {
				int posicaoProduto = ThreadLocalRandom.current().nextInt(0, listar.size());
//				vendaProdutos.add(listar.get(posicaoProduto));
				Item item = new Item();
				Produto produtoTmp = listar.get(posicaoProduto);
//				System.out.println("Produto: " + produtoTmp.toString());
				item.setProduto(produtoTmp);
				item.setVenda(venda);
				item.setQuantidade((short) 1);
				item.setPrecoParcial(produtoTmp.getPreco());
				precoTotal += produtoTmp.getPreco().doubleValue();
				itemDAO.salvar(item);
			}			
			venda.setPrecoTotal(new BigDecimal(precoTotal));
			vendaDAO.salvar(venda);
		}
		
	}
	
	public static void main1(String[] args) {
		Calendar instance = Calendar.getInstance();
		instance.set(2019, 1, 1);
		long comeco = instance.getTimeInMillis();
		System.out.println(comeco);
		long agora = Calendar.getInstance().getTimeInMillis();
		System.out.println(agora);
//		long diff = agora - comeco;
		for (int i = 0 ; i < 10 ; i++) {
			
			long aleatorio = ThreadLocalRandom.current().nextLong(comeco, agora);
//			System.out.println(aleatorio);
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			System.out.println(dateFormat.format(aleatorio));
			
		}
	}

	
}
