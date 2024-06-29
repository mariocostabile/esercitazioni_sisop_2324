package appelli.Esercizio4.gokart;

import java.util.*;

public abstract class Pista {
	
	protected LinkedList<Pilota> attendiNoleggio = new LinkedList<>();
	protected LinkedList<Pilota> attendiPista = new LinkedList<>();
	
	public Pista(LinkedList<Pilota> attendiNoleggio, LinkedList<Pilota> attendiPista) {
		this.attendiNoleggio=attendiNoleggio;
		this.attendiPista=attendiPista;
	}
	
	public abstract void noleggia();
	
	public abstract int entraInPista();
	
	public abstract void lasciaPista();
	
	public abstract void riconsegna();
}
