package appello.ufficioPostale;

public class Impiegato extends Thread {
    private String tipoOperazione;
    private UfficioPostale ufficio;

    public Impiegato(String tipoOperazione, UfficioPostale ufficio) {
        this.ufficio = ufficio;
        this.tipoOperazione=tipoOperazione;
    }

    @Override
    public void run() {
        while (true) {
            ufficio.prossimoCliente();
            ufficio.eseguiOperazione();
        }
    }

	public String getTipoOperazione() {
		return tipoOperazione;
	}
}

