package appelli.Esercizio4.salaBowling;

import java.util.LinkedList;

public abstract class SalaBowling {
	protected LinkedList<Giocatore> listaGiocatori = new LinkedList<>();
	protected String[] listaNomi;
	protected int numGiocatori;
	
	public SalaBowling(int numGiocatori, String[] listaNomi) {
		this.numGiocatori=numGiocatori;
		this.listaNomi=listaNomi;
	}
	
	public abstract String fornisciInformazioni() throws InterruptedException; 
	public abstract void preparaPartita() throws InterruptedException;
	public abstract void gioca(String info) throws InterruptedException;
	public abstract void deposita() throws InterruptedException;
	
	public void test() {
		for(int i=0; i<numGiocatori; i++) {
			Giocatore g = new Giocatore(listaNomi[i], Integer.toString(i+39), this);
			listaGiocatori.add(g);
		}
		for(Giocatore g : listaGiocatori) {
			g.start();
		}
	}
}
