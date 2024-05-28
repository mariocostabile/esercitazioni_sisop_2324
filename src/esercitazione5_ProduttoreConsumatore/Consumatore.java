package esercitazione5_ProduttoreConsumatore;

import java.util.concurrent.TimeUnit;

public class Consumatore implements Runnable {

	private Buffer buffer;
	
	public Consumatore(Buffer b) { buffer = b; }
	
	@Override
	public void run() {
		try {
			while(true) {
				int i = buffer.get();
				consuma(i);
			}
		} catch (InterruptedException e) {}	
	}
	
	private void consuma(int i) throws InterruptedException{
		TimeUnit.SECONDS.sleep(i);
	}
}
