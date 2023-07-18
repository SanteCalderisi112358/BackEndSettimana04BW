package BWentities;

import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity
@Getter
@Setter
@NoArgsConstructor
public class StoricoTratta {

	@Id
	@GeneratedValue
	private UUID idStoricoTratta;
	private double tempoEffettivo;
	@OneToOne
	private Mezzo mezzo;
	@OneToOne
	private Tratta tratta;

	public StoricoTratta(double tempoEffettivo, Mezzo mezzo, Tratta tratta) {

		this.tempoEffettivo = tempoEffettivo;
		this.mezzo = mezzo;
		this.tratta = tratta;
	}

	@Override
	public String toString() {
		return "StoricoTratta [idStoricoTratta=" + idStoricoTratta + ", tempoEffettivo=" + tempoEffettivo + ", mezzo="
				+ mezzo + ", tratta=" + tratta + "]";
	}

}
