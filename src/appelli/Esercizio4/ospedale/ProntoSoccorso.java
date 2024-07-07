package appelli.Esercizio4.ospedale;

import java.util.Iterator;
import java.util.LinkedList;

public abstract class ProntoSoccorso {

//	protected Paziente[] paziente; //poi viene riempito nel test
	protected LinkedList<Thread> verde = new LinkedList<>();
	protected LinkedList<Thread> giallo = new LinkedList<>();
	protected LinkedList<Thread> rosso = new LinkedList<>();
	
	private int numVerdi;
	private int numGialli;
	private int numRossi;
	
	
	public ProntoSoccorso(int numVerdi, int numGialli, int numRossi) {
		this.numVerdi=numVerdi;
		this.numGialli=numGialli;
		this.numRossi=numRossi;
		
	}
	
	public abstract void iniziaVisita()throws InterruptedException;
	public abstract void terminaVisita()throws InterruptedException;
	public abstract void accediPaziente()throws InterruptedException;
	public abstract void esciPaziente()throws InterruptedException;
	
	public void test() {
		
		//riempimento
		for(int i=0; i<numVerdi;i++) {
			Paziente p = new Paziente(this,"verde");
			verde.add(p);
			p.start();
		}
		for(int i=0; i<numGialli;i++) {
			Paziente p = new Paziente(this,"giallo");
			giallo.add(p);
			p.start();
		}
		for(int i=0; i<numRossi;i++) {
			Paziente p = new Paziente(this,"rosso");
			rosso.add(p);
			p.start();
		}
		
//		//start
//		for(Thread t: verde) 
//			t.start();
//		}
//		for(Thread t: giallo) {
//			t.start();
//		}
//		for(Thread t: rosso) {
//			t.start();
//		}
		
		
	}
	
}
