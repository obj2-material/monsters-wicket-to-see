package ar.edu.unq.ciu.monsters.web.bandCrud;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.ciu.monsters.dominio.Discografica;
import ar.edu.unq.ciu.monsters.store.MonstersStore;

public class AlbumEditionController implements Serializable {
	private static final long serialVersionUID = 7230995521136850909L;
	
	private String name;
	private Discografica company;
	private int year;
	private int copiesInArgentina;
	private BandEditionController parentController;
	
	public AlbumEditionController(BandEditionController _parentController) { 
		super();
		this.parentController = _parentController;
	}
	
	public AlbumEditionController(BandEditionController _parentController, String name, Discografica company, int year, int copiesInArgentina) {
		this(_parentController);
		this.name = name;
		this.company = company;
		this.year = year;
		this.copiesInArgentina = copiesInArgentina;
	}


	public String getName() { return this.name; }
	public void setName(String _name) { this.name = _name; }
	
	public Discografica getCompany() { return this.company; }
	public void setCompany(Discografica _company) { this.company = _company; }	
	
	public int getYear() { return this.year; }
	public void setYear(int _year) { this.year = _year; }
	
	public int getCopiesInArgentina() { return this.copiesInArgentina; }
	public void setCopiesInArgentina(int copies) { this.copiesInArgentina = copies; }

	public List<Discografica> getCompaniesToShow() {
		return MonstersStore.store().getDiscograficas().stream()
				.sorted(Comparator.comparing(Discografica::getNombre))
				.collect(Collectors.toList());
	}

	public void accept() {
		this.parentController.addToAlbums(this);		
	}

	public BandEditionController getParentController() {
		return this.parentController;
	}	
	
}
