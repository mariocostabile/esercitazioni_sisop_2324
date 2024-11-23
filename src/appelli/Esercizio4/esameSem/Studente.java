package appelli.Esercizio4.esameSem;

public class Studente extends Thread {
	private String traccia;
	private Esame esame;
	
	public Studente(String traccia) {
		this.traccia=traccia;
	}
	
	public void run() {
		try {
		esame.prendiPosto();
		esame.consegnaCompito();
		}catch(InterruptedException e) {}
	}
	
	public String getTraccia() {
		return traccia;
	}
}
