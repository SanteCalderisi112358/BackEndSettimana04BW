package Mezzi;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	@GeneratedValue
	private UUID id;
	private int numeroTratte;
	private int capienza;
	@Enumerated(EnumType.STRING)
	private TipoMezzo tipoMezzo;
	
    @OneToMany(mappedBy = "mezzo")
    private List<Manutenzione> manutenzioni = new ArrayList<>();
    
    @OneToMany(mappedBy = "mezzo")
    private List<StoricoTratte> storicoTratte = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "tratta_id")
    private Tratta tratta;
	
	public Mezzi(int numeroTratte, int capienza, TipoMezzo tipoMezzo) {
		this.numeroTratte = numeroTratte;
		this.capienza = capienza;
		this.tipoMezzo = tipoMezzo;
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
	
    public double getMaxTempoPercorrenza() {
        List<StoricoTratte> storico = this.getStoricoTratte();

        if (storico.isEmpty()) {
            throw new IllegalArgumentException("Nessuna tratta trovata per questo mezzo");
        }

        double max = 0;
        for (int i = 0; i < storico.size(); i++ ) {
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
        for (int i = 0; i < storico.size(); i++ ) {
            min = Math.min(storico.get(i).getTEffettivo(), min);
        }

        return min;
    }
	
}
