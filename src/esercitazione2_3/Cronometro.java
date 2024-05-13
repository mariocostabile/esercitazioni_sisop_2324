package esercitazione2_3;

public class Cronometro extends Thread{
	
	public void run() {
		int numSecondi=1;
		while( !isInterrupted() ) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				break;
			}
			System.out.println("\n" + numSecondi);
			numSecondi++;
		}
	}
}
