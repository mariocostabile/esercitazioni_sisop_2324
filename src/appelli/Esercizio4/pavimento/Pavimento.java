package appelli.Esercizio4.pavimento;

public abstract class Pavimento {
	
	//sempre protected
	protected int[][] pavimento; //area dati
	protected int numRighe; //servono per il costruttore
	protected int numColonne;
	protected int riga=0;

	
	public Pavimento(int numRige, int numColonne) { //costr
		this.pavimento=new int[numRige][numColonne];
		this.numColonne=numColonne;
		this.numRighe=numRige;
	}
	
	//metodi astratti definiti in PavimentoSem
	public abstract int[] inizia(int T) throws InterruptedException;
	public abstract void finisci(int T, int[] B) throws InterruptedException;
	
	
	public void print() {
		for( int i=0; i<pavimento.length; ++i ) {
			for(int j=0; j<pavimento[0].length; ++j )
				System.out.printf( "%7d", pavimento[i][j] );
			System.out.println();
		}		
	}//print;
	
	
}
