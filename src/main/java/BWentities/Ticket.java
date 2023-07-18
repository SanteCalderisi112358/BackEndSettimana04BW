package BWentities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Ticket {
	@Id
	@GeneratedValue
	protected UUID id;
	protected LocalDate dataEmissione;
	@ManyToOne
	protected Tessera tessera;

	public Ticket(String dataEmissione, Tessera tessera) {

		this.dataEmissione = LocalDate.parse(dataEmissione);
		this.tessera = tessera;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", dataEmissione=" + dataEmissione + ", Tessera=" + tessera + "]";
	}

}
