package BWentities;

import java.util.Set;
import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tratta {
	@Id
	@GeneratedValue
	private UUID id;
	private String zonaPartenza;
	private String capolinea;
	private int tempoMedioPercorrenza;// IN SECONDI
	private int numeroCorse;
	private int tempoEffettivoPercorrenza;// IN SECONDI

	@ManyToMany
	@JoinTable(name = "mezzo_tratta", joinColumns = @JoinColumn(name = "tratta_id"), inverseJoinColumns = @JoinColumn(name = "mezzo_id"))
	private Set<Mezzo> mezzi;

	public Tratta(String zonaPartenza, String capolinea, int tempoMedioPercorrenza, int numeroCorse) {

		this.zonaPartenza = zonaPartenza;
		this.capolinea = capolinea;
		this.tempoMedioPercorrenza = tempoMedioPercorrenza;
		this.numeroCorse = numeroCorse;
		this.tempoEffettivoPercorrenza = this.numeroCorse * this.tempoMedioPercorrenza;
	}

	@Override
	public String toString() {
		return "Tratta [id=" + id + ", zonaPartenza=" + zonaPartenza + ", capolinea=" + capolinea
				+ ", tempoMedioPercorrenza=" + tempoMedioPercorrenza + ", numeroCorse=" + numeroCorse
				+ ", tempoEffettivoPercorrenza=" + tempoEffettivoPercorrenza + "]";
	}

}
