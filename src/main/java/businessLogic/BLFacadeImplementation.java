package businessLogic;
import java.util.ArrayList;
//hola
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import dataAccess.ApustuEginParameter;
import dataAccess.DataAccess;
import domain.Question;
import domain.Registered;
import domain.User;
import domain.Apustua;
import domain.Boleto;
import domain.Event;
import domain.Existitu_adminDa;
import domain.Kuota;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	DataAccess dbManager;

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c=ConfigXML.getInstance();
		
		String initialize = "initialize";
		if (c.getDataBaseOpenMode().equals(initialize)) {
		    dbManager=new DataAccess(c.getDataBaseOpenMode().equals(initialize));
		    dbManager.initializeDB();
		    } else
		     dbManager=new DataAccess();
		dbManager.close();

		
	}
	
    public BLFacadeImplementation(DataAccess da)  {
		
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.getDataBaseOpenMode().equals("initialize")) {
			da.open(true);
			da.initializeDB();
			da.close();

		}
		dbManager=da;		
	}
	

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
   @WebMethod
   public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{
	   
	    //The minimum bed must be greater than 0
		dbManager.open(false);
		Question qry=null;
		
	    
		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
				
		
		 qry=dbManager.createQuestion(event,question,betMinimum);		

		dbManager.close();
		
		return qry;
   }
	
	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
    @WebMethod	
	public ArrayList<Event> getEvents(Date date)  {
		dbManager.open(false);
		ArrayList<Event>  events=dbManager.getEvents(date);
		dbManager.close();
		return events;
	}

    
	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date) {
		dbManager.open(false);
		Vector<Date>  dates=dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}
	
	
	public void close() {
		DataAccess dB4oManager=new DataAccess(false);

		dB4oManager.close();

	}

	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
    @WebMethod	
	 public void initializeBD(){
    	dbManager.open(false);
		dbManager.initializeDB();
		dbManager.close();
	}
    
    public boolean register(String username, String password) {
    	dbManager.open(false);
		boolean ema = dbManager.register(username, password);
		dbManager.close();
		return ema;
    }
    
    public User Login(String username, String password) {
    	dbManager.open(false);
    	User ema = dbManager.Login(username, password);
		dbManager.close();
		return ema;
    }
    
    public boolean addKuota(Question q, float b, String s) {
    	dbManager.open(false);
    	Kuota k = new Kuota(b,s,q); 
    	if(!dbManager.kuotaExist(k)) {
        	dbManager.close();
    		return false;
    	} else {
        	dbManager.addKuota(q, b, s);
        	dbManager.close();
        	return true;
    	}
    }
    
    public boolean addEvent(String description,Date eventDate) {
    	dbManager.open(false);
    	Event e = new Event(description, eventDate);
    	if(!dbManager.eventExists(e)) {
    		dbManager.close();
    		return false;
    	} else {
    		dbManager.addEvent(e);
        	dbManager.close();
        	return true;
    	}
    }
    
    public void addMoney(float dirua, Registered user) {
    	dbManager.open(false);
    	dbManager.addMoney(dirua, user);
    	dbManager.close();
    }
    
    public String getMugimenduak(Registered user) {
    	dbManager.open(false);
    	String str = dbManager.getMugimenduak(user);
    	dbManager.close();
    	return str;
    }
    
    public boolean apustuEgin(Registered user, float dirua, Vector<String> kuotak, boolean kopiatuta, int b) {
        dbManager.open(false);
        boolean ema = dbManager.apustuEgin(new ApustuEginParameter(user, dirua, kuotak, kopiatuta, b));
        dbManager.close();
        return ema;
    }
    
    public User getUser(User u) {
    	dbManager.open(false);
        User ema = dbManager.getUser(u.getUsername());
    	dbManager.close();
    	return ema;
    }
    
    public void apustuaEzabatu(Registered u, Integer apustuaID) {
    	dbManager.open(false);
        dbManager.apustuaEzabatu(u, apustuaID);
    	dbManager.close();
    }
    
    public void gertaeraEzabatu(Event e) {
    	dbManager.open(false);
    	dbManager.gertaeraEzabatu(e);
    	dbManager.close();
    }
    
    public void emaitzakIpini(String id) {
        dbManager.open(false);
        dbManager.emaitzakIpini(id);
        dbManager.close();
    }
    
    public float getDirua(Registered u) {
    	dbManager.open(false);
    	float f = dbManager.getDirua(u);
    	dbManager.close();
    	return f;
    }
    
    public void jarraitu(Registered jarraitzailea, String jarraitua, float murriztapena) {
    	dbManager.open(false);
        dbManager.jarraitu(jarraitzailea, jarraitua, murriztapena);
        dbManager.close();
    }
    
    public List<Registered> getRegistered(){
    	dbManager.open(false);
    	List<Registered> users = dbManager.getRegistered();
        dbManager.close();
        return users;
    }
    
    public List<User> getAllUsers() {
    	dbManager.open(false);
    	List<User> users = dbManager.getAllUsers();
        dbManager.close();
        return users;
    }
    
    public void mezuaBidali(String mezua, User user1, String u2) {
    	dbManager.open(false);
        dbManager.mezuaBidali(mezua, user1, u2);
        dbManager.close();
    }
    
    public void gertaeraBikoiztu(Event ev, Date firstDay) {
    	dbManager.open(false);
    	dbManager.gertaeraBikoiztu(ev, firstDay);
    	dbManager.close();
    }
    
    public void boletoaGehitu(String Uname, String efektua, float biderkatzailea) {
    	dbManager.open(false);
    	dbManager.boletoaGehitu(Uname, efektua, biderkatzailea);
    	dbManager.close();
    }
    
    public void removeUser(User u1) {
    	dbManager.open(false);
    	dbManager.removeUser(u1);
    	dbManager.close();
    }
    
    public void removeMezua(User u1, String u2) {
    	dbManager.open(false);
    	dbManager.removeMessage(u1, u2);
    	dbManager.close();
    }
    
    public void addEvent(Event e) {
    	dbManager.open(false);
    	dbManager.addEvent(e);
    	dbManager.close();
    }
    
    public List<Question> getAllQuestions(){
    	dbManager.open(false);
    	List<Question> ema = dbManager.getAllQuestions();
    	dbManager.close();
    	return ema;
    }
    
    public List<Kuota> getAllFees(){
    	dbManager.open(false);
    	List<Kuota> ema = dbManager.getAllFees();
    	dbManager.close();
    	return ema;
    }
    
    public List<Apustua> getAllBets(){
    	dbManager.open(false);
    	List<Apustua> ema = dbManager.getAllBets();
    	dbManager.close();
    	return ema;
    }
    
    public void storeUser(User u) {
    	dbManager.open(false);
    	dbManager.storeUser(u);;
    	dbManager.close();
    }
}
