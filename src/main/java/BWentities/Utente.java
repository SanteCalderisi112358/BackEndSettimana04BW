package BWentities;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Utente {

	@Id
	@GeneratedValue
	private UUID codiceFiscale;
	private String nome;
	private String cognome;
	@OneToOne(mappedBy = "utente")
	private Tessera tessera;

	public Utente(String nome, String cognome) {
		this.nome = nome;
		this.cognome = cognome;
	}





	@Override
	public String toString() {
		return "Utente [id=" + codiceFiscale + ", nome=" + nome + ", cognome=" + cognome + "]";
	}

}
