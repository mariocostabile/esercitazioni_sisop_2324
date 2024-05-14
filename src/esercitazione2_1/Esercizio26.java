package esercitazione2_1;

public class Esercizio26 {

	public static void main(String[] args) throws InterruptedException{
		stampa(); //stampa il nome e lo stato di main 
		MyThread t1 = new MyThread("T1");
		t1.start(); //stampa il nome e lo stato di t1
	}
	
	private static void stampa() {
		Thread t = Thread.currentThread();
		System.out.println(t.getName()+" "+t.getState());
	}
}

class MyThread extends Thread {
	public MyThread(String name) {
		setName(name);
	}
	
	@Override
	public void run() {
		System.out.println(getName()+" "+getState());
	}
}
