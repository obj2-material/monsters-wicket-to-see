package ar.edu.unq.ciu.monsters.dominio;

import ar.edu.unq.ciu.monsters.evento.Evento;

public class Estadio extends Sede {
	private int costoPorHora;
	
	public Estadio() { super(); }
	public Estadio(Ciudad ciudad, String nombre, int capacidad, int costoPorHora) { 
		super(ciudad, nombre, capacidad); 
		this.costoPorHora = costoPorHora;
	}

	@Override
	public int getCosto(Evento evento) {
		int horasOFraccion = evento.getDuracion() / 60;
		if (evento.getDuracion() % 60 != 0) {
			horasOFraccion++;
		}
		return this.costoPorHora * horasOFraccion;
	}

	@Override
	public boolean aceptaFestivales() { return true; }
	
	@Override
	public boolean aceptaCenasShow() { return false; }

}
