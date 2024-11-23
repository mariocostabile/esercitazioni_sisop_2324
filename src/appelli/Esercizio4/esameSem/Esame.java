package appelli.Esercizio4.esameSem;

import java.util.LinkedList;

public abstract class Esame {
	protected int numStudenti;
	
	LinkedList<Studente> studentiTA=new LinkedList<>();
	LinkedList<Studente> studentiTB=new LinkedList<>();
	LinkedList<Studente> studentiTC=new LinkedList<>();
	LinkedList<Studente> studentiTD=new LinkedList<>();
	
	public Esame(int numStudenti) {
		this.numStudenti=numStudenti;
	}
	
	public abstract void prendiPosto() throws InterruptedException; //stud
	
	public abstract void consegnaCompito() throws InterruptedException; //stud
	
	public abstract void iniziaEsame() throws InterruptedException; //doc
	
	public abstract void fineEsame(); //doc
	
}
