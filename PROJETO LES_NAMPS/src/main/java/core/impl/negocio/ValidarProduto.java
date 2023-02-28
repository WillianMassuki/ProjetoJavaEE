package core.impl.negocio;

import java.math.BigDecimal;

import br.com.willian.les2019.ecommerce.dominio.Entidade;
import br.com.willian.les2019.ecommerce.dominio.Produto;
import core.IStrategy;

public class ValidarProduto implements IStrategy {

	@Override
	public String processar(Entidade entidade) {
		Produto produto = new Produto();
		
		String descricaoProduto = produto.getDescricao();
		Short quantidade = produto.getQuantidade();
		BigDecimal preco = produto.getPreco();
		String DescricaoFabri = produto.getFabricante().getDescricao();
		
		if(descricaoProduto.trim().equals("") || DescricaoFabri.trim().equals("") )
		{
			return "Todos os campos são obrigatorios";
		}
		
		if(descricaoProduto == null || quantidade == null || preco == null || DescricaoFabri == null )
		{
			return "Todos os campos são obrigatorios";
		}

	
		return null;
	}

}
