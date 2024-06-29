package appelli.Esercizio4.aziendaAgricola;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Cliente extends Thread {
	private Random random = new Random();
	private AziendaAgricola aziendaAgricola;
	private int numeroSacchiDaPrelevare = 0;
	private int id;

	public Cliente(AziendaAgricola aziendaAgricola, int id) {
		this.aziendaAgricola = aziendaAgricola;
		this.id = id;
	}

	@Override
	public void run() {
		try {
			attendi(0, 20);
			numeroSacchiDaPrelevare = decidi(10);

			aziendaAgricola.paga(numeroSacchiDaPrelevare);

			while (numeroSacchiDaPrelevare > 0) {
				aziendaAgricola.ritira();
				System.out.println(this);
				spostaInAuto();
				numeroSacchiDaPrelevare--;
			}
		} catch (InterruptedException e) {
		}

	}

	private void spostaInAuto() throws InterruptedException {
		TimeUnit.SECONDS.sleep(1);
	}

	public int decidi(int max) {
		return random.nextInt(max) + 1;
	}

	private void attendi(int min, int max) throws InterruptedException {
		TimeUnit.SECONDS.sleep(random.nextInt(max - min + 1) + min);
	}

	public String toString() {
		return "Cliente [" + id + "] ha prelevato 1 sacco dal magazzino. Deve ancora prelevare "
				+ numeroSacchiDaPrelevare + " sacchi.";
	}
}
