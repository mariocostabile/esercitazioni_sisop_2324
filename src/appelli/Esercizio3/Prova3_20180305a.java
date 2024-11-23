package appelli.Esercizio3;

import java.util.concurrent.TimeUnit;

public class Prova3_20180305a {
    public static void main(String[] args) throws InterruptedException {
        MyThread[] threads = new MyThread[6]; // 6 thread
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new MyThread(i, threads); // inizializzati con indice cresente per 0 e 5 e come secondo arg. proprio l'array
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start(); // fatti partire
        }
    }
}

class MyThread extends Thread {
    private int myId;
    private MyThread[] threads;

    public MyThread(int id, MyThread[] t) {
        this.myId = id;
        this.threads = t;
    }

    public void run() {
        try {
            int s = myId + 1;
            if (s < threads.length) {
                threads[s].join();
            }
            TimeUnit.MILLISECONDS.sleep(myId);
            System.out.println("T" + myId + " " + getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /*
     * t3 - join 4
     * t4 - join 5 -... 
     * t5 - 
     * t2 - join 3
     * t0 - 
     * t1 - join 2
     * 
     * 
     * t5 stato
     * t4 stato 
     * t3 stato
     */
}
