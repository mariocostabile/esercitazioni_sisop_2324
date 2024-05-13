package prova;

public class Prova2 implements Runnable {
	private String nome;
	
	public Prova2(String nome) {
		this.nome=nome;
	}
	
	@Override
	public void run() {
		for(int i=0; i<10 ; ++i) {
			System.out.println("Ciao da " + nome);
		}
	}
	
	public static void main(String[] args) {
		Prova2 p = new Prova2("Secondo Thread");
		Thread t = new Thread(p);
		t.start();
		
		// creo un oggetto della classe che implementa runnable dove ridefinisco il metodo run 
		// creo un nuovo thread a partire da quell'oggetto passandolo come parametro 
		// chiamo start sul thread
		
	}
}
