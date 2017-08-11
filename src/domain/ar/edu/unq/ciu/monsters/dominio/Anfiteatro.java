package ar.edu.unq.ciu.monsters.dominio;

import java.util.Collection;
import java.util.HashSet;

import ar.edu.unq.ciu.monsters.evento.Evento;

public class Anfiteatro extends Sede {
	private int costoPorEvento;
	private Collection<String> generosEnPromocion = new HashSet<String>();
	
	public Anfiteatro() { super(); }
	public Anfiteatro(Ciudad ciudad, String nombre, int capacidad, int costoPorEvento) { 
		super(ciudad, nombre, capacidad);
		this.costoPorEvento = costoPorEvento;
	}
	
	public Collection<String> getGenerosEnPromocion() { return this.generosEnPromocion; }
	public void addToGenerosEnPromocion(String genero) { 
		this.generosEnPromocion.add(genero); 
	}
	
	@Override
	public int getCosto(Evento evento) { 
		int result = this.costoPorEvento;
		if (this.generosEnPromocion.stream().anyMatch(genero -> evento.incluyeGenero(genero))) {
			result *= 0.8;
		}
		return result;
	}
	
	@Override
	public boolean aceptaFestivales() { return false; }
	
	@Override
	public boolean aceptaCenasShow() { return true; }
}
