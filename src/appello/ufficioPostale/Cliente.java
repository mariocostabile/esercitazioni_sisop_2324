package appello.ufficioPostale;

public class Cliente extends Thread {
    private String tipoOperazione;
    private UfficioPostale ufficio;

    public Cliente(String tipoOperazione, UfficioPostale ufficio) {
        this.tipoOperazione = tipoOperazione;
        this.ufficio = ufficio;
    }

    @Override
    public void run() {
        if (ufficio.ritiraTicket(tipoOperazione)) {
            ufficio.attendiSportello(tipoOperazione);
        } else {
            System.out.println("Nessun ticket disponibile per l'operazione " + tipoOperazione);
        }
    }
}

