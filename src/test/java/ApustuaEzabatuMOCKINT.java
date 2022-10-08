import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Apustua;
import domain.Registered;

@RunWith(MockitoJUnitRunner.class)
class ApustuaEzabatuMOCKINT {
	
	@Mock
	DataAccess da;
	
	@InjectMocks
	BLFacadeImplementation sut;
	
	@Test
	public void test1() {//Apustua ez dago db-an
		Registered u1 = new Registered("Juan", "juan");
		try {			
			sut.register("Juan", "juan");
			Mockito.doThrow(new Exception("bet not in the db")).when(da).apustuaEzabatu(u1, 123);
			
			sut.apustuaEzabatu(u1, 123);
			
			assertTrue(false);
			
		}catch(Exception e) {
			assertTrue(true);
	}
	}
	
	public void test2() {//Erabiltzailea ez dago datubasean
		
		Registered u1 = new Registered("Ibon", "Ibonaldo");
		try {
			sut.apustuEgin(u1, 3, null, false, 0);
			Mockito.doThrow(new Exception("u1 not in the db")).when(da).apustuaEzabatu(u1, 123);
			
			sut.apustuaEzabatu(u1, 123);
			
			assertTrue(false);
			
		}catch(Exception e) {
			assertTrue(true);
		}
	}
	
	public void test3() { //Apustua eta erabiltzailea datubasean
		Registered u1 = new Registered("Markel", "mossi");
		try {
			sut.register("Markel", "mossi");
			sut.apustuEgin(u1, 3, null, false, 0);
			Vector<Apustua> a=u1.getApustuak();
			
			Mockito.doReturn(true).when(da).apustuaEzabatu(u1, a.get(0).getBetNumber());
			
			sut.apustuaEzabatu(u1, a.get(0).getBetNumber());
			
			assertTrue(true);
		}catch(Exception e) {
			assertTrue(false);
		}
	}
	
}
