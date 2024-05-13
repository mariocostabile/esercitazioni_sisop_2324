package prova;

/*
 * Esistono due modi per implementare i thread in java:
 * 1. Definire una sottoclasse della classe Thread (più facile ma limitato)
 * 2. Definire una classe che implementa l'interfaccia runnable
 */

public class Prova1 extends Thread{
	public Prova1(String nome){
		super(nome);   //uso il costruttore della classe super passando una stringa.....perché? (forse è il nome che assegno al thread)
	}
	
	public void run() {  //la chiave è il metodo run che fa partire il thread
		for(int i=0; i<10 ; ++i) {
			System.out.println(" Ciao da " + getName());  //get name è una funzione di thread e ritorna il nome del thread
		}
	}
	
	public static void main(String[] args) {
		Prova1 t = new Prova1("PrimoThread");
		t.start(); //creo un istanda della sottoclasse che eredita thread e su di essa chiamo il metodo start
	}
}

