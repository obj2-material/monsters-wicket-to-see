package ar.edu.unq.ciu.monsters.evento;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import ar.edu.unq.ciu.monsters.dominio.Banda;
import ar.edu.unq.ciu.monsters.dominio.Disco;
import ar.edu.unq.ciu.monsters.dominio.Sede;

public class CenaShow extends Evento {
	private ParticipacionEnEvento laBanda;	

	public CenaShow() { super("Cena-show ignota"); }
	public CenaShow(String nombre) {
		super(nombre);
	}

	public Banda getBanda() {
		return this.laBanda == null ? null : laBanda.getBanda();
	}

	@Override
	public Collection<Banda> getBandas() {
		return this.laBanda == null ? new ArrayList<>() : Collections.singletonList(laBanda.getBanda());
	}

	@Override
	public int getDuracion() {
		return this.laBanda == null ? 0 : laBanda.getMinutos();
	}

	@Override
	public void addToBandas(Banda banda, Disco discoQuePresenta, int minutos) {
		if (this.canAddToBandas(banda, discoQuePresenta, minutos)) {
			this.laBanda = new ParticipacionEnEvento(banda, discoQuePresenta, minutos);
		} else {
			throw new RuntimeException("no se puede agregar la banda tal cual lo pedido");
		}
	}

	@Override
	public boolean canAddToBandas(Banda banda, Disco discoQuePresenta, int minutos) {
		return super.canAddToBandas(banda, discoQuePresenta, minutos) && this.getBanda() == null;
	}
	
	@Override
	public boolean incluyeGenero(String genero) {
		return genero.equals(this.getBanda().getGenero());
	}

	@Override
	public int getIngresoAseguradoExtra() {
		return this.getIngresoMaximoPorEntradas() / 2;
	}

	@Override
	public int getGastosBasicos() {
		return (int)(this.getBanda().getCachet() * 0.8) + 80000;
	}

	@Override
	protected boolean esCompatibleConSede(Sede sede2) {
		return this.getSede().aceptaCenasShow();
	}

}
