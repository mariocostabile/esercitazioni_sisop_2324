package appelli.Esercizio4.salaBowling;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SalaBowlingSem extends SalaBowling {
	
	LinkedList<String> listaInfo = new LinkedList<String>();
	
	private Random r = new Random();
	
	private Semaphore mutex = new Semaphore(1); 
	private Semaphore fornisciInfo = new Semaphore(1);  //gestisce la fifo della consegna delle info all'operatore
	private Semaphore possoGiocare = new Semaphore(0);
	private Semaphore consegnaScarpe = new Semaphore(1); //una consegna per ogni giocatore tra un'acquire e una release
	//deposita fa l'acquire di inizio partita che viene rilasciata solo dopo che tutte le scarpe sono state consegnate
	private Semaphore possoDepositare = new Semaphore(0);
	
	
	public SalaBowlingSem(int numGiocatori, String[] listaNomi) {
		super(numGiocatori, listaNomi);
	}
	
	public String fornisciInformazioni() throws InterruptedException {
		fornisciInfo.acquire();
		mutex.acquire();
		String info= ((Giocatore)Thread.currentThread()).getInfo();
		listaInfo.add(info);
		//mettere controllo con print per vedere chi è current thread
		mutex.release();
		fornisciInfo.release();
		return info;
	}
	
	public void preparaPartita() throws InterruptedException {
		mutex.acquire();
		if((listaInfo.size()==numGiocatori)) {
			TimeUnit.MILLISECONDS.sleep(r.nextInt(10-5+1)+5);
			for(Giocatore g : listaGiocatori) {
				g.setScarpe(true); //fare le scarpe a tutti
			}
		}
		possoGiocare.release(numGiocatori); //do il permesso di giocare
		mutex.release();
	}
	
	public void gioca(String info) throws InterruptedException {
		possoGiocare.acquire();
		mutex.acquire();
		String giocatore = listaInfo.getFirst();
		if(info.equals(giocatore)) {
			Giocatore g = ((Giocatore) Thread.currentThread());
			g.setTiro(); //--
			if(g.getTiro()==0) { 
				consegnaScarpe.acquire();
				g.setScarpe(false); //farsi fare le scarpe da tutti
				System.out.println(g.getInfo()+" ha depositato le scarpe."); //stampa Cristian-43
				consegnaScarpe.release(); 
				listaInfo.remove(giocatore);
			}
			else {
				listaInfo.remove(giocatore);
				listaInfo.addLast(giocatore);
			}
		}
		mutex.release();
		possoGiocare.release();
		if(listaInfo.isEmpty())
			possoDepositare.release();
	}
	
	public void deposita() throws InterruptedException{
		possoDepositare.acquire();
		mutex.acquire();
		System.out.println("Tutti hanno consegnato le scarpe.");
		mutex.release();
	}

			
	public static void main(String[] args) {
		String[] listaNomi = {"Giovanni", "Mario", "Simone", "Daddy"};
		SalaBowlingSem sb = new SalaBowlingSem(4,listaNomi);
		Operatore staff = new Operatore(sb);
		sb.test();
		staff.start();
	}

}
