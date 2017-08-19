package ar.edu.unq.ciu.monsters.web.bandsDiscsCopies;

import ar.edu.unq.ciu.monsters.dominio.Banda;
import ar.edu.unq.ciu.monsters.dominio.Disco;
import ar.edu.unq.ciu.monsters.dominio.Pais;

public class CopiesFormController {

	private Pais country;
	private Banda band;
	private Disco album;
	private int copiesToAdd;
	
	public CopiesFormController(Banda _band, Disco _album) {
		this.band = _band;
		this.album = _album;
		this.copiesToAdd = 0;
	}
	
	public Banda getBand() { return this.band; }
	public Disco getAlbum() { return this.album; }
	public Pais getCountry() { return this.country; }
	public void setCountry(Pais _country) { this.country = _country; }
	public int getCopiesToAdd() { return this.copiesToAdd; }
	public void setCopiesToAdd(int howMany) { this.copiesToAdd = howMany; }

	/**
	 * Realiza la accion de negocio de agregar la cantidad de copias al disco 
	 */
	public void doAddCopies() {
		this.getAlbum().agregarCopias(this.getCountry(), this.getCopiesToAdd());
	}

}
