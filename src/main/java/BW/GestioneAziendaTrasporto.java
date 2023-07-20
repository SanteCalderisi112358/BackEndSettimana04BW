package BW;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import BWdao.ManutenzioneDao;
import BWdao.MezziDao;
import BWdao.PuntoEmissioneDao;
import BWdao.StoricoTratteDao;
import BWdao.TesseraDao;
import BWdao.TicketDao;
import BWdao.TrattaDao;
import BWdao.UtenteDao;
import BWentities.Biglietto;
import BWentities.RivenditoreAutorizzato;
import BWentities.Tessera;
import BWentities.Utente;
import BWenum.StatoMezzo;
import BWenum.TipoMezzo;
import BWutils.JpaUtil;
import Mezzi.Manutenzione;
import Mezzi.Mezzi;
import Mezzi.StoricoTratte;
import Mezzi.Tratta;


public class GestioneAziendaTrasporto {
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {

		EntityManager em = emf.createEntityManager();

		// IstanziamentoDao
		UtenteDao utenteDao = new UtenteDao(em);
		TesseraDao tesseraDao = new TesseraDao(em);
		TicketDao ticketDao = new TicketDao(em);
		MezziDao mezzoDao = new MezziDao(em);
		TrattaDao trattaDao = new TrattaDao(em);
		ManutenzioneDao manutenzioneDao = new ManutenzioneDao(em);
		StoricoTratteDao storicoTratteDao = new StoricoTratteDao(em);
		PuntoEmissioneDao puntoEmissioneDao = new PuntoEmissioneDao(em);

		/* Creazione tratta */
		Tratta tratta = new Tratta("Milano", "Roma", 500, 1000);
		trattaDao.save(tratta);

		/* Creazione rivenditore autorizzato */
		RivenditoreAutorizzato rivenditoreAutorizzato01 = new RivenditoreAutorizzato(
				"Via Sandro PErtini, 26 - Vieste(FG)", "Tabaccheria Ciao Mondo");

		puntoEmissioneDao.save(rivenditoreAutorizzato01);

		/* Creazione e salvataggio nuovi utenti nel DB */
		Utente sante = new Utente("Sante", "Calderisi");
		Utente erika = new Utente("Erika", "Quitadamo");
		Utente andrea = new Utente("Andrea", "Loto");
		Utente giulio = new Utente("Giulio", "Di Carlo");
		Utente laura = new Utente("Laura", "Zazzera");
		utenteDao.save(erika);
		utenteDao.save(giulio);
		utenteDao.save(andrea);
		utenteDao.save(laura);
		utenteDao.save(sante);

		/* Creazione tessere */
		Tessera tesseraSante = new Tessera(LocalDate.of(2023, 6, 12), sante);
		Tessera tesseraAndrea = new Tessera(LocalDate.of(2023, 6, 12), andrea);
		Tessera tesseraErika = new Tessera(LocalDate.of(2022, 5, 23), erika);
		Tessera tesseraGiulio = new Tessera(LocalDate.of(2020, 12, 7), giulio);
		Tessera tesseraLaura = new Tessera(LocalDate.of(2023, 8, 4), laura);
		tesseraDao.save(tesseraAndrea);
		tesseraDao.save(tesseraErika);
		tesseraDao.save(tesseraGiulio);
		tesseraDao.save(tesseraSante);
		tesseraDao.save(tesseraLaura);

		/* Creazione Biglietti */
		Biglietto biglietto01 = new Biglietto(LocalDate.of(2023, 4, 2), tesseraLaura, rivenditoreAutorizzato01);
		ticketDao.save(biglietto01);

		/* Creazione mezzo */
		Mezzi mezzo = new Mezzi("AS-898-DF", StatoMezzo.IN_MANUTENZIONE, TipoMezzo.TRAM, 43);
		mezzo.setTratta(tratta);
		mezzoDao.save(mezzo);

		/* Creazione manutenzione */
		Manutenzione manutenzione = new Manutenzione("Cambio olio", LocalDate.now(), LocalDate.now().plusDays(3), 3,
				mezzo);
		manutenzioneDao.save(manutenzione);

		/* Creazione storicoTratte */
		StoricoTratte storicoTratte = new StoricoTratte(10.5, mezzo);
		StoricoTratte storicoTratte2 = new StoricoTratte(15.5, mezzo);
		StoricoTratte storicoTratte3 = new StoricoTratte(12, mezzo);
		StoricoTratte storicoTratte4 = new StoricoTratte(12.5, mezzo);
		StoricoTratte storicoTratte5 = new StoricoTratte(9.5, mezzo);
		storicoTratteDao.save(storicoTratte);
		storicoTratteDao.save(storicoTratte2);
		storicoTratteDao.save(storicoTratte3);
		storicoTratteDao.save(storicoTratte4);
		storicoTratteDao.save(storicoTratte5);




		/* PROVE VARIE */

		mezzo.getManutenzioni().add(manutenzione);
		mezzoDao.refresh(mezzo);

		List<Mezzi> mezziPerTratta = mezzoDao.trovaPerTratta(tratta.getId());
		System.out.println("Mezzi per la tratta " + tratta.getId() + ": " + mezziPerTratta.size());

		List<Manutenzione> manutenzioniPerMezzo = manutenzioneDao.trovaPerMezzo(mezzo.getTarga());
		System.out.println("Manutenzioni per il mezzo " + mezzo.getTarga() + ": " + manutenzioniPerMezzo.size());

		System.out.println("Tempo medio della tratta per il mezzo " + mezzo.getTarga() + ": "
				+ mezzo.getMediaTempiPercorrenza() + ", ha percorso " + mezzo.getStoricoTratte().size()
				+ " volte la tratta, impiegandoci al massimo " + mezzo.getMaxTempoPercorrenza() + " minuti, e minimo "
				+ mezzo.getMinTempoPercorrenza() + " minuti");



//		timbrato(UUID.fromString("5103910d-f7d6-4cc5-93f7-6d8b1f8ee9fa"), "AA-432-WE", ticketDao, mezzoDao, em,
//				tesseraLaura);

//		try {
//			Scanner input = new Scanner(System.in);
//			int scelta = -100;
//			do {
//				System.out.println("Benvenuto");
//				System.out.println("Digita:");
//				System.out.println("1 - Se vuoi diventare un nostro nuovo tesserato");
//				System.out.println("0 - Se vuoi uscire dal nostro sistema");
//
//				try {
//					scelta = Integer.parseInt(input.nextLine());
//				} catch (NumberFormatException ex) {
//					System.out.println("Inserisci un numero valido.");
//					continue;
//				}
//
//				switch (scelta) {
//				case 1:
//					System.out.println("Inserisci il tuo nome");
//					String nome = input.nextLine();
//					System.out.println("Inserisci il tuo cognome");
//					String cognome = input.nextLine();
//					Utente nuovoUtente = new Utente(nome, cognome);
//					utenteDao.save(nuovoUtente);
//					Tessera tesseraNuovoUtente = new Tessera(LocalDate.now(), nuovoUtente);
//					tesseraDao.save(tesseraNuovoUtente);
//					break;
//				case 0:
//					System.out.println("Arrivederci!");
//					break;
//				default:
//					System.out.println("Scelta non valida. Riprova.");
//				}
//
//			} while (scelta != 0);
//			input.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}

//	public static void timbrato(UUID idBiglietto, String targa, TicketDao ticketDao, MezzoDao mezzodao,
//			EntityManager em, Tessera tessera) {
//		EntityTransaction t = em.getTransaction();
//		try {
//			t.begin();
//		Biglietto bigliettoTimbrato = (Biglietto) ticketDao.getById(idBiglietto);
//		// Mezzo mezzoCheTimbra = mezzodao.getById(targa);
//		bigliettoTimbrato.setTimbrato(true);
//		// bigliettoTimbrato.setMezzo(mezzoCheTimbra);
//		t.commit();
//		System.err.println(bigliettoTimbrato + "è stato obliterato il giorno "
//				+ LocalDate.now() + "sul mezzo " + mezzoCheTimbra);
//	} catch (Exception ex) {
//		ex.printStackTrace();
//	}
//
	}
}