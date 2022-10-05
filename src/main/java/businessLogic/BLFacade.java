package businessLogic;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import domain.Event;
//import domain.Booking;
import domain.Question;
import domain.Registered;
import domain.User;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade  {
	  

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	@WebMethod Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist;
	
	
	/**
	 * This method retrieves the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod public Vector<Event> getEvents(Date date);
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date);
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();

	public boolean register(String password, String username);
	
	public User Login(String password, String username);
	
	public boolean addKuota(Question q, float b, String s);
	
	public boolean addEvent(String description,Date eventDate);
	
	public void addMoney(float dirua, Registered user);
	
	public String getMugimenduak(Registered user);
	
	public boolean apustuEgin(Registered user, float dirua, Vector<String> kuotakA, boolean kopiatuta, int b);
	
	public User getUser(User u);
	
	public void apustuaEzabatu(Registered u, Integer apustuaID);
	
	public void gertaeraEzabatu(Event e);
	
	public void emaitzakIpini(String id);
	
	public float getDirua(Registered u);
	
	public void jarraitu(Registered jarraitzailea, String jarraitua, float murriztapena);
	
	public List<Registered> getRegistered();
	
	public List<User> getAllUsers();
	
	public void mezuaBidali(String mezua, User user1, String u2);
	
	public void gertaeraBikoiztu(Event ev, Date firstDay);
	
	public void boletoaGehitu(String Uname, String efektua, float biderkatzailea);
}
