package dataAccess;

import java.util.List;

import domain.Registered;

public class ApustuEginParameter {
	public Registered user;
	public float dirua;
	public List<String> kuotak;
	public boolean kopiatuta;
	public int boleto;

	public ApustuEginParameter(Registered user, float dirua, List<String> kuotak, boolean kopiatuta, int boleto) {
		this.user = user;
		this.dirua = dirua;
		this.kuotak = kuotak;
		this.kopiatuta = kopiatuta;
		this.boleto = boleto;
	}
}