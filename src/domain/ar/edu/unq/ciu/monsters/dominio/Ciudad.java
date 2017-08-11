package ar.edu.unq.ciu.monsters.dominio;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

import ar.edu.unq.ciu.monsters.dominio.basicos.ConNombre;
import ar.edu.unq.ciu.monsters.evento.ParticipacionEnEvento;

public class Ciudad extends ConNombre implements OrganizadorDeEvento, Serializable {

	private Pais pais;
	private Collection<Sede> sedes = new HashSet<>();

	private static final long serialVersionUID = -1833283818500033848L;

	public Ciudad() { super(); }
	public Ciudad(String nombre, Pais pais) {
		super(nombre);
		this.pais = pais;
		pais.addToCiudades(this);
	}
	
	public Pais getPais() { return pais; }
	public void setPais(Pais pais) {
		if (this.pais != null) {
			this.pais.removeFromCiudades(this);
		}
		this.pais = pais; 
		pais.addToCiudades(this);
	}
	
	public Collection<Sede> getSedes() { return sedes; }
	public void addToSedes(Sede sede) { this.sedes.add(sede); }
	public void removeFromSedes(Sede sede) { this.sedes.remove(sede); }
	
	public boolean aceptaBanda(ParticipacionEnEvento banda) {
		return banda.getBanda().getPais().equals(this.getPais());
	}

	public Collection<Banda> getBandasExtranjerasEnEventos() {
		return this.getSedes().stream()
				.flatMap(sede -> sede.getBandasExtranjerasQueSePresentan().stream())
				.collect(Collectors.toSet());				
	}

	@Override
	public boolean aceptaSede(Sede sede) {
		return sede.getCiudad().getPais().equals(this.getPais());
	}

}
