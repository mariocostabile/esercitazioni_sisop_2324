package esercitazione2_2;

public class StampanteTSequenziale {
	public static void main(String[] args) {
		
		StampanteT s1 = new StampanteT(1, 10);
		s1.start();
		
		try {
			s1.join(); //il thread main aspetta finchè non finisce s1 //il thread chiamante è il main (thread corrente) (che invoca la join), e il thread chiamato è s1 (this) 
		} catch (InterruptedException e) {
			System.out.println("Fine");
		}
		
	}
}
