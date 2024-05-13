package esercitazione2_2;

public class Sommatore extends Thread{
	private int da; 
	private int a; 
	private int somma;
	
	public Sommatore(int da ,int a) {
		this.da=da;
		this.a=a;
	}
	
	public void run() {
		somma=0;
		for (int i = da; i <= a; i++) {
			somma+=i;
		}
	}
	
	public int getSomma() throws InterruptedException{
		//this.join(); //prima di ritornare la somma aspetta che finisca 
		return somma;
	}
}
