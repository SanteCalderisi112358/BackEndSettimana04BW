package Application;


import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import BWenum.TipoMezzo;
import BWutils.JpaUtil;
import Mezzi.Manutenzione;
import Mezzi.Mezzi;
import Mezzi.StoricoTratte;
import Mezzi.Tratta;

import BWdao.ManutenzioneDao;
import BWdao.MezziDao;
import BWdao.StoricoTratteDao;
import BWdao.TrattaDao;

public class App {
	
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();
		
		MezziDao mezzoDao = new MezziDao(em);
		TrattaDao trattaDao = new TrattaDao(em);
		ManutenzioneDao manutenzioneDao = new ManutenzioneDao(em);
		StoricoTratteDao storicoTratteDao = new StoricoTratteDao(em);

		Tratta tratta = new Tratta("Milano", "Roma", 500, 1000);
		trattaDao.save(tratta);

		Mezzi mezzo = new Mezzi(10, 50, TipoMezzo.AUTOBUS);
		mezzo.setTratta(tratta);
		mezzoDao.save(mezzo);

		Manutenzione manutenzione = new Manutenzione("Cambio olio", LocalDate.now(), LocalDate.now().plusDays(3), 3, mezzo);
		manutenzioneDao.save(manutenzione);
		
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

		mezzo.getManutenzioni().add(manutenzione);
		mezzoDao.refresh(mezzo);

		List<Mezzi> mezziPerTratta = mezzoDao.trovaPerTratta(tratta);
		System.out.println("Mezzi per la tratta " + tratta.getId() + ": " + mezziPerTratta.size());

		List<Manutenzione> manutenzioniPerMezzo = manutenzioneDao.trovaPerMezzo(mezzo.getId());
		System.out.println("Manutenzioni per il mezzo " + mezzo.getId() + ": " + manutenzioniPerMezzo.size());
		
		System.out.println("Tempo medio della tratta per il mezzo " + mezzo.getId() + ": " + mezzo.getMediaTempiPercorrenza() + ", ha percorso " + mezzo.getStoricoTratte().size() + " volte la tratta, impiegandoci al massimo " + mezzo.getMaxTempoPercorrenza()+" minuti, e minimo " + mezzo.getMinTempoPercorrenza()+ " minuti");
	}
}


