package appelli.Esercizio4.ospedale;

public class Paziente extends Thread{
	 private ProntoSoccorso ps;
	 private String codice;
	 private int contatoreGiallo = 0;
	 private boolean consumato;
	 
	 public Paziente(ProntoSoccorso ps, String codice) {
	  this.ps=ps;
	  this.codice=codice;
	 }
	 
	 public void run() {
	  try {
	   ps.accediPaziente();
	   ps.esciPaziente();
	  } catch (InterruptedException e) {}
	 }
	 
	 public String getCodice() {
	  return codice;
	 }
	 
	 public int getContatoreGiallo() {
	  return contatoreGiallo;
	 }
	 
	 public void incrContatoreGiallo() {
	  contatoreGiallo++;
	 }

	 public void setConsumato() {
		 consumato=true;
	 }
	 
	 public boolean getConsumato() {
		 return consumato;
	 }
}
