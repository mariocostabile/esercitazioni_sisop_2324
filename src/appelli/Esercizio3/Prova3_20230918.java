package appelli.Esercizio3;


public class Prova3_20230918 {
    public static void main(String[] args) throws InterruptedException {
        Thread t0 = new Thread(new MyThread(true));
        Thread t1 = new Thread(new MyThread(false));
        t1.start();
        t1.join();
        System.out.printf("%s, %s\n", t0.getState(), t1.getState());
        Thread t2 = new Thread(new MyThread(true));
        t2.start();
        System.out.printf("%s, %s\n", t2.getState(), Thread.currentThread().getState());
    }

    static class MyThread implements Runnable {
        private boolean flag;

        public MyThread(boolean flag) {
            this.flag = flag;
        }

        public void run() {
            try {
                while (flag) {
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {}
        }
    }
}
