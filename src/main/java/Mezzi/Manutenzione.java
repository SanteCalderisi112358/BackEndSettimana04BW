package Mezzi;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Manutenzione {
	
	@Id
	@GeneratedValue
	private UUID idManutenzione;
	private String descrizione;
	private LocalDate dataInizio;
	private LocalDate dataFine;
	private int durata; 
	
    @ManyToOne
    @JoinColumn(name = "mezzo_id")
    private Mezzi mezzo;
	
	public Manutenzione( String descrizione, LocalDate dataInizio,
			LocalDate dataFine, int durata, Mezzi mezzo) {
		this.descrizione = descrizione;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.durata = durata;
		this.mezzo = mezzo;
	}

	@Override
	public String toString() {
		return "Manutenzione [idManutenzione=" + idManutenzione + ", descrizione=" + descrizione + ", dataInizio="
				+ dataInizio + ", dataFine=" + dataFine + ", durata=" + durata + ", mezzo=" + mezzo + "]";
	}
	
}
