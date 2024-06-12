package appelli;

public class Prova3_20231110 {
    public static void main(String[] args) {
        int n = 10;
        MyThread[] threads = new MyThread[n];
        for (int i = 0; i < n; i++)
            threads[i] = new MyThread(i, threads);
        for (int i = 0; i < n; i++)
            threads[i].start();
    }

    static class MyThread extends Thread {
        private int id;
        private MyThread[] threads;

        public MyThread(int id, MyThread[] threads) {
            this.id = id;
            this.threads = threads;
        }

        @Override
        public void run() {
            try {
                int p = threads.length - id - 1;
                if (p >= threads.length / 2)
                    threads[p].join();
                System.out.printf("Thread-%d Thread-%d %s\n", id, p, threads[p].getState());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


