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

//	public Abbonamento(String dataEmissione, Tessera tessera, TipoAbbonamento tipoAbbonamento, LocalDate dataScadenza) {
//		super(dataEmissione, tessera, puntoEmissione);
//		this.tipoAbbonamento = tipoAbbonamento;
//		this.dataScadenza = dataScadenza;
//	}

	public Abbonamento(String dataEmissione, Tessera tessera, PuntoEmissione puntoEmissione,
			TipoAbbonamento tipoAbbonamento, LocalDate dataScadenza) {
		super(dataEmissione, tessera, puntoEmissione);
		this.tipoAbbonamento = tipoAbbonamento;
		this.dataScadenza = dataScadenza;
	}

	@Override
	public String toString() {
		return "Abbonamento [tipoAbbonamento=" + tipoAbbonamento + ", dataScadenza=" + dataScadenza + "]";
	}


}
