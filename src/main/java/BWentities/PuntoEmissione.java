package BWentities;

import java.util.Set;
import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class PuntoEmissione {

	@Id
	@GeneratedValue
	private UUID id;
	private String indirizzo;

	@OneToMany
	private Set<Ticket> ticket;

	public PuntoEmissione(String indirizzo) {

		this.indirizzo = indirizzo;
	}

	@Override
	public String toString() {
		return "PuntoEmissione [id=" + id + ", indirizzo=" + indirizzo + "]";
	}

}
