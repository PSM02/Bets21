package dataAccess;

import java.util.ArrayList;
//hello
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Admin;
import domain.Apustua;
import domain.Boleto;
import domain.Event;
import domain.Existitu_adminDa;
import domain.Jarraitzailea;
import domain.Kuota;
import domain.Mezua;
import domain.Mugimendua;
import domain.Question;
import domain.Registered;
import domain.User;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess {
	protected static EntityManager db;
	protected static EntityManagerFactory emf;

	ConfigXML c = ConfigXML.getInstance();

	public DataAccess(boolean initializeMode) {

		System.out.println("Creating DataAccess instance => isDatabaseLocal: " + c.isDatabaseLocal()
				+ " getDatabBaseOpenMode: " + c.getDataBaseOpenMode());

		open(initializeMode);

	}

	public DataAccess() {
		this(false);
	}

	/**
	 * This is the data access method that initializes the database with some events
	 * and questions. This method is invoked by the business logic (constructor of
	 * BLFacadeImplementation) when the option "initialize" is declared in the tag
	 * dataBaseOpenMode of resources/config.xml file
	 */
	public void initializeDB() {

		db.getTransaction().begin();
		try {

			Calendar today = Calendar.getInstance();
			Admin ADMIN = new Admin("Martin", "123");
			Registered user1 = new Registered("j", "1");
			Registered user2 = new Registered("m", "1");

			int month = today.get(Calendar.MONTH);
			month += 1;
			int year = today.get(Calendar.YEAR);
			if (month == 12) {
				month = 0;
				year += 1;
			}

			Event ev1 = new Event(1, "Atlético-Athletic", UtilDate.newDate(year, month, 17));
			Event ev2 = new Event(2, "Eibar-Barcelona", UtilDate.newDate(year, month, 17));
			Event ev3 = new Event(3, "Getafe-Celta", UtilDate.newDate(year, month, 17));
			Event ev4 = new Event(4, "Alavés-Deportivo", UtilDate.newDate(year, month, 17));
			Event ev5 = new Event(5, "Español-Villareal", UtilDate.newDate(year, month, 17));
			Event ev6 = new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year, month, 17));
			Event ev7 = new Event(7, "Malaga-Valencia", UtilDate.newDate(year, month, 17));
			Event ev8 = new Event(8, "Girona-Leganés", UtilDate.newDate(year, month, 17));
			Event ev9 = new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year, month, 17));
			Event ev10 = new Event(10, "Betis-Real Madrid", UtilDate.newDate(year, month, 17));

			Event ev11 = new Event(11, "Atletico-Athletic", UtilDate.newDate(year, month, 1));
			Event ev12 = new Event(12, "Eibar-Barcelona", UtilDate.newDate(year, month, 1));
			Event ev13 = new Event(13, "Getafe-Celta", UtilDate.newDate(year, month, 1));
			Event ev14 = new Event(14, "Alavés-Deportivo", UtilDate.newDate(year, month, 1));
			Event ev15 = new Event(15, "Español-Villareal", UtilDate.newDate(year, month, 1));
			Event ev16 = new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year, month, 1));

			Event ev17 = new Event(17, "Málaga-Valencia", UtilDate.newDate(year, month + 1, 28));
			Event ev18 = new Event(18, "Girona-Leganés", UtilDate.newDate(year, month + 1, 28));
			Event ev19 = new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year, month + 1, 28));
			Event ev20 = new Event(20, "Betis-Real Madrid", UtilDate.newDate(year, month + 1, 28));

			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;

			if (Locale.getDefault().equals(new Locale("es"))) {
				q1 = ev1.addQuestion("¿Quién ganará el partido?", 1);
				q2 = ev1.addQuestion("¿Quién meterá el primer gol?", 2);
				q3 = ev11.addQuestion("¿Quién ganará el partido?", 1);
				q4 = ev11.addQuestion("¿Cuántos goles se marcarán?", 2);
				q5 = ev17.addQuestion("¿Quién ganará el partido?", 1);
				q6 = ev17.addQuestion("¿Habrá goles en la primera parte?", 2);
			} else if (Locale.getDefault().equals(new Locale("en"))) {
				q1 = ev1.addQuestion("Who will win the match?", 1);
				q2 = ev1.addQuestion("Who will score first?", 2);
				q3 = ev11.addQuestion("Who will win the match?", 1);
				q4 = ev11.addQuestion("How many goals will be scored in the match?", 2);
				q5 = ev17.addQuestion("Who will win the match?", 1);
				q6 = ev17.addQuestion("Will there be goals in the first half?", 2);
			} else {
				q1 = ev1.addQuestion("Zeinek irabaziko du partidua?", 1);
				q2 = ev1.addQuestion("Zeinek sartuko du lehenengo gola?", 2);
				q3 = ev11.addQuestion("Zeinek irabaziko du partidua?", 1);
				q4 = ev11.addQuestion("Zenbat gol sartuko dira?", 2);
				q5 = ev17.addQuestion("Zeinek irabaziko du partidua?", 1);
				q6 = ev17.addQuestion("Golak sartuko dira lehenengo zatian?", 2);

			}

			db.persist(ADMIN);
			db.persist(user1);
			db.persist(user2);

			Kuota k1 = q3.addKuota(2, "k1");
			Kuota k2 = q3.addKuota(2, "k2");
			Kuota k3 = q4.addKuota(2, "k3");
			Kuota k4 = q1.addKuota(2, "k4");

			db.persist(k1);
			db.persist(k2);

			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6);

			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);

			db.getTransaction().commit();
			System.out.println("Db initialized");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method creates a question for an event, with a question text and the
	 * minimum bet
	 * 
	 * @param event      to which question is added
	 * @param question   text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws QuestionAlreadyExist if the same question already exists for the
	 *                              event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= " + event + " question= " + question + " betMinimum="
				+ betMinimum);

		Event ev = db.find(Event.class, event.getEventNumber());

		if (ev.DoesQuestionExists(question))
			throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));

		db.getTransaction().begin();
		Question q = ev.addQuestion(question, betMinimum);
		// db.persist(q);
		db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions
						// property of Event class
						// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		db.getTransaction().commit();
		return q;

	}

	/**
	 * This method retrieves from the database the events of a given date
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public ArrayList<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		ArrayList<Event> res = new ArrayList<Event>();
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1", Event.class);
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
		for (Event ev : events) {
			System.out.println(ev.toString());
			res.add(ev);
		}
		return res;
	}

	/**
	 * This method retrieves from the database the dates a month for which there are
	 * events
	 * 
	 * @param date of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();

		Date firstDayMonthDate = UtilDate.firstDayMonth(date);
		Date lastDayMonthDate = UtilDate.lastDayMonth(date);

		TypedQuery<Date> query = db.createQuery(
				"SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2", Date.class);
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
		for (Date d : dates) {
			System.out.println(d.toString());
			res.add(d);
		}
		return res;
	}

	public void open(boolean initializeMode) {

		System.out.println("Opening DataAccess instance => isDatabaseLocal: " + c.isDatabaseLocal()
				+ " getDatabBaseOpenMode: " + c.getDataBaseOpenMode());

		String fileName = c.getDbFilename();
		if (initializeMode) {
			fileName = fileName + ";drop";
			System.out.println("Deleting the DataBase");
		}

		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:" + fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());

			emf = Persistence.createEntityManagerFactory(
					"objectdb://" + c.getDatabaseNode() + ":" + c.getDatabasePort() + "/" + fileName, properties);

			db = emf.createEntityManager();
		}

	}

	public boolean existQuestion(Event event, String question) {
		System.out.println(">> DataAccess: existQuestion=> event= " + event + " question= " + question);
		Event ev = db.find(Event.class, event.getEventNumber());
		return ev.DoesQuestionExists(question);

	}

	public boolean kuotaExist(Kuota k) {
		return (db.find(Kuota.class, k) == null);
	}

	public void addKuota(Question q, float b, String s) {
		db.getTransaction().begin();
		Question quest = db.find(Question.class, q);
		quest.addKuota(b, s);
		db.persist(quest);
		db.getTransaction().commit();
	}

	public void close() {
		db.close();
		System.out.println("DataBase closed");
	}

	public boolean userExsists(String u) {
		return (db.find(User.class, u) != null);
	}

	public User getUser(String u) {
		return db.find(User.class, u);
	}

	public void storeUser(User u) {
		db.getTransaction().begin();
		db.persist(u);
		db.getTransaction().commit();
	}

	public void addEvent(Event e) {
		db.getTransaction().begin();
		db.persist(e);
		db.getTransaction().commit();
	}

	public boolean eventExists(Event e) {
		return (db.find(Event.class, e) == null);
	}

	public boolean register(String userName, String password) {
		if (this.userExsists(userName)) {
			return false;
		} else {
			Registered reg = new Registered(userName, password);
			db.getTransaction().begin();
			db.persist(reg);
			db.getTransaction().commit();
			return true;
		}
	}

	public User Login(String username, String password) {
		if (!this.userExsists(username)) {
			return null;
		} else {
			User u = db.find(User.class, username);
			if (u.getPassword().equals(password)) {
				return u;
			} else {
				return null;
			}
		}
	}

	public void addMoney(float dirua, Registered user) {
		Registered u = (Registered) db.find(User.class, user);
		u.setDirua(u.getDirua() + dirua);
		Mugimendua m = new Mugimendua(dirua, ResourceBundle.getBundle("Etiquetas").getString("EurosEntered"), user);
		u.addMugimendua(m);
		db.getTransaction().begin();
		db.persist(u);
		db.getTransaction().commit();
	}

	public String getMugimenduak(Registered user) {
		TypedQuery<Mugimendua> query = db.createQuery(
				"SELECT m FROM Mugimendua m WHERE m.user.Username='" + user.getUsername() + "'", Mugimendua.class);
		List<Mugimendua> mList = query.getResultList();
		String str = "";
		for (Mugimendua m : mList) {
			str = str + m.toString() + "\n";
		}
		return str;
	}

	public boolean apustuEgin(ApustuEginParameter parameterObject) {
		Registered r = (Registered) db.find(User.class, parameterObject.user);
		Boleto b = null;
		b = boletoBaliozko(parameterObject.kopiatuta, parameterObject.boleto, b);
		if (r.getDirua() - parameterObject.dirua >= 0) {
			Vector<Kuota> kl = new Vector<Kuota>();
			getKuotakAndAdd(parameterObject.kuotak, kl);
			db.getTransaction().begin();
			apustua(parameterObject.user, parameterObject.dirua, parameterObject.kopiatuta, r, b, kl);
			db.getTransaction().commit();
			doApustuaForFollowers(parameterObject.dirua, parameterObject.kuotak, r);
			return true;
		} else
			return false;
	}

	private void doApustuaForFollowers(float dirua, List<String> kuotak, Registered r) {
		for (Jarraitzailea f : r.getFollowers()) {
			apustuEgin(new ApustuEginParameter(f.getJarraitzailea(), dirua * f.getMurriztapena(), kuotak, true, 0));
		}
	}

	private void apustua(Registered user, float dirua, boolean kopiatuta, Registered r, Boleto b, Vector<Kuota> kl) {
		Apustua apustua;
		apustua = new Apustua(dirua, user, kl, kopiatuta, b);
		Mugimendua m = new Mugimendua(dirua, ResourceBundle.getBundle("Etiquetas").getString("HaveStaked"), user);
		r.setDirua(r.getDirua() - dirua);
		r.addApustua(apustua);
		r.addMugimendua(m);
		r.getBoletoak().remove(b);
		for (Kuota k : kl) {
			k.addApustua(apustua);
		}
	}

	private void getKuotakAndAdd(List<String> kuotak, Vector<Kuota> kl) {
		for (String str : kuotak) {
			Kuota k = db.find(Kuota.class, str);
			kl.add(k);
		}
	}

	private Boleto boletoBaliozko(boolean kopiatuta, int boleto, Boleto b) {
		if (!kopiatuta || boleto != 0) {
			b = db.find(Boleto.class, boleto);
		}
		return b;
	}

	public void apustuaEzabatu(Registered u, Integer apustuaID) {
		Apustua a = db.find(Apustua.class, apustuaID);
		Registered r = (Registered) db.find(User.class, u);
		r.setDirua((float) (r.getDirua() + a.getDirua()));
		Mugimendua m = new Mugimendua(a.getDirua(),
				ResourceBundle.getBundle("Etiquetas").getString("getbycancellation"), r);
		r.addMugimendua(m);
		db.getTransaction().begin();
		db.remove(a);
		db.persist(r);
		db.getTransaction().commit();
		for (Jarraitzailea f : r.getFollowers()) {
			Apustua apustu = null;
			for (Apustua apus : f.getJarraitzailea().getApustuak()) {
				if (apus.isKopiatuta()) {
					if (apus.getKuota().equals(a.getKuota()) && apus.getDirua() == a.getDirua() * f.getMurriztapena()) {
						apustu = apus;
					}
				}
			}
			if (apustu != null) {
				apustuaEzabatu(f.getJarraitzailea(), apustu.getBetNumber());
			}
		}
	}

	public void gertaeraEzabatu(Event e) {
		Event gertaera = db.find(Event.class, e);
		List<Question> questions = gertaera.getQuestions();
		List<Kuota> kuotak = new ArrayList<Kuota>();
		for (Question q : questions) {
			kuotak.addAll(q.getFees());
		}
		for (Kuota k : kuotak) {
			for (Apustua a : k.getApustuak()) {
				this.apustuaEzabatu(a.getUser(), a.getBetNumber());
			}
		}
		db.getTransaction().begin();
		db.remove(gertaera);
		db.getTransaction().commit();
	}

	public void emaitzakIpini(String id) {
		Mugimendua m;
		Registered user = null;
		float money = 0, userMoney, biderkatzailea = 0;
		boolean guztiak_ebaluatuta = true;
		boolean guztiak_irabaziak = true;
		db.getTransaction().begin();
		db.find(Kuota.class, id).setEvaluatuta(true);
		db.find(Kuota.class, id).setIrabazia(true);
		Kuota k = db.find(Kuota.class, id);
		for (Kuota kt : k.getQuestion().getFees()) {
			if (!kt.equals(k)) {
				db.find(Kuota.class, kt).setEvaluatuta(true);
				db.find(Kuota.class, kt).setIrabazia(false);
			}
		}
		for (Apustua bet : k.getApustuak()) {
			guztiak_ebaluatuta = true;
			guztiak_irabaziak = true;
			biderkatzailea = 0;
			for (Kuota kt : bet.getKuota()) {
				guztiak_ebaluatuta = guztiak_ebaluatuta && kt.isEvaluatuta();
				guztiak_irabaziak = guztiak_irabaziak && kt.isIrabazia();
				biderkatzailea += kt.getBiderkatzailea();
			}
			if (guztiak_ebaluatuta) {
				user = db.find(Registered.class, bet.getUser());
				if (guztiak_irabaziak) {
					if (bet.getBoleto() != null && bet.getBoleto().getEfektua().equals("up")) {
						money = bet.getDirua() * bet.getBoleto().getBiderkatzailea();
					} else {
						money = bet.getDirua();
					}
					userMoney = user.getDirua() + money * biderkatzailea;
					m = new Mugimendua(money * biderkatzailea,
							ResourceBundle.getBundle("Etiquetas").getString("betWon"), user);
					user.addMugimendua(m);
					user.setDirua(userMoney);
				} else {
					if (bet.getBoleto() != null && bet.getBoleto().getEfektua().equals("down")) {
						money = bet.getDirua() * bet.getBoleto().getBiderkatzailea();
					}
					userMoney = user.getDirua() + money;
					m = new Mugimendua(money, ResourceBundle.getBundle("Etiquetas").getString("DineroPerdida"), user);
					user.addMugimendua(m);
					user.setDirua(userMoney);
				}
			}
		}
		db.getTransaction().commit();
	}

	public float getDirua(Registered u) {
		Registered r = (Registered) db.find(User.class, u);
		return r.getDirua();
	}

	public void jarraitu(Registered jarraitzailea, String jarraitua, float murriztapena) {
		db.getTransaction().begin();
		Registered Jarraitzailea = db.find(Registered.class, jarraitzailea);
		Registered Jarraitua = db.find(Registered.class, jarraitua);
		Jarraitua.addFollower(new Jarraitzailea(Jarraitzailea, Jarraitua, murriztapena));
		db.persist(Jarraitua);
		db.getTransaction().commit();
	}

	public List<Registered> getRegistered() {
		TypedQuery<Registered> query = db.createQuery("SELECT r FROM Registered r", Registered.class);
		return query.getResultList();
	}

	public List<User> getAllUsers() {
		TypedQuery<User> query = db.createQuery("SELECT u FROM User u", User.class);
		return query.getResultList();
	}

	public void mezuaBidali(String mezua, User u1, String u2) {
		if (!mezua.equals("")) {
			User user2 = db.find(User.class, u2);
			User user1 = db.find(User.class, u1);
			TypedQuery<Mezua> query = db.createQuery("SELECT m FROM Mezua m WHERE (m.User1.Username='"
					+ user1.getUsername() + "' AND m.User2.Username='" + u2 + "') OR (m.User1.Username='" + u2
					+ "' AND m.User2.Username='" + user1.getUsername() + "')", Mezua.class);
			List<Mezua> m = query.getResultList();
			db.getTransaction().begin();
			if (m.isEmpty()) {
				if (u1 instanceof Admin) {
					Mezua me = new Mezua(user1, user2, user1.getUsername() + "(Admin): " + mezua + "\n");
					user1.addMezua(me);
					user2.addMezua(me);
				} else {
					Mezua me = new Mezua(user1, user2, user1.getUsername() + "(User): " + mezua + "\n");
					user1.addMezua(me);
					user2.addMezua(me);
				}
			} else {
				String me = m.get(0).getMezua();
				if (u1 instanceof Admin) {
					me = me + user1.getUsername() + "(Admin): " + mezua + "\n";
					user1.getMezuaId(m.get(0).getMezuId()).setMezua(me);
					user2.getMezuaId(m.get(0).getMezuId()).setMezua(me);
				} else {
					me = me + user1.getUsername() + "(User): " + mezua + "\n";
					user1.getMezuaId(m.get(0).getMezuId()).setMezua(me);
					user2.getMezuaId(m.get(0).getMezuId()).setMezua(me);
				}
			}
			db.persist(user1);
			db.persist(user2);
			db.getTransaction().commit();
		}
	}

	public void gertaeraBikoiztu(Event ev, Date firstDay) {
		Event originala = db.find(Event.class, ev);
		db.getTransaction().begin();
		Event duplikatua = new Event(originala.getDescription(), firstDay);
		db.persist(duplikatua);
		db.getTransaction().commit();
	}

	public void boletoaGehitu(String Uname, String efektua, float biderkatzailea) {
		Registered user = db.find(Registered.class, Uname);
		Mugimendua m = null;
		db.getTransaction().begin();
		Boleto b = new Boleto(efektua, biderkatzailea, user);
		if (efektua.equals("down")) {
			m = new Mugimendua(0, ResourceBundle.getBundle("Etiquetas").getString("GanarBoleto"), user);
		} else {
			m = new Mugimendua(0, ResourceBundle.getBundle("Etiquetas").getString("GanarBoleto2"), user);
		}
		user.addBoleto(b);
		user.addMugimendua(m);
		db.getTransaction().commit();
	}
	
	public void removeUser(User u1) {
		db.getTransaction().begin();
		db.remove(db.find(User.class, u1));
		db.getTransaction().commit();
	}
	
	public void removeMessage(User u1, String u2) {
		db.getTransaction().begin();
		db.remove(db.find(Mezua.class, u1.getMezuaErabiltzaile(u2)));
		db.getTransaction().commit();
	}
	
	public List<Question> getAllQuestions(){
		TypedQuery<Question> query = db.createQuery("SELECT q FROM Question q", Question.class);
		return query.getResultList();
	}
	
	public List<Kuota> getAllFees(){
		TypedQuery<Kuota> query = db.createQuery("SELECT k FROM Kuota k", Kuota.class);
		return query.getResultList();
	}
	
	public List<Apustua> getAllBets(){
		TypedQuery<Apustua> query = db.createQuery("SELECT a FROM Apustua a", Apustua.class);
		return query.getResultList();
	}
}
