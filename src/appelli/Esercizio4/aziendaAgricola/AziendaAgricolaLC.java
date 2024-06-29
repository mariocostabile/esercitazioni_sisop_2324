package appelli.Esercizio4.aziendaAgricola;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AziendaAgricolaLC extends AziendaAgricola{
	private Lock l = new ReentrantLock();
	private Condition paga = l.newCondition();
	private Condition ritira = l.newCondition();
	private Condition carica = l.newCondition();
	private LinkedList<Thread> cassa = new LinkedList<Thread>();
	private LinkedList<Thread> magazzino = new LinkedList<Thread>();
	
	public AziendaAgricolaLC(int sacchetti){
		super(sacchetti);
	}
	
	public void paga(int numSacchi) throws InterruptedException{
		l.lock();
		try{
			cassa.add(Thread.currentThread());
			while(!possoPagare()){
				paga.await();
			}
			cassa.remove();
			incasso += numSacchi * 3;
			paga.signalAll();
		}finally{
			l.unlock();
		}
	}//paga
	public boolean possoPagare(){
		return Thread.currentThread() == cassa.getFirst();
	}
	
	public void ritira()throws InterruptedException{
		l.lock();
		try{
			magazzino.add(Thread.currentThread());
			while(!possoPrelevare()){
				ritira.await();
			}
			magazzino.remove(); //rimuove primo
			sacchetti--;
			System.out.println("Sacchetti: "+sacchetti);
			if(sacchetti == 0)
				carica.signal();
			ritira.signalAll();
		}finally{
			l.unlock();
		}
	}//ritira
	
	public boolean possoPrelevare(){
		return Thread.currentThread() == magazzino.getFirst() && sacchetti > 0;
	}
	
	public void carica()throws InterruptedException{
		l.lock();
		try{
			while(!possoCaricare()){
				carica.await();
			}
			sacchetti+= sacchettiIniziali;
			TimeUnit.SECONDS.sleep(5);
			System.out.println("Sacchetti: "+sacchetti);
			ritira.signalAll();
		}finally{
			l.unlock();
		}
		
	}
	
	private boolean possoCaricare() {
		return sacchetti == 0;
	}
	public static void main(String[] args) throws InterruptedException {
		AziendaAgricola azienda = new AziendaAgricolaLC(200);
		int numClienti  = 100;
		azienda.test(numClienti);
	}
}
