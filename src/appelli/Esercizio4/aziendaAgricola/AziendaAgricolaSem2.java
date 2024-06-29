package appelli.Esercizio4.aziendaAgricola;

import java.util.concurrent.Semaphore;

public class AziendaAgricolaSem2 extends AziendaAgricola{
	Semaphore cassa = new Semaphore(1,true);
	Semaphore magazzino;
	Semaphore mutex = new Semaphore(1);
	Semaphore magazziniere = new Semaphore(0);
	
	public AziendaAgricolaSem2(int sacchetti){
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
		magazziniere.release();
		System.out.println("Sacchetti: "+sacchetti);
		mutex.release();		
	}//ritira
	
	public void carica()throws InterruptedException{
		magazziniere.acquire(sacchettiIniziali);
		mutex.acquire();
		sacchetti += sacchettiIniziali ;
		System.out.println("Sacchetti: "+sacchetti);
		mutex.release();
		magazzino.release(200);
		
	}
	
	public int getIncasso() {
		return incasso;
	}



	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		AziendaAgricolaSem2 azienda = new AziendaAgricolaSem2(200);
		int numClienti  = 100;
		azienda.test(numClienti);
	}
}
