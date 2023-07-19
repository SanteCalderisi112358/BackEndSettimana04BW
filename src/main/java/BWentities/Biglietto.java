package BWentities;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Biglietto extends Ticket {

	private boolean timbrato;
	// @ManyToOne
//	@JoinColumn(name = "mezzo_id")
	// private Mezzo mezzo;

//	public Biglietto(LocalDate localDate, Tessera tessera) {
//		super(localDate, tessera);
//		this.timbrato = false;
//
//	}
//	public Biglietto(String dataEmissione, Tessera tessera) {
//		super(dataEmissione, tessera, puntoEmissione);
//		this.timbrato = false;
//
//	}

	public Biglietto(String dataEmissione, Tessera tessera, PuntoEmissione puntoEmissione) {
		super(dataEmissione, tessera, puntoEmissione);
		this.timbrato = false;

	}

	@Override
	public String toString() {
		return "Biglietto [id=" + id + ", data Emissione=" + dataEmissione + ", tessera=" + tessera
				+ ", punto Emissione=" + puntoEmissione + "]";
	}

//	public void timbrato(UUID idBiglietto, String targa) {
//		EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
//		EntityManager em = null;
//		EntityTransaction t = em.getTransaction();
//		t.begin();
//		// @SuppressWarnings("null")
//		Biglietto biglietto = em.find(Biglietto.class, idBiglietto);
//		biglietto.setTimbrato(true);
//		biglietto.getMezzo().setTarga(targa);
//
//		t.commit();
//	}





}
