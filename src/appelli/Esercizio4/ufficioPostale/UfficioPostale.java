package appelli.Esercizio4.ufficioPostale;

import java.util.LinkedList;

public abstract class UfficioPostale {
	
	protected LinkedList<Cliente> filaA = new LinkedList<>();
	protected LinkedList<Cliente> filaB = new LinkedList<>();
	protected LinkedList<Cliente> filaC = new LinkedList<>();
	protected int ticketA=50;
	protected int ticketB=50;
	protected int ticketC=50;
	protected int numClienti;
	
	
	public UfficioPostale(int numClienti) {
		this.numClienti=numClienti;
	}
	
	public abstract boolean ritiraTicket(String operazione) throws InterruptedException;
	
	public abstract void attendiSportello(String operazione) throws InterruptedException;
	
	public abstract void prossimoCliente() throws InterruptedException;
	
	public abstract void eseguiOperazione() throws InterruptedException;
	
	
}
