package esercitazione2_2;

public class SommatoriaConcorrente {
	
	public static void main(String[] args) {
		int primo = 1;
		int ultimo = 100;
		int intermedio = (ultimo + primo) / 2;
		Sommatore s1 = new Sommatore(primo, intermedio);
		Sommatore s2 = new Sommatore(intermedio+1, ultimo);
		s1.start();
		s2.start();
		try {
			long somma=0;
			int count=0;
			while(somma<=5000) {
				somma=s1.getSomma()+s2.getSomma();
				count++;
			}
			
			System.out.println(somma); //IL THREAD PRINCIPALE ASPETTA LA FINE DI S1 E S2 PRIMA DI CHIEDERE LA SOMMA
			System.out.println(count);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
