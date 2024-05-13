package esercitazione2_2;

public class StampanteCurrentThreadTest2 {
	
	public static void main(String[] args) {	
		Thread t = Thread.currentThread();
		System.out.println(t.getId()+" "+t.getName());
		StampanteT s1 = new StampanteT(1, 10);
		s1.run(); //in questo esempio invece viene chiamato il metodo run direttamente quindi non si avvia un nuovo thread ed esegue il codice nel thread corrente vedi differenza con output
		//per avviare un nuovo thread in java si usa start() come nel Test1
	}
}
