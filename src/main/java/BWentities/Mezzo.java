package BWentities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
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
	private String targa;
	@Enumerated(EnumType.STRING)
	private StatoMezzo statoMezzo;
	@Enumerated(EnumType.STRING)
	private TipoMezzo tipoMezzo;
	private int capienza;
	private int corse;
	
	@OneToMany(mappedBy = "mezzo")
	private Set<Biglietto> biglietti;

//	@OneToMany
//	private Set<Manutenzione> manutenzioni;
//
//	@OneToMany
//	private Set<StoricoTratta> soricoTratta;

	public Mezzo(String targa, StatoMezzo statoMezzo, TipoMezzo tipoMezzo, int corse) {
		this.targa = targa;
		this.statoMezzo = statoMezzo;
		this.tipoMezzo = tipoMezzo;
		this.corse = corse;
	}

	public void setCapienza() {
		if (this.tipoMezzo == TipoMezzo.AUTOBUS) {
			this.capienza = 58;
		} else {
			this.capienza = 98;
		}
	}
	@Override
	public String toString() {
		return "Mezzo [targa=" + targa + ", statoMezzo=" + statoMezzo + ", tipoMezzo=" + tipoMezzo + ", capienza="
				+ capienza + ", corse=" + corse + "]";
	}

}
