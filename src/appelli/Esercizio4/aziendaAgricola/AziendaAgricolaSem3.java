package appelli.Esercizio4.aziendaAgricola;

import java.util.concurrent.Semaphore;

public class AziendaAgricolaSem3 extends AziendaAgricola{
	Semaphore cassa = new Semaphore(1,true);
	Semaphore magazzino;
	Semaphore magazziniere = new Semaphore(0);
	public AziendaAgricolaSem3(int sacchetti){
		super(sacchetti);
		magazzino = new Semaphore(1,true);
		
	}
	public void paga(int numSacchi)throws InterruptedException{
		cassa.acquire();
		incasso += numSacchi * 3;
		cassa.release();
	}//paga
	
	public void ritira()throws InterruptedException{
		magazzino.acquire();
		sacchetti--;
		System.out.println("Sacchetti: "+sacchetti);
		if(sacchetti == 0){
			magazziniere.release();
		}
		else{
			magazzino.release();
		}		
	}//ritira
	
	public void carica()throws InterruptedException{
		magazziniere.acquire();
		sacchetti += sacchettiIniziali ;
		System.out.println("Sacchetti: "+sacchetti);
		magazzino.release();
		
	}
	
	public int getIncasso() {
		return incasso;
	}



	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		AziendaAgricolaSem3 azienda = new AziendaAgricolaSem3(200);
		int numClienti  = 100;
		azienda.test(numClienti);
	}
}
