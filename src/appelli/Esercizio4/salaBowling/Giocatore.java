package appelli.Esercizio4.salaBowling;

public class Giocatore extends Thread{
	private String nome;
	private String numero;
	private String info = nome+"-"+numero;
	private int tiri=10;
	private boolean scarpe=false;
	private SalaBowling sb;
	
	public Giocatore(String nome, String numero, SalaBowling sb) {
		this.nome=nome;
		this.numero=numero;
		this.sb=sb;
	}
	
	public void run() {
		try {
			sb.fornisciInformazioni();
			sb.gioca(info);
		}catch (InterruptedException e) {
		}
		
	}
	
	public String getInfo() {
		return info;
	}
	
	public void setScarpe(boolean modo) {
		scarpe=modo;
	}
	
	public void setTiro() {
		tiri--;
	}
	
	public int getTiro() {
		return tiri;
	}
	
}
