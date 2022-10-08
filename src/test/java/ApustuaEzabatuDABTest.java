import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;

import dataAccess.DataAccess;
import domain.Apustua;
import domain.Registered;
import domain.User;
import test.dataAccess.TestDataAccess;


public class ApustuaEzabatuDABTest {
	// sut:system under test
		static DataAccess sut = new DataAccess();

		// additional operations needed to execute the test
		static TestDataAccess testDA = new TestDataAccess();
	@Test
	public void test1() {//Apustua ez dago db-an
		Registered u1 = new Registered("Juan", "juan");
		try {			
			sut.open(false);
			sut.storeUser(u1);
			sut.apustuaEzabatu(u1, 123);
	}catch(Exception e) {
		sut.close();
		assertFalse(e.getMessage().equals(""));
	}finally {
		sut.open(false);
		sut.removeUser(u1);
		sut.close();
	}

	}
	
	public void test2() {//Erabiltzailea ez dago datubasean
		
		Registered u1 = new Registered("Ibon", "Ibonaldo");
		try {
			sut.open(false);
			sut.apustuEgin(u1, 3, null, false, 0);
			sut.apustuaEzabatu(u1, 3124);
			
		}catch(Exception e) {
			sut.close();
			assertFalse(e.getMessage().equals(""));
		}finally {
			sut.open(false);
			sut.removeUser(u1);
			sut.close();
		}
		
	}
	public void test3() { //Apustua eta erabiltzailea datubasean
		Registered u1 = new Registered("Markel", "mossi");
		try {
			sut.open(false);
			sut.apustuEgin(u1, 3, null, false, 0);
			Vector<Apustua> a=u1.getApustuak();
			sut.apustuaEzabatu(u1, a.get(0).getBetNumber());
			assertEquals(null, u1.getApustuak().get(0));
			
		}catch(Exception e) {
			assertTrue(false);
		}finally {
			sut.open(false);
			sut.removeUser(u1);
			sut.close();
		}
	}
}
