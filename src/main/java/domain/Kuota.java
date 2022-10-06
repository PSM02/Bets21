package domain;

import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;

@Entity
public class Kuota {

	
	float biderkatzailea;
	@Id
	String deskripzioa;
	@XmlIDREF
	private Question question;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Apustua> apustuak=new Vector<Apustua>();
	boolean evaluatuta;
	boolean irabazia;
	
	public Kuota(float biderkatzailea, String deskripzioa, Question quest) {
		super();
		this.biderkatzailea = biderkatzailea;
		this.deskripzioa = deskripzioa;
		this.question = quest;
		evaluatuta = false;
		irabazia = false;
	}
	
	public boolean isEvaluatuta() {
		return evaluatuta;
	}



	public void setEvaluatuta(boolean evaluatuta) {
		this.evaluatuta = evaluatuta;
	}



	public boolean isIrabazia() {
		return irabazia;
	}



	public void setIrabazia(boolean irabazia) {
		this.irabazia = irabazia;
	}



	public float getBiderkatzailea() {
		return biderkatzailea;
	}

	public void setBiderkatzailea(float biderkatzailea) {
		this.biderkatzailea = biderkatzailea;
	}

	public String getDeskripzioa() {
		return deskripzioa;
	}

	public void setDeskripzioa(String deskripzioa) {
		this.deskripzioa = deskripzioa;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Vector<Apustua> getApustuak() {
		return apustuak;
	}

	public void setApustuak(Vector<Apustua> apustuak) {
		this.apustuak = apustuak;
	}
	
	public void addApustua(Apustua apustua) {
		this.apustuak.add(apustua);
	}
	
	public Apustua addApustua(float dirua, Registered user, boolean kop, Boleto b) {
		Apustua a = new Apustua(dirua, user, this, kop, b);
		this.apustuak.add(a);
		return a;
	}
}
