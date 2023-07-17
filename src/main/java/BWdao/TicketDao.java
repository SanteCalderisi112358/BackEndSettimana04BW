package BWdao;


import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import BWentities.Ticket;
import BWutils.JpaUtil;

public class TicketDao {
	private final EntityManager em;
	private static Logger log = LoggerFactory.getLogger(TicketDao.class);

	public TicketDao(EntityManager em) {
		this.em = em;
	}

	public void save(Ticket ticket) {
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(ticket);
		t.commit();
		log.info(ticket + " è stata salvata correttamente");
	}

	public Ticket getById(UUID id) {
		Ticket ticket = em.find(Ticket.class, id);

		if (ticket == null) {
			log.info("");
		}

		return ticket;
	}

	public void delete(UUID id) {
		Ticket ticket = em.find(Ticket.class, id);
		if (ticket != null) {
			EntityTransaction t = em.getTransaction();
			t.begin();
			em.remove(ticket);
			t.commit();
			log.info(ticket.toString() + " è stato eliminato");
		} else {
			log.info(ticket.toString() + " non è presente.");
		}

	}

	public void refresh(Ticket ticket) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {

			em.refresh(ticket);

		} finally {
			em.close();
		}

	}
}
