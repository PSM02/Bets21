package test.dataAccess;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import domain.Admin;
import domain.Apustua;
import domain.Event;
import domain.Jarraitzailea;
import domain.Mezua;
import domain.Mugimendua;
import domain.Question;
import domain.Registered;
import domain.User;

public class TestDataAccess {
	protected  EntityManager  db;
	protected  EntityManagerFactory emf;

	ConfigXML  c=ConfigXML.getInstance();


	public TestDataAccess()  {
		
		System.out.println("Creating TestDataAccess instance");

		open();
		
	}

	
	public void open(){
		
		System.out.println("Opening TestDataAccess instance ");

		String fileName=c.getDbFilename();
		
		if (c.isDatabaseLocal()) {
			  emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			  db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			  db = emf.createEntityManager();
    	   }
		
	}
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}

	public boolean removeEvent(Event ev) {
		System.out.println(">> DataAccessTest: removeEvent");
		Event e = db.find(Event.class, ev.getEventNumber());
		if (e!=null) {
			db.getTransaction().begin();
			db.remove(e);
			db.getTransaction().commit();
			return true;
		} else 
		return false;
    }
		
		public Event addEventWithQuestion(String desc, Date d, String question, float qty) {
			System.out.println(">> DataAccessTest: addEvent");
			Event ev=null;
				db.getTransaction().begin();
				try {
				    ev=new Event(desc,d);
				    ev.addQuestion(question, qty);
					db.persist(ev);
					db.getTransaction().commit();
				}
				catch (Exception e){
					e.printStackTrace();
				}
				return ev;
	    }
		
		public boolean existQuestion(Event ev,Question q) {
			System.out.println(">> DataAccessTest: existQuestion");
			Event e = db.find(Event.class, ev.getEventNumber());
			if (e!=null) {
				return e.DoesQuestionExists(q.getQuestion());
			} else 
			return false;
			
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
					if (user1 instanceof Admin) {
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
					if (user1 instanceof Admin) {
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

}

