package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Entity
public class Mugimendua {

	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer moveNumber;
	float Dirua;
	String Arrazoia;
	@XmlIDREF
	Registered user;
	
	public Mugimendua(Integer moveNumber, float dirua, String arrazoia, Registered user) {
		super();
		this.moveNumber = moveNumber;
		Dirua = dirua;
		Arrazoia = arrazoia;
		this.user = user;
	}

	public Mugimendua(float dirua, String arrazoia, Registered user) {
		super();
		Dirua = dirua;
		Arrazoia = arrazoia;
		this.user = user;
	}

	public Integer getMoveNumber() {
		return moveNumber;
	}

	public void setMoveNumber(Integer moveNumber) {
		this.moveNumber = moveNumber;
	}

	public float getDirua() {
		return Dirua;
	}

	public void setDirua(float dirua) {
		Dirua = dirua;
	}

	public String getArrazoia() {
		return Arrazoia;
	}

	public void setArrazoia(String arrazoia) {
		Arrazoia = arrazoia;
	}

	public User getUser() {
		return user;
	}

	public void setUser(Registered user) {
		this.user = user;
	}
	
	public String toString() {
		return (this.Dirua+" "+this.Arrazoia);
	}
}
