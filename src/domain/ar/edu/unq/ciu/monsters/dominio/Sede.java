package ar.edu.unq.ciu.monsters.dominio;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import ar.edu.unq.ciu.monsters.dominio.basicos.ConNombre;
import ar.edu.unq.ciu.monsters.evento.Evento;

public abstract class Sede extends ConNombre implements Serializable {

	private Ciudad ciudad;
	private Collection<Evento> eventos = new HashSet<>();
	private int capacidad;

	private static final long serialVersionUID = 3976312405142396559L;

	public Sede() { super(); }
	public Sede(Ciudad ciudad, String nombre, int capacidad) {
		super(nombre);
		this.ciudad = ciudad;
		ciudad.addToSedes(this);
		this.capacidad = capacidad;
	}

	public Collection<Evento> getEventos() { return this.eventos; }
	public void addToEventos(Evento evento) { this.eventos.add(evento); }
	public void removeFromEventos(Evento evento) { this.eventos.remove(evento); }
	
	public Ciudad getCiudad() { return this.ciudad; }
	public void setCiudad(Ciudad ciudad) {
		if (this.ciudad != null) {
			this.ciudad.removeFromSedes(this);
		}
		this.ciudad = ciudad; 
		ciudad.addToSedes(this);
	}
	public Pais getPais() { return this.getCiudad().getPais(); }

	public int getCapacidad() { return capacidad; }
	public void setCapacidad(int capacidad) { this.capacidad = capacidad; }
	
	public Collection<Banda> getBandasQueSePresentan() {
		Set<Banda> lasBandas = new HashSet<>();
		for (Evento evento : this.getEventos()) {
			lasBandas.addAll(evento.getBandas());
		}
		return lasBandas;
	}
	
	public Collection<Banda> getBandasExtranjerasQueSePresentan() {
		return this.getBandasQueSePresentan().stream()
				.filter(banda -> banda.isExtranjera(this.getPais()))
				.collect(Collectors.toSet());
	}
	
	public abstract int getCosto(Evento evento);
	
	public abstract boolean aceptaFestivales();

	public boolean aceptaRecitales() { return true; }

	public abstract boolean aceptaCenasShow();
}
