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

	public Abbonamento(LocalDate dataEmissione, PuntoEmissione puntoEmissione, TipoAbbonamento tipoAbbonamento) {
		super(dataEmissione, puntoEmissione);
		this.tipoAbbonamento = tipoAbbonamento;
	}

	@Override
	public String toString() {
		return "Abbonamento [tipoAbbonamento=" + tipoAbbonamento + ", dataScadenza=" + dataScadenza + ", id=" + id
				+ ", dataEmissione=" + dataEmissione + ", puntoEmissione=" + puntoEmissione + "]";
	}



}
