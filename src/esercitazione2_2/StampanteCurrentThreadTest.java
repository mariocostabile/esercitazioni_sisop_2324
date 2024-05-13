package esercitazione2_2;

public class StampanteCurrentThreadTest {
	
	public static void main(String[] args) {
		Thread t = Thread.currentThread(); //ritorna l'oggetto thread corrente (in questo caso main)
		System.out.println(t.getId()+" "+t.getName());
		StampanteT t1 = new StampanteT(1, 10);
		t1.start();//chiamando start si esegue il run di StampanteT quindi vengono creati 2 thread, il main e quello di Stampante vedi output
	}
	
}
