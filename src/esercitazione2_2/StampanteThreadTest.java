package esercitazione2_2;

public class StampanteThreadTest{

	/*
	 * l'output non è deterministico, dato lo stesso input gli output sono diversi
	 */
	public static void main(String[] args) {
		StampanteT s1 = new StampanteT(1, 10);
		StampanteT s2 = new StampanteT(11, 20);
		s1.start();
		s2.start();
		System.out.println("Fine");
	}
	
	
	
	
}