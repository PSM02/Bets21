
import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.junit.Test;

import dataAccess.DataAccess;
import domain.Apustua;
import domain.Event;
import domain.Kuota;
import domain.Question;
import domain.Registered;

public class GertaeraEzabatuDAWTest {

	// sut:system under test
	static DataAccess sut = new DataAccess();
	
	@Test
	//galderak, kuotak eta apustuak ditu
	public void test1() {
		try {
			//Egoera prestatu
			Date d = new Date();
			Registered u1 = new Registered("u1", "123");
			Event e = new Event(100, "probaEventua", d);
			Question q1 = e.addQuestion("probaGaldera1", 1);
			Kuota k1 = q1.addKuota(2, "probaKuota1");
			Kuota k2 = q1.addKuota(2, "probaKuota2");
			
			//gertaera gehitu
			sut.addEvent(e);
			
			//Apustuak gehitu
			sut.storeUser(u1);
			sut.addMoney(10000, u1);
			Vector<String> kuotak=new Vector<String>();
			kuotak.add(k1.getDeskripzioa());
			sut.apustuEgin(u1, 10, kuotak, false, 0);
			kuotak.removeAllElements();
			kuotak.add(k2.getDeskripzioa());
			sut.apustuEgin(u1, 10, kuotak, false, 0);
			
			
			//begiratu esperotakoa datubasean dagoela
			List<Event> events = sut.getEvents(d);
			List<Question> questions = sut.getAllQuestions();
			List<Kuota> fees = sut.getAllFees();
			List<Apustua> bets = sut.getAllBets();
			
			assertTrue(events.contains(e) && questions.contains(q1) 
					&& fees.contains(k1) && fees.contains(k2) && bets.contains(k1.getApustuak())
					&& bets.contains(k2.getApustuak()));

			//gertaera ezabatu
			sut.gertaeraEzabatu(e);
			
			//frogatu db-tik kendu ditugula
			events = sut.getEvents(d);
			questions = sut.getAllQuestions();
			fees = sut.getAllFees();
			assertFalse(events.contains(e) || questions.contains(q1) 
					|| fees.contains(k1) || fees.contains(k2)
					|| bets.contains(k1.getApustuak())
					|| bets.contains(k2.getApustuak()));
			
		} catch(Exception e) {
			//hemendik segitzen bada gaizki 
			assertTrue(false);
		}
	}
	
	@Test
	//galdera bectorea hutsik
	public void test2() {
		try {
			//Egoera prestatu
			Date d = new Date();
			Event e = new Event(100, "probaEventua", d);
			//Ez ditu galderarik
			assertTrue(e.getQuestions().isEmpty());
			
			sut.addEvent(e);
			//begiratuko e datubasean dagoela
			assertTrue(sut.getEvents(d).contains(e));

			//gertaera ezabatu
			sut.gertaeraEzabatu(e);
			
			//frogatuko dugu db-tik kendu dugula
			assertFalse(sut.getEvents(d).contains(e));
		} catch(Exception e) {
			//hemendik segitzen bada gaizki 
			assertTrue(false);
		}
	}
	
	@Test
	//galderak eta kuotak ditu baina azkeneko hauek ez dituzte apusturik
	public void test3() {
		try {
			//Egoera prestatu
			Date d = new Date();
			Event e = new Event(100, "probaEventua", d);
			Question q1 = e.addQuestion("probaGaldera1", 1);
			Question q2 = e.addQuestion("probaGaldera2", 1);
			Kuota k1 = q1.addKuota(2, "probaKuota1");
			Kuota k2 = q1.addKuota(2, "probaKuota2");
			Kuota k3 = q2.addKuota(2, "probaKuota3");
			Kuota k4 = q2.addKuota(2, "probaKuota4");
			
			//Ez ditu Apusturik
			for(Question q: e.getQuestions()) {
				for(Kuota k: q.getFees()) {
					assertTrue(k.getApustuak().isEmpty());
				}
			}
			
			//gertaera gehitu
			sut.addEvent(e);
			
			//begiratu esperotakoa datubasean dagoela
			List<Event> events = sut.getEvents(d);
			List<Question> questions = sut.getAllQuestions();
			List<Kuota> fees = sut.getAllFees();
			assertTrue(events.contains(e) && questions.contains(q1) 
					&& questions.contains(q2) && fees.contains(k1) && fees.contains(k2) 
					&& fees.contains(k3) && fees.contains(k4));

			//gertaera ezabatu
			sut.gertaeraEzabatu(e);
			
			//frogatu db-tik kendu ditugula
			events = sut.getEvents(d);
			questions = sut.getAllQuestions();
			fees = sut.getAllFees();
			assertFalse(events.contains(e) || questions.contains(q1) 
					|| questions.contains(q2) || fees.contains(k1) 
					|| fees.contains(k2) || fees.contains(k3) || fees.contains(k4));
			
		} catch(Exception e) {
			//hemendik segitzen bada gaizki 
			assertTrue(false);
		}
	}
	
	@Test
	//bere galderak ez dituzte kuotarik
	public void test4() {
		try {
			//Egoera prestatu
			Date d = new Date();
			Event e = new Event(100, "probaEventua", d);
			Question q1 = e.addQuestion("probaGaldera1", 1);
			Question q2 = e.addQuestion("probaGaldera2", 1);
			Question q3 = e.addQuestion("probaGaldera3", 1);
			
			//Ez ditu kuotarik
			for(Question q: e.getQuestions()) {
				assertTrue(q.getFees().isEmpty());
			}
			
			//Gertaera gehitu
			sut.addEvent(e);
			
			//begiratu esperotakoa datubasean dagoela
			List<Event> events = sut.getEvents(d);
			List<Question> questions = sut.getAllQuestions();
			assertTrue(events.contains(e) && questions.contains(q1) && questions.contains(q2) && questions.contains(q3));

			//gertaera ezabatu
			sut.gertaeraEzabatu(e);
			
			//frogatu db-tik kendu ditugula
			events = sut.getEvents(d);
			questions = sut.getAllQuestions();
			assertFalse(events.contains(e) || questions.contains(q1) || questions.contains(q2) || questions.contains(q3));
		
		} catch(Exception e) {
			//hemendik segitzen bada gaizki 
			assertTrue(false);
		}
	}
}
