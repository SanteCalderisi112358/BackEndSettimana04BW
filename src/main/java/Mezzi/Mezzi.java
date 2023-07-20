//package Mezzi;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//
//import BWentities.Biglietto;
//import BWenum.StatoMezzo;
//import BWenum.TipoMezzo;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Entity
//@Setter
//@Getter
//@NoArgsConstructor
//public class Mezzi {
//
//	@Id
////	@GeneratedValue
////	private UUID id;
//	private String targa;
//	private int numeroTratte;
//	private int capienza;
//	@Enumerated(EnumType.STRING)
//	private TipoMezzo tipoMezzo;
//	/* AGGIUNTA STATOMEZZO (IN SERVIZIO, IN MANUTENZIONE) */
//	@Enumerated(EnumType.STRING)
//	private StatoMezzo statoMezzo;
//
//	@OneToMany(mappedBy = "mezzo")
//	private List<Manutenzione> manutenzioni = new ArrayList<>();
//
//	@OneToMany(mappedBy = "mezzo")
//	private List<StoricoTratte> storicoTratte = new ArrayList<>();
//
//	@OneToMany(mappedBy = "mezzo")
//	private List<Biglietto> biglietti = new ArrayList<Biglietto>();
//
//	@ManyToOne
//	@JoinColumn(name = "tratta_id")
//	private Tratta tratta;
//
//	/*
//	 * ELIMINAZIONE CAPIENZA NEL COSTRUTTORE E AGGIUNTA METODO PER SETTARE CAPIENZA
//	 * IN BASE AL TIPO MEZZO
//	 */
//	public Mezzi(String targa, StatoMezzo statoMezzo, TipoMezzo tipoMezzo, int numeroTratte) {
//		this.targa = targa;
//		this.numeroTratte = numeroTratte;
//		this.tipoMezzo = tipoMezzo;
//		this.statoMezzo = statoMezzo;
//	}
//
//	public void setBigliettiVidiminati(Biglietto biglietto) {
//		this.biglietti.add(biglietto);
//	}
//	public void setCapienzaMezzo() {
//		if (this.tipoMezzo == TipoMezzo.AUTOBUS) {
//			this.capienza = 58;
//		} else {
//			this.capienza = 98;
//		}
//	}
//	public double getMediaTempiPercorrenza() {
//		List<StoricoTratte> storico = this.getStoricoTratte();
//
//		if (storico.isEmpty()) {
//			throw new IllegalArgumentException("Nessuna tratta trovata per questo mezzo");
//		}
//
//		double somma = 0;
//		for (StoricoTratte tratta : storico) {
//			somma += tratta.getTEffettivo();
//		}
//
//		return somma / storico.size();
//	}
//
//	public double getMaxTempoPercorrenza() {
//		List<StoricoTratte> storico = this.getStoricoTratte();
//
//		if (storico.isEmpty()) {
//			throw new IllegalArgumentException("Nessuna tratta trovata per questo mezzo");
//		}
//
//		double max = 0;
//		for (int i = 0; i < storico.size(); i++) {
//			max = Math.max(storico.get(i).getTEffettivo(), max);
//		}
//
//		return max;
//	}
//
//	public double getMinTempoPercorrenza() {
//		List<StoricoTratte> storico = this.getStoricoTratte();
//
//		if (storico.isEmpty()) {
//			throw new IllegalArgumentException("Nessuna tratta trovata per questo mezzo");
//		}
//
//		double min = storico.get(0).getTEffettivo();
//		for (int i = 0; i < storico.size(); i++) {
//			min = Math.min(storico.get(i).getTEffettivo(), min);
//		}
//
//		return min;
//	}
//
//	@Override
//	public String toString() {
//		return "Mezzo [targa=" + targa + ", tratta=" + tratta + "]";
//	}
//
//}
package Mezzi;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import BWentities.Biglietto;
import BWenum.StatoMezzo;
import BWenum.TipoMezzo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Mezzi {

	@Id
//    @GeneratedValue
//    private UUID id;
	private String targa;
	private int capienza;
	@Enumerated(EnumType.STRING)
	private TipoMezzo tipoMezzo;
	/* AGGIUNTA STATOMEZZO (IN SERVIZIO, IN MANUTENZIONE) */
	@Enumerated(EnumType.STRING)
	private StatoMezzo statoMezzo;

	@OneToMany(mappedBy = "mezzo")
	private List<Manutenzione> manutenzioni = new ArrayList<>();

	@OneToMany(mappedBy = "mezzo")
	private List<StoricoTratte> storicoTratte = new ArrayList<>();

	@OneToMany(mappedBy = "mezzo")
	private List<Biglietto> biglietti = new ArrayList<Biglietto>();

	@ManyToOne
	@JoinColumn(name = "tratta_id")
	private Tratta tratta;

	/*
	 * ELIMINAZIONE CAPIENZA NEL COSTRUTTORE E AGGIUNTA METODO PER SETTARE CAPIENZA
	 * IN BASE AL TIPO MEZZO
	 */
	public Mezzi(String targa, StatoMezzo statoMezzo, TipoMezzo tipoMezzo) {
		this.targa = targa;
		this.tipoMezzo = tipoMezzo;
		this.statoMezzo = statoMezzo;
		setCapienza();
	}


	public void setCapienza() { // credo che non servisse creare un metodo aggiuntivo (creato il metodo lombock
								// non lo sovrascrive)
		if (this.tipoMezzo == TipoMezzo.AUTOBUS) {
			this.capienza = 58;
		} else {
			this.capienza = 98;
		}
	}

	public double getMediaTempiPercorrenza() {
		List<StoricoTratte> storico = this.getStoricoTratte();

		if (storico.isEmpty()) {
			throw new IllegalArgumentException("Nessuna tratta trovata per questo mezzo");
		}

		double somma = 0;
		for (StoricoTratte tratta : storico) {
			somma += tratta.getTEffettivo();
		}

		return somma / storico.size();
	}

	public void setBigliettiVidiminati(Biglietto biglietto) {
		this.biglietti.add(biglietto);
	}

	public double getMaxTempoPercorrenza() {
		List<StoricoTratte> storico = this.getStoricoTratte();

		if (storico.isEmpty()) {
			throw new IllegalArgumentException("Nessuna tratta trovata per questo mezzo");
		}

		double max = 0;
		for (int i = 0; i < storico.size(); i++) {
			max = Math.max(storico.get(i).getTEffettivo(), max);
		}

		return max;
	}

	public double getMinTempoPercorrenza() {
		List<StoricoTratte> storico = this.getStoricoTratte();

		if (storico.isEmpty()) {
			throw new IllegalArgumentException("Nessuna tratta trovata per questo mezzo");
		}

		double min = storico.get(0).getTEffettivo();
		for (int i = 0; i < storico.size(); i++) {
			min = Math.min(storico.get(i).getTEffettivo(), min);
		}

		return min;
	}

}