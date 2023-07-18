package BWdao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import BWentities.Tessera;

public class TesseraDao {

	private final EntityManager em;
	private static Logger log = LoggerFactory.getLogger(TesseraDao.class);

	public TesseraDao(EntityManager em) {
		this.em = em;
	}

	public void save(Tessera s) {
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(s);
		t.commit();
		log.info(s + " è stata salvata correttamente");
	}

	public Tessera getById(UUID id) {
		Tessera found = em.find(Tessera.class, id);

		if (found == null) {
			log.info("");
		}

		return found;
	}

	public void delete(UUID id) {
		Tessera found = em.find(Tessera.class, id);
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
		Tessera found = em.find(Tessera.class, id);
		found.setNumeroTessera(id);

		System.out.println("PRE REFRESH");
		System.out.println(found);

		em.refresh(found);
		System.out.println("POST REFRESH");
		System.out.println(found);
	}
}
