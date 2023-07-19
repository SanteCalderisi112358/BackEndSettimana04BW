package Mezzi;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class StoricoTratte {
	@Id
	@GeneratedValue
	private UUID codiceStoricoTratte;
	private double tEffettivo;
	
	@ManyToOne
	private Mezzi mezzo;


	@Override
	public String toString() {
		return "StoricoTratte [codiceStoricoTratte=" + codiceStoricoTratte + ", tEffettivo=" + tEffettivo + ", mezzo="
				+ mezzo + "]";
	}


	public StoricoTratte(double tEffettivo, Mezzi mezzo) {
		super();
		this.tEffettivo = tEffettivo;
		this.mezzo = mezzo;
	}
	
	
}
