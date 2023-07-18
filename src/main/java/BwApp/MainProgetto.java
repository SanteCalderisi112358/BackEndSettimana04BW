package BwApp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import BWdao.TesseraDao;
import BWdao.UtenteDao;
import BWentities.Utente;
import BWutils.JpaUtil;

public class MainProgetto {
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();
		System.out.println("DATABASE COLLEGATO CON SUCCESSO!!!!");

		UtenteDao utenteDao = new UtenteDao(em);

		TesseraDao tesseraDao = new TesseraDao(em);

		Utente utente1 = new Utente("Luca", "Guerra");

		// Tessera tessera1 = new Tessera("2023-02-02", utente1);

		utenteDao.save(utente1);
		// tesseraDao.save(tessera1);
		// utente1.setTessera(tessera1);

		System.out.println("ciao");

		// CHIUSURA
		em.close();
		emf.close();
	}

}
