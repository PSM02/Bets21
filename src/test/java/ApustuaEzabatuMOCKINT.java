import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Vector;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Apustua;
import domain.Kuota;
import domain.Question;
import domain.Registered;
import domain.User;

@RunWith(MockitoJUnitRunner.class)
public class ApustuaEzabatuMOCKINT {
	
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
	
	@Test
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
	
	@Test
	public void test3() { //Apustua eta erabiltzailea datubasean
		Registered u1 = new Registered("Markel", "mossi");
		Kuota k = new Kuota(2, "prueba", null);
		Apustua a = new Apustua(13, u1, k, false, null);
		u1.addApustua(a);
		try {
			
			ArgumentCaptor<Registered> user1Cap = ArgumentCaptor.forClass(Registered.class);
			ArgumentCaptor<Integer> ApustuaCap = ArgumentCaptor.forClass(Integer.class);
			
			sut.apustuaEzabatu(u1, a.getBetNumber());
			
			Mockito.verify(da,Mockito.times(1)).apustuaEzabatu(user1Cap.capture(), ApustuaCap.capture());
			
			assertEquals(user1Cap.getValue(), u1);
			assertEquals(ApustuaCap.getValue(), a);
		}catch(Exception e) {
			assertTrue(false);
		}
	}
	
}
