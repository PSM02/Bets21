import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import dataAccess.DataAccess;
import domain.Admin;
import domain.Event;
import domain.Mezua;
import domain.Question;
import domain.User;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import test.businessLogic.TestFacadeImplementation;
import test.dataAccess.TestDataAccess;

public class MezuaBidaliDAWTest {

	// sut:system under test
	static DataAccess sut = new DataAccess();

	// additional operations needed to execute the test
	static TestDataAccess testDA = new TestDataAccess();
	
	//Bi erabiltzaileak datubasean daude, baita ere bien arteko elkarrizketa, mezua utsa da
	@Test
	public void test1() {
		User u1 = new User("Pepito", "123");
		User u2 = new User("Patxi", "123");
		String mezua = "";
		try {			
			sut.open(false);
			sut.storeUser(u1);
			sut.storeUser(u2);
			//Bien arteko elkarrizketa existitzen da
			sut.mezuaBidali("Kaixo", u1, "Patxi");
			//Espero duguna horain daukaguaren bardina da mezzu uts bat bidalita ez delako ezer gehituko
			Mezua expected = u1.getMezuaErabiltzaile("Patxi");
			
			//horain mezu utsa bidali
			sut.mezuaBidali(mezua, u1, "Patxi");
			sut.close();
			assertEquals(u1.getMezuaErabiltzaile("Patxi"), expected);
		} catch (Exception e) {
			//Emen sartzen bada gaizki
			assertTrue(false);
		} finally {
			sut.open(false);
			sut.removeMessage(u1, "Patxi");
			sut.removeUser(u1);
			sut.removeUser(u2);
			sut.close();
		}
	}
	
	//Bi erabiltzaileak datubasean daude, baina ez bien arteko elkarrizketa, mezua hutsa da
	@Test
	public void test2() {
		User u1 = new User("Pepito", "123");
		User u2 = new User("Patxi", "123");
		String mezua = "";
		try {			
			sut.open(false);
			sut.storeUser(u1);
			sut.storeUser(u2);
			//horain mezu utsa bidali
			sut.mezuaBidali(mezua, u1, "Patxi");
			sut.close();
			//Espero duguna elkarrizketarik ez egotea da
			assertEquals(null, u1.getMezuaErabiltzaile("Patxi"));
		} catch (Exception e) {
			//Emen sartzen bada gaizki
			assertTrue(false);
		} finally {
			sut.open(false);
			sut.removeUser(u1);
			sut.removeUser(u2);
			sut.close();
		}
	}
	
	//Bi arabiltzaileak existitzen dira eta beraien arteko elkarrizketa ere, user1 Admin bat da
	@Test
	public void test3() {
		Admin u1 = new Admin("Pepito", "123");
		User u2 = new User("Patxi", "123");
		String mezua = "Kaixo";
		try {			
			sut.open(false);
			sut.storeUser(u1);
			sut.storeUser(u2);
			//Bien arteko elkarrizketa sortu
			sut.mezuaBidali("1", u2, "Pepito");
			sut.mezuaBidali("2", u1, "Patxi");
			sut.mezuaBidali("3", u2, "Pepito");
			//Horain duguna + "Pepito(Admin): Kaixo" ezpero dugu beraz:
			Mezua expected = u1.getMezuaErabiltzaile("Patxi");
			expected.setMezua(expected.getMezua()+"\n Pepito(Admin): Kaixo");
			
			//horain mezua bidali
			sut.mezuaBidali(mezua, u1, "Patxi");
			sut.close();
			
			//Espero dugu bidali dugun mezua Pepito(User): Kaixo bezala agertzea
			assertEquals(u1.getMezuaErabiltzaile("Patxi"), expected);
		} catch (Exception e) {
			//Emen sartzen bada gaizki
			assertTrue(false);
		} finally {
			sut.open(false);
			sut.removeMessage(u1, "Patxi");
			sut.removeUser(u1);
			sut.removeUser(u2);
			sut.close();
		}
	}
	
	//Bi erabiltzaileak datubasean daude, baina ez bien arteko elkarrizketarik, U1 user normal bat da eta mezua ez da utsa
	@Test
	public void test4() {
		User u1 = new User("Pepito", "123");
		User u2 = new User("Patxi", "123");
		String mezua = "Kaixo";
		String expected = "Pepito(User): Kaixo\n";
		try {			
			sut.open(false);
			sut.storeUser(u1);
			sut.storeUser(u2);
			sut.mezuaBidali(mezua, u1, "Patxi");
			sut.close();
			//Espero dugu bidali dugun mezua Pepito(User): Kaixo bezala agertzea
			assertEquals(expected, u1.getMezuaErabiltzaile("Patxi").getMezua());
		} catch (Exception e) {
			//Emen sartzen bada gaizki
			assertTrue(false);
		} finally {
			sut.open(false);
			sut.removeMessage(u1, "Patxi");
			sut.removeUser(u1);
			sut.removeUser(u2);
			sut.close();
		}
	}
}
