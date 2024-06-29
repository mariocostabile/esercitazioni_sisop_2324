package appello.ufficioPostale;

public class Main {
    public static void main(String[] args) {
        UfficioPostaleLC ufficioPostale = new UfficioPostaleLC();

        // Creazione e avvio dei thread degli impiegati
        Impiegato impiegatoA = new Impiegato("A", ufficioPostale);
        Impiegato impiegatoB = new Impiegato("B", ufficioPostale);
        Impiegato impiegatoC = new Impiegato("C", ufficioPostale);

        impiegatoA.start();
        impiegatoB.start();
        impiegatoC.start();

        // Creazione e avvio dei thread dei clienti
        for (int i = 0; i < 200; i++) {
            String[] operazioni = {"A", "B", "C"};
            String operazione = operazioni[i % 3];
            Cliente cliente = new Cliente(operazione, ufficioPostale);
            cliente.start();
            try {
                Thread.sleep(100); // Simula un ritardo tra l'arrivo dei clienti
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Ciclo per visualizzare lo stato in tempo reale
        while (true) {
            System.out.println(ufficioPostale.toString());
            try {
                Thread.sleep(1000); // Attendere 1 secondo prima di aggiornare lo stato
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

