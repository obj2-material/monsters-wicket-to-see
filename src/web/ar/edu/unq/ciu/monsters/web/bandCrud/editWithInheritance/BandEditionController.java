package ar.edu.unq.ciu.monsters.web.bandCrud.editWithInheritance;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.ciu.monsters.dominio.Banda;
import ar.edu.unq.ciu.monsters.dominio.Pais;
import ar.edu.unq.ciu.monsters.store.MonstersStore;

public class BandEditionController implements Serializable {
	private static final long serialVersionUID = -4445073790663837247L;
	
	private String name;
	private Pais country;
	private int cachet;
	private String musicalGenre;
	private List<AlbumEditionController> albums;
	private Banda bandBeingUpdated;
	
	public BandEditionController() { 
		this.albums = new ArrayList<>();
		this.bandBeingUpdated = null;
	}
	
	public void setBandToBeUpdated(Banda band) {
		this.bandBeingUpdated = band;
		this.setName(band.getNombre());
		this.setCountry(band.getPais());
		this.setCachet(band.getCachet());
		this.setMusicalGenre(band.getGenero());
	}

	public String getName() { return this.name; }
	public void setName(String _name) { this.name = _name; }
	
	public Pais getCountry() { return this.country; }
	public void setCountry(Pais _country) { this.country = _country; }
	
	public int getCachet() { return this.cachet; }
	public void setCachet(int _cachet) { this.cachet = _cachet; }
	
	public String getMusicalGenre() { return this.musicalGenre; }
	public void setMusicalGenre(String genre) { this.musicalGenre = genre; }
	
	public List<AlbumEditionController> getAlbums() { return this.albums; }
	public void addToAlbums(AlbumEditionController album) { this.albums.add(album); }
	
	public List<Pais> getCountriesToShow() {
		return MonstersStore.store().getPaisesOrdenados();
	}

	public void doAddBand() {
		Banda newBand = this.buildBand();
		for (AlbumEditionController albumController : this.albums) {
			newBand.addToDiscos(albumController.buildAlbum());
		}
		MonstersStore.store().addToBandas(newBand);
	}
	
	public void doUpdateBand() {
		this.bandBeingUpdated.setNombre(this.getName());
		this.bandBeingUpdated.setPais(this.getCountry());
		this.bandBeingUpdated.setCachet(this.getCachet());
		this.bandBeingUpdated.setGenero(this.getMusicalGenre());
	}
	
	public Banda buildBand() {
		return new Banda(this.getName(), this.getCountry(), this.getMusicalGenre(), this.getCachet());
	}
	
	
}
