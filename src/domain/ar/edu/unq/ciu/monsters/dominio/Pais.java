package ar.edu.unq.ciu.monsters.dominio;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

import ar.edu.unq.ciu.monsters.dominio.basicos.ConNombre;

public class Pais extends ConNombre implements Serializable {
	private Collection<Ciudad> ciudades = new HashSet<>();

	private static final long serialVersionUID = -60639913902873613L;

	public Pais(String nombre) { super(nombre); }
	
	public Collection<Ciudad> getCiudades() { return this.ciudades; }
	public void addToCiudades(Ciudad ciudad) { this.ciudades.add(ciudad); }
	public void removeFromCiudades(Ciudad ciudad) { this.ciudades.remove(ciudad); }
	
	public Collection<Banda> getBandasExtranjerasEnEventos() {
		return this.getCiudades().stream()
				.flatMap(ciudad -> ciudad.getBandasExtranjerasEnEventos().stream())
				.collect(Collectors.toSet());				
	}

	
	// esto lo necesita Arena
	public void setCiudades(Collection<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}
	
	
}
