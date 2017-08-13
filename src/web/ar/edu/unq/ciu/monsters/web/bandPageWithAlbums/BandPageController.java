package ar.edu.unq.ciu.monsters.web.bandPageWithAlbums;

import java.io.Serializable;

import ar.edu.unq.ciu.monsters.dominio.Banda;
import ar.edu.unq.ciu.monsters.dominio.Disco;

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
}
