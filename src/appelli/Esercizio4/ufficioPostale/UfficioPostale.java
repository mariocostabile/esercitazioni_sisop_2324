package appelli.Esercizio4.ufficioPostale;

import java.util.LinkedList;

public abstract class UfficioPostale {
	
	protected LinkedList<Cliente> codaClientiA= new LinkedList<>();
	protected LinkedList<Cliente> codaClientiB= new LinkedList<>();
	protected LinkedList<Cliente> codaClientiC= new LinkedList<>();
	protected LinkedList<Cliente> codaTicket= new LinkedList<>(); //in coda per il ticket
	
	public UfficioPostale(LinkedList<Cliente> codaClientiA, LinkedList<Cliente> codaClientiB,
			LinkedList<Cliente> codaClientiC, LinkedList<Cliente> codaTicket) {
		super();
		this.codaClientiA = codaClientiA;
		this.codaClientiB = codaClientiB;
		this.codaClientiC = codaClientiC;
		this.codaTicket = codaTicket;
	}
	
	public abstract boolean ritiraTicket( String operazione );
	public abstract void attendiSportello( String operazione ) throws InterruptedException;
	public abstract void prossimoCliente() throws InterruptedException; //impiegato
	public abstract void eseguiOperazione() throws InterruptedException; //impiegato
	
	public void test() {
		//TODO
		//Inizializza i thread e fa start con 2 for 
		//rimepie codaTicket
	}
	
}
