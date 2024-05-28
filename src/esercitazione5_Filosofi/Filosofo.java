package esercitazione5_Filosofi;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Filosofo extends Thread{
	private Tavolo tavolo;
	private int posizione;
	
	public Filosofo( Tavolo t, int pos ) {
		tavolo = t;
		posizione = pos;
	}
	
	public void run() {
		try {
			while(true) {
				tavolo.prendBacchette(posizione);
				System.out.format("Il filosofo %d ha iniziato a mangiare%n", posizione);
				mangia();
				System.out.format("Il filosofo %d ha finito di mangiare%n", posizione);
				tavolo.rilasciaBacchette(posizione);
				pensa();
			}
		}
		catch(InterruptedException e ) { e.printStackTrace(); }
	}
	
	//simulazione mangia e pensa
	private final static int MIN_TEMPO_MANGIA = 1; 
	private final static int MAX_TEMPO_MANGIA = 4;
	private final static int MIN_TEMPO_PENSA = 6;
	private final static int MAX_TEMPO_PENSA = 10;
	
	private Random random = new Random();
	
	private void mangia() throws InterruptedException {
		attendi(MIN_TEMPO_MANGIA, MAX_TEMPO_MANGIA);
	}
	
	private void pensa() throws InterruptedException {
		attendi(MIN_TEMPO_PENSA, MAX_TEMPO_PENSA);
	}
	
	private void attendi(int min, int max) throws InterruptedException {
		TimeUnit.SECONDS.sleep(random.nextInt(max - min + 1) + min);
	}
}
