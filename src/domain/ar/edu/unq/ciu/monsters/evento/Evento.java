package ar.edu.unq.ciu.monsters.evento;

import java.io.Serializable;
import java.util.Collection;

import ar.edu.unq.ciu.monsters.dominio.Banda;
import ar.edu.unq.ciu.monsters.dominio.Disco;
import ar.edu.unq.ciu.monsters.dominio.OrganizadorDeEvento;
import ar.edu.unq.ciu.monsters.dominio.Pais;
import ar.edu.unq.ciu.monsters.dominio.Sede;
import ar.edu.unq.ciu.monsters.dominio.basicos.ConNombre;

public abstract class Evento extends ConNombre implements Serializable {
	private Sede sede;
	private int montoFinanciamientoInicial;
	private int precioEntrada;
	private OrganizadorDeEvento organizador;

	private static final long serialVersionUID = 6072289275229375693L;

	public Evento() { }

	public Evento(String nombre) {
		super(nombre);
	}

	public void setSede(Sede sede) {
		if (this.sede != null) {
			this.sede.removeFromEventos(this);
		}
		this.sede = sede;
		sede.addToEventos(this);
	}
	public Sede getSede() { return this.sede; }
	public Pais getPais() { return this.getSede().getCiudad().getPais(); }
	
	public abstract Collection<Banda> getBandas();
	public abstract int getDuracion();

	public OrganizadorDeEvento getOrganizador() { return organizador; }
	public void setOrganizador(OrganizadorDeEvento organizador) {
		this.organizador = organizador;
	}

	public int getPrecioEntrada() { return this.precioEntrada; }
	public void setPrecioEntrada(int precioEntrada) {
		this.precioEntrada = precioEntrada;
	}
	public int getIngresoMaximoPorEntradas() {
		return this.getPrecioEntrada() * this.getSede().getCapacidad();
	}

	public int getMontoFinanciamientoInicial() {
		return montoFinanciamientoInicial;
	}
	public void setMontoFinanciamientoInicial(int montoFinanciamientoInicial) {
		this.montoFinanciamientoInicial = montoFinanciamientoInicial;
	}

	public abstract void addToBandas(Banda banda, Disco discoQuePresenta, int minutos);
	
	public boolean canAddToBandas(Banda banda, Disco discoQuePresenta, int minutos) {
		ParticipacionEnEvento participacion = 
				new ParticipacionEnEvento(banda, discoQuePresenta, minutos);
		return banda.getDiscos().contains(discoQuePresenta)
				&&
				this.getOrganizador().aceptaBanda(participacion);
	};
	
	public abstract boolean incluyeGenero(String genero);
	
	public boolean isEconomicamenteFactible() {
		return this.getIngresoAsegurado() > this.getGastosBasicos();
	}
	public int getIngresoAsegurado() {
		return this.getMontoFinanciamientoInicial() + this.getIngresoAseguradoExtra();
	}
	protected abstract int getIngresoAseguradoExtra();
	
	public abstract int getGastosBasicos();
	
	public boolean puedeHacerseEn(Sede sede) {
		return this.getOrganizador().aceptaSede(sede) && this.esCompatibleConSede(sede);
	}

	protected abstract boolean esCompatibleConSede(Sede sede);
	
	public int getIndiceDeExitoPotencial() {
		int totalCopiasEnPais = this.getBandas().stream()
				.mapToInt(banda -> banda.getTotalCopiasVendidas(this.getPais())).sum();
		int cantBandasDelPais = (int) this.getBandas().stream()
				.filter(banda -> banda.getPais().equals(this.getPais())).count();
		return (int) (totalCopiasEnPais * (1 + (cantBandasDelPais * 0.05)));
	}
	
	
}
