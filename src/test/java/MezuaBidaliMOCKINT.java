import static org.junit.Assert.*;
//import static org.mockito.Mockito.when;

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
import domain.Event;
import domain.User;

@RunWith(MockitoJUnitRunner.class)
public class MezuaBidaliMOCKINT {

	@Mock
	DataAccess da;
	
    @InjectMocks
    BLFacadeImplementation sut;
	
	@Test
	//Bietako user bat ez dago datubasean
	public void test1() {
		User u1 = new User("Pepito", "123");
		String u2 = "Patxi";
		String mezua = "Kaixo";
		try {
		
		Mockito.doThrow(new Exception("user not in the db")).when(da).mezuaBidali(mezua, u1, u2);
		
		sut.mezuaBidali(mezua, u1, u2);
		
		assertTrue(false);
		
		} catch(Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	//Bi erabiltzaileak datubasean daude mezua utsa da
	public void test2() {
		User u1 = new User("Pepito", "123");
		User u2 = new User("Patxi", "123");
		String mezua = "";
		
		ArgumentCaptor<User> user1Cap = ArgumentCaptor.forClass(User.class);
		ArgumentCaptor<String> user2Cap = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> mezuCap = ArgumentCaptor.forClass(String.class);
		
		sut.mezuaBidali(mezua, u1, "Patxi");
		
		Mockito.verify(da,Mockito.times(1)).mezuaBidali(mezuCap.capture(),user1Cap.capture(), user2Cap.capture());
		
		assertEquals(mezuCap.getValue(),mezua);
		assertEquals(user1Cap.getValue(),u1);
		assertEquals(user2Cap.getValue(),"Patxi");
	}

	@Test
	//User1 null da, exception bat altsatu
	public void test3() {
		User u1 = new User("Pepito", "123");
		String u2 = "Patxi";
		String mezua = "Kaixo";
		try {
		
		Mockito.doThrow(new Exception("User1 is null")).when(da).mezuaBidali(mezua, null, u2);
		
		sut.mezuaBidali(mezua, null, u2);
		
		assertTrue(false);
		
		} catch(Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	//mezua null da, exception bat altsatu
	public void test4() {
		User u1 = new User("Pepito", "123");
		String u2 = "Patxi";
		String mezua = "Kaixo";
		try {
		
		Mockito.doThrow(new Exception("mezua is null")).when(da).mezuaBidali(null, u1, u2);
		
		sut.mezuaBidali(null, u1, u2);
		
		assertTrue(false);
		
		} catch(Exception e) {
			assertTrue(true);
		}
	}
}
