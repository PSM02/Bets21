package domain;

import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Registered extends User{
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Mugimendua> mugimenduak=new Vector<Mugimendua>();
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Apustua> apustuak=new Vector<Apustua>();
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Jarraitzailea> followers=new Vector<Jarraitzailea>();
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Boleto> boletoak=new Vector<Boleto>();

	public Registered(String username, String password) {
		super(username, password);
	}
	
	public void addMugimendua(Mugimendua m) {
		mugimenduak.add(m);
	}

	public void addApustua(Apustua a) {
		apustuak.add(a);
	}

	public Vector<Mugimendua> getMugimenduak() {
		return mugimenduak;
	}

	public void setMugimenduak(Vector<Mugimendua> mugimenduak) {
		this.mugimenduak = mugimenduak;
	}

	public Vector<Apustua> getApustuak() {
		return apustuak;
	}

	public void setApustuak(Vector<Apustua> apustuak) {
		this.apustuak = apustuak;
	}

	public Vector<Jarraitzailea> getFollowers() {
		return followers;
	}

	public void setFollowers(Vector<Jarraitzailea> followers) {
		this.followers = followers;
	}
	
	public void addFollower(Jarraitzailea r) {
		followers.add(r);
	}

	public Vector<Boleto> getBoletoak() {
		return boletoak;
	}

	public void setBoletoak(Vector<Boleto> boletoak) {
		this.boletoak = boletoak;
	}
	
	public void addBoleto(Boleto b) {
		boletoak.add(b);
	}
}
