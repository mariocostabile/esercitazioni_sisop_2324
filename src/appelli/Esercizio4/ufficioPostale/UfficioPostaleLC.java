package appelli.Esercizio4.ufficioPostale;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UfficioPostaleLC extends UfficioPostale {
	
	private LinkedList<Cliente> attesaTicket = new LinkedList<>();
	private LinkedList<Cliente> attesaFila = new LinkedList<>();
	private Lock l = new ReentrantLock();
	private Condition possoRitirare = l.newCondition(); 
	private Condition possoInFila = l.newCondition();
	private Condition possoProssimoCliente = l.newCondition();
	
	public UfficioPostaleLC(int numeroClienti) {
		super(numeroClienti);
	}

	@Override
	public boolean ritiraTicket(String operazione) throws InterruptedException {
		l.lock();
		try {
			Cliente curr= (Cliente)Thread.currentThread();
			attesaTicket.add(curr);
			while(!possoTicket(operazione,curr))
				possoRitirare.await();
			if(curr.getRitirato()) {
				attesaTicket.remove(curr);
				attesaFila.add(curr);
				decrementaTicket(operazione);
				possoRitirare.signalAll();
				possoInFila.signalAll();
				return true;
			}else {
				attesaTicket.remove(curr);
				return false;
			}
		}finally {
			l.unlock();
		}
	}

	@Override
	public void attendiSportello(String operazione) throws InterruptedException {
		l.lock();
		try {
			Cliente curr = (Cliente)Thread.currentThread();
			while(!possoAndareInFila(curr))
				possoInFila.await();
			attesaFila.remove(curr);
			vaiInFila(operazione, curr);
			possoProssimoCliente.signalAll();
		}finally {
			l.unlock();
		}
		
	}

	@Override
	public void prossimoCliente() throws InterruptedException {
		l.lock();
		try {
//			Impiegato curr = (Impiegato)Thread.currentThread();
//			String op = curr.getOperazione();
//			while(!possoServire(curr,op))
//				possoProssimoCliente.await();
//			rimuoviDallaFila(op);
//			possoEseguire.signal
		} finally {
			l.unlock();
		}
		
	}

	@Override
	public void eseguiOperazione() throws InterruptedException {
		// TODO Auto-generated method stub
		
	}
	
	private boolean possoTicket(String op, Cliente c) {
		if(c==attesaTicket.getFirst()) {
			if(op.equals("A") && ticketA>0) {
				c.setRitirato();
				return true;
			}	
			if(op.equals("B")&& ticketB>0) {
				c.setRitirato();
				return true;
			}
			if(op.equals("C") && ticketC>0) {
				c.setRitirato();
				return true;
			}
		}
		return false;
	}
	
	private void decrementaTicket(String op){
		if(op.equals("A"))
			ticketA--;
		if(op.equals("B"))
			ticketB--;
		if(op.equals("C"))
			ticketC--;
	}
	
	private boolean possoAndareInFila(Cliente c) {
		if(c==attesaFila.getFirst() && c.getRitirato()) {
			return true;
		}
		return false;
	}
	
	private void vaiInFila(String op, Cliente c) {
		if(op.equals("A"))
			filaA.add(c);
		if(op.equals("B"))
			filaB.add(c);
		if(op.equals("C"))
			filaC.add(c);
	}
	
	private boolean possoServire(Impiegato imp, String op) {
		if(op.equals("A") && !filaA.isEmpty())
			return true;
		if(op.equals("B") && !filaB.isEmpty())
			return true;
		if(op.equals("C") && !filaC.isEmpty())
			return true;
		return false;
	}
	
}
