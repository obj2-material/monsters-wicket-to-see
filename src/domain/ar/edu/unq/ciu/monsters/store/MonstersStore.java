package ar.edu.unq.ciu.monsters.store;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ar.edu.unq.ciu.monsters.dominio.Banda;
import ar.edu.unq.ciu.monsters.dominio.Ciudad;
import ar.edu.unq.ciu.monsters.dominio.Disco;
import ar.edu.unq.ciu.monsters.dominio.Discografica;
import ar.edu.unq.ciu.monsters.dominio.Estadio;
import ar.edu.unq.ciu.monsters.dominio.Pais;
import ar.edu.unq.ciu.monsters.dominio.Sede;
import ar.edu.unq.ciu.monsters.dominio.basicos.ConNombre;

public class MonstersStore implements Serializable {

	private static final long serialVersionUID = 1955499359534188843L;

	private static MonstersStore theStore = new MonstersStore();
	
	private Set<Banda> bandas = new HashSet<>();
	private Set<Pais> paises = new HashSet<>();
	private Set<Discografica> discograficas = new HashSet<>();
	
	
	public static MonstersStore store() {
		return theStore;
	}
	
	public MonstersStore() {
		this.fillData();
	}
	
	@SuppressWarnings("unused")
	private void fillData() {
		Pais argentina = new Pais("Argentina");
		Pais mexico = new Pais("Mexico");
		Pais chile = new Pais("Chile");
		Pais espania = new Pais("España");
		Pais uruguay = new Pais("Uruguay");

		Ciudad buenosAires = new Ciudad("Buenos Aires", argentina);
		Ciudad mendoza = new Ciudad("Mendoza", argentina);

		Discografica emi = new Discografica();
		Discografica azul = new Discografica();

		Disco signos = new Disco("Signos", 1986);  
		Disco dynamo = new Disco("Dynamo", 1992);
		Disco laGrasa = new Disco("Grasa de las Capitales", 1979);
		Disco agujeroInterior = new Disco("Agujero interior", 1983);
		Disco locura = new Disco("Locura", 1985);
		Disco divididosPorLaFelicidad = new Disco("Divididos por la Felicidad", 1985);
		Disco afterChabon= new Disco("After Chabon", 1985);
		Disco donde= new Disco("Donde jugarán las niñas", 1997);

		Banda soda = new Banda("Soda Stereo", argentina, "pop", 150000);
		Banda seru = new Banda("Serú Girán", argentina, "rock", 42000);
		Banda virus = new Banda("Virus", argentina, "pop", 30000);
		Banda divididos = new Banda("Divididos", argentina, "rock", 22000); 
		Banda molotov = new Banda("Molotov", mexico, "rock", 20000);
		Banda sumo = new Banda("Sumo", argentina, "reggae", 25000); 

		Estadio luna = new Estadio(buenosAires, "Luna Park", 20000, 15000);

		signos.setCopiasEnPais(argentina, 120000);	
		signos.setCopiasEnPais(chile, 25000);
		dynamo.setCopiasEnPais(argentina, 150000);
		dynamo.setCopiasEnPais(chile, 50000);
		dynamo.setCopiasEnPais(mexico, 40000);
		soda.addToDiscos(signos);
		soda.addToDiscos(dynamo);		

		laGrasa.setProductor(emi);
		laGrasa.setCopiasEnPais(argentina, 300000);
		seru.addToDiscos(laGrasa);

		agujeroInterior.setProductor(azul);
		agujeroInterior.setCopiasEnPais(argentina, 80000);
		agujeroInterior.setCopiasEnPais(chile, 20000);
		virus.addToDiscos(agujeroInterior);
		locura.setProductor(emi);
		locura.setCopiasEnPais(argentina, 130000);
		locura.setCopiasEnPais(mexico, 30000);
		virus.addToDiscos(locura);

		divididosPorLaFelicidad.setProductor(azul);
		divididosPorLaFelicidad.setCopiasEnPais(argentina, 70000);
		sumo.addToDiscos(divididosPorLaFelicidad);
		afterChabon.setProductor(azul);
		afterChabon.setCopiasEnPais(argentina, 130000);
		afterChabon.setCopiasEnPais(uruguay, 1000);
		sumo.addToDiscos(afterChabon);

		this.addToPaises(argentina); this.addToPaises(uruguay);
		this.addToPaises(espania); this.addToPaises(chile);
		this.addToPaises(mexico);

		donde.setCopiasEnPais(espania, 4000);
		donde.setCopiasEnPais(mexico, 30000);
		molotov.addToDiscos(donde);

		this.addToBandas(soda); this.addToBandas(seru); this.addToBandas(virus);
		this.addToBandas(sumo); this.addToBandas(divididos); this.addToBandas(molotov);
		
		this.addToDiscograficas(azul); this.addToDiscograficas(emi);
	}
	
	public void addToPaises(Pais pais) {
		this.paises.add(pais);
	}

	public void addToBandas(Banda banda) {
		this.bandas.add(banda);
	}

	public void addToDiscograficas(Discografica discograf) {
		this.discograficas.add(discograf);
	}
	
	public Collection<Banda> getBandas() { return this.bandas; }
	public Collection<Pais> getPaises() { return this.paises; }
	public List<Pais> getPaisesOrdenados() {
		List<Pais> paisesOrdenados = new ArrayList<>(this.paises);
		paisesOrdenados.sort(Comparator.comparing(pais -> pais.getNombre()));
		return paisesOrdenados;
	}
	public Collection<Discografica> getDiscograficas() { return this.discograficas; }
	
	public Collection<Ciudad> getCiudades() {
		return this.getPaises().stream()
				.flatMap((pais) -> pais.getCiudades().stream())
				.collect(Collectors.toSet());
	}

	public Collection<Sede> getSedes() {
		return this.getCiudades().stream()
				.flatMap((ciudad) -> ciudad.getSedes().stream())
				.collect(Collectors.toSet());
	}

	public Collection<Disco> getDiscos() {
		return this.getBandas().stream()
				.flatMap((banda) -> banda.getDiscos().stream())
				.collect(Collectors.toSet());
	}

	public Pais getPaisLlamado(String nombre) {
		return this.getObjectLlamado(this.getPaises(), nombre);
	}

	public Ciudad getCiudadLlamada(String nombre) {
		return this.getObjectLlamado(this.getCiudades(), nombre);
	}

	public Sede getSedeLlamada(String nombre) {
		return this.getObjectLlamado(this.getSedes(), nombre);
	}

	public Banda getBandaLlamada(String nombre) {
		return this.getObjectLlamado(this.getBandas(), nombre);
	}

	public Disco getDiscoLlamado(String nombre) {
		return this.getObjectLlamado(this.getDiscos(), nombre);
	}

	private <T extends ConNombre> T getObjectLlamado(Collection<T> objs, String nombre) {
		return objs.stream()
				.filter((obj) -> obj.getNombre().equals(nombre)).findFirst().get();
	}
}















