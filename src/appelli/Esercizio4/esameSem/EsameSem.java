package appelli.Esercizio4.esameSem;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class EsameSem extends Esame {

	private Semaphore mutex = new Semaphore(1); 
	private  Semaphore prendiPosto = new Semaphore(1,true);
	private Semaphore inizioEsame = new Semaphore(0);
	private Semaphore possoConsegnare = new Semaphore(0,true);
	private int numStudOG;
	private static Random r = new Random();
	
	
	public EsameSem(int numStud) {
		super(numStud);
		numStudOG=numStud;
	}

	@Override
	public void prendiPosto() throws InterruptedException {
		prendiPosto.acquire();
		mutex.acquire();
		numStudenti--;
		if(numStudenti==0) {
			inizioEsame.release();
			prendiPosto.release(); //rimetto a posto il permesso anche se non serve
			mutex.release();
		}
		else{
			mutex.release();
			prendiPosto.release();
		}
	}

	@Override
	public void consegnaCompito() throws InterruptedException {
		possoConsegnare.acquire();
		mutex.acquire();
		Studente studCurr = (Studente)Thread.currentThread();
		if(studCurr.getTraccia().equals("A"))
			studentiTA.add(studCurr);
		if(studCurr.getTraccia().equals("B"))
			studentiTB.add(studCurr);
		if(studCurr.getTraccia().equals("C"))
			studentiTC.add(studCurr);
		if(studCurr.getTraccia().equals("D"))
			studentiTD.add(studCurr);
		numStudenti++;
		if(numStudenti==numStudOG) { //simulo che hanno consegnato tutti e l'aula si svuota
			studentiTA.clear();
			studentiTB.clear();
			studentiTC.clear();
			studentiTD.clear();
		}
		mutex.release();
		possoConsegnare.release();
	}

	@Override
	public void iniziaEsame() throws InterruptedException {
		inizioEsame.acquire();
		mutex.acquire();
	}

	@Override
	public void fineEsame(){
		mutex.release();
		inizioEsame.release();
		possoConsegnare.release();
		
	}
	
	private static String scegliTraccia() {
		int i = r.nextInt(3);
		if(i==0) return "A";
		if(i==1) return "B";
		if(i==2) return "C";
		return "D";
	}
	
	public static void main(String[] args) {
		Esame esame = new EsameSem(60);
		Docente doc = new Docente(esame);
		
		for(int i=0; i<60; i++) {
			String traccia=scegliTraccia();
			new Studente(traccia).start();;
		}
		doc.start();
	}
	
	//la traccia la scelgo al momento della creazione nel main
	
}
