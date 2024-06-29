package appello.ufficioPostale;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public abstract class UfficioPostale {
    // Metodi astratti che saranno implementati nella classe concreta
    abstract boolean ritiraTicket(String operazione);
    abstract void attendiSportello(String operazione);
    abstract void prossimoCliente();
    abstract void eseguiOperazione();
}

