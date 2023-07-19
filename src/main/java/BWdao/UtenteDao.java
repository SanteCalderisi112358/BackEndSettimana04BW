package BWdao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import BWentities.Utente;

public class UtenteDao {

	private final EntityManager em;
	private static Logger log = LoggerFactory.getLogger(UtenteDao.class);

	public UtenteDao(EntityManager em) {
		this.em = em;
	}

	public void save(Utente s) {
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(s);
		t.commit();
		log.info(s + " è stato salvato correttamente\n");
	}

	public Utente getById(UUID id) {
		Utente found = em.find(Utente.class, id);

		if (found == null) {
			log.info("");
		}

		return found;
	}

	public void delete(UUID id) {
		Utente found = em.find(Utente.class, id);
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
		Utente found = em.find(Utente.class, id);
		found.setCodiceFiscale(id);
		;

		System.out.println("PRE REFRESH");
		System.out.println(found);

		em.refresh(found);
		System.out.println("POST REFRESH");
		System.out.println(found);
	}
}
