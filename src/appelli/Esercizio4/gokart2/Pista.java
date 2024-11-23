package appelli.Esercizio4.gokart2;

import java.util.LinkedList;

public abstract class Pista {
	protected int gokartS;
	protected int gokartL;
	protected int maxGokartM;
	protected LinkedList<Pilota> pilotiInPista = new LinkedList<>(); //lista dei piloti in pista
	protected static LinkedList<Pilota> pilotiArrivati = new LinkedList<>(); //lista dei piloti totali che sono arrivati
	//pilotiArrivati da riempire nel main 
	
	
	public Pista(int gokartS, int gokartL, int maxGokartM) {
		this.gokartS=gokartS;
		this.gokartL=gokartL;
		this.maxGokartM=maxGokartM;
	}
	
	public abstract void noleggia() throws InterruptedException;
	
	public abstract int entraInPista() throws InterruptedException;
	
	public abstract void lasciaPista();
	
	public abstract void riconsegna();
	
	public void test() {
		for(Pilota p : pilotiArrivati) {
			new Pilota(p.getEta(),this).start();
		}
	}
}
