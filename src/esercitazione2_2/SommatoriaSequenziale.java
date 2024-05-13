package esercitazione2_2;

import java.util.Iterator;

public class SommatoriaSequenziale {
	
	public static void main(String[] args) {
		int primo = 1; 
		int ultimo = 100;
		long somma = 0; //n*(n+1)/2
		for (int i = primo; i <= ultimo; i++)
			somma+=i;
		System.out.println(somma);
	}
}
