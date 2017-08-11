package ar.edu.unq.ciu.monsters.dominio;

import java.io.Serializable;

public class CopiasVendidas implements Serializable {

	private Pais pais;
	private int cantidad;

	private static final long serialVersionUID = -3330662536068089312L;

	public CopiasVendidas(Pais pais, int cantidad) {
		this();
		this.pais = pais;
		this.cantidad = cantidad;
	}
	
	public CopiasVendidas() {
		super();
	}

	public Pais getPais() { return pais; }
	public void setPais(Pais pais) { this.pais = pais; }
	public int getCantidad() { return cantidad; }
	public void setCantidad(int cantidad) { this.cantidad = cantidad; }

	public void sumarCantidad(int laCantidad) {
		this.cantidad += laCantidad; 
	}
	
}
