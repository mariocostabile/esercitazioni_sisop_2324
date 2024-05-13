package esercitazione2_1;

public class Esercizio21 extends Thread{
	private static String nome = "Ciao";
	
	public static void main(String[] args) {
		new Esercizio21().prova();
		System.out.println(nome);
	}
	
	private void prova() {
		start();
		try {
			this.join();
		} catch (Exception e) {
			// TODO: handle exception
		}
		nome = nome +"mondo";
	}
	
	@Override
	public void run() {
		nome += "1 2 3";
	}
}
