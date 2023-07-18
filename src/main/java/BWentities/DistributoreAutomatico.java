package BWentities;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import BWenum.StatoServizio;

public class DistributoreAutomatico extends PuntoEmissione {

	@Enumerated(EnumType.STRING)
	private StatoServizio statoServizio;

	public DistributoreAutomatico(String indirizzo, StatoServizio statoServizio) {
		super(indirizzo);
		this.statoServizio = statoServizio;
	}

	@Override
	public String toString() {
		return "DistributoreAutomatico [statoServizio=" + statoServizio + "]";
	}

}
