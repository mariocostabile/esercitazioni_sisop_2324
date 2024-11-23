package appelli.Esercizio4.ospedale;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

public class ProntoSoccorsoLC extends ProntoSoccorso{
 
	 private LinkedList<Paziente> postoVisita= new LinkedList<Paziente>();
	 
	 private boolean inVisita;
	 private boolean visitato;
	 
	 private Random tempoAttesa = new Random();
	 private Lock l = new ReentrantLock();
	 
	 private Condition permettiEntrata = l.newCondition();
	 private Condition permettiVisita = l.newCondition();
	 private Condition permettiUscita = l.newCondition();
	 private Condition permettiTerminazione = l.newCondition();

	 
	 public ProntoSoccorsoLC(int numVerdi, int numGialli, int numRossi) {
	  super(numVerdi, numGialli, numRossi);
	 }
	
	 @Override //medico
	 public void iniziaVisita() throws InterruptedException {
	  l.lock();
	  try {
		  while(inVisita)
			  permettiVisita.await();
		  Paziente p= postoVisita.getFirst();
		  if (p!=null) {
			  inVisita=true;
			  visitato=false;
			  System.out.printf("Il medico ha inziato la visita del paziente %s.", p.getId());
			  System.out.println();
			  visita(p);
			  permettiTerminazione.signal();
		  }
	  } finally {
		  l.unlock();
	  	}
	 }
	
	 @Override //medico
	 public void terminaVisita() throws InterruptedException{
	  l.lock();
	  try {
		while(inVisita)
			permettiTerminazione.await();
		System.out.printf("Il medico ha terminato la visita del paziente %s.", postoVisita.getFirst().getId());
		System.out.println();
		visitato=true;
		permettiUscita.signalAll();
	  }	finally {
		  l.unlock();
	  }
	 }
	
	 //notifica che può iniziare la visita 
	 @Override
	 public void accediPaziente() throws InterruptedException{ 
		 l.lock();
		 try {
			 while(inVisita)
				 permettiEntrata.await();
			 for(Paziente p: pazienti) {
				 if(p.getCodice().equals("giallo") && p.getContatoreGiallo()>=5 && !p.getConsumato()) { //precedenza superiore
					 postoVisita.add(p);
					 p.setConsumato();
					 permettiVisita.signalAll();
					 return;
				 }
			 } 
			 for(Paziente p: pazienti) {
				 if(p.getCodice().equals("rosso") && !p.getConsumato()) {
					 postoVisita.add(p);
					 p.setConsumato();
					 permettiVisita.signalAll();
					 return;
				 }
			 }
			 for(Paziente p: pazienti) {
				 if(p.getCodice().equals("giallo") && !p.getConsumato()) {
					 postoVisita.add(p);
					 p.setConsumato();
					 permettiVisita.signalAll();
					 return;
				 }
			 }
			 for(Paziente p: pazienti) {
				 if(p.getCodice().equals("verde") && !p.getConsumato()) {
					 postoVisita.add(p);
					 p.setConsumato();
					 permettiVisita.signalAll();
					 return;
				 }	
			 }
			 System.out.printf("Il paziente %s è entrato al pronto soccorso.", postoVisita.getFirst().getId() );
		} catch (InterruptedException e) {}
	 }
	
	 @Override
	 public void esciPaziente() throws InterruptedException {
	  l.lock();
	  try {
	   while(!visitato)
		   permettiUscita.await();
	   System.out.printf("Il paziente %s ha terminato la visita.", postoVisita.getFirst().getId());
	   System.out.println();
	   postoVisita.removeFirst();
	   permettiEntrata.signalAll();
	  } finally {
	   l.unlock();
	  }
	 }
	
	 
	 private void visita(Paziente p) {
	  try {
		  aumentaCountGialli();
		  if(p.getCodice().equals("verde")){
		   TimeUnit.MILLISECONDS.sleep(tempoAttesa.nextInt(15-10+1)+10);
		  } 
		  if(p.getCodice().equals("giallo")){
	       TimeUnit.MILLISECONDS.sleep(tempoAttesa.nextInt(20-15+1)+15);
		  } 
		  if(p.getCodice().equals("rosso")){
	       TimeUnit.MILLISECONDS.sleep(tempoAttesa.nextInt(40-20+1)+20);
		  }
		  inVisita=false;
	  }catch (InterruptedException e) {} 
	 }
	 
	 private void aumentaCountGialli() {
	  for(Paziente p: pazienti) {
		  if(p.getCodice().equals("giallo"))
			  p.incrContatoreGiallo(); //++ ad ogni giallo 
	  }
	 }
	 
	 public static void main(String[] args) throws InterruptedException {
		  ProntoSoccorsoLC psLC = new ProntoSoccorsoLC(10, 10, 10);
		  Medico m = new Medico(psLC);
		  psLC.test();
		  m.setDaemon(true);
		  m.start();
	 }
	 
	 /*
	  * 	 //notifica che può iniziare la visita 
	 @Override
	 public void accediPaziente() throws InterruptedException{ 
		 l.lock();
		 try {
			 while(occupato())
				 permettiEntrata.await();
			 Paziente p = ((Paziente)(Thread.currentThread()));
			 if( p.getCodice().equals("verde") && (rossi.isEmpty() && gialli.isEmpty()) ) {
				 permettiVisita.signalAll();
				 paziente = p;
			 }
			 if( p.getCodice().equals("giallo") && (rossi.isEmpty() || p.getContatoreGiallo()>=5) ) {
				 permettiVisita.signalAll();
				 paziente = p;
			 }
			 if ( p.getCodice().equals("rosso") ) {
				 permettiVisita.signalAll();
				 paziente = p;
			 }
			 System.out.printf("Il paziente %s con codice %s ha fatto l'accesso al pronto soccorso", Thread.currentThread(), p.getCodice());
			 System.out.println();
		 } finally {
			 l.unlock();
		 }
	 }
	  */
	 
}