import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.junit.Test;

import dataAccess.DataAccess;
import domain.Apustua;
import domain.Boleto;
import domain.Jarraitzailea;
import domain.Kuota;
import domain.Registered;
import test.dataAccess.TestDataAccess;

public class ApustuaEzabatuDAWTest {
	
	// sut:system under test
	static DataAccess sut = new DataAccess();

	// additional operations needed to execute the test
	static TestDataAccess testDA = new TestDataAccess();
	
	@Test
	public void test1() {	//Ez du jarraitzailerik
		Registered u1 = new Registered("Manolo", "manolo");
		Apustua a = new Apustua(1, u1, new Vector<Kuota>(), false, null);
		u1.addApustua(a);
		try {
			sut.open(false);
			sut.storeUser(u1);
			sut.apustuaEzabatu(u1, a.getBetNumber());
			u1 = (Registered) sut.getUser("Manolo");
			ArrayList<Apustua> aps = u1.getApustuak();
			sut.close();
			assertEquals(aps, null);
		}catch(Exception e) {
			assertTrue(false);
		}finally {
			sut.open(false);
			sut.removeUser(u1);
			sut.close();
		}
	}
	
	@Test
	public void test2() {
		Registered u1 = new Registered("Manolo", "manolo");
		Registered u2 = new Registered("Paqui", "paqui");
		sut.apustuEgin(u1, 12, null, false, 0);
		ArrayList<Apustua> aps = u1.getApustuak();
		u1.addFollower(new Jarraitzailea(1, u2, u1, 5));
		try {
		sut.open(false);
		sut.storeUser(u1);
		sut.storeUser(u2);
		int ID = aps.get(0).getBetNumber();
		sut.apustuaEzabatu(u1, ID);
		sut.close();
		ArrayList<Apustua> aps2 = u1.getApustuak();
		assertEquals(aps2, null);
		}catch(Exception e) {
			assertTrue(false);
		}finally {
			sut.open(false);
			sut.removeUser(u1);
			sut.removeUser(u2);
			sut.close();
		}
	}
	
	@Test
	public void test3() { //Apustua eta erabiltzailea datubasean
		Registered u1 = new Registered("Markel", "mossi");
		try {
			sut.open(false);
			sut.apustuEgin(u1, 3, null, false, 0);
			ArrayList<Apustua> a=u1.getApustuak();
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
