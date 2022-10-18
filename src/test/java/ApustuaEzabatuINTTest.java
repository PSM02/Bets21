import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Vector;

import org.junit.BeforeClass;
import org.junit.Test;

import businessLogic.BLFacadeImplementation;
import domain.Apustua;
import domain.Registered;

public class ApustuaEzabatuINTTest {

	static BLFacadeImplementation sut;

	@BeforeClass
	public static void setUpClass() {
		
		//DataAccess da= new DataAccess();

		sut=new BLFacadeImplementation();
	}
	
	@Test
	public void test1() {	//Apustua ez dago db-an
		Registered u1 = new Registered("Juan", "juan");
		try {			
			sut.register("Juan", "juan");
			sut.apustuaEzabatu(u1, 123);
	}catch(Exception e) {
		assertFalse(e.getMessage().equals(""));
	}finally {
		sut.removeUser(u1);
	}

	}
	
	@Test
	public void test2() {//Erabiltzailea ez dago datubasean
		
		Registered u1 = new Registered("Ibon", "Ibonaldo");
		try {
			sut.storeUser(u1);
			sut.apustuEgin(u1, 3, null, false, 0);
			sut.apustuaEzabatu(u1, 3124);
			
		}catch(Exception e) {
			assertFalse(e.getMessage().equals(""));
		}finally {
			sut.removeUser(u1);
		}
		
	}
	
	@Test
	public void test3() { //Apustua eta erabiltzailea datubasean
		Registered u1 = new Registered("Markel", "mossi");
		try {
			sut.register("Markel", "mossi");
			sut.apustuEgin(u1, 3, null, false, 0);
			ArrayList<Apustua> a=u1.getApustuak();
			sut.apustuaEzabatu(u1, a.get(0).getBetNumber());
			assertEquals(null, u1.getApustuak().get(0));
			
		}catch(Exception e) {
			assertTrue(false);
		}finally {
			sut.removeUser(u1);
		}
	}

}
