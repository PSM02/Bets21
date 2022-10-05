package domain;

import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

	@Id
	String Username;
	String Password;
	Float Dirua;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Mezua> Mezuak=new Vector<Mezua>();
	
	public User(String username, String password) {
		super();
		Username = username;
		Password = password;
		Dirua = (float) 0;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public Float getDirua() {
		return Dirua;
	}

	public void setDirua(Float dirua) {
		Dirua = dirua;
	}
	
	public void addMezua(Mezua mezua) {
		Mezuak.add(mezua);
	}
	
	public Mezua getMezuaErabiltzaile(String u2) {
		Mezua ema = null;
		for (Mezua m:Mezuak) {
			if ((m.User1.getUsername().equals(this.getUsername())&&m.User2.getUsername().equals(u2)) || (m.User1.getUsername().equals(u2)&&m.User2.getUsername().equals(this.getUsername()))) {
				ema = m;
			} 
		}
		return ema;
	}
	
	public Mezua getMezuaId(int id) {
		Mezua ema = null;
		for (Mezua m:Mezuak) {
			if (m.getMezuId()==id) {
				ema = m;
			} 
		}
		return ema;
	}
}
