package esercitazione5_LettoriScrittori;

import java.util.concurrent.Semaphore;

public class MemoriaCondivisaSem extends MemoriaCondivisa {
	
	private int numLettori = 0;
	
	private Semaphore lettura = new Semaphore(1);
	private Semaphore lock = new Semaphore(1);

	@Override
	public void inizioScrittura() throws InterruptedException {
		lock.acquire();
	}

	@Override
	public void fineScrittura() throws InterruptedException {
		lock.release();
	}

	@Override
	public void inizioLettura() throws InterruptedException {
		lettura.acquire();
		if( numLettori == 0 ) { //se è la prima lettura prendo il lock così non ci sono scritture altrimenti lo avevo già preso
			lock.acquire(); //se faccio acquire e uno scrittore prova a farne un'altra si avvia un meccanismo di attesa finché non sarà disponibile, appena è disponibile si risveglia e prende lui il lock
		}
		numLettori++;
		lettura.release();
	}

	@Override
	public void fineLettura() throws InterruptedException {
		lettura.acquire();
		numLettori--;
		if( numLettori==0 ) { //se è l'ultima concedo la scrittura 
			lock.release();
		}
		lettura.release();
	}
	
	public static void main(String[] args) {
		MemoriaCondivisaSem mem = new MemoriaCondivisaSem();
		mem.test(5, 5);
	}
	
	
}
