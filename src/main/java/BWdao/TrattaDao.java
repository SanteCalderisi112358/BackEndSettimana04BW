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

public class TrattaDao {
	private final EntityManager em;
	private static Logger log = LoggerFactory.getLogger(TrattaDao.class);

	public TrattaDao(EntityManager em) {
		this.em = em;
	}

	public void save(Tratta tratta) {
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(tratta);
		t.commit();
		log.info(tratta + " è stata salvata correttamente");
	}

	public Tratta getById(UUID id) {
		Tratta tratta = em.find(Tratta.class, id);

		if (tratta == null) {
			log.info("");
		}

		return tratta;
	}

	public void delete(UUID id) {
		Tratta tratta = em.find(Tratta.class, id);
		if (tratta != null) {
			EntityTransaction t = em.getTransaction();
			t.begin();
			em.remove(tratta);
			t.commit();
			log.info(tratta.toString() + " è stato eliminato");
		} else {
			log.info(tratta.toString() + " non è presente.");
		}

	}

	public void refresh(Tratta Tratta) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {

			em.refresh(Tratta);

		} finally {
			em.close();
		}

	}
	



}