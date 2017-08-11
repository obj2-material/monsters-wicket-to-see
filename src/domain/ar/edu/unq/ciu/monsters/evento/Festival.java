package ar.edu.unq.ciu.monsters.evento;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import ar.edu.unq.ciu.monsters.dominio.Banda;
import ar.edu.unq.ciu.monsters.dominio.Disco;
import ar.edu.unq.ciu.monsters.dominio.Sede;

public class Festival extends EventoMultiBanda {
	private Collection<AuspicioAFestival> auspicios = new HashSet<>();
	private Collection<String> generosIndicados = new HashSet<>();
	
	public Festival() { super("Festival ignoto"); }
	public Festival(String nombre) {
		super(nombre);
	}
	public Banda getBandaDeCierre() { 
		return this.getBandas().isEmpty() ? null : this.getBandas().get(0); 
	}
	
	public Collection<Banda> getBandasNoDeCierre() {
		return this.getBandas().size() < 2 ? new ArrayList<Banda>() : this.getBandas().subList(1, this.getBandas().size()); 
	}
	
	@Override
	public boolean canAddToBandas(Banda banda, Disco discoQuePresenta, int minutos) {
		if (!super.canAddToBandas(banda, discoQuePresenta, minutos)) {
			return false;
		}
		if (this.getDuracion() + minutos > 12 * 60) {
			return false;
		}
		if (this.getBandaDeCierre() != null && 
				this.getBandaDeCierre().getTotalCopiasVendidas() <= banda.getTotalCopiasVendidas()) {
			return false;
		}
		if (!this.getGenerosIndicados().contains(banda.getGenero())) {
			return false;
		}
		return true;
	};

	public Collection<AuspicioAFestival> getAuspicios() { return this.auspicios; }
	public void addToAuspicios(String empresa, int monto) {
		this.auspicios.add(new AuspicioAFestival(empresa, monto));
	}
	
	public Collection<String> getGenerosIndicados() { return this.generosIndicados; }
	public void addToGenerosIndicados(String genero) { this.generosIndicados.add(genero); }

	@Override
	public boolean incluyeGenero(String genero) {
		return genero.equals(this.getBandaDeCierre().getGenero()) ||
				this.getCantidadDeBandas(genero) >= 3;
	}

	private long getCantidadDeBandas(String genero) {
		return this.getBandas().stream().filter(banda -> banda.getGenero().equals(genero)).count();
	}

	@Override
	public int getIngresoAseguradoExtra() {
		return this.getMontoAuspiciantes();
	}

	protected int getMontoAuspiciantes() {
		return this.auspicios.stream().mapToInt(auspicio -> auspicio.getMonto()).sum();
	}

	@Override
	public int getGastosBasicos() {
		if (this.getBandas().isEmpty()) {
			return 0;
		}
		return this.getBandaDeCierre().getCachet() +
				(this.getBandasNoDeCierre().stream().mapToInt(banda -> banda.getCachet()).sum()) / 2;
	}

	@Override
	protected boolean esCompatibleConSede(Sede sede2) {
		return this.getSede().aceptaFestivales();
	}

	
	// estos los necesita Arena
	public void setAuspicios(Collection<AuspicioAFestival> auspicios) {
		this.auspicios = auspicios;
	}
	public void setGenerosIndicados(Collection<String> generosIndicados) {
		this.generosIndicados = generosIndicados;
	}

	
}
