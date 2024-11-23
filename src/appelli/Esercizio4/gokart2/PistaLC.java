package appelli.Esercizio4.gokart2;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.*;

public class PistaLC extends Pista{
	
	private final int MAX_GIRI=10; //li metto qui perché devo ritornare nel metodo il num di giri scelto
	private final int MIN_GIRI=3;
	private Lock l = new ReentrantLock();
	private Condition noleggio = l.newCondition(); //possoNoleggiare
	private Condition entra = l.newCondition(); //possoEntrarePista
	private LinkedList<Pilota> attesaNoleggio = new LinkedList<>();
	private LinkedList<Pilota> attesaPista = new LinkedList<>();
	private Random r = new Random();
	
	
	public PistaLC(int goS, int goL, int maxGoM) {
		super(goS, goL, maxGoM);
		
	}
	
	
	public void noleggia() throws InterruptedException{
		l.lock();
		try {
			Pilota pilotaCurr=(Pilota)Thread.currentThread();
			int etaPilota = pilotaCurr.getEta();
			attesaNoleggio.add(pilotaCurr);	
			while(!possoNoleggiare(etaPilota))
				noleggio.await(); //signal dentro riconsegna
			attesaNoleggio.remove();
			decrementaGok(etaPilota);
			entra.signalAll();
		}finally {
			l.unlock();
		}
	}
	
	public int entraInPista() throws InterruptedException{
		l.lock();
		try {
			Pilota pilotaCurr = (Pilota)Thread.currentThread();
			//int etaPilota = pilotaCurr.getEta();
			attesaPista.add(pilotaCurr);
			while(!possoEntrare())
				entra.await();
			attesaPista.remove();
			pilotiInPista.add(pilotaCurr);
			maxGokartM--;
			int numGiri = scegliGiri();
			return numGiri;
		}finally {
			l.unlock();
		}
	}
	
	public void lasciaPista() {
		//entra.signalAll
		//maXgok++
		l.lock();
		try {
			Pilota pilotaCurr=(Pilota)Thread.currentThread();
			pilotiInPista.remove(pilotaCurr);
			maxGokartM++;
			entra.signalAll();
		}finally {
			l.unlock();
		}
	}
	
	public void riconsegna() {
		//noleggio.signalAll
		//INCRGOKART(ETAPILOTA)
		l.lock();
		try {
			Pilota pilotaCurr = (Pilota)Thread.currentThread();
			pilotiArrivati.remove(pilotaCurr);
			incrementaGokartSL(pilotaCurr.getEta());
			noleggio.signalAll();
		}finally {
			l.unlock();
		}
	}
	
	private boolean possoNoleggiare(int etaPilota) {
		if(Thread.currentThread()==attesaNoleggio.getFirst()) {
			if (etaPilota>=18)
				return gokartL>0;
			else {
				return gokartS>0;
			}
		}
		return false;
	}
	
	private void decrementaGok(int etaPilota) {
		if(etaPilota>=18)
			gokartL--;
		else
			gokartS--;
	}
	
	private boolean possoEntrare() {
		if(Thread.currentThread()==attesaPista.getFirst()) {
			return maxGokartM>0;
		}
		return false;
	}
	
	private int scegliGiri() {
		return r.nextInt((MAX_GIRI-MIN_GIRI+1)+MIN_GIRI);
	}
	
	private void incrementaGokartSL(int eta) {
		if(eta>=18)
			gokartL++;
		else
			gokartS++;
	}
	
	
	public static void main(String[] args) {
		Pista pista = new PistaLC(4,8,6);
		for(int i=0; i<5; i++) {
			pilotiArrivati.add(new Pilota(15,pista));
		}
		for(int i=0; i<15; i++) {
			pilotiArrivati.add(new Pilota(20,pista));
		}
		pista.test();
	}
}
