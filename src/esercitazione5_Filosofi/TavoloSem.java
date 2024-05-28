package esercitazione5_Filosofi;

import java.util.concurrent.Semaphore;

public class TavoloSem extends Tavolo {
	Semaphore bacchetta[] = new Semaphore[5];
	
	public TavoloSem() {
		for(int i=0; i<bacchetta.length; i++)
			bacchetta[i] = new Semaphore(1);
	}

	@Override
	public void prendBacchette(int i) throws InterruptedException {
		bacchetta[i].acquire();
		bacchetta[(i+1) % 5].acquire();
	}

	@Override
	public void rilasciaBacchette(int i) {
		bacchetta[i].release();
		bacchetta[(i+1) % 5].release();
	}
	
	public static void main(String[] args) {
		TavoloSem tavolo = new TavoloSem();
		tavolo.test();
	}

}
