package BWdao;


import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import Mezzi.StoricoTratte;


public class StoricoTratteDao {
	private final EntityManager em;
	private static Logger log = LoggerFactory.getLogger(StoricoTratteDao.class);

	public StoricoTratteDao(EntityManager em) {
		this.em = em;
	}

	public void save(StoricoTratte storicoTratte) {
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(storicoTratte);
		t.commit();
		log.info(storicoTratte + " è stata salvata correttamente");
	}

	public StoricoTratte getById(UUID id) {
		StoricoTratte storicoTratte = em.find(StoricoTratte.class, id);

		if (storicoTratte == null) {
			log.info("");
		}

		return storicoTratte;
	}

	public void delete(UUID id) {
		StoricoTratte storicoTratte = em.find(StoricoTratte.class, id);
		if (storicoTratte != null) {
			EntityTransaction t = em.getTransaction();
			t.begin();
			em.remove(storicoTratte);
			t.commit();
			log.info(storicoTratte.toString() + " è stato eliminato");
		} else {
			log.info(storicoTratte.toString() + " non è presente.");
		}

	}

	public void refresh(StoricoTratte storicoTratte) {
		storicoTratte = em.merge(storicoTratte);
	    em.refresh(storicoTratte);
	}



}