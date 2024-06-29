package appelli.Esercizio4.ospedale;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

public class ProntoSoccorsoLC extends ProntoSoccorso{
	
	private LinkedList<Thread> postoVisita= new LinkedList<Thread>();
	//la lista dei pazienti arriva da sopra 
	
	private Random tempoAttesa = new Random();
	private Lock l = new ReentrantLock();
	private Condition permettiVisita = l.newCondition();
	//private Condition permettiTerminazione = l.newCondition();
	
	public ProntoSoccorsoLC(Medico medico) {
		super(medico);
	}

	@Override
	public void iniziaVisita() throws InterruptedException {
		l.lock();
		try {
			while(!libero()) {
				permettiVisita.await(); 
			}//la signal fa partire da qua 
			String codice = ((Paziente) postoVisita.getFirst()).getCodice();
			visita(codice);
		} finally {
			l.unlock();
		}
	}

	@Override
	public void terminaVisita() throws InterruptedException{
		permettiVisita.signalAll();
	}

	@Override
	public void accediPaziente() throws InterruptedException{ //mette il paziente in postovisita
		l.lock();
		try {
			while(!libero())
				permettiVisita.await();
			Paziente p = ((Paziente)(Thread.currentThread()));
			if( p==giallo.getFirst() && p.getContatoreGiallo()>=5 ) {
				postoVisita.add(p);
				giallo.removeFirst();
			}
			if( p==rosso.getFirst() ) {
				postoVisita.add(p);
				rosso.removeFirst();
			}
			if( p==giallo.getFirst() ) {
				postoVisita.add(p);
				giallo.removeFirst();
			}
			if( p==verde.getFirst() ) {
				postoVisita.add(p);
				verde.removeFirst();
			}
			if(giallo.size()>0) {
				Paziente g = ((Paziente)giallo.getFirst());
				g.setContatoreGiallo();
			}
		} finally {
			l.unlock();
		}
		//permetti.wait
		//dopo che sblocco la condition
		//variabile paziente
		//if(gialli and count gialli >= 5 ) paziente=giallo (add postovisita) 
		//if(rosso) paziente = rosso
		//if giallo paziente = giallo
		//if(verde) paziente = verde
		//permettiVisita.add(paziente)
		//if ( fila gialli length>0 ) 
		//allora filagialli.getfirst.setcontatore()
	}

	@Override
	public void esciPaziente() {
		postoVisita.remove();
		permettiVisita.signalAll();
		
	}
	
	private boolean libero() {
		return postoVisita.isEmpty();
	}

	private void visita(String codice) {
		try {
			if(codice.equals(verde)) TimeUnit.SECONDS.sleep(tempoAttesa.nextInt(15-10+1)+10);
			if(codice.equals(giallo)) TimeUnit.SECONDS.sleep(tempoAttesa.nextInt(20-15+1)+15);
			if(codice.equals(rosso)) TimeUnit.SECONDS.sleep(tempoAttesa.nextInt(40-20+1)+20);
		} catch (InterruptedException e) {}	
	}
	
}
