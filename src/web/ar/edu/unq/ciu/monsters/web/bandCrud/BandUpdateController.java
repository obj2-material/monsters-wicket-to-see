package ar.edu.unq.ciu.monsters.web.bandCrud;

import ar.edu.unq.ciu.monsters.dominio.Banda;

public class BandUpdateController extends BandEditionController {
	private static final long serialVersionUID = 6929446597691515513L;
	
	private Banda bandBeingUpdated;

	public void setBandToBeUpdated(Banda band) {
		this.bandBeingUpdated = band;
		this.setName(band.getNombre());
		this.setCountry(band.getPais());
		this.setCachet(band.getCachet());
		this.setMusicalGenre(band.getGenero());
	}
	
	public void doUpdateBand() {
		this.bandBeingUpdated.setNombre(this.getName());
		this.bandBeingUpdated.setPais(this.getCountry());
		this.bandBeingUpdated.setCachet(this.getCachet());
		this.bandBeingUpdated.setGenero(this.getMusicalGenre());
	}


}
