package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Entity
public class Boleto {
	
	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer boletoId;
	String efektua;
	float biderkatzailea;
	@XmlIDREF
	Registered user;
	@XmlIDREF
	Apustua apustu;
	
	public Boleto(String efektua, float biderkatzailea, Registered user) {
		super();
		this.efektua = efektua;
		this.biderkatzailea = biderkatzailea;
		this.user = user;
	}

	public Boleto(Integer boletoId, String efektua, float biderkatzailea, Registered user) {
		super();
		this.boletoId = boletoId;
		this.efektua = efektua;
		this.biderkatzailea = biderkatzailea;
		this.user = user;
	}

	public Integer getBoletoId() {
		return boletoId;
	}

	public void setBoletoId(Integer boletoId) {
		this.boletoId = boletoId;
	}

	public String getEfektua() {
		return efektua;
	}

	public void setEfektua(String efektua) {
		this.efektua = efektua;
	}

	public float getBiderkatzailea() {
		return biderkatzailea;
	}

	public void setBiderkatzailea(float biderkatzailea) {
		this.biderkatzailea = biderkatzailea;
	}

	public Registered getUser() {
		return user;
	}

	public void setUser(Registered user) {
		this.user = user;
	}
	
	public String toString() {
		String str = ""+boletoId;
		if(efektua.equals("up")) {
			str += " "+"gehiago irabazi";
		} else {
			str += " "+"gutxiago galdu";
		}
		return str;
	}
}
