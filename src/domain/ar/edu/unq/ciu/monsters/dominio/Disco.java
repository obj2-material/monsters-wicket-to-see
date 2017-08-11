package ar.edu.unq.ciu.monsters.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.ciu.monsters.dominio.basicos.ConNombre;

public class Disco extends ConNombre implements Serializable {
	private int anio;
	private List<CopiasVendidas> copiasPorPais = new ArrayList<CopiasVendidas>();
	private Discografica productor;

	private static final long serialVersionUID = -7679154378199894106L;

	public Disco(String nombre, int anio) {
		super(nombre);
		this.anio = anio;
	}

	public Discografica getProductor() { return productor; }
	public void setProductor(Discografica productor) { this.productor = productor; }

	public int getAnio() { return anio; }
	public List<CopiasVendidas> getCopiasPorPais() { return copiasPorPais; }
	
	/** 
	 * Cambia la cantidad de copias vendidas en el pais, por la cantidad indicada.
	 */
	public void setCopiasEnPais(Pais pais, int cantidad) {
		this.borrarRegistroCopiasVendidasEnPais(pais);
		copiasPorPais.add(new CopiasVendidas(pais, cantidad));
	}

	public void agregarCopias(Pais pais, int cantidad) {
		this.forceRegistroCopiasVendidasEnPais(pais).sumarCantidad(cantidad);
	}

	public int getTotalCopiasVendidas() {
		return this.getCopiasPorPais().stream().mapToInt(copias -> copias.getCantidad()).sum();
	}

	public int getCopiasVendidas(Pais pais) {
		return this.getCopiasPorPais().stream()
				.filter(copias -> copias.getPais().equals(pais))
				.mapToInt(copias -> copias.getCantidad()).sum();
	}
	
	/**
	 * El registro de las ventas de este disco en un pais. 
	 * <code>null</code> si no se registran ventas en el pais solicitado.
	 * @throws RuntimeException si hay mas de un registro, cosa que no deberia ocurrir
	 */
	protected CopiasVendidas getRegistroCopiasVendidasEnPais(Pais pais) {
		List<CopiasVendidas> registrosDelPais = this.getCopiasPorPais().stream()
				.filter(copias -> copias.getPais().equals(pais)).collect(Collectors.toList());
		long cantidadRegistros = registrosDelPais.size(); 
		if (cantidadRegistros > 1) {
			throw new RuntimeException("no deberia haber dos registros del mismo pais en un disco");
		} else if (cantidadRegistros == 1) {
			return registrosDelPais.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * El registro de las ventas de este disco en un pais.
	 * Si no hay, lo crea con cantidad de copias = 0. 
	 */
	protected CopiasVendidas forceRegistroCopiasVendidasEnPais(Pais pais) {
		CopiasVendidas registroDelPais = this.getRegistroCopiasVendidasEnPais(pais);
		if (registroDelPais == null) {
			registroDelPais = new CopiasVendidas(pais, 0);
			copiasPorPais.add(registroDelPais);
		}
		return registroDelPais;
	}

	/**
	 * Se borra el registro de copias vendidas en un pais determinado.
	 * Si no hay tal registro, no se hace nada.
	 */
	protected void borrarRegistroCopiasVendidasEnPais(Pais pais) {
		CopiasVendidas registroABorrar = this.getRegistroCopiasVendidasEnPais(pais);
		if (registroABorrar != null) {
			copiasPorPais.remove(registroABorrar);
		}		
	}


	// estos los necesita Arena
	public void setCopiasPorPais(List<CopiasVendidas> copiasPorPais) {
		this.copiasPorPais = copiasPorPais;
	}	
	public void setAnio(int anio) { this.anio = anio; }
	
}
