package esercitazione3;

public class ContoCorrenteTest {
	public static void main(String[] args) throws InterruptedException{
		int depositoIniziale = 100000;
		//ContoCorrente cc = new ContoCorrenteNTS(depositoIniziale); //test non thread safe
		//ContoCorrente cc = new ContoCorrenteAI(depositoIniziale);  //test atomic integer
		ContoCorrente cc = new ContoCorrenteSem(depositoIniziale);   //test semafori
		int numCorrentisti = 200;
		int importo = 100;
		int numOperazioni = 5000;
		testContoCorrente(cc, numCorrentisti, importo, numOperazioni);
		
		if(cc.getDeposito() == depositoIniziale) {
			System.out.format("Corretto! Il deposito finale è %s%n", cc.getDeposito());
		}else {
			System.out.format("Errore! Il deposito iniziale era %s, il deposito finale era di %s%n", depositoIniziale, cc.getDeposito());
		}
	}
	
	private static void testContoCorrente(ContoCorrente cc, int numCorrentisti, 
			int importo, int numOperazioni) throws InterruptedException{
		
		Correntista correntisti[] = new Correntista[numCorrentisti]; //array di correntisti
		for( int i=0; i < numCorrentisti; i++ ) {
			correntisti[i] = new Correntista(cc, importo, numOperazioni); //per ogni cella dell'array faccio un istanza di correntista 
		}
		
		Thread threadCorrentisti[] = new Thread[numCorrentisti]; //un array di oggetti thread da riempire con correntisti 
		for(int i=0; i<numCorrentisti; i++) {
			threadCorrentisti[i] = new Thread(correntisti[i]);
			threadCorrentisti[i].start();
		}
		for(int i=0; i<numCorrentisti; i++) {
			threadCorrentisti[i].join(); //metto la join a tutti i thread (poi se la vede il main dopo che ho chiamato le start)
		}
	}
	
}
