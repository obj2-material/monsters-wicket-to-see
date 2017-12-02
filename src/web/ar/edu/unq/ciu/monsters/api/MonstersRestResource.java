package ar.edu.unq.ciu.monsters.api;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.wicketstuff.rest.annotations.MethodMapping;
import org.wicketstuff.rest.resource.gson.GsonRestResource;

import ar.edu.unq.ciu.monsters.dominio.Banda;
import ar.edu.unq.ciu.monsters.store.MonstersStore;

/*
 * Paso 1: armar la clase que extiend GsonRestResource
 * (lo de "Gson" es para que genere Json, hay otra que no sé cómo se llama
 *  para que genere XML)
 */
public class MonstersRestResource extends GsonRestResource {
	private static final long serialVersionUID = 2248095774654428282L;

	// a cada método que quiero que escuche un pedido, 
	// le agrego la annotation @MethodMapping con el path como valor
	@MethodMapping("/bandas")
	public List<BandaDataObject> getBandas() {
		return MonstersStore.store().getBandas().stream()
				.sorted(Comparator.comparing(Banda::getNombre))
				.map(banda -> new BandaDataObject(banda))
				.collect(Collectors.toList());
	}

	// si la URL tiene partes entre llaves, esas partes son variables.
	// el valor va a parar al parámetro del método
	// p.ej. si pongo .../banda/Sumo, el parámetro nombre toma el valor "Sumo"
	@MethodMapping("/banda/{nombre}")	
	public BandaDataObject getBanda(String nombre) {
		return new BandaDataObject(MonstersStore.store().getBandaLlamada(nombre));
	}

}
