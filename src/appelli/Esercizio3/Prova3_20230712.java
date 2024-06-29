package appelli.Esercizio3;

import java.util.concurrent.Semaphore;

public class Prova3_20230712 {
    private static Semaphore[] sems = new Semaphore[] { new Semaphore(1), new Semaphore(0) };

    static class MyThread extends Thread {
        private int tipo;

        public MyThread(int tipo) {
            this.tipo = tipo;
        }

        public void run() {
            try {
                sems[tipo].acquire();
                System.out.print(tipo);
                sems[1 - tipo].release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int N = 10;
        for (int i = 0; i < N; i++) {
            new MyThread(0).start();
            new MyThread(1).start();
        }
    }
}
