package appelli.Esercizio4.ospedale;

import java.util.LinkedList;

public abstract class ProntoSoccorso {
	protected Medico medico;
//	protected Paziente[] paziente; //poi viene riempito nel test
	protected LinkedList<Thread> verde = new LinkedList<>();
	protected LinkedList<Thread> giallo = new LinkedList<>();
	protected LinkedList<Thread> rosso = new LinkedList<>();
	
	public ProntoSoccorso(Medico medico) {
		this.medico=medico;
	}
	
	public abstract void iniziaVisita()throws InterruptedException;
	public abstract void terminaVisita()throws InterruptedException;
	public abstract void accediPaziente()throws InterruptedException;
	public abstract void esciPaziente()throws InterruptedException;
	
	public void test() {
		//TODO
	}
	
}
