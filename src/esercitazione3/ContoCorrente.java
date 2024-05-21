package esercitazione3;
 
public abstract class ContoCorrente{
	
	protected int deposito; //campo
	
	public ContoCorrente(int depositoIniziale) {
		this.deposito=depositoIniziale;
	}
	
	public abstract void deposita(int importo); //i due metodi sono stati lasciati abstract per essere definiti dopo 
	
	public abstract void preleva(int importo);
	
	public int getDeposito() { //ritorna il deposito residuo del contoCorrente
		return deposito;
	}
	
}
