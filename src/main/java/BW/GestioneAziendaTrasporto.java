package BW;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.github.javafaker.Faker;

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
import BWentities.PuntoEmissione;
import BWentities.RivenditoreAutorizzato;
import BWentities.Tessera;
import BWentities.Utente;
import BWenum.StatoMezzo;
import BWenum.StatoServizio;
import BWenum.TipoAbbonamento;
import BWenum.TipoMezzo;
import BWutils.JpaUtil;
import Mezzi.Mezzi;
import Mezzi.Tratta;


public class GestioneAziendaTrasporto {
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {
		Faker f = new Faker(Locale.ITALIAN);
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
		Tratta tratta = new Tratta("Milano", "Roma", 38.4);
		trattaDao.save(tratta);
		Tratta tratta01 = new Tratta("Piazza Carducci", "Corso Giulio Cesare", 10.6);
		trattaDao.save(tratta01);
		/* Creazione rivenditore autorizzato */
		RivenditoreAutorizzato rivenditoreAutorizzato01 = new RivenditoreAutorizzato(
				"Via Sandro Pertini, 26", "Tabaccheria 'Ciao Mondo'");
		RivenditoreAutorizzato rivenditoreAutorizzato02 = new RivenditoreAutorizzato("Via del Corso, 10",
				"Tabaccheria 'Del Corso'");
		RivenditoreAutorizzato rivenditoreAutorizzato03 = new RivenditoreAutorizzato("Via Veneto, 56",
				"Bar 'Grappino'");
		RivenditoreAutorizzato rivenditoreAutorizzato04 = new RivenditoreAutorizzato("Corso Epicode, 4",
				"Centro Info Turistiche");
		RivenditoreAutorizzato rivenditoreAutorizzato05 = new RivenditoreAutorizzato(f.address().fullAddress(),
				"Tabaccheria Ciao Mondo");

//		puntoEmissioneDao.save(rivenditoreAutorizzato01);
//		puntoEmissioneDao.save(rivenditoreAutorizzato02);
//		puntoEmissioneDao.save(rivenditoreAutorizzato03);
//		puntoEmissioneDao.save(rivenditoreAutorizzato04);
//		puntoEmissioneDao.save(rivenditoreAutorizzato05);

		/* Creazione distributori automatici */

		DistributoreAutomatico distributore01 = new DistributoreAutomatico(f.address().fullAddress(),
				StatoServizio.ATTIVO);
		DistributoreAutomatico distributore02 = new DistributoreAutomatico(f.address().fullAddress(),
				StatoServizio.FUORI_SERVIZIO);
		DistributoreAutomatico distributore03 = new DistributoreAutomatico(f.address().fullAddress(),
				StatoServizio.FUORI_SERVIZIO);
		DistributoreAutomatico distributore04 = new DistributoreAutomatico(f.address().fullAddress(),
				StatoServizio.ATTIVO);
		DistributoreAutomatico distributore05 = new DistributoreAutomatico(f.address().fullAddress(),
				StatoServizio.ATTIVO);
		puntoEmissioneDao.save(distributore01);
		puntoEmissioneDao.save(distributore02);
		puntoEmissioneDao.save(distributore03);
		puntoEmissioneDao.save(distributore04);
		puntoEmissioneDao.save(distributore05);

		/* Creazione e salvataggio nuovi utenti nel DB */
		Utente sante = new Utente("Sante", "Calderisi");
		Utente erika = new Utente("Erika", "Quitadamo");
		Utente andrea = new Utente("Andrea", "Loto");
		Utente giulio = new Utente("Giulio", "Di Carlo");
		Utente laura = new Utente("Laura", "Zazzera");
//		utenteDao.save(erika);
//		utenteDao.save(giulio);
//		utenteDao.save(andrea);
//		utenteDao.save(laura);
//		utenteDao.save(sante);

		/* Creazione tessere */
		Tessera tesseraSante = new Tessera(LocalDate.of(2023, 6, 12), sante);
		Tessera tesseraAndrea = new Tessera(LocalDate.of(2023, 6, 12), andrea);
		Tessera tesseraErika = new Tessera(LocalDate.of(2022, 5, 23), erika);
		Tessera tesseraGiulio = new Tessera(LocalDate.of(2020, 12, 7), giulio);
		Tessera tesseraLaura = new Tessera(LocalDate.of(2023, 8, 4), laura);
		Tessera tesseraScaduta01 = new Tessera(LocalDate.of(2021, 8, 8), sante);
//		tesseraDao.save(tesseraScaduta01);

//		tesseraDao.save(tesseraAndrea);
//		tesseraDao.save(tesseraErika);
//		tesseraDao.save(tesseraGiulio);
//		tesseraDao.save(tesseraSante);
//		tesseraDao.save(tesseraLaura);

		/* Creazione Biglietti */
		Biglietto biglietto01 = new Biglietto(LocalDate.of(2023, 4, 2), tesseraLaura, rivenditoreAutorizzato01);
		// ticketDao.save(biglietto01);

		/* Creazione mezzo */
		Mezzi mezzo = new Mezzi("AS-898-DF", StatoMezzo.IN_MANUTENZIONE, TipoMezzo.TRAM);
		mezzo.setTratta(tratta);
		// mezzoDao.save(mezzo);
		Mezzi mezzo01 = new Mezzi("HA-675-JU", StatoMezzo.IN_SERVIZIO, TipoMezzo.AUTOBUS);
		mezzo01.setTratta(tratta01);
		// mezzoDao.save(mezzo01);
		;
		/* Creazione manutenzione */
//		Manutenzione manutenzione = new Manutenzione("Cambio olio", LocalDate.now(), LocalDate.now().plusDays(3), 3,
//				mezzo);
		// manutenzioneDao.save(manutenzione);

		/* Creazione storicoTratte */
//		StoricoTratte storicoTratte = new StoricoTratte(10.5, mezzo);
//		StoricoTratte storicoTratte2 = new StoricoTratte(15.5, mezzo);
//		StoricoTratte storicoTratte3 = new StoricoTratte(12, mezzo);
//		StoricoTratte storicoTratte4 = new StoricoTratte(12.5, mezzo);
//		StoricoTratte storicoTratte5 = new StoricoTratte(9.5, mezzo);
//		storicoTratteDao.save(storicoTratte);
//		storicoTratteDao.save(storicoTratte2);
//		storicoTratteDao.save(storicoTratte3);
//		storicoTratteDao.save(storicoTratte4);
//		storicoTratteDao.save(storicoTratte5);




		/* PROVE VARIE */

		// mezzo.getManutenzioni().add(manutenzione);
		// mezzoDao.refresh(mezzo);

//		List<Mezzi> mezziPerTratta = mezzoDao.trovaPerTratta(tratta.getId());
//		System.out.println("Mezzi per la tratta " + tratta.getId() + ": " + mezziPerTratta.size());

		// List<Manutenzione> manutenzioniPerMezzo =
		// manutenzioneDao.trovaPerMezzo(mezzo.getTarga());
//		System.out.println("Manutenzioni per il mezzo " + mezzo.getTarga() + ": " + manutenzioniPerMezzo.size());
//
//		System.out.println("Tempo medio della tratta per il mezzo " + mezzo.getTarga() + ": "
//				+ mezzo.getMediaTempiPercorrenza() + ", ha percorso " + mezzo.getStoricoTratte().size()
//				+ " volte la tratta, impiegandoci al massimo " + mezzo.getMaxTempoPercorrenza() + " minuti, e minimo "
//				+ mezzo.getMinTempoPercorrenza() + " minuti");





		try {
			Scanner input = new Scanner(System.in);
			int scelta = -100;

			while (scelta != 0) {

				System.out.println("1. Gestione per Utenza");
				System.out.println("2. Gestione per Amministrazione");
				System.out.println("0. Esci");

				int sceltaLato;
				try {
					sceltaLato = Integer.parseInt(input.nextLine());
				} catch (NumberFormatException ex) {
					System.out.println("Inserire un numero valido!");
					continue;
				}

				switch (sceltaLato) {
				case 1:
					do {
						System.out.println("Digita:");
						System.out.println("1. Creazione nuova tessera");
						System.out.println("2. Eliminazione tessera");
						System.out.println("3. Controllo scadenza tessera");
						System.out.println("4. Acquista Ticket");// esce la scelta tra Biglietto/Abbonamento ->Lista
																	// puntiEmissione ->
						System.out.println("5. Oblitera biglietto");
						System.out.println("0. Torna indietro");

						try {
							sceltaLato = Integer.parseInt(input.nextLine());
						} catch (NumberFormatException ex) {
							System.out.println("Inserire un numero valido!");
							continue;
						}

						switch (sceltaLato) {
						case 1:

							System.out.println("Inserire nome del nuovo utente");
							System.out.println("0. Torna indietro");
							String nome = input.nextLine();
							if (nome.equals("0")) {
								break;
							}
							System.out.println("Inserire cognome del nuovo utente");
							System.out.println("0. Torna indietro");
							String cognome = input.nextLine();
							if (cognome.equals("0")) {
								break;
							}
							nuovaTessera(nome, cognome, utenteDao, tesseraDao);
							break;
						case 2:

							System.out.println("Inserire il numero della tessera da eliminare");
							System.out.println("0. Torna indietro");
							String tesseraDaEliminare = input.nextLine();
							if (tesseraDaEliminare.equals("0")) {
								break;
							}
							eliminaTessera(tesseraDaEliminare, utenteDao, tesseraDao);
							break;

						case 3:

							System.out.println("Inserisci il tuo numero tessera");
							System.out.println("0. Torna indietro");
							String numeroTessera = input.nextLine();
							if (numeroTessera.equals("0")) {
								break;
							}
							controlloScadenza(numeroTessera, tesseraDao, input, em);
							break;
						case 4:
							System.out.println("Inserisci il tuo numero di tessera");
							System.out.println("0. Torna indietro");

							String numeroTesseraPerTicket = input.nextLine();
							if (numeroTesseraPerTicket.equals("0")) {
								break;
							}
							Tessera tesseraPerTicket = tesseraDao.getById(UUID.fromString(numeroTesseraPerTicket));
							if (tesseraPerTicket != null) {
								controlloScadenza(numeroTesseraPerTicket, tesseraDao, input, em);
								System.out.println("Scegli il punto di emissione dove vuoi acquistare il tuo Ticket");
								List<PuntoEmissione> puntiEmissione = puntoEmissioneDao.trovaPuntiEmissione();
								for (int i = 0; i < puntiEmissione.size(); i++) {
									PuntoEmissione punto = puntiEmissione.get(i);
									System.out.println((i + 1) + ". " + punto.getIndirizzo());
								}
								System.out.println("0. Torna indietro");
								int sceltaPuntoEmissione = Integer.parseInt(input.nextLine());
								PuntoEmissione puntoEmissioneScelto = null;

								switch (sceltaPuntoEmissione) {
								case 1:

									puntoEmissioneScelto = puntiEmissione.get(0);

									System.err.println("Hai scelto il Punto di Emissione in" + puntiEmissione.get(0));
									break;
								case 2:
									puntoEmissioneScelto = puntiEmissione.get(1);
									System.err.println("Hai scelto il Punto di Emissione in" + puntiEmissione.get(1));

									break;
								case 3:
									puntoEmissioneScelto = puntiEmissione.get(2);
									System.err.println("Hai scelto il Punto di Emissione in" + puntiEmissione.get(2));

									break;
								case 4:
									puntoEmissioneScelto = puntiEmissione.get(3);
									System.out.println("Hai scelto il Punto di Emissione in" + puntiEmissione.get(3));

									break;
								case 5:
									puntoEmissioneScelto = puntiEmissione.get(4);
									System.err.println("Hai scelto il Punto di Emissione in" + puntiEmissione.get(4));

									break;
								case 6:
									puntoEmissioneScelto = puntiEmissione.get(5);
									System.err.println("Hai scelto il Punto di Emissione in" + puntiEmissione.get(5));

									break;
								case 7:
									puntoEmissioneScelto = puntiEmissione.get(6);
									System.err.println("Hai scelto il Punto di Emissione in" + puntiEmissione.get(6));

									break;
								case 8:
									puntoEmissioneScelto = puntiEmissione.get(7);
									System.err.println("Hai scelto il Punto di Emissione in" + puntiEmissione.get(7));

									break;
								case 9:
									puntoEmissioneScelto = puntiEmissione.get(8);
									System.err.println("Hai scelto il Punto di Emissione in" + puntiEmissione.get(8));

									break;
								case 10:
									puntoEmissioneScelto = puntiEmissione.get(9);
									System.err.println("Hai scelto il Punto di Emissione in" + puntiEmissione.get(9));

									break;

								case 0:
									System.out.println("Arrivederci");
								default:
									System.err.println("Scelta non valida");
								}

								if (puntoEmissioneScelto instanceof DistributoreAutomatico) {
									DistributoreAutomatico distributoreAutomatico = (DistributoreAutomatico) puntoEmissioneScelto;
									if (distributoreAutomatico.setStatoServizioCheck()
											.equals(StatoServizio.FUORI_SERVIZIO)) {
										System.out.println(
												"Il Distributore Automatico selezionato è fuori servizio. Torna indietro...");
										continue; // Return to the previous loop iteration
									}
								}
								System.out.println("Digita:");
								System.out.println("1. Se vuoi acquistare un nuovo Abbonamento");
								System.out.println("2. Se vuoi acquistare un nuovo Biglietto");
								System.out.println("0. Torna indietro");
								int sceltaTicket = Integer.parseInt(input.nextLine());
								switch (sceltaTicket) {
								case 1:
									Abbonamento nuovoAbbonamento = new Abbonamento();
									nuovoAbbonamento.setPuntoEmissione(puntoEmissioneScelto);
									nuovoAbbonamento.setDataEmissione(LocalDate.now());
									nuovoAbbonamento.setTessera(tesseraPerTicket);
									System.out.println("Scegli il tipo di abbonamento:");
									System.out.println("1. Settimanale");
									System.out.println("2. Mensile");
									System.out.println("0. Torna indietro");
									int sceltaAbbonamento = Integer.parseInt(input.nextLine());
									switch (sceltaAbbonamento) {
									case 1:
										nuovoAbbonamento.setTipoAbbonamento(TipoAbbonamento.SETTIMANALE);
										System.err.println("Hai scelto un abbonamento settimanale");
										break;
									case 2:
										nuovoAbbonamento.setTipoAbbonamento(TipoAbbonamento.MENSILE);
										System.err.println("Hai scelto un abbonamento mensile");
										break;
									case 0:
										System.out.println("Arrivederci");
										break;
									default:
										System.err.println("Hai scelto un valore non valido");
									}
									nuovoAbbonamento.setScadenza();
									ticketDao.save(nuovoAbbonamento);
									puntoEmissioneScelto.setAbbonamentoVenduto(nuovoAbbonamento);
									System.err.println("Il tuo abbonamento è:\n" + nuovoAbbonamento + ", acquistato in "
											+ puntoEmissioneScelto + " il giorno " + LocalDate.now());

								case 2:
									Biglietto nuovoBiglietto = new Biglietto();
									nuovoBiglietto.setDataEmissione(LocalDate.now());
									nuovoBiglietto.setTessera(tesseraPerTicket);
									nuovoBiglietto.setPuntoEmissione(puntoEmissioneScelto);
									ticketDao.save(nuovoBiglietto);
									puntoEmissioneScelto.setBigliettoVenduto(nuovoBiglietto);
									System.err.println("Il tuo biglietto è:\n" + nuovoBiglietto);
									System.out.println("**********");
								default:

								}

							} else {
								System.err.println("Il tuo numero tessera non esiste");
							}

							break;
						case 5:
							System.out.println("Inserire il biglietto");
							System.out.println("0. Torna indietro");
							String idBigliettoDaVidiminare = input.nextLine();
							if (idBigliettoDaVidiminare.equals("0")) {
								break;
							}
							Biglietto biglietto = (Biglietto) ticketDao
									.getById(UUID.fromString(idBigliettoDaVidiminare));
							if(biglietto.isTimbrato()) {
								System.err.println(
										"Il biglietto con id " + biglietto.getId() + " è stato già obliterato");
								break;
							}
							System.out.println("Inserire targa del mezzo");
							System.out.println("0. Torna indietro");
							String mezzoChetimbra = input.nextLine();
							if (mezzoChetimbra.equals("0")) {
								break;
							}
//							System.out.println("Inserire tessera");
//							String tessera = input.nextLine();
								timbrato(UUID.fromString(idBigliettoDaVidiminare), mezzoChetimbra, ticketDao, mezzoDao,
										em);
							break;
						}

					} while (sceltaLato != 0);

					break;
				case 2:
					int sceltaBO = -100;
					do {
						System.out.println("1. Elenco manutenzioni per mezzo");
						System.out.println("2. Statistiche temporali per mezzo");
						System.out.println("3. Statistiche temporali per tratta");
						System.out.println("4. Elenco mezzi per tratta");
						System.out.println("5. Informazioni in base al tipo dei mezzi");
						System.out.println("6. Informazioni in base allo stato dei mezzi");
						System.out.println("7. Cambio stato di un mezzo da Manutenzione a In Servizio");
						System.out.println("8. Cambia capienza del mezzo");
					System.out.println("0. Torna indietro");

					try {
						sceltaBO = Integer.parseInt(input.nextLine());
					} catch (NumberFormatException ex) {
						System.err.println("Inserire un numero valido!");
						continue;
					}

					switch (sceltaBO) {
					case 1:
						System.out.println("Inserire targa del mezzo per l'elenco delle manutenzioni");
						System.out.println("0. Torna indietro");
						String targaMezzoManutenzione = input.nextLine();
						if (targaMezzoManutenzione.equals("0")) {
							break;
						}
						manutenzioneDao.trovaPerMezzo(targaMezzoManutenzione).forEach(m -> {
							System.err.println(m);
							System.out.println("**********");
						});

						break;
					case 2:
						System.out.println("Inserire targa del mezzo per le statistiche temporali");
						System.out.println("0. Torna indietro");
						String targaMezzoStatistiche = input.nextLine();
						if (targaMezzoStatistiche.equals("0")) {
							break;
						}
						Mezzi mezzoStatistiche = em.find(Mezzi.class, targaMezzoStatistiche);
						mezzoStatistiche.getMaxTempoPercorrenza();
						mezzoStatistiche.getMinTempoPercorrenza();
						mezzoStatistiche.getMediaTempiPercorrenza();
						System.out.println("**********");
						break;
					case 3:
						System.out.println("Inserire id della tratta per le statistiche temporali");
						System.out.println("0. Torna indietro");
						String idtratta = input.nextLine();
						if (idtratta.equals("0")) {
							break;
						}
						System.out.println("Inserire targa del mezzo");
						System.out.println("0. Torna indietro");
						String targaMezzoPerTratta = input.nextLine();
						if (targaMezzoPerTratta.equals("0")) {
							break;
						}
						Tratta trattaStatistiche = em.find(Tratta.class, idtratta);
						Mezzi mezzoPerTratta = em.find(Mezzi.class, targaMezzoPerTratta);
						trattaStatistiche.comparazioneTempoPercorrenza(mezzoPerTratta);
						System.out.println("**********");

						break;
					case 4:
						System.out.println("Inserire id della tratta per l'elenco dei mezzi a quella tratta");
						System.out.println("0. Torna indietro");
						String idTrattaPerMezzi = input.nextLine();
						if (idTrattaPerMezzi.equals("0")) {
							break;
						}
						mezzoDao.trovaPerTratta(UUID.fromString(idTrattaPerMezzi));
						System.out.println("**********");

						break;
					case 5:

						int tipoMezzoScelta;
						do {

							System.out.println("1. Conta il numero di Autobus");
							System.out.println("2. Conta il numero di Tram");
							System.out.println("0. Torna indietro");

							tipoMezzoScelta = Integer.parseInt(input.nextLine());

							switch (tipoMezzoScelta) {
							case 1:
								System.err.println(
										"Gli autobus presenti sono: " + mezzoDao.countByTipoMezzo(TipoMezzo.AUTOBUS));
								System.out.println("**********");

								break;
							case 2:
								System.err.println(
										"I tram presenti sono: " + mezzoDao.countByTipoMezzo(TipoMezzo.AUTOBUS));
								System.out.println("**********");

								break;
							case 0:
								break;
							default:
								System.out.println("Scelta non valida.");
								System.out.println("**********");

							}
						} while (tipoMezzoScelta != 0);
						break;

					case 6:
						int statoMezzoScelta = -100;
						do {

							System.out.println("1. Conta il numero di mezzi in servizio");
							System.out.println("2. Conta il numero di mezzi in manutenzione");
							System.out.println("0. Torna indietro");
							System.out.println("**********");

							tipoMezzoScelta = Integer.parseInt(input.nextLine());

							switch (statoMezzoScelta) {
							case 1:
								System.err.println(
										"I mezzi in servizio sono: " + mezzoDao.countByStato(StatoMezzo.IN_SERVIZIO));
								System.out.println("**********");

								break;
							case 2:
								System.err.println(
										"I tram presenti sono: " + mezzoDao.countByStato(StatoMezzo.IN_MANUTENZIONE));
								System.out.println("**********");


								break;

							case 7:
								System.out.println("Inserire targa del mezzo");
								String targaMezzoCambioStato = input.nextLine();
								mezzoDao.cambiaStato(targaMezzoCambioStato);
								System.out.println("**********");

								break;
							case 0:
								break;
							default:
								System.out.println("Scelta non valida.");
							}
						} while (statoMezzoScelta != 0);
						break;
					case 8:
						System.out.println("Inserire targa");
						System.out.println("0. Torna indietro");
						String targaMezzoCambioCapienza = input.nextLine();
						if (targaMezzoCambioCapienza.equals("0")) {
							break;
						}
						System.out.println("Inserire nuova capienza");
						System.out.println("0. Torna indietro");
						int nuovaCapienza = Integer.parseInt(input.nextLine());
						if (nuovaCapienza == 0) {
							break;
						}
						mezzoDao.cambiaCapienza(targaMezzoCambioCapienza, nuovaCapienza);
						System.out.println("**********");

						break;
					case 0:

						break;
					default:

						break;
					}
					break;
				} while (sceltaBO != 0);

//				case 0:
//					System.out.println("Arrivederci");
//					break;
				default:
					System.out.println("Scelta non valida. Riprova.");
					break;
				}
			}

			input.close();
		} catch (Exception e) {
//		      e.printStackTrace();
			System.out.println("Si è verificato un errore: " + e.getMessage());
		}

	}

	public static void timbrato(UUID idBiglietto, String targa, TicketDao ticketDao, MezziDao mezzodao,
			EntityManager em) {
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			Biglietto bigliettoTimbrato = (Biglietto) ticketDao.getById(idBiglietto);
			Mezzi mezzoCheTimbra = mezzodao.getById(targa);
			if (mezzoCheTimbra.getStatoMezzo() == StatoMezzo.IN_MANUTENZIONE) {
				System.out.println("Il mezzo con targa " + mezzoCheTimbra.getTarga() + " è in manutenzione");
				return;
			}
			mezzoCheTimbra.setBigliettiVidiminati(bigliettoTimbrato);
			bigliettoTimbrato.setTimbrato(true);
			bigliettoTimbrato.setMezzo(mezzoCheTimbra);
			t.commit();
			System.err.println(bigliettoTimbrato + "è stato obliterato il giorno " + LocalDate.now() + "sul mezzo "
					+ mezzoCheTimbra);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static void nuovaTessera(String nome, String cognome, UtenteDao uD, TesseraDao tD) {
		Utente nuovoUtente = new Utente(nome, cognome);
		uD.save(nuovoUtente);
		Tessera nuovaTessera = new Tessera(LocalDate.now(), nuovoUtente);
		tD.save(nuovaTessera);
		System.err.println("L'utente " + nuovoUtente.getNome() + " " + nuovoUtente.getCognome()
				+ " è stato salvato nel DataBase. All'utente è stata assegnata una tessera con codice:\n"
				+ nuovaTessera.getNumeroTessera());
	}

	public static void eliminaTessera(String tessera, UtenteDao uD, TesseraDao tD) {
		try {
			Tessera tesseraTrovata = tD.getById(UUID.fromString(tessera));
		if (tesseraTrovata != null) {

			Utente utenteTrovato = tesseraTrovata.getUtente();
			tD.delete(UUID.fromString(tessera));

			uD.delete(utenteTrovato.getCodiceFiscale());

			System.err.println("L'utente " + utenteTrovato.getNome() + " " + utenteTrovato.getCognome()
					+ " è stato eliminato dal Database insieme alla sua tessera con codice:\n"
					+ tesseraTrovata.getNumeroTessera());
		} else {
			System.err.println("Il codice inserito non è associato a nessuna tessera");
		}
	} catch (Exception ex) {
		System.err.println("Inserire un numero tessera nel formato valido!");
	}

	}

	public static void controlloScadenza(String numeroTessera, TesseraDao tesseraDao,
			Scanner input, EntityManager em) {
		try {
			/* DA IMPLEMENTARE TRY CATCH CHE MI RIPORTA INDIETRO */
		Tessera tesseraDaControllare = tesseraDao.getById(UUID.fromString(numeroTessera));
		if (tesseraDaControllare != null) {
			try {

				Utente utenteDaControllare = tesseraDaControllare.getUtente();
				boolean scaduta = tesseraDao.tesseraScaduta(UUID.fromString(numeroTessera));

				if (scaduta) {
					System.err.println(
							"La tua tessera è scaduta il giorno " + tesseraDaControllare.getDateScadenzaTessera()
									+ "! Vuoi rinnovarla?");
					System.out.println("Digita:");
					System.out.println("1. Si");
					System.out.println("2. No");

					try {
						int scelta = Integer.parseInt(input.nextLine());
					switch (scelta) {
					case 1:
						EntityTransaction t = em.getTransaction();
						t.begin();

						tesseraDaControllare.setDataScadenza();
						t.commit();
						System.err.println("La tua tessera è stata rinnovata e scadrà il giorno "
								+ tesseraDaControllare.getDateScadenzaTessera());
						break;
					case 2:
						break;

					default:
						System.out.println("Scelta non valida");
					}

				} catch (Exception ex) {
					System.out.println("Valore non valido");
				}

				} else {
					System.err.println(
							"Non preoccuparti " + utenteDaControllare.getNome()
									+ ", la tua tessera è ancora valida fino al "
									+ tesseraDaControllare.getDateScadenzaTessera());
				}
			} catch (Exception ex) {
				System.err.println("Inserire un numero tessera del formato valido!");
			}

		}


	} catch (Exception e) {
		System.out.println("Inserire un numero tessera del formato valido!");
	}
}


}