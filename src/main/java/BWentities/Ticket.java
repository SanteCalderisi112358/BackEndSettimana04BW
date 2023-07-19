package BWentities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

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


	public Ticket(LocalDate dataEmissione) {

		this.dataEmissione = dataEmissione;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", dataEmissione=" + dataEmissione +  "]";
	}

}
