package BWdao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import BWutils.JpaUtil;
import Mezzi.Manutenzione;

public class ManutenzioneDao {
	private final EntityManager em;
	private static Logger log = LoggerFactory.getLogger(ManutenzioneDao.class);

	public ManutenzioneDao(EntityManager em) {
		this.em = em;
	}

	public void save(Manutenzione manutenzione) {
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(manutenzione);
		t.commit();
		log.info(manutenzione + " è stata salvata correttamente");
	}

	public Manutenzione getById(UUID id) {
		Manutenzione manutenzione = em.find(Manutenzione.class, id);

		if (manutenzione == null) {
			log.info("Manutenzione non trovata con l'ID: " + id);
		}

		return manutenzione;
	}

	public void delete(UUID id) {
		Manutenzione manutenzione = em.find(Manutenzione.class, id);
		if (manutenzione != null) {
			EntityTransaction t = em.getTransaction();
			t.begin();
			em.remove(manutenzione);
			t.commit();
			log.info(manutenzione.toString() + " è stata eliminata");
		} else {
			log.info("Manutenzione non trovata con l'ID: " + id);
		}

	}

	public void refresh(Manutenzione manutenzione) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {

			em.refresh(manutenzione);

		} finally {
			em.close();
		}

	}

	public List<Manutenzione> trovaPerMezzo(UUID mezzoId) {
		TypedQuery<Manutenzione> query = em.createQuery("SELECT m FROM Manutenzione m WHERE m.mezzo.id = :mezzoId",
				Manutenzione.class);
		query.setParameter("mezzoId", mezzoId);
		return query.getResultList();
	}
}