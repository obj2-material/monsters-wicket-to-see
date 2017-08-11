package ar.edu.unq.ciu.monsters.evento;

public class AuspicioAFestival {
	private String empresa;
	private int monto;
	public AuspicioAFestival(String empresa, int monto) {
		super();
		this.empresa = empresa;
		this.monto = monto;
	}
	public final String getEmpresa() {
		return empresa;
	}
	public final int getMonto() {
		return monto;
	}
	
	
}
