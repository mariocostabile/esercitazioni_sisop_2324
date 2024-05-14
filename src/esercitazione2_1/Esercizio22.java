package esercitazione2_1;

public class Esercizio22 extends Thread{

	public Esercizio22(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 2; i++) {
			System.out.print(getName());
		}
	}
	
	public static void main(String[] args) {
		new Esercizio22("0").start();
		new Esercizio22("1").start();
	}
}
