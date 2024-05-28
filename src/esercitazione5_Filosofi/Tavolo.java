package esercitazione5_Filosofi;

public abstract class Tavolo {

	public final static int NUM_FILOSOFI = 5;
	
	public abstract void prendBacchette( int i ) throws InterruptedException;
	
	public abstract void rilasciaBacchette( int i );
	
	public void test() {
		for(int i=0; i<NUM_FILOSOFI; i++) {
			new Filosofo(this, i).start();
		}
	}
}
