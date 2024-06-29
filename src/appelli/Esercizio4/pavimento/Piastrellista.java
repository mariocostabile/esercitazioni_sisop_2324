package appelli.Esercizio4.pavimento;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Piastrellista extends Thread{
	private Random random = new Random();
	private Pavimento pavimento;
	private final int T;
	
	public Piastrellista(Pavimento pavimento,int T) {
		this.pavimento = pavimento;
		this.T=T;
	}
	
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(1);//tempo preparazione, andava messo in minuti per l'esame
			int []blocco=pavimento.inizia(T);
			System.out.println(blocco.toString());
			while(blocco!=null) {
				if(T==0) {
					TimeUnit.SECONDS.sleep(random.nextInt(3)+4); //Tipo A nextInt ritorna da 0 a 2, quindi da 4 a 6  )
				}else {
					TimeUnit.SECONDS.sleep(random.nextInt(2)+2); //Tipo B
				}
				pavimento.finisci(T,blocco);	
				
				TimeUnit.SECONDS.sleep(1); //min
				
			}
		} catch (Exception e) {
		}
	}

}
