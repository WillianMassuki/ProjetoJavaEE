package br.com.willian.les2019.ecommerce.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

@SuppressWarnings("serial")
public class AutenticacaoPhaseListener implements PhaseListener {
	
	//phase listener serve para inteceptar ações dentro do frameWork 

	@Override
	public void afterPhase(PhaseEvent event) {
	/*	String paginaAtual = Faces.getViewId();
		// pegar a pagina atual que o usuario está
	
		boolean ehPaginaDeAutenticacao = paginaAtual.contains("autenticacaoCliente.xhtml");
	
		if(!ehPaginaDeAutenticacao){
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
														// pegar coisas da sessão		
			if(autenticacaoBean == null ){
				// o usuario nunca logou no sistema
				Faces.navigate("/pages/autenticacaoCliente.xhtml");
				return;
			}
			
			Usuario usuario = autenticacaoBean.getUsuarioLogado();
			if(usuario == null){
				Faces.navigate("/pages/autenticacaoCliente.xhtml");
				return;
			}
		}
		*/		
	}

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
}

}
