package esercitazione2_1;

public class Esercizio27 {
	public static void main(String[] args) throws InterruptedException{
		MyThread2 t1 = new MyThread2("T1");
		t1.start();
		t1.join();
		MyThread2 t2 = new MyThread2("T2");
		System.out.println(t1.getName()+" "+t1.getState());
		t2.start();
		t2.join();
	}
}

class MyThread2 extends Thread{
	public MyThread2(String name) {
		setName(name);
	}
	
	@Override
	public void run() {
		System.out.println(getName()+" "+getState());
	}
	
	/*t1 runnable
	 * t1 terminated
	 * t2 runnable
	 */
	
	
	
}
