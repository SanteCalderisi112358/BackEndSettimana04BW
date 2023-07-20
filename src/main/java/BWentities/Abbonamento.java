package BWentities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import BWenum.TipoAbbonamento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Abbonamento extends Ticket {
	@Enumerated(EnumType.STRING)
	private TipoAbbonamento tipoAbbonamento;

	private LocalDate dataScadenza;



	public Abbonamento(LocalDate dataEmissione, Tessera tessera, PuntoEmissione puntoEmissione,
			TipoAbbonamento tipoAbbonamento) {
		super(dataEmissione, tessera, puntoEmissione);
		this.tipoAbbonamento = tipoAbbonamento;

	}

	public void setScadenza() {
		if (this.tipoAbbonamento == TipoAbbonamento.MENSILE) {
			this.dataScadenza = this.dataEmissione.plusMonths(1);
		} else {
			this.dataScadenza = this.dataEmissione.plusWeeks(1);
	}
	}
	@Override
	public String toString() {
		return "Abbonamento [tipoAbbonamento=" + tipoAbbonamento + ", dataScadenza=" + dataScadenza + "]";
	}


}
