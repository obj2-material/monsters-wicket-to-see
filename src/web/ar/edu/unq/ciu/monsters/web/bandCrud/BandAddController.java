package ar.edu.unq.ciu.monsters.web.bandCrud;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.ciu.monsters.dominio.Banda;
import ar.edu.unq.ciu.monsters.store.MonstersStore;

public class BandAddController extends BandEditionController {
	private static final long serialVersionUID = 5934630388788077391L;

	protected List<AlbumEditionController> albums;

	public BandAddController() {
		super();
		this.albums = new ArrayList<>();
	}

	public List<AlbumEditionController> getAlbums() { return this.albums; }
	public void addToAlbums(AlbumEditionController album) { this.albums.add(album); }
	
	public void doAddBand() {
		Banda newBand = this.buildBand();
		for (AlbumEditionController albumController : this.albums) {
			newBand.addToDiscos(albumController.buildAlbum());
		}
		MonstersStore.store().addToBandas(newBand);
	}
	
	public Banda buildBand() {
		return new Banda(this.getName(), this.getCountry(), this.getMusicalGenre(), this.getCachet());
	}

	
}
