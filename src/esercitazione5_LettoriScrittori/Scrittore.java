package esercitazione5_LettoriScrittori;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Scrittore implements Runnable{
	
	private MemoriaCondivisa memoria;
	
	public Scrittore( MemoriaCondivisa mem ) {
		memoria = mem; 
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				memoria.inizioScrittura();
				scrivi();
				memoria.fineScrittura();
				faiAltro();
			}
		}
		catch( InterruptedException e) {}
	}
	
	private final static int MIN_TEMPO_SCRITTURA = 1; 
	private final static int MAX_TEMPO_SCRITTURA = 4;
	private final static int MIN_TEMPO_ALTRO = 6;
	private final static int MAX_TEMPO_ALTRO = 10;
	
	private Random random = new Random();
	
	private void scrivi() throws InterruptedException {
		attendi(MIN_TEMPO_SCRITTURA, MAX_TEMPO_SCRITTURA);
		System.out.println("Sta scrivendo: " + this.toString() );
	}
	
	private void faiAltro() throws InterruptedException {
		attendi(MIN_TEMPO_ALTRO, MAX_TEMPO_ALTRO);
	}
	
	private void attendi(int min, int max) throws InterruptedException {
		TimeUnit.SECONDS.sleep(random.nextInt(max - min + 1) + min);
	}
}
