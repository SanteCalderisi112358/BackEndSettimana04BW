package BWentities;

import javax.persistence.Entity;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
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
