//package BWdao;
//
//import java.util.UUID;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityTransaction;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import BWutils.JpaUtil;
//import Mezzi.StoricoTratte;
//
//public class StoricoTratteDao {
//	private final EntityManager em;
//	private static Logger log = LoggerFactory.getLogger(StoricoTratteDao.class);
//
//	public StoricoTratteDao(EntityManager em) {
//		this.em = em;
//	}
//
//	public void save(StoricoTratte storicoTratte) {
//		EntityTransaction t = em.getTransaction();
//		t.begin();
//		em.persist(storicoTratte);
//		t.commit();
//		log.info(storicoTratte + " è stato salvato correttamente");
//	}
//
//	public StoricoTratte getById(UUID id) {
//		StoricoTratte storicoTratte = em.find(StoricoTratte.class, id);
//
//		if (storicoTratte == null) {
//			log.info("Storico Tratte non trovato con l'ID: " + id);
//		}
//
//		return storicoTratte;
//	}
//
//	public void delete(UUID id) {
//		StoricoTratte storicoTratte = em.find(StoricoTratte.class, id);
//		if (storicoTratte != null) {
//			EntityTransaction t = em.getTransaction();
//			t.begin();
//			em.remove(storicoTratte);
//			t.commit();
//			log.info(storicoTratte.toString() + " è stato eliminato");
//		} else {
//			log.info("Storico Tratte non trovato con l'ID: " + id);
//		}
//
//	}
//
//	public void refresh(StoricoTratte storicoTratte) {
//		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
//		try {
//
//			em.refresh(storicoTratte);
//
//		} finally {
//			em.close();
//		}
//
//	}
//}

package BWdao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import BWutils.JpaUtil;
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
		log.info(storicoTratte + " è stato salvato correttamente");
	}

	public StoricoTratte getById(UUID id) {
		StoricoTratte storicoTratte = em.find(StoricoTratte.class, id);

		if (storicoTratte == null) {
			log.info("Storico Tratte non trovato con l'ID: " + id);
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
			log.info("Storico Tratte non trovato con l'ID: " + id);
		}

	}

	public void refresh(StoricoTratte storicoTratte) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {

			em.refresh(storicoTratte);

		} finally {
			em.close();
		}

	}
}
