package appelli.Esercizio4.gokart2;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Pilota extends Thread {
	
	private int eta;
	private final int MAX_TEMPO_GIRO=2;
	private final int MIN_TEMPO_GIRO=1;
	private Random random = new Random();
	 
	private Pista pista; 
	
	public Pilota(int eta, Pista pista) {
		this.eta=eta;
		this.pista=pista;
	}
	
	public int getEta() {
		return eta;
	}
	
	public void run() {
		try {
			pista.noleggia();
			int numGiri = pista.entraInPista();
			gira(numGiri);
			pista.lasciaPista();
			pista.riconsegna();
		}catch(InterruptedException e){}
	}
	
	private void gira(int numeroGiri) throws InterruptedException{
		TimeUnit.MINUTES.sleep(random.nextInt((MAX_TEMPO_GIRO-MIN_TEMPO_GIRO+1)+MIN_TEMPO_GIRO));
	}
	
	
}
