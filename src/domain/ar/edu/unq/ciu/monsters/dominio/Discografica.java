package ar.edu.unq.ciu.monsters.dominio;

import java.io.Serializable;

import ar.edu.unq.ciu.monsters.dominio.basicos.ConNombre;
import ar.edu.unq.ciu.monsters.evento.ParticipacionEnEvento;

public class Discografica extends ConNombre implements OrganizadorDeEvento, Serializable {

	private static final long serialVersionUID = -4477090980686410640L;

	@Override
	public boolean aceptaBanda(ParticipacionEnEvento banda) {
		return banda.getDiscoQuePresenta().getProductor().equals(this)
				||
				banda.getBanda().getUltimoDiscoEditado().getProductor().equals(this);
	}

	@Override
	public boolean aceptaSede(Sede sede) { return true; }

}
