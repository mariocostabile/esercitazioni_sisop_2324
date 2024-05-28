package esercitazione5_ProduttoreConsumatore;

import java.nio.channels.InterruptedByTimeoutException;
import java.sql.Time;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Produttore implements Runnable {
	private static final int MAX_RANDOM = 10;
	private static final int MIN_TEMPO_PRODUZIONE = 1, MAX_TEMPO_PRODUZIONE = 10;
	
	private Random random = new Random();
	private Buffer buffer;
	
	public Produttore(Buffer b) { buffer = b; }

	//ho inserito un limite alle operazioni di produzione per evitare che il programma non termini mai
	@Override
	public void run() {
		try {
			while(true) {
				int i = produci();
				buffer.put(i);
			}
		} catch (InterruptedException e) {}
	}
	
	private int produci() throws InterruptedException{
		attendi(MIN_TEMPO_PRODUZIONE, MAX_TEMPO_PRODUZIONE); //attende prima di produrre 
		return random.nextInt(MAX_RANDOM); //produce un prodotto che ha un peso tra 1 e 10
		
	}
	
	private void attendi(int min, int max) throws InterruptedException{ //simula l'attesa con un intervallo tra 1 e 10 secondi
		TimeUnit.SECONDS.sleep(random.nextInt(max - min + 1) + min);
	}
}
