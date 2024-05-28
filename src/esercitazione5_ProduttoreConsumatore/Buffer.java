package esercitazione5_ProduttoreConsumatore;

import java.util.Iterator;

public abstract class Buffer  {
	protected int[] buffer;
	protected int in = 0;
	protected int out = 0;
	
	protected Buffer(int dimensione) {
		buffer = new int[dimensione];
	}
	
	public abstract void put(int i) throws InterruptedException;
	
	public abstract int get() throws InterruptedException;
	
	public String toString() {
		String ret = "[";
		for (int elemento : buffer) {
			ret += (buffer[elemento] == 0)? "*":buffer[elemento];
		}
		ret+="]";
		return ret;
	}
	
	public void test(int numProdotti, int numConsumatori) {
		for(int i=0; i<numProdotti; i++) {
			new Thread(new Produttore(this)).start();
		}
		for(int i=0; i<numConsumatori; i++) {
			new Thread(new Consumatore(this)).start();
		}
	}
}
