package appelli.Esercizio4.ufficioPostale;

import java.util.LinkedList;

public class Impiegato extends Thread{
	
	private UfficioPostale uffpost;
	private String tipoOP;
	
	public Impiegato(UfficioPostale uffpost, String tipoOP) {
		this.uffpost=uffpost;
		this.tipoOP=tipoOP;
	}
	
	public void run() {
		try {
			uffpost.prossimoCliente();
			uffpost.eseguiOperazione();
		}catch(InterruptedException e) {}
	}
	
	public String getOperazione() {
		return tipoOP;
	}
}
