package domain;

import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Entity
public class Apustua {
	
	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer betNumber;
	float dirua;
	@XmlIDREF
	Registered user;
	@XmlIDREF
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Kuota> kuotak=new Vector<Kuota>();
	boolean kopiatuta;
	@XmlIDREF
	Boleto boleto;
	
	public Apustua(float dirua, Registered user, Vector<Kuota> kuota, boolean kop, Boleto b) {
		this.user = user;
		this.dirua = dirua;
		this.kuotak = kuota;
		kopiatuta = kop;
		boleto = b;
	}
	
	public Apustua(float dirua, Registered user, Kuota kuota, boolean kop, Boleto b) {
		this.user = user;
		this.dirua = dirua;
		this.kuotak.add(kuota);
		kopiatuta = kop;
		boleto = b;
	}

	public Apustua(Integer betNumber, float dirua, Registered user, Vector<Kuota> kuota, boolean kop) {
		super();
		this.betNumber = betNumber;
		this.dirua = dirua;
		this.user = user;
		this.kuotak = kuota;
		kopiatuta = kop;
	}

	public Vector<Kuota> getKuotak() {
		return kuotak;
	}

	public void setKuotak(Vector<Kuota> kuotak) {
		this.kuotak = kuotak;
	}

	public Boleto getBoleto() {
		return boleto;
	}

	public void setBoleto(Boleto boleto) {
		this.boleto = boleto;
	}

	public Integer getBetNumber() {
		return betNumber;
	}

	public void setBetNumber(Integer betNumber) {
		this.betNumber = betNumber;
	}

	public float getDirua() {
		return dirua;
	}

	public void setDirua(float dirua) {
		this.dirua = dirua;
	}

	public Registered getUser() {
		return user;
	}

	public void setUser(Registered user) {
		this.user = user;
	}

	public Vector<Kuota> getKuota() {
		return kuotak;
	}

	public void setKuota(Vector<Kuota> kuota) {
		this.kuotak = kuota;
	}

	//@Override
	public String toString() {
		String str="";
		for(Kuota k:kuotak) {
			str += k.getDeskripzioa() + " ";
		}
		return betNumber+" "+str+" "+dirua;
	}
	
	public boolean isKopiatuta() {
		return kopiatuta;
	}

	public void setKopiatuta(boolean kopiatuta) {
		this.kopiatuta = kopiatuta;
	}
	
	public void addKuota(Kuota k) {
		kuotak.add(k);
	}

//	public boolean equals(Apustua a) {
//		if(a.dirua==this.dirua && a.kuota.equals(this.kuota)) {
//			return true;
//		} else return false;
//	}
}