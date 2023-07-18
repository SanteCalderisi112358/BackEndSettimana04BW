package BWentities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity
@Setter
@Getter
@NoArgsConstructor
public class Manutenzione {

	@Id
	@GeneratedValue
	private UUID idManutenzione;
	private LocalDate dataInizioManutenzione;
	private LocalDate dataFineManutenzione;
	private String causaManutenzione;

	@ManyToOne
	private Mezzo mezzo;

	public Manutenzione(String dataInizioManutenzione, String dataFineManutenzione, String causaManutenzione,
			Mezzo mezzo) {
		this.dataInizioManutenzione = LocalDate.parse(dataInizioManutenzione);
		this.dataFineManutenzione = LocalDate.parse(dataFineManutenzione);
		this.causaManutenzione = causaManutenzione;
		this.mezzo = mezzo;
	}

	@Override
	public String toString() {
		return "Manutenzione [idManutenzione=" + idManutenzione + ", dataInizioManutenzione=" + dataInizioManutenzione
				+ ", dataFineManutenzione=" + dataFineManutenzione + ", causaManutenzione=" + causaManutenzione
				+ ", mezzo=" + mezzo + "]";
	}

}
