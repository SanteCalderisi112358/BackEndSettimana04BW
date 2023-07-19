package BW;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import BWdao.MezzoDao;
import BWdao.PuntoEmissioneDao;
import BWdao.TesseraDao;
import BWdao.TicketDao;
import BWdao.UtenteDao;
import BWentities.Biglietto;
import BWentities.Mezzo;
import BWentities.RivenditoreAutorizzato;
import BWentities.Tessera;
import BWentities.Utente;
import BWenum.StatoMezzo;
import BWenum.TipoMezzo;
import BWutils.JpaUtil;

public class GestioneAziendaTrasporto {
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {

		EntityManager em = emf.createEntityManager();

		UtenteDao utenteDao = new UtenteDao(em);
		TesseraDao tesseraDao = new TesseraDao(em);
		TicketDao ticketDao = new TicketDao(em);
		MezzoDao mezzodao = new MezzoDao(em);
		PuntoEmissioneDao puntoEmissioneDao = new PuntoEmissioneDao(em);


		/* Creazione e salvataggio nuovi utenti nel DB */
//		Utente sante = new Utente("Sante", "Calderisi");
//		utenteDao.save(sante);
		Utente erika = new Utente("Erika", "Quitadamo");
		Utente andrea = new Utente("Andrea", "Loto");
		Utente giulio = new Utente("Giulio", "Di Carlo");
		Utente laura = new Utente("Laura", "Zazzera");
//		utenteDao.save(erika);
//		utenteDao.save(giulio);
//		utenteDao.save(andrea);
		utenteDao.save(laura);

		/* Creazione e salvataggio nuove tessere nel DB */
//		Tessera tesseraSante = new Tessera("2023-02-02", sante);
//		tesseraDao.save(tesseraSante);
		
		Tessera tesseraAndrea = new Tessera(LocalDate.of(2023, 6, 12), andrea);
		Tessera tesseraErika = new Tessera(LocalDate.of(2022, 5, 23), erika);
		Tessera tesseraGiulio = new Tessera(LocalDate.of(2020, 12, 7), giulio);
		Tessera tesseraLaura = new Tessera(LocalDate.of(2023, 8, 4), laura);
//		tesseraDao.save(tesseraAndrea);
//		tesseraDao.save(tesseraErika);
//		tesseraDao.save(tesseraGiulio);
		tesseraDao.save(tesseraLaura);

		/* Creazione e salvataggio 10 mezzi */
		Mezzo mezzo01 = new Mezzo("AA-432-WE", StatoMezzo.IN_SERVIZIO, TipoMezzo.AUTOBUS, 12);
		mezzo01.setCapienza();
		Mezzo mezzo02 = new Mezzo("AB-321-GF", StatoMezzo.IN_SERVIZIO, TipoMezzo.TRAM, 23);
		mezzo02.setCapienza();
		Mezzo mezzo03 = new Mezzo("ER-456-HG", StatoMezzo.IN_SERVIZIO, TipoMezzo.TRAM, 34);
		mezzo03.setCapienza();
		Mezzo mezzo04 = new Mezzo("NB-123-HG", StatoMezzo.IN_MANUTENZIONE, TipoMezzo.AUTOBUS, 65);
		mezzo04.setCapienza();
		Mezzo mezzo05 = new Mezzo("NH-765-DF", StatoMezzo.IN_SERVIZIO, TipoMezzo.AUTOBUS, 7);
		mezzo05.setCapienza();
		Mezzo mezzo06 = new Mezzo("KJ-378-WE", StatoMezzo.IN_SERVIZIO, TipoMezzo.TRAM, 23);
		mezzo06.setCapienza();
		Mezzo mezzo07 = new Mezzo("JK-098-IO", StatoMezzo.IN_MANUTENZIONE, TipoMezzo.AUTOBUS, 13);
		mezzo07.setCapienza();
		Mezzo mezzo08 = new Mezzo("JK-766-JK", StatoMezzo.IN_SERVIZIO, TipoMezzo.AUTOBUS, 4);
		mezzo08.setCapienza();
		Mezzo mezzo09 = new Mezzo("GT-432-WE", StatoMezzo.IN_SERVIZIO, TipoMezzo.TRAM, 45);
		mezzo09.setCapienza();
		Mezzo mezzo10 = new Mezzo("YU-566-UI", StatoMezzo.IN_MANUTENZIONE, TipoMezzo.AUTOBUS, 56);
		mezzo10.setCapienza();
		Mezzo mezzo11 = new Mezzo("IU-879-DF", StatoMezzo.IN_SERVIZIO, TipoMezzo.TRAM, 32);
		mezzo11.setCapienza();

//		mezzodao.save(mezzo01);
//		mezzodao.save(mezzo02);
//		mezzodao.save(mezzo03);
//		mezzodao.save(mezzo04);
//		mezzodao.save(mezzo05);
//		mezzodao.save(mezzo06);
//		mezzodao.save(mezzo07);
//		mezzodao.save(mezzo08);
//		mezzodao.save(mezzo09);
//		mezzodao.save(mezzo10);
//		mezzodao.save(mezzo11);


		/* Creazione rivenditore autorizzato */

		RivenditoreAutorizzato rivenditoreAutorizzato01 = new RivenditoreAutorizzato(
				"Via Sandro PErtini, 26 - Vieste(FG)", "Tabaccheria Ciao Mondo");

		// puntoEmissioneDao.save(rivenditoreAutorizzato01);
		/* Creazione Biglietti */

		Biglietto biglietto01 = new Biglietto("2023-07-19", tesseraLaura, rivenditoreAutorizzato01);
		// ticketDao.save(biglietto01);
		timbrato(UUID.fromString("5103910d-f7d6-4cc5-93f7-6d8b1f8ee9fa"), "AA-432-WE", ticketDao, mezzodao, em,
				tesseraLaura);

		try {
			Scanner input = new Scanner(System.in);
			int scelta = -100;
			do {
				System.out.println("Benvenuto");
				System.out.println("Digita:");
				System.out.println("1 - Se vuoi diventare un nostro nuovo tesserato");
				System.out.println("0 - Se vuoi uscire dal nostro sistema");

				try {
					scelta = Integer.parseInt(input.nextLine());
				} catch (NumberFormatException ex) {
					System.out.println("Inserisci un numero valido.");
					continue;
				}

				switch (scelta) {
				case 1:
					System.out.println("Inserisci il tuo nome");
					String nome = input.nextLine();
					System.out.println("Inserisci il tuo cognome");
					String cognome = input.nextLine();
					Utente nuovoUtente = new Utente(nome, cognome);
					utenteDao.save(nuovoUtente);
					Tessera tesseraNuovoUtente = new Tessera(LocalDate.now(), nuovoUtente);
					tesseraDao.save(tesseraNuovoUtente);
					break;
				case 0:
					System.out.println("Arrivederci!");
					break;
				default:
					System.out.println("Scelta non valida. Riprova.");
				}

			} while (scelta != 0);
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void timbrato(UUID idBiglietto, String targa, TicketDao ticketDao, MezzoDao mezzodao,
			EntityManager em, Tessera tessera) {
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
		Biglietto bigliettoTimbrato = (Biglietto) ticketDao.getById(idBiglietto);
		Mezzo mezzoCheTimbra = mezzodao.getById(targa);
		bigliettoTimbrato.setTimbrato(true);
		bigliettoTimbrato.setMezzo(mezzoCheTimbra);
		t.commit();
		System.err.println(bigliettoTimbrato + "è stato obliterato il giorno "
				+ LocalDate.now() + "sul mezzo " + mezzoCheTimbra);
	} catch (Exception ex) {
		ex.printStackTrace();
	}

	}
}
