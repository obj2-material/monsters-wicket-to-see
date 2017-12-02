package ar.edu.unq.ciu.monsters.api;

import ar.edu.unq.ciu.monsters.dominio.Banda;

/*
 * Paso 2: armar el DataObject, la misma idea que lo que hicimos
 * con los DataProvider en Android.
 * Creo que la herramienta mira los atributos, con lo cual ni siquiera harían falta
 * los getter.
 */
public class BandaDataObject {
	private String nombre;
	private String pais;
	private int cachet;
	private String genero;
	private int totalCopiasVendidas;
	
	// para comodidad, le puse un constructor que toma el "objeto inteligente"
	// como parámetro y lo destripa
	public BandaDataObject(Banda banda) {
		super();
		this.nombre = banda.getNombre();
		this.pais = banda.getPais().getNombre();
		this.cachet = banda.getCachet();
		this.genero = banda.getGenero();
		this.totalCopiasVendidas = banda.getTotalCopiasVendidas();
	}

	public String getNombre() { return this.nombre; }
	public String getPais() { return this.pais; }
	public int getCachet() { return this.cachet; }
	public String getGenero() { return this.genero; }
	public int getTotalCopiasVendidas() { return this.totalCopiasVendidas; }

}
