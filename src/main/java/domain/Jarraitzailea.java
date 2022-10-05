package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Entity
public class Jarraitzailea {

	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	private Integer Id;
	private Registered jarraitzailea;
	@XmlIDREF
	private Registered jarraitua;
	private float murriztapena;
	
	public Jarraitzailea(Registered jarraitzailea, Registered jarraitua, float murriztapena) {
		super();
		this.jarraitzailea = jarraitzailea;
		this.jarraitua = jarraitua;
		this.murriztapena = murriztapena;
	}

	public Jarraitzailea(Integer Id, Registered jarraitzailea, Registered jarraitua, float murriztapena) {
		super();
		this.Id = Id;
		this.jarraitzailea = jarraitzailea;
		this.jarraitua = jarraitua;
		this.murriztapena = murriztapena;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer eventNumber) {
		this.Id = eventNumber;
	}

	public Registered getJarraitzailea() {
		return jarraitzailea;
	}

	public void setJarraitzailea(Registered jarraitzailea) {
		this.jarraitzailea = jarraitzailea;
	}

	public Registered getJarraitua() {
		return jarraitua;
	}

	public void setJarraitua(Registered jarraitua) {
		this.jarraitua = jarraitua;
	}

	public float getMurriztapena() {
		return murriztapena;
	}

	public void setMurriztapena(float murriztapena) {
		this.murriztapena = murriztapena;
	}
}
