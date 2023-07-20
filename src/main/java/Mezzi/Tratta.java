//package Mezzi;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Entity
//@Setter
//@Getter
//@NoArgsConstructor
//public class Tratta {
//
//	@Id
//	@GeneratedValue
//	private UUID Id;
//	private String partenza;
//	private String capolinea;
//	private double tMedio;
//	private double tEffettivo;
//
//	@OneToMany(mappedBy = "tratta")
//	private List<Mezzi> mezzi = new ArrayList<>();
//
//	public Tratta(String partenza, String capolinea, double tMedio, double tEffettivo) {
//		this.partenza = partenza;
//		this.capolinea = capolinea;
//		this.tMedio = tMedio;
//		this.tEffettivo = tEffettivo;
//
//	}
//
//	@Override
//	public String toString() {
//		return "Tratta [Id=" + Id + ", partenza=" + partenza + ", capolinea=" + capolinea + ", tMedio=" + tMedio
//				+ ", tEffettivo=" + tEffettivo + "]";
//	}
//
//}

package Mezzi;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Tratta {

	@Id
	@GeneratedValue
	private UUID Id;
	private String partenza;
	private String capolinea;
	private double tMedio;

	@OneToMany(mappedBy = "tratta")
	private List<Mezzi> mezzi = new ArrayList<>();

	@OneToMany(mappedBy = "tratta")
	private List<StoricoTratte> storicoTratte = new ArrayList<>();


	public Tratta(String partenza, String capolinea, double tMedio) {
		this.partenza = partenza;
		this.capolinea = capolinea;
		this.tMedio = tMedio;
	}

	@Override
	public String toString() {
		return "Tratta [Id=" + Id + ", partenza=" + partenza + ", capolinea=" + capolinea + ", tMedio=" + tMedio + "]";
	}

	// confronta tempo percorrenza
	public void comparazioneTempoPercorrenza(Mezzi mezzo) {
		if (this.getTMedio() > mezzo.getMediaTempiPercorrenza()) {
			System.out.println("Il mezzo ci mette meno tempo della tempo medio richiesto per la tratta.");
		} else if (this.getTMedio() < mezzo.getMaxTempoPercorrenza()) {
			System.out.println("Il mezzo ci mette più tempo della tempo medio richiesto per la tratta.");
		} else {
			System.out.println("Il mezzo ci mette il giusto tempo per percorrere la tratta");
		}
	}

}