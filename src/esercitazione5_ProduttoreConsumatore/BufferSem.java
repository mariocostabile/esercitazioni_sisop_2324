package esercitazione5_ProduttoreConsumatore;

import java.util.concurrent.Semaphore;

public class BufferSem extends Buffer{
	
	private Semaphore ciSonoElementi = new Semaphore(0);
	private Semaphore ciSonoPostiVuoti;
	private Semaphore mutex = new Semaphore(1);
	
	public BufferSem(int dimensione) {
		super(dimensione);
		ciSonoPostiVuoti = new Semaphore(dimensione);
	}
	
	/*
	 * togliendo e aggiungendo permessi ad un semaforo verifico nello stesso momento se
	 * posso fare quella operazione, per questo appena chiamo put e get faccio come prima 
	 * istruzione un acquire sul semaforo che mi tiene conto rispettivamente di posti vuoti e posti pieni
	 */
	
	public void put(int i) throws InterruptedException {
		ciSonoPostiVuoti.acquire(); //tolgo un posto vuoto togliendo un permesso dal semaforo
		mutex.acquire(); //prendo il "lock"  
		buffer[in] = i; //aggiungo elemento i in pos in 
		in = (in + 1) % buffer.length; //aggiorno i considerando un buffer circolare
		System.out.println(this); //this è il buffer
		mutex.release(); // rilascio "lock"
		ciSonoElementi.release(); //aggiungo un elemento aggiungendo un permesso al semaforo
	}
	
	public int get() throws InterruptedException{
		ciSonoElementi.acquire(); //tolgo un elemento 
		mutex.acquire(); //prendo "lock"
		int i = buffer[out]; //mi salvo l'elemento in pos out
		out = (out + 1) % buffer.length; //faccio girare la pos out come per in
		System.out.println(this); //this è il buffer
		mutex.release(); //rilascio il "lock"
		ciSonoPostiVuoti.release(); //aggiungo un posto vuoto disponibile
		return i; //ritonro elemento i
	}
	
	public static void main(String[] args) {
		try{
			int dimensione = 10;
			Buffer buffer = new BufferSem(dimensione);
			int numProduttori = 10;
			int numConsumatori = 10;
			buffer.test(numProduttori, numConsumatori);
		} catch (Exception e) {
			System.err.println("Errore!");
			System.exit(-1);
		}
	}
}
