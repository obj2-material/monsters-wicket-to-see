package ar.edu.unq.ciu.monsters.web.bandsDiscsCopies;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.ciu.monsters.dominio.Banda;
import ar.edu.unq.ciu.monsters.dominio.Disco;
import ar.edu.unq.ciu.monsters.store.MonstersStore;

public class BandPageController implements Serializable {
	private static final long serialVersionUID = -6056661782844211267L;
	
	private Banda chosenBand;
	private Disco chosenAlbum;
	
	public Banda getChosenBand() { return this.chosenBand; }
	public void setChosenBand(Banda banda) { 
		this.chosenBand = banda;
		this.chosenAlbum = null;
	}
	public Disco getChosenAlbum() { return this.chosenAlbum; }
	public void setChosenAlbum(Disco album) { 
		this.chosenAlbum = album;
	}
	public boolean hasChosenAlbum() { return this.chosenAlbum != null; }
	
	public List<Disco> getAlbumsToShow() {
		return this.getChosenBand().getDiscos();
	}
	
	public List<Banda> getBandsToShow() {
		return MonstersStore.store().getBandas().stream()
				.sorted(Comparator.comparing(Banda::getNombre))
				.collect(Collectors.toList());
	}

}
