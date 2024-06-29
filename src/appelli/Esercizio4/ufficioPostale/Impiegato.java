package appelli.Esercizio4.ufficioPostale;

import java.util.LinkedList;

public class Impiegato extends Thread{
	
	private UfficioPostale uf;
	private String operazione;
	
	public Impiegato(UfficioPostale uf, String operazione) {
		this.uf=uf;
		this.operazione=operazione;
	}
	
	public String getOperazione() {
		return operazione;
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				uf.prossimoCliente();
				uf.eseguiOperazione();
			}
		} catch (Exception e) {	}
	}
}
