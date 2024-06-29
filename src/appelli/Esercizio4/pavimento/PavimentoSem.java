package appelli.Esercizio4.pavimento;

import java.util.concurrent.Semaphore;

public class PavimentoSem extends Pavimento{
	
	private Semaphore mutex = new Semaphore(1); //semaforo condiviso tra 2 piastrellisti per la mutua esclusione 
	private Semaphore [][] pavimento_A;
	private Semaphore [][] pavimento_B;
	private Semaphore lavoroA ;
	private Semaphore lavoroB;
	private int cnt;
	
	public PavimentoSem(int numRige,int numColonne) {
		super(numRige,numColonne);
		
		this.lavoroA=new Semaphore(numColonne);
		this.lavoroB=new Semaphore(0);
		this.cnt=0;
		this.pavimento_A=new Semaphore[numRige][numColonne]; //colla
		this.pavimento_B = new Semaphore[numRige][numColonne]; //piastrelle
		for(int i=0;i<numRige;++i) {
			for(int j=0;j<numColonne;++j) {
				if(i==0) { //se siamo sulla prima colonna
					pavimento_A[i][j] = new Semaphore(1); //non c'è predecessore (no release)
					pavimento_B[i][j] = new Semaphore(0);
				}else {
					pavimento_B[i][j] = new Semaphore(0); //devono aspettare i permessi dei precedenti
					pavimento_A[i][j] = new Semaphore(0);
				}
			}
		}
	}

	@Override
	public int[] inizia(int T) throws InterruptedException {
		int [] indici = new int [2];
		if(T==1) { //se siamo nel thread di tipo B
			lavoroB.acquire();
			mutex.acquire();
			for(int i=0;i<numRighe;++i) {
				for(int j=0;j<numColonne;++j) {
					if(pavimento[i][j]==1) {//ce la colla
						pavimento[i][j]=2; //mette piastrella
						indici[0]=i; //
						indici[1]=j;
						System.out.println("Vuoi mettere la piastrella");
						break;
					}
				}
				
			}
			mutex.release();
		}else{ //se siamo nel thread di tipo A
			lavoroA.acquire();
			mutex.acquire();
			for(int j=0;j<numColonne;++j) {
				if(pavimento[riga][j]==0) {//non ce colla
					//pavimento_A[riga][j].release();
					pavimento[riga][j]=1;
					indici[0]=riga; //
					indici[1]=j;
					System.out.println("Voui mettere la colla");
					cnt++;
					if(cnt==numColonne) { //azzera arrivato all'ultima colonna, scorre la riga
						riga++;
						cnt=0;
						lavoroA.release(numColonne); //quante righe sono rimaste 
					}
					
					break;
				}
			}
			mutex.release();
		}
		return indici;
	}

	@Override
	public void finisci(int T, int[] B) throws InterruptedException {
		int i= B[0];
		int j= B[1]; 
		if(T==1) {
			pavimento_B[i][j].acquire();  //
			mutex.acquire();
			pavimento[i][j]=2;
			System.out.println("Metto la piastrella");
			mutex.release();
			pavimento_A[i+1][j].release(); //dai il permesso a mettere la colla 
			
		}else {
			pavimento_A[i][j].acquire();
			mutex.acquire();
			pavimento[i][j]=1;
			System.out.println("Metto la colla");
			lavoroB.release();
			mutex.release();
			pavimento_B[i][j].release();
		}
		
	}

	public static void main(String[] args) throws InterruptedException {
		Thread[] piastrellistiA=new Thread[8];
		Thread[] piastrellistiB = new Thread[8];
		Pavimento p = new PavimentoSem(2,4);
		p.print();
		//test che andava in astratta
		for(int i=0;i<8;++i) {
			piastrellistiA[i]=new Piastrellista(p, 0);
			piastrellistiB[i]=new Piastrellista(p, 1);
			piastrellistiA[i].start();
			piastrellistiB[i].start();
			
		}
		
		p.print();
	}
	
	
	 
}
