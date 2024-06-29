package appelli.Esercizio4.ospedale;

public class Medico extends Thread {
	private ProntoSoccorso ps;
	
	public Medico(ProntoSoccorso ps) {
		this.ps=ps;
	}
	
	public void run() {
		try {
			while(true) {
				ps.iniziaVisita();
				ps.terminaVisita();
			}
		}catch(InterruptedException e) {}
	}
}
