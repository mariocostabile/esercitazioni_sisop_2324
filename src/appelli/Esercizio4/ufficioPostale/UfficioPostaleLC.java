package appelli.Esercizio4.ufficioPostale;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UfficioPostaleLC extends UfficioPostale {
	
	private int countA, countB, countC; //servono per il metodo possoInserire per la condition
	private boolean liberoA, liberoB, liberoC, liberoNigro; //ultima è un tributo
	
	private Lock l = new ReentrantLock();
	private Condition condition = l.newCondition();
	
	public UfficioPostaleLC(LinkedList<Cliente> codaClientiA, LinkedList<Cliente> codaClientiB,
			LinkedList<Cliente> codaClientiC, LinkedList<Cliente> codaTicket) {
		super(codaClientiA, codaClientiB, codaClientiC, codaTicket);
	}

	@Override
	public boolean ritiraTicket(String operazione){
		l.lock();
		try {
			if(!possoInserire()) {
				return false;
			}
//			if(operazione.equals("A") && c.getOperazione().equals("A")) {
//				codaClientiA.add(c);
//				countA++;
//			}
			if(operazione.equals("A")) countA++;
			if(operazione.equals("B")) countB++;
			if(operazione.equals("C")) countC++;
			return true;
		} finally {
			l.unlock();
		}
	}

	@Override
	public void attendiSportello(String operazione) throws InterruptedException {
		l.lock();
		Cliente c = codaTicket.removeFirst();
		if(operazione.equals("A") && c.getOperazione().equals("A")) {
			codaClientiA.add(c);
		}
		if(operazione.equals("B") && c.getOperazione().equals("B")) {
			codaClientiB.add(c);
		}
		if(operazione.equals("C") && c.getOperazione().equals("C")) {
			codaClientiC.add(c);
		}	
		try {
			while(!libero()) {
				condition.await();
			}
			
		} finally {
			l.unlock();
		}
	}

	@Override
	public void prossimoCliente() throws InterruptedException {
		l.lock();
		try {
			condition.signalAll();
		}finally {
			l.unlock();
		}
	}

	@Override
	public void eseguiOperazione() {
		 try {
            String operazione = ((Impiegato) Thread.currentThread()).getOperazione();
            LinkedList<Cliente> coda;
            switch (operazione) {
                case "A":
                    coda = codaClientiA;
                    break;
                case "B":
                    coda = codaClientiB;
                    break;
                case "C":
                    coda = codaClientiC;
                    break;
                default:
                    throw new IllegalArgumentException("Operazione non valida");
            }
            Cliente cliente = coda.poll();
            if (cliente != null) {
                try {
                    switch (operazione) {
                        case "A":
                            Thread.sleep(300);
                            break;
                        case "B":
                            Thread.sleep(500);
                            break;
                        case "C":
                            Thread.sleep(700);
                            break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                condition.signalAll();
            }
        } finally {
            l.unlock();
        }
   }

	
	private boolean possoInserire() {
		if(countA>=50 || countB>=50 || countC>=50) return false;
		return true;
	}
	
	private boolean libero() {
		return liberoA || liberoB || liberoC;
	}
	
	 public static void main(String[] args) {
			LinkedList<Cliente> codaClientiA= new LinkedList<>();
			LinkedList<Cliente> codaClientiB= new LinkedList<>();
			LinkedList<Cliente> codaClientiC= new LinkedList<>();
			LinkedList<Cliente> codaTicket= new LinkedList<>(); //in coda per il ticket
			
	        UfficioPostaleLC ufficioPostale = new UfficioPostaleLC(codaClientiA, codaClientiB, codaClientiC, codaTicket);

	        Impiegato impiegatoA = new Impiegato(ufficioPostale, "A");
	        Impiegato impiegatoB = new Impiegato(ufficioPostale, "B");
	        Impiegato impiegatoC = new Impiegato(ufficioPostale, "C");

	        impiegatoA.start();
	        impiegatoB.start();
	        impiegatoC.start();
	        
	        for (int i = 0; i < 200; i++) {
	            String[] operazioni = {"A", "B", "C"};
	            String operazione = operazioni[i % 3];
	            Cliente cliente = new Cliente(ufficioPostale, operazione);
	            cliente.start();
	            try {
	                Thread.sleep(100); // Simula un ritardo tra l'arrivo dei clienti
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	 }
}
