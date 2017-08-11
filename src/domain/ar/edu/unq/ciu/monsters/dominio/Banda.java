package ar.edu.unq.ciu.monsters.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import ar.edu.unq.ciu.monsters.dominio.basicos.ConNombre;

public class Banda extends ConNombre implements Serializable {
	private static final long serialVersionUID = 8741218322033288845L;
	
	private List<Disco> discos = new ArrayList<>();
	private String genero;
	private int cachet;
	private Pais pais;
	
	
	public Banda() { }
	public Banda(String nombre, Pais pais, String genero, int cachet) {
		super(nombre);
		this.pais = pais;
		this.genero = genero; 
		this.cachet = cachet;
	}

	public Pais getPais() { return this.pais; }
	public void setPais(Pais pais) { this.pais = pais; }
	public boolean isExtranjera(Pais pais) { return !this.getPais().equals(pais); }

	public String getGenero() { return this.genero; }
	public void setGenero(String genero) { this.genero = genero; }
	public List<Disco> getDiscos() { return this.discos; }
	public void addToDiscos(Disco disco) { discos.add(disco); }
	public void setDiscos(List<Disco> losDiscos) { this.discos = losDiscos; }
	public int getCachet() { return this.cachet; }
	public void setCachet(int cachet) { this.cachet = cachet; }
	
	public int getTotalCopiasVendidas() {
		return this.discos.stream().mapToInt(disco -> disco.getTotalCopiasVendidas()).sum();
	}

	public int getTotalCopiasVendidas(Pais pais) {
		return this.discos.stream().mapToInt(disco -> disco.getCopiasVendidas(pais)).sum();
	}

	public Disco getUltimoDiscoEditado() {
		return this.discos.parallelStream().max(new Comparator<Disco>() {
			@Override
			public int compare(Disco disco1, Disco disco2) {
				return disco1.getAnio() < disco2.getAnio() ? -1 : 1;
			} 
		}).get();
	}
}
