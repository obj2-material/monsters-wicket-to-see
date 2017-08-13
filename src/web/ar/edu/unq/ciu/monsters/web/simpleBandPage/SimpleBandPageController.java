package ar.edu.unq.ciu.monsters.web.simpleBandPage;

import java.io.Serializable;

import ar.edu.unq.ciu.monsters.dominio.Banda;

public class SimpleBandPageController implements Serializable {
	private static final long serialVersionUID = -1743865769301967670L;
	
	private Banda chosenBand;
	
	public Banda getChosenBand() { return this.chosenBand; }
	public void setChosenBand(Banda banda) { 
		this.chosenBand = banda;
	}
}
