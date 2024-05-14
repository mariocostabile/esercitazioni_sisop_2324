package esercitazione2_1;

public class Esercizio28 {

	public static void main(String[] args) throws InterruptedException{
		MyThread3 t3 = new MyThread3("T1");
		t3.start();
		t3.join();
		System.out.println("Exit");
	}

}

class MyThread3 extends Thread{
	public MyThread3(String name) {
		setName(name);
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}