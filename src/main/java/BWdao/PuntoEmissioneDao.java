package BWdao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import BWentities.PuntoEmissione;

public class PuntoEmissioneDao {

	private final EntityManager em;
	private static Logger log = LoggerFactory.getLogger(PuntoEmissioneDao.class);

	public PuntoEmissioneDao(EntityManager em) {
		this.em = em;
	}

	public void save(PuntoEmissione s) {
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(s);
		t.commit();
		log.info(s + " è stata salvata correttamente");
	}

	public PuntoEmissione getById(UUID id) {
		PuntoEmissione found = em.find(PuntoEmissione.class, id);

		if (found == null) {
			log.info("");
		}

		return found;
	}

	public void delete(UUID id) {
		PuntoEmissione found = em.find(PuntoEmissione.class, id);
		if (found != null) {
			EntityTransaction t = em.getTransaction();
			t.begin();
			em.remove(found);
			t.commit();
			log.info(found.toString() + " è stato eliminato");
		} else {
			log.info(found.toString() + " non è presente.");
		}

	}

	public void refresh(UUID id) {
		PuntoEmissione found = em.find(PuntoEmissione.class, id);
		found.setId(id);

		System.out.println("PRE REFRESH");
		System.out.println(found);

		em.refresh(found);
		System.out.println("POST REFRESH");
		System.out.println(found);
	}

	public List<PuntoEmissione> trovaPuntiEmissione() {
		TypedQuery<PuntoEmissione> puntiDiEmissione = em.createQuery("SELECT p FROM PuntoEmissione p",
				PuntoEmissione.class);
		return puntiDiEmissione.getResultList();
	}
}
