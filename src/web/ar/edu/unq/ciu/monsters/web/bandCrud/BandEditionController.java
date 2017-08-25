package ar.edu.unq.ciu.monsters.web.bandCrud;

import java.io.Serializable;
import java.util.List;

import ar.edu.unq.ciu.monsters.dominio.Pais;
import ar.edu.unq.ciu.monsters.store.MonstersStore;

public class BandEditionController implements Serializable {
	private static final long serialVersionUID = -4445073790663837247L;
	
	private String name;
	private Pais country;
	private int cachet;
	private String musicalGenre;
	
	public BandEditionController() { }

	public String getName() { return this.name; }
	public void setName(String _name) { this.name = _name; }
	
	public Pais getCountry() { return this.country; }
	public void setCountry(Pais _country) { this.country = _country; }
	
	public int getCachet() { return this.cachet; }
	public void setCachet(int _cachet) { this.cachet = _cachet; }
	
	public String getMusicalGenre() { return this.musicalGenre; }
	public void setMusicalGenre(String genre) { this.musicalGenre = genre; }
	
	public List<Pais> getCountriesToShow() {
		return MonstersStore.store().getPaisesOrdenados();
	}
}
