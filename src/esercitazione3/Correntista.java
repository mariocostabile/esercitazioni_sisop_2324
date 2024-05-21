package esercitazione3;

import java.util.Random;

import javax.management.RuntimeErrorException;

public class Correntista implements Runnable {
	private final static int MIN_ATTESA = 1;
	private final static int MAX_ATTESA = 3; //serve per simulare l'attesa di un correntista prima di eseguire l'operazione
	 
	private Random random = new Random(); //servirà dopo per simulare l'attesa
	private ContoCorrente cc; //ad ogni correntista sono associati i seguenti campi della classe
	private int importo; 
	private int numOperazioni; 
	
	public Correntista(ContoCorrente cc, int importo, int numOperazioni) {
		if(numOperazioni % 2 !=0 ) {
			throw new RuntimeException("Il numero delle operazioni deve essere pari");
		}
		this.cc=cc;
		this.importo=importo;
		this.numOperazioni=numOperazioni;
	}
	
	@Override
	public void run() {
		try {
			for(int i=0; i < numOperazioni; i++) {
				attesaCasuale();
				if(i % 2 == 0) {
					cc.deposita(importo); //se il numero operazione è pari deposita altrimenti preleva
				}else {
					cc.preleva(importo);
				}
			}
		} catch (InterruptedException e) {
			System.out.println("Correntista "+ Thread.currentThread().getId() + " ha terminato le sue operazioni");
		}
	}
	
	private void attesaCasuale() throws InterruptedException{
		Thread.sleep(random.nextInt(MAX_ATTESA - MIN_ATTESA +1) + MIN_ATTESA );
	}
	
}

