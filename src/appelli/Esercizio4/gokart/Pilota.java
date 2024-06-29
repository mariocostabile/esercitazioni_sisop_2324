package appelli.Esercizio4.gokart;

public class Pilota extends Thread{
	
	private int eta;
	private Pista pista;
	
	public Pilota(int eta, Pista pista) {
		this.eta=eta;
		this.pista=pista;
	}
	
	public void run() {
		try {
			pista.noleggia();
			
			
		} catch (Exception e) {}
	}
	
	
}
