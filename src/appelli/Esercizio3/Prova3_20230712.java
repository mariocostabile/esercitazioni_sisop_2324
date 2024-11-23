package appelli.Esercizio3;

import java.util.concurrent.Semaphore;

public class Prova3_20230712 {
    private static Semaphore[] sems = new Semaphore[] { new Semaphore(1), new Semaphore(0) };

    static class MyThread extends Thread {
        private int tipo;
        private int indice;

        public MyThread(int tipo, int indice) {
            this.tipo = tipo;
            this.indice=indice;
        }

        public void run() {
            try {
                sems[tipo].acquire();
                System.out.println("tipo: "+tipo+" indice: "+indice);
                sems[1 - tipo].release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int N = 10;
        for (int i = 0; i < N; i++) {
            new MyThread(0,i).start();
            new MyThread(1,i).start();
        }
    }
}
