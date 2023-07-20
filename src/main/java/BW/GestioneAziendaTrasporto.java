package BW;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import BWdao.ManutenzioneDao;
import BWdao.MezziDao;
import BWdao.PuntoEmissioneDao;
import BWdao.StoricoTratteDao;
import BWdao.TesseraDao;
import BWdao.TicketDao;
import BWdao.TrattaDao;
import BWdao.UtenteDao;
import BWentities.Abbonamento;
import BWentities.Biglietto;
import BWentities.DistributoreAutomatico;
import BWentities.RivenditoreAutorizzato;
import BWentities.Tessera;
import BWentities.Utente;
import BWenum.StatoMezzo;
import BWenum.StatoServizio;
import BWenum.TipoAbbonamento;
import BWenum.TipoMezzo;
import BWutils.JpaUtil;
import Mezzi.Mezzi;

public class GestioneAziendaTrasporto {
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {

		EntityManager em = emf.createEntityManager();

		UtenteDao utenteDao = new UtenteDao(em);
		TesseraDao tesseraDao = new TesseraDao(em);
		TicketDao ticketDao = new TicketDao(em);
		MezziDao mezzidao = new MezziDao(em);
		PuntoEmissioneDao puntoEmissioneDao = new PuntoEmissioneDao(em);
		StoricoTratteDao storicoTratteDao = new StoricoTratteDao(em);
		ManutenzioneDao manutenzioneDao = new ManutenzioneDao(em);
		TrattaDao trattaDao = new TrattaDao(em);



		/* CREZIONE UTENTI E SALVATAGGIO */

		Utente sante = new Utente("Sante", "Calderisi");
		Utente ivan = new Utente("Ivan", "Iasnig");
		Utente luca = new Utente("Luca", "Guerra");
		Utente marco = new Utente("Marco", "Tumminia");
		Utente erika = new Utente("Erika", "Quitadamo");
		Utente andrea = new Utente("Andrea", "Loto");
		Utente giulio = new Utente("Giulio", "Di Carlo");
		Utente laura = new Utente("Laura", "Zazzera");
		utenteDao.save(erika);
		utenteDao.save(giulio);
		utenteDao.save(andrea);
		utenteDao.save(laura);
		utenteDao.save(sante);
		utenteDao.save(ivan);
		utenteDao.save(luca);
		utenteDao.save(marco);


		/* CREAZIONE TESSERE E SALVATAGGIO */

		Tessera tesseraSante = new Tessera(LocalDate.now(), sante);
		Tessera tesseraAndrea = new Tessera(LocalDate.of(2023, 6, 12), andrea);
		Tessera tesseraErika = new Tessera(LocalDate.of(2022, 5, 23), erika);
		Tessera tesseraGiulio = new Tessera(LocalDate.of(2020, 12, 7), giulio);
		Tessera tesseraIvan = new Tessera(LocalDate.of(2023, 5, 4), laura);
		Tessera tesseraLuca = new Tessera(LocalDate.of(2022, 9, 14), laura);
		Tessera tesseraMarco = new Tessera(LocalDate.of(2021, 6, 4), laura);
		Tessera tesseraLaura = new Tessera(LocalDate.of(2023, 4, 2), laura);

		tesseraDao.save(tesseraAndrea);
		tesseraDao.save(tesseraErika);
		tesseraDao.save(tesseraGiulio);
		tesseraDao.save(tesseraLaura);
		tesseraDao.save(tesseraSante);
		tesseraDao.save(tesseraIvan);
		tesseraDao.save(tesseraLuca);
		tesseraDao.save(tesseraMarco);

		/* CREZIONE MEZZI E SALVATAGGIO */

		Mezzi mezzo01 = new Mezzi("AH-746-HS", StatoMezzo.IN_SERVIZIO, TipoMezzo.AUTOBUS, 5);
		mezzo01.setCapienzaMezzo();
		Mezzi mezzo02 = new Mezzi("AB-321-GF", StatoMezzo.IN_SERVIZIO, TipoMezzo.TRAM, 3);
		mezzo02.setCapienzaMezzo();
		Mezzi mezzo03 = new Mezzi("ER-456-HG", StatoMezzo.IN_SERVIZIO, TipoMezzo.TRAM, 34);
		mezzo03.setCapienzaMezzo();
		Mezzi mezzo04 = new Mezzi("NB-123-HG", StatoMezzo.IN_MANUTENZIONE, TipoMezzo.AUTOBUS, 65);
		mezzo04.setCapienzaMezzo();
		Mezzi mezzo05 = new Mezzi("NH-765-DF", StatoMezzo.IN_SERVIZIO, TipoMezzo.AUTOBUS, 7);
		mezzo05.setCapienzaMezzo();
		Mezzi mezzo06 = new Mezzi("KJ-378-WE", StatoMezzo.IN_SERVIZIO, TipoMezzo.TRAM, 23);
		mezzo06.setCapienzaMezzo();
		Mezzi mezzo07 = new Mezzi("JK-098-IO", StatoMezzo.IN_MANUTENZIONE, TipoMezzo.AUTOBUS, 13);
		mezzo07.setCapienzaMezzo();
		Mezzi mezzo08 = new Mezzi("JK-766-JK", StatoMezzo.IN_SERVIZIO, TipoMezzo.AUTOBUS, 4);
		mezzo08.setCapienzaMezzo();
		Mezzi mezzo09 = new Mezzi("GT-432-WE", StatoMezzo.IN_SERVIZIO, TipoMezzo.TRAM, 45);
		mezzo09.setCapienzaMezzo();
		Mezzi mezzo10 = new Mezzi("YU-566-UI", StatoMezzo.IN_MANUTENZIONE, TipoMezzo.AUTOBUS, 56);
		mezzo10.setCapienzaMezzo();
		Mezzi mezzo11 = new Mezzi("IU-879-DF", StatoMezzo.IN_SERVIZIO, TipoMezzo.TRAM, 32);
		mezzo11.setCapienzaMezzo();



		mezzidao.save(mezzo01);
		mezzidao.save(mezzo02);
		mezzidao.save(mezzo03);
		mezzidao.save(mezzo04);
		mezzidao.save(mezzo05);
		mezzidao.save(mezzo06);
		mezzidao.save(mezzo07);
		mezzidao.save(mezzo08);
		mezzidao.save(mezzo09);
		mezzidao.save(mezzo10);
		mezzidao.save(mezzo11);


		/* CREAZIONE RIVENDITORI AUTORIZZATI E SALVATAGGIO */

		RivenditoreAutorizzato rivenditoreAutorizzato01 = new RivenditoreAutorizzato(
				"Via Sandro Pertini, 26", "Tabaccheria 'Ciao Mondo'");
		RivenditoreAutorizzato rivenditoreAutorizzato02 = new RivenditoreAutorizzato("Piazzale Aldo Moro, 2",
				"Bar 'Aldo Moro'");
		RivenditoreAutorizzato rivenditoreAutorizzato03 = new RivenditoreAutorizzato("Via Garibaldi, 5",
				"Tabaccheria 'Area Nuova'");
		RivenditoreAutorizzato rivenditoreAutorizzato04 = new RivenditoreAutorizzato("Corso Liberazione,11",
				"Bar 'Epicode'");
		RivenditoreAutorizzato rivenditoreAutorizzato05 = new RivenditoreAutorizzato("Via Vespi",
				"Tabaccheria 'Boolean'");

		puntoEmissioneDao.save(rivenditoreAutorizzato01);
		puntoEmissioneDao.save(rivenditoreAutorizzato02);
		puntoEmissioneDao.save(rivenditoreAutorizzato03);
		puntoEmissioneDao.save(rivenditoreAutorizzato04);
		puntoEmissioneDao.save(rivenditoreAutorizzato05);


		/* CREAZIONE DISTRIBUTORI AUTOMATICI E SALVATAGGIO */

		DistributoreAutomatico distributoreAutomatico01 = new DistributoreAutomatico("Via Giolitti, 1",
				StatoServizio.ATTIVO);
		DistributoreAutomatico distributoreAutomatico02 = new DistributoreAutomatico("Via Puglia, 2",
				StatoServizio.ATTIVO);
		DistributoreAutomatico distributoreAutomatico03 = new DistributoreAutomatico("Via Trento, 3",
				StatoServizio.FUORI_SERVIZIO);
		DistributoreAutomatico distributoreAutomatico04 = new DistributoreAutomatico("Via Sicilia, 4",
				StatoServizio.ATTIVO);
		DistributoreAutomatico distributoreAutomatico05 = new DistributoreAutomatico("Via Lazio, 5",
				StatoServizio.FUORI_SERVIZIO);
		puntoEmissioneDao.save(distributoreAutomatico01);
		puntoEmissioneDao.save(distributoreAutomatico02);
		puntoEmissioneDao.save(distributoreAutomatico03);
		puntoEmissioneDao.save(distributoreAutomatico04);
		puntoEmissioneDao.save(distributoreAutomatico05);

		/* CREAZIONE BIGLIETTI E SALVATAGGIO */

		Biglietto biglietto01 = new Biglietto(LocalDate.of(2021, 11, 3), tesseraLaura, rivenditoreAutorizzato01);
		Biglietto biglietto02 = new Biglietto(LocalDate.of(2022, 2, 4), tesseraSante, distributoreAutomatico02);
		Biglietto biglietto03 = new Biglietto(LocalDate.of(2023, 10, 13), tesseraLaura, rivenditoreAutorizzato03);
		Biglietto biglietto04 = new Biglietto(LocalDate.of(2023, 9, 3), tesseraAndrea, distributoreAutomatico04);
		Biglietto biglietto05 = new Biglietto(LocalDate.of(2022, 7, 3), tesseraErika, rivenditoreAutorizzato03);
		Biglietto biglietto06 = new Biglietto(LocalDate.of(2021, 1, 13), tesseraLaura, rivenditoreAutorizzato02);

		ticketDao.save(biglietto01);
		ticketDao.save(biglietto02);
		ticketDao.save(biglietto03);
		ticketDao.save(biglietto04);
		ticketDao.save(biglietto05);
		ticketDao.save(biglietto06);

		/* CREAZIONE ABBONAMENTI E SALVATAGGIO */

		Abbonamento abbonamento01 = new Abbonamento(LocalDate.now(), tesseraLaura, distributoreAutomatico05,
				TipoAbbonamento.MENSILE);
		abbonamento01.setScadenza();
		Abbonamento abbonamento02 = new Abbonamento(LocalDate.of(2023, 11, 8), tesseraErika, distributoreAutomatico04,
				TipoAbbonamento.MENSILE);
		abbonamento02.setScadenza();
		Abbonamento abbonamento03 = new Abbonamento(LocalDate.of(2021, 7, 9), tesseraSante, distributoreAutomatico03,
				TipoAbbonamento.MENSILE);
		abbonamento03.setScadenza();
		Abbonamento abbonamento04 = new Abbonamento(LocalDate.of(2022, 10, 11), tesseraGiulio, distributoreAutomatico05,
				TipoAbbonamento.SETTIMANALE);
		abbonamento04.setScadenza();
		Abbonamento abbonamento05 = new Abbonamento(LocalDate.of(2023, 4, 11), tesseraIvan, rivenditoreAutorizzato01,
				TipoAbbonamento.SETTIMANALE);
		abbonamento05.setScadenza();
		Abbonamento abbonamento06 = new Abbonamento(LocalDate.now(), tesseraMarco, rivenditoreAutorizzato01,
				TipoAbbonamento.MENSILE);
		abbonamento06.setScadenza();

		ticketDao.save(abbonamento01);
		ticketDao.save(abbonamento02);
		ticketDao.save(abbonamento03);
		ticketDao.save(abbonamento04);
		ticketDao.save(abbonamento05);
		ticketDao.save(abbonamento06);

		/* METODO PER OBLITERARE UN BIGLIETTO */
//		timbrato(UUID.fromString("5103910d-f7d6-4cc5-93f7-6d8b1f8ee9fa"), "AA-432-WE", ticketDao, Mezzidao, em,
//				tesseraLaura);

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

	public static void timbrato(UUID idBiglietto, String targa, TicketDao ticketDao, MezziDao Mezzidao,
			EntityManager em, Tessera tessera) {
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			Biglietto bigliettoTimbrato = (Biglietto) ticketDao.getById(idBiglietto);
			Mezzi MezziCheTimbra = Mezzidao.getById(targa);
			bigliettoTimbrato.setTimbrato(true);
			bigliettoTimbrato.setMezzo(MezziCheTimbra);
			t.commit();
			System.err.println(bigliettoTimbrato + "è stato obliterato il giorno " + LocalDate.now() + "sul Mezzi "
					+ MezziCheTimbra);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
