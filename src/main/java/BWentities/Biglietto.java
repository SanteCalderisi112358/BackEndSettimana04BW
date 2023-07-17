package BWentities;

import java.time.LocalDate;

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

	public Biglietto(LocalDate dataEmissione, PuntoEmissione puntoEmissione, boolean timbrato) {
		super(dataEmissione, puntoEmissione);
		this.timbrato = timbrato;
	}

	@Override
	public String toString() {
		return "Biglietto [timbrato=" + timbrato + ", id=" + id + ", dataEmissione=" + dataEmissione
				+ ", puntoEmissione=" + puntoEmissione + "]";
	}

}
