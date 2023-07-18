package BWentities;

public class RivenditoreAutorizzato extends PuntoEmissione {

	private String nome;

	public RivenditoreAutorizzato(String indirizzo, String nome) {
		super(indirizzo);
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "RivenditoreAutorizzato [nome=" + nome + "]";
	}

}
