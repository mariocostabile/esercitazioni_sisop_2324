package appelli.Esercizio4.aziendaAgricola;

import java.util.concurrent.TimeUnit;

public class Magazziniere extends Thread{
	private AziendaAgricola aziendaAgricola;
	
	public Magazziniere(AziendaAgricola aziendaAgricola){
		this.aziendaAgricola = aziendaAgricola;
	}
	
	@Override
	public void run(){
		while(true){
			try{
				aziendaAgricola.carica();
				TimeUnit.SECONDS.sleep(10);
				System.out.println(this);
			}catch(InterruptedException e){
			}			
		}
	}
	
	public String toString(){
		return "Magazziniere ha caricato "+aziendaAgricola.sacchettiIniziali+ " sacchi nel magazzino";
	}
}
