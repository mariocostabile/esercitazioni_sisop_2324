package esercitazione3;

import java.util.concurrent.Semaphore;

//Slide esercitazione 4

public class ContoCorrenteSem extends ContoCorrente {
	
	private Semaphore mutex = new Semaphore(1);
	
	public ContoCorrenteSem(int depositoIniziale) {
		super(depositoIniziale);
	}
	
	@Override
	public void deposita(int importo) {
		try {
			mutex.acquire();
			deposito+=importo;
			mutex.release();
		} catch (InterruptedException e) {}
	}
	
	@Override
	public void preleva(int importo) {
		try {
			mutex.acquire();
			deposito-=importo;
			mutex.release();
		} catch (InterruptedException e) {}	
	}
}
