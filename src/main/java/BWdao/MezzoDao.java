package BWdao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import BWentities.Mezzo;

public class MezzoDao {
	private final EntityManager em;
	private static Logger log = LoggerFactory.getLogger(TesseraDao.class);

	public MezzoDao(EntityManager em) {
		this.em = em;
	}

	public void save(Mezzo mezzo) {
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(mezzo);
		t.commit();
		log.info(mezzo + " è stata salvata correttamente");
	}

	public Mezzo getById(String targa) {
		Mezzo found = em.find(Mezzo.class, targa);

		if (found == null) {
			log.info("Il mezzo è stato trovato");
		}

		return found;
	}

	public void delete(UUID id) {
		Mezzo found = em.find(Mezzo.class, id);
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
		Mezzo found = em.find(Mezzo.class, id);
		found.setTarga(null);
		;

		System.out.println("PRE REFRESH");
		System.out.println(found);

		em.refresh(found);
		System.out.println("POST REFRESH");
		System.out.println(found);
	}
}
