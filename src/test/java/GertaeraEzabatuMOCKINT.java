import static org.junit.Assert.*;
//import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Apustua;
import domain.Event;
import domain.Kuota;
import domain.Question;
import domain.Registered;
import domain.User;

@RunWith(MockitoJUnitRunner.class)
public class GertaeraEzabatuMOCKINT {

	@Mock
	DataAccess da;
	
    @InjectMocks
    BLFacadeImplementation sut;
	
    @Test
	//pasatako gertaera null da, exception bat altsatu
	public void test1() {
		try {
			Mockito.doThrow(new Exception("parameter is null")).when(da).gertaeraEzabatu(null);;
			
			sut.gertaeraEzabatu(null);
			
			//hemendik segitzen bada gaizki
			assertTrue(false);
		} catch(Exception e) {
			//guk uste duguna 
			assertTrue(true);
		}
	}
    
    @Test
    public void test2() {
    	Date d = new Date();
		Event e = new Event(100, "probaEventua", d);
    	try {
			Mockito.doThrow(new Exception("Event not in the DB")).when(da).gertaeraEzabatu(e);;
			
			sut.gertaeraEzabatu(e);
			
			//hemendik segitzen bada gaizki
			assertTrue(false);
		} catch(Exception exc) {
			//guk uste duguna 
			assertTrue(true);
		}
	}
	
	@Test
	//Begiratuko dugu metodoa ondo deitzen dela eta esperotako balioak bidaltzen direla
	public void test3() {
		try {
			//Egoera prestatu
			Date d = new Date();
			Registered u1 = new Registered("u1", "123");
			Event e = new Event(100, "probaEventua", d);
			Question q1 = e.addQuestion("probaGaldera1", 1);
			q1.addKuota(2, "probaKuota1");
			q1.addKuota(2, "probaKuota2");
			
			ArgumentCaptor<Event> EventCap = ArgumentCaptor.forClass(Event.class);
			
			sut.gertaeraEzabatu(e);
			
			Mockito.verify(da,Mockito.times(1)).gertaeraEzabatu(EventCap.capture());
			
			assertEquals(EventCap.getValue(),e);
			
		} catch(Exception e) {
			//hemendik segitzen bada gaizki 
			assertTrue(false);
		}
	}
}
