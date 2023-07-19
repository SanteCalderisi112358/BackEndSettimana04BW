package BWdao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
		log.info(mezzi + " è stata salvata correttamente");
	}

	public Mezzi getById(UUID id) {
		Mezzi mezzi = em.find(Mezzi.class, id);

		if (mezzi == null) {
			log.info("");
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
			log.info(mezzi.toString() + " non è presente.");
		}

	}

	public void refresh(Mezzi mezzi) {
	    mezzi = em.merge(mezzi);
	    em.refresh(mezzi);
	}

	public List<Mezzi> trovaPerTratta(Tratta tratta) {
	    TypedQuery<Mezzi> query = em.createQuery("SELECT m FROM Mezzi m WHERE m.tratta.id = :trattaId", Mezzi.class);
	    query.setParameter("trattaId", tratta.getId());
	    return query.getResultList();
	}


}
