package ar.edu.unq.ciu.monsters.dominio;

import ar.edu.unq.ciu.monsters.evento.ParticipacionEnEvento;

public interface OrganizadorDeEvento {
	public boolean aceptaBanda(ParticipacionEnEvento banda);
	
	public boolean aceptaSede(Sede sede);
}
