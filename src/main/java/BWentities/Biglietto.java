package BWentities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Biglietto extends Ticket {

	private boolean timbrato;
	@ManyToOne
	@JoinColumn(name = "mezzo_id")
	private Mezzo mezzo;

	public Biglietto(String dataEmissione, Tessera tessera, boolean timbrato, Mezzo mezzo) {
		super(dataEmissione, tessera);
		this.timbrato = timbrato;
		this.mezzo = mezzo;
	}

	@Override
	public String toString() {
		return "Biglietto [timbrato=" + timbrato + "]";
	}

}
