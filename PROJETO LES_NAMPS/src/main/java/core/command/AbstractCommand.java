package core.command;

import core.IFachada;
import core.impl.controle.Fachada;

public abstract class AbstractCommand implements Icommand{
	
	protected IFachada fachada = new Fachada();


}
