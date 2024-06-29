package appelli.Esercizio4.ufficioPostale;

public class Cliente extends Thread{
	
	private UfficioPostale uf;
	private String operazione;
	
	public Cliente(UfficioPostale uf, String operazione) {
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
				uf.ritiraTicket(operazione);
				uf.attendiSportello(operazione);
			}
		} catch (Exception e) {	}
	}
}
