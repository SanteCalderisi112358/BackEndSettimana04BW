package BWentities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import Mezzi.Mezzi;
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
	private Mezzi mezzo;

	public Biglietto(LocalDate dataEmissione, Tessera tessera, PuntoEmissione puntoEmissione) {
		super(dataEmissione, tessera, puntoEmissione);
		this.timbrato = false;

	}

	@Override
	public String toString() {
		return "Biglietto [id=" + id + ", data Emissione=" + dataEmissione + ", tessera=" + tessera
				+ ", punto Emissione=" + puntoEmissione + "]";
	}






}
