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
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

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

	public void statisticheTempo(String targa) {
		try {
			TypedQuery<Double> tEffettivoPerMezzoQuery = em
					.createQuery("SELECT teffettivo FROM storicotratte WHERE mezzo_targa LIKE :targa", Double.class);
			tEffettivoPerMezzoQuery.setParameter("targa", targa);
			double tEffettivoPerMezzo = tEffettivoPerMezzoQuery.getSingleResult();

			TypedQuery<Long> totaleQuery = em.createQuery("SELECT COUNT(teffettivo) FROM storicotratte", Long.class);
			long totale = totaleQuery.getSingleResult();

			TypedQuery<Double> sommaQuery = em.createQuery("SELECT SUM(teffettivo) FROM storicotratte", Double.class);
			double sommaDouble = sommaQuery.getSingleResult();

			double media = sommaDouble / totale;

			if (tEffettivoPerMezzo > media) {
				System.err.println("Il mezzo con targa " + targa + " impiega più tempo di tutti");
			} else {
				System.err.println("Il mezzo con targa " + targa + " impiega meno tempo di tutti");
			}
		} catch (NoResultException ex) {
			System.err.println("Nessun risultato trovato per la targa " + targa);
		} catch (Exception ex) {
			System.err.println("Errore: " + ex.getMessage());
		}
	}

}
