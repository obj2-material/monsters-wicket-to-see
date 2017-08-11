package ar.edu.unq.ciu.monsters.evento;

import ar.edu.unq.ciu.monsters.dominio.Banda;
import ar.edu.unq.ciu.monsters.dominio.Disco;
import ar.edu.unq.ciu.monsters.dominio.Sede;

public class Recital extends EventoMultiBanda {
	private int importePublicidad = 0;
	
	public Recital() { super("Recital ignoto"); }
	public Recital(String nombre) {
		super(nombre);
	}

	public int getImportePublicidad() { return importePublicidad; }
	public void setImportePublicidad(int importePublicidad) {
		this.importePublicidad = importePublicidad;
	}

	public Banda getBandaPrincipal() { 
		return this.getBandas().isEmpty() ? null : this.getBandas().get(0); 
	}	

	public boolean canAddToBandas(Banda banda, Disco discoQuePresenta, int minutos) {
		if (!super.canAddToBandas(banda, discoQuePresenta, minutos)) {
			return false;
		}
		return 
			(this.getBandaPrincipal() == null || 
				(this.getBandaPrincipal().getTotalCopiasVendidas() >= banda.getTotalCopiasVendidas() * 3
				 &&
				 this.getBandaPrincipal().getGenero().equals(banda.getGenero())
				)
			);
	};

	@Override
	public boolean incluyeGenero(String genero) {
		return genero.equals(this.getBandaPrincipal().getGenero());
	}

	@Override
	public int getIngresoAseguradoExtra() {
		return 250000;
	}

	@Override
	public int getGastosBasicos() {
		return this.getBandaPrincipal().getCachet() 
				+ this.getSede().getCosto(this) / 2
				+ this.getImportePublicidad();
	}

	@Override
	protected boolean esCompatibleConSede(Sede sede2) {
		return this.getSede().aceptaRecitales();
	}

}
