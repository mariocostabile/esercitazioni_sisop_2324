package appello.ufficioPostale;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.util.LinkedList;

public class UfficioPostaleLC extends UfficioPostale {
    private final int MAX_TICKETS = 50;
    private int ticketsA;
    private int ticketsB;
    private int ticketsC;
    private LinkedList<Cliente> codaA;
    private LinkedList<Cliente> codaB;
    private LinkedList<Cliente> codaC;
    private Lock lock;
    private Condition conditionA;
    private Condition conditionB;
    private Condition conditionC;

    public UfficioPostaleLC() {
        ticketsA = MAX_TICKETS;
        ticketsB = MAX_TICKETS;
        ticketsC = MAX_TICKETS;
        codaA = new LinkedList<>();
        codaB = new LinkedList<>();
        codaC = new LinkedList<>();
        lock = new ReentrantLock();
        conditionA = lock.newCondition();
        conditionB = lock.newCondition();
        conditionC = lock.newCondition();
    }

    @Override
    public boolean ritiraTicket(String operazione) {
        lock.lock();
        try {
            switch (operazione) {
                case "A":
                    if (ticketsA > 0) {
                        ticketsA--;
                        return true;
                    }
                    break;
                case "B":
                    if (ticketsB > 0) {
                        ticketsB--;
                        return true;
                    }
                    break;
                case "C":
                    if (ticketsC > 0) {
                        ticketsC--;
                        return true;
                    }
                    break;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void attendiSportello(String operazione) {
        lock.lock();
        try {
            LinkedList<Cliente> coda;
            Condition condition;
            switch (operazione) {
                case "A":
                    coda = codaA;
                    condition = conditionA;
                    break;
                case "B":
                    coda = codaB;
                    condition = conditionB;
                    break;
                case "C":
                    coda = codaC;
                    condition = conditionC;
                    break;
                default:
                    throw new IllegalArgumentException("Operazione non valida");
            }
            coda.add((Cliente) Thread.currentThread());
            while (coda.peek() != Thread.currentThread()) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void prossimoCliente() {
        lock.lock();
        try {
            Condition condition = null;
            switch (((Impiegato) Thread.currentThread()).getTipoOperazione()) {
                case "A":
                    condition = conditionA;
                    break;
                case "B":
                    condition = conditionB;
                    break;
                case "C":
                    condition = conditionC;
                    break;
            }
            if (condition != null) {
                condition.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void eseguiOperazione() {
        lock.lock();
        try {
            String operazione = ((Impiegato) Thread.currentThread()).getTipoOperazione();
            LinkedList<Cliente> coda;
            switch (operazione) {
                case "A":
                    coda = codaA;
                    break;
                case "B":
                    coda = codaB;
                    break;
                case "C":
                    coda = codaC;
                    break;
                default:
                    throw new IllegalArgumentException("Operazione non valida");
            }
            Cliente cliente = coda.poll();
            if (cliente != null) {
                try {
                    switch (operazione) {
                        case "A":
                            Thread.sleep(300); // Simula il tempo di esecuzione per operazione A
                            break;
                        case "B":
                            Thread.sleep(500); // Simula il tempo di esecuzione per operazione B
                            break;
                        case "C":
                            Thread.sleep(700); // Simula il tempo di esecuzione per operazione C
                            break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                switch (operazione) {
                    case "A":
                        conditionA.signalAll();
                        break;
                    case "B":
                        conditionB.signalAll();
                        break;
                    case "C":
                        conditionC.signalAll();
                        break;
                }
            }
        } finally {
            lock.unlock();
        }
    }

    // Metodo toString per visualizzare lo stato corrente delle code e dei ticket
    @Override
    public String toString() {
        lock.lock();
        try {
            return String.format(
                "Stato Ufficio Postale:\n" +
                "Tickets A: %d, Tickets B: %d, Tickets C: %d\n" +
                "Coda A: %d clienti, Coda B: %d clienti, Coda C: %d clienti\n",
                ticketsA, ticketsB, ticketsC,
                codaA.size(), codaB.size(), codaC.size()
            );
        } finally {
            lock.unlock();
        }
    }
}
