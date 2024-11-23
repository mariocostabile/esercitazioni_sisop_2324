package appelli.Esercizio4.ufficioPostale;

public class Cliente extends Thread{
	
	private UfficioPostale uffpost;
	private String tipoOP;
	private boolean ritirato=false;
	
	public Cliente(UfficioPostale uffpost, String tipoOP) {
		this.uffpost=uffpost;
		this.tipoOP=tipoOP;
	}
	
	public void run() {
		try {
			boolean flag = uffpost.ritiraTicket(tipoOP);
			if(flag)
				uffpost.attendiSportello(tipoOP);
		}catch(InterruptedException e) {}
	}
	
	public String getOperazione() {
		return tipoOP;
	}
	
	public void setRitirato() {
		ritirato=true;
	}
	
	public boolean getRitirato() {
		return ritirato;
	}
}
