package appelli.Esercizio4.aziendaAgricola;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class AziendaAgricolaSem extends AziendaAgricola{
	Semaphore cassa = new Semaphore(1,true);
	Semaphore magazzino;
	Semaphore mutex = new Semaphore(1);
	Semaphore magazziniere = new Semaphore(0);
	
	public AziendaAgricolaSem(int sacchetti){
		super(sacchetti);
		magazzino = new Semaphore(sacchetti,true);		
	}
	
	public void paga(int numSacchi)throws InterruptedException{
		cassa.acquire();
		incasso += numSacchi * 3;
		cassa.release();
	}//paga
	
	public void ritira()throws InterruptedException{
		magazzino.acquire();
		mutex.acquire();
		sacchetti--;
		if(sacchetti == 0)
			magazziniere.release();
		System.out.println("Sacchetti: "+sacchetti);
		mutex.release();
		
	}//ritira
	
	public void carica()throws InterruptedException{
		magazziniere.acquire();
		mutex.acquire();
		sacchetti += sacchettiIniziali;
		System.out.println("Sacchetti: "+sacchetti);
		mutex.release();
		TimeUnit.SECONDS.sleep(10);
		magazzino.release(200);		
	}
	
	public int getIncasso() {
		return incasso;
	}



	public static void main(String[] args) throws InterruptedException {
		AziendaAgricola azienda = new AziendaAgricolaSem(200);
		int numClienti  = 100;
		azienda.test(numClienti);
	}
}
