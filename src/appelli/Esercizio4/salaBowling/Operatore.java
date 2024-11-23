package appelli.Esercizio4.salaBowling;

import java.util.LinkedList;

public class Operatore extends Thread{
	private SalaBowling sb;
	
	public Operatore(SalaBowling sb) {
		this.sb=sb;
	}
	
	public void run() {
		try {
			sb.preparaPartita();
			sb.deposita();
		} catch (InterruptedException e) {
		}
	}
}
