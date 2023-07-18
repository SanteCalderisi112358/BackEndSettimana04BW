package BWdao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import BWentities.Manutenzione;

public class ManutenzioneDao {

	private final EntityManager em;
	private static Logger log = LoggerFactory.getLogger(ManutenzioneDao.class);

	public ManutenzioneDao(EntityManager em) {
		this.em = em;
	}

	public void save(Manutenzione s) {
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(s);
		t.commit();
		log.info(s + " è stata salvata correttamente");
	}

	public Manutenzione getById(UUID idManutenzione) {
		Manutenzione found = em.find(Manutenzione.class, idManutenzione);

		if (found == null) {
			log.info("");
		}

		return found;
	}

	public void delete(UUID idManutenzione) {
		Manutenzione found = em.find(Manutenzione.class, idManutenzione);
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

	/*
	 * public void refresh(UUID id) { Manutenzione found =
	 * em.find(Manutenzione.class, id); found.setId(id);
	 * 
	 * System.out.println("PRE REFRESH"); System.out.println(found);
	 * 
	 * em.refresh(found); System.out.println("POST REFRESH");
	 * System.out.println(found); }
	 */
}
