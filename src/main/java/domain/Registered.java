package domain;

import java.util.ArrayList;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Registered extends User{
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private ArrayList<Mugimendua> mugimenduak=new ArrayList<Mugimendua>();
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private ArrayList<Apustua> apustuak=new ArrayList<Apustua>();
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private ArrayList<Jarraitzailea> followers=new ArrayList<Jarraitzailea>();
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

	public ArrayList<Mugimendua> getMugimenduak() {
		return mugimenduak;
	}

	public void setMugimenduak(ArrayList<Mugimendua> mugimenduak) {
		this.mugimenduak = mugimenduak;
	}

	public ArrayList<Apustua> getApustuak() {
		return apustuak;
	}

	public void setApustuak(ArrayList<Apustua> apustuak) {
		this.apustuak = apustuak;
	}

	public ArrayList<Jarraitzailea> getFollowers() {
		return followers;
	}

	public void setFollowers(ArrayList<Jarraitzailea> followers) {
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
