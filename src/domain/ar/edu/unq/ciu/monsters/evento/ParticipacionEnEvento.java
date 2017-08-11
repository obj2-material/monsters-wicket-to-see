package ar.edu.unq.ciu.monsters.evento;

import ar.edu.unq.ciu.monsters.dominio.Banda;
import ar.edu.unq.ciu.monsters.dominio.Disco;

public class ParticipacionEnEvento {
	private Banda banda;
	private Disco discoQuePresenta;
	private int minutos;
	
	public ParticipacionEnEvento(Banda banda, Disco discoQuePresenta, int minutos) {
		this();
		this.banda = banda;
		this.discoQuePresenta = discoQuePresenta;
		this.minutos = minutos;
	}

	public ParticipacionEnEvento() {
	}

	public Banda getBanda() { return banda; }
	public Disco getDiscoQuePresenta() { return discoQuePresenta; }
	public int getMinutos() { return minutos; }

	public void setBanda(Banda banda) {
		this.banda = banda;
	}

	public void setDiscoQuePresenta(Disco discoQuePresenta) {
		this.discoQuePresenta = discoQuePresenta;
	}

	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}
	
}
