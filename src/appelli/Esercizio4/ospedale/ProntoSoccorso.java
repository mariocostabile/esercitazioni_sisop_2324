package appelli.Esercizio4.ospedale;

import java.util.LinkedList;

import java.util.ArrayList;
import java.util.List;

public abstract class ProntoSoccorso {
 
//  protected LinkedList<Thread> verdi= new LinkedList<>();
//  protected LinkedList<Thread> gialli= new LinkedList<>();
//  protected LinkedList<Thread> rossi= new LinkedList<>();
  protected LinkedList<Paziente> pazienti = new LinkedList<>();
 
  private int numVerdi;
  private int numGialli;
  private int numRossi;
 
 
  public ProntoSoccorso(int numVerdi, int numGialli, int numRossi) {
      this.numVerdi=numVerdi;
      this.numGialli=numGialli;
      this.numRossi=numRossi;
  }
 
  public abstract void iniziaVisita()throws InterruptedException;
  public abstract void terminaVisita()throws InterruptedException;
  public abstract void accediPaziente()throws InterruptedException;
  public abstract void esciPaziente()throws InterruptedException;
 
  public void test() throws InterruptedException {
      //riempimento
      for(int i=0; i<numVerdi;i++) {
          Paziente p = new Paziente(this,"verde");
          pazienti.add(p);
      }
      for(int i=0; i<numGialli;i++) {
          Paziente p = new Paziente(this,"giallo");
          pazienti.add(p);
      }
      for(int i=0; i<numRossi;i++) {
          Paziente p = new Paziente(this,"rosso");
          pazienti.add(p);
      }

      //start una volta completato l'array pazienti
      for(Thread paziente : pazienti) {
          paziente.start();
      }
      
     
  }
 
}
