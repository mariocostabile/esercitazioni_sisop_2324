package esercitazione2_2;

public class Esercizio21a {

    private static String nome = "Ciao";

    public static void main(String[] args) throws InterruptedException {
    	while(true) {
            new Esercizio21a().prova();
            System.out.println(nome);
    	}
    }

    public void prova() {
        Esercizio21aT ese=new Esercizio21aT();
        ese.start();
        try {
			ese.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        nome = nome + " mondo";
    }

    class Esercizio21aT extends Thread {
        public void run() {
            nome += " 1 2 3";
        }
    }
}

