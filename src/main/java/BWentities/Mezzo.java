package BWentities;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import BWenum.StatoMezzo;
import BWenum.TipoMezzo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Mezzo {
	@Id
	@GeneratedValue
	private UUID id;
	private int capienza;
	private TipoMezzo tipoMezzo;
	private StatoMezzo statoMezzo;
	private LocalDate inizioPeriodoServizio;
	private LocalDate finePeriodoServizio;
	private LocalDate inizioPeriodoManutenzione;
	private LocalDate finePeriodoManutenzione;
	@OneToMany(mappedBy = "mezzo")
	private Set<Biglietto> biglietti;

	@ManyToMany
	@JoinTable(name = "mezzo_tratta", joinColumns = @JoinColumn(name = "mezzo_id"), inverseJoinColumns = @JoinColumn(name = "tratta_id"))
	private Set<Tratta> tratte;

// Costruttore per mezzo IN_SERVIZIO
	public Mezzo(TipoMezzo tipoMezzo, LocalDate inizioPeriodoServizio, LocalDate finePeriodoServizio,
			Set<Tratta> tratte) {
		
		this.tipoMezzo = tipoMezzo;
		this.statoMezzo = StatoMezzo.IN_SERVIZIO;
		this.inizioPeriodoServizio = inizioPeriodoServizio;
		this.finePeriodoServizio = finePeriodoServizio;
		this.tratte = tratte;
	}

// Costruttore per mezzo IN_MANUTENZIONE
	public Mezzo(TipoMezzo tipoMezzo, LocalDate inizioPeriodoManutenzione,
			LocalDate finePeriodoManutenzione) {

		this.tipoMezzo = tipoMezzo;
		this.statoMezzo = StatoMezzo.IN_MANUTENZIONE;
		this.inizioPeriodoManutenzione = inizioPeriodoManutenzione;
		this.finePeriodoManutenzione = finePeriodoManutenzione;
	}

//Metodo per settare la capienza in base al tipo del mezzo
	public void setcapienza() {
		if (this.tipoMezzo == TipoMezzo.AUTOBUS) {
			this.capienza = 58;
		} else {
			this.capienza = 100;
		}
	}

	@Override
	public String toString() {
		if (this.statoMezzo == StatoMezzo.IN_MANUTENZIONE) {
			return "Mezzo [id=" + id + ", capienza=" + capienza + ", tipoMezzo=" + tipoMezzo + ", statoMezzo="
					+ statoMezzo + ", inizioPeriodoManutenzione=" + inizioPeriodoManutenzione
					+ ", finePeriodoManutenzione=" + finePeriodoManutenzione + "]";
		}else {
			return "Mezzo [id=" + id + ", capienza=" + capienza + ", tipoMezzo=" + tipoMezzo + ", statoMezzo=" + statoMezzo +
			           ", inizioPeriodoServizio=" + inizioPeriodoServizio + ", finePeriodoServizio=" + finePeriodoServizio +
			           ", tratte=" + tratte + "]";
		}
	}
}
