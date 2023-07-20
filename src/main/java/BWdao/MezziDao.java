package BWdao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import BWutils.JpaUtil;
import Mezzi.Mezzi;
import Mezzi.Tratta;

public class MezziDao {
	private final EntityManager em;
	private static Logger log = LoggerFactory.getLogger(MezziDao.class);

	public MezziDao(EntityManager em) {
		this.em = em;
	}

	public void save(Mezzi mezzi) {
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(mezzi);
		t.commit();
		log.info(mezzi + " è stato salvato correttamente");
	}

	public Mezzi getById(String targa) {
		Mezzi mezzi = em.find(Mezzi.class, targa);

		if (mezzi == null) {
			log.info("Mezzo non trovato con l'ID: " + targa);
		}

		return mezzi;
	}

	public void delete(UUID id) {
		Mezzi mezzi = em.find(Mezzi.class, id);
		if (mezzi != null) {
			EntityTransaction t = em.getTransaction();
			t.begin();
			em.remove(mezzi);
			t.commit();
			log.info(mezzi.toString() + " è stato eliminato");
		} else {
			log.info("Mezzo non trovato con l'ID: " + id);
		}

	}

	public void refresh(Mezzi mezzi) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {

			em.refresh(mezzi);

		} finally {
			em.close();
		}

	}

	public List<Mezzi> trovaPerTratta(Tratta tratta) {
		TypedQuery<Mezzi> query = em.createQuery("SELECT m FROM Mezzi m WHERE m.tratta.id = :trattaId", Mezzi.class);
		query.setParameter("trattaId", tratta);
		return query.getResultList();
	}
}