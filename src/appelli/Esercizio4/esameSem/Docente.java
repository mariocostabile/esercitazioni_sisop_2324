package appelli.Esercizio4.esameSem;

import java.util.concurrent.TimeUnit;

public class Docente extends Thread{

	private Esame esame;
	
	public Docente(Esame esame) {
		this.esame=esame;
	}
	
	public void run() {
		try {
		esame.iniziaEsame();
		svolgimento();
		esame.fineEsame();
		}catch(InterruptedException e){}
	}
	
	private void svolgimento() throws InterruptedException{
		TimeUnit.HOURS.sleep(2);
	}
}
