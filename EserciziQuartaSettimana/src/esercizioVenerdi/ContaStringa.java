package esercizioVenerdi;

public class ContaStringa {

	private String stringa;
	private int numeroRipetizione;

	public ContaStringa(String stringa) {
		this.stringa = stringa;
		this.numeroRipetizione = 1;
	}

	public String getStringa() {
		return stringa;
	}

	public int getNumeroRipetizione() {
		return numeroRipetizione;
	}

	public void add() {
		numeroRipetizione++;
	}
	
	public int compareTo(){
		Integer f =numeroRipetizione;
		//Integer.compare(numeroRipetizione, numeroRipetizione)
		return 0;
	}
	
}
