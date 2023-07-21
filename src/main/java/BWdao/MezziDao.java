//package BWdao;
//
//import java.util.List;
//import java.util.UUID;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityTransaction;
//import javax.persistence.TypedQuery;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import Mezzi.Mezzi;
//
//public class MezziDao {
//	private final EntityManager em;
//	private static Logger log = LoggerFactory.getLogger(MezziDao.class);
//
//	public MezziDao(EntityManager em) {
//		this.em = em;
//	}
//
//	public void save(Mezzi mezzi) {
//		EntityTransaction t = em.getTransaction();
//		t.begin();
//		em.persist(mezzi);
//		t.commit();
//		log.info(mezzi + " è stato salvato correttamente");
//	}
//
//	public Mezzi getById(String targa) {
//		Mezzi mezzi = em.find(Mezzi.class, targa);
//
//		if (mezzi == null) {
//			log.info("Mezzo non trovato con l'ID: " + targa);
//		}
//
//		return mezzi;
//	}
//
//	public void delete(UUID id) {
//		Mezzi mezzi = em.find(Mezzi.class, id);
//		if (mezzi != null) {
//			EntityTransaction t = em.getTransaction();
//			t.begin();
//			em.remove(mezzi);
//			t.commit();
//			log.info(mezzi.toString() + " è stato eliminato");
//		} else {
//			log.info("Mezzo non trovato con l'ID: " + id);
//		}
//
//	}
//
//	public void refresh(Mezzi mezzi) {
//		mezzi = em.merge(mezzi);
//		em.refresh(mezzi);
//
//	}
//
//	public List<Mezzi> trovaPerTratta(UUID idTratta) {
//		TypedQuery<Mezzi> query = em.createQuery("SELECT m FROM Mezzi m WHERE m.tratta.id = :idTratta", Mezzi.class);
//		query.setParameter("idTratta", idTratta);
//		return query.getResultList();
//	}
//}

package BWdao;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import BWenum.StatoMezzo;
import BWenum.TipoMezzo;
import Mezzi.Mezzi;

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
		mezzi = em.merge(mezzi);
		em.refresh(mezzi);

	}

	// trova i mezzi in base alla tratta
//	public List<Mezzi> trovaPerTratta(UUID idTratta) {
//		TypedQuery<Mezzi> query = em.createQuery("SELECT m FROM Mezzi m WHERE m.tratta.id = :idTratta", Mezzi.class);
//		query.setParameter("idTratta", idTratta);
//		return query.getResultList();
//	}
	public List<Mezzi> trovaPerTratta(UUID idTratta) {
		if (idTratta == null) {
			throw new IllegalArgumentException("ID Tratta non può essere nullo");

		}

		TypedQuery<Mezzi> query = em.createQuery("SELECT m FROM Mezzi m WHERE m.tratta.id = :idTratta", Mezzi.class);
		query.setParameter("idTratta", idTratta);

		try {
			return query.getResultList();
		} catch (Exception e) {
			System.err.println(
					"Si è verificato un errore durante il recupero dei mezzi per la tratta con ID: " + idTratta);
			e.printStackTrace();
			return Collections.emptyList();
		}
	}


//conta i mezzi in base al loro tipo di mezzo
	public Long countByTipoMezzo(TipoMezzo tipoMezzo) {
		TypedQuery<Long> query = em.createQuery("SELECT COUNT(m) FROM Mezzi m WHERE m.tipoMezzo = :tipo", Long.class);
		query.setParameter("tipo", tipoMezzo);
		return query.getSingleResult();
	}

	// conta i mezzi in base al loro stato
	public Long countByStato(StatoMezzo statoMezzo) {
		TypedQuery<Long> query = em.createQuery("SELECT COUNT(m) FROM Mezzi m WHERE m.statoMezzo = :stato", Long.class);
		query.setParameter("stato", statoMezzo);
		return query.getSingleResult();
	}

	// cambia stato
	public void cambiaStato(String targa) {
		EntityTransaction t = em.getTransaction();
		t.begin();
		Mezzi bot = getById(targa);
		if (bot.getStatoMezzo() != StatoMezzo.IN_SERVIZIO) {
			bot.setStatoMezzo(StatoMezzo.IN_SERVIZIO);
		} else {
			bot.setStatoMezzo(StatoMezzo.IN_MANUTENZIONE);
		}
		t.commit();
		System.err.println("Stato del mezzo " + targa + " cambiato correttamente!");
	}

	// cambia la capienza
	public void cambiaCapienza(String targa, int nuovaCapienza) {
		EntityTransaction t = em.getTransaction();
		t.begin();
		Mezzi mezzo = getById(targa);
		mezzo.setCapienza(nuovaCapienza);
		t.commit();
		System.err.println("La capienza del mezzo con targa " + targa + " è stata cambiata correttamente!");
	}

}