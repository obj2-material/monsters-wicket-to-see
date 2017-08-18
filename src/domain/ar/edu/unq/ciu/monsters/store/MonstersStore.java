package ar.edu.unq.ciu.monsters.store;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ar.edu.unq.ciu.monsters.dominio.Anfiteatro;
import ar.edu.unq.ciu.monsters.dominio.Banda;
import ar.edu.unq.ciu.monsters.dominio.Ciudad;
import ar.edu.unq.ciu.monsters.dominio.Disco;
import ar.edu.unq.ciu.monsters.dominio.Discografica;
import ar.edu.unq.ciu.monsters.dominio.Estadio;
import ar.edu.unq.ciu.monsters.dominio.Pais;
import ar.edu.unq.ciu.monsters.dominio.Sede;
import ar.edu.unq.ciu.monsters.dominio.basicos.ConNombre;
import ar.edu.unq.ciu.monsters.evento.Festival;

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
		this.fillFestivales();
	}
	
	@SuppressWarnings("unused")
	private void fillData() {
		Pais argentina = new Pais("Argentina");
		Pais mexico = new Pais("Mexico");
		Pais chile = new Pais("Chile");
		Pais espania = new Pais("España");
		Pais uruguay = new Pais("Uruguay");

		this.addToPaises(argentina); this.addToPaises(uruguay);
		this.addToPaises(espania); this.addToPaises(chile);
		this.addToPaises(mexico);

		Ciudad buenosAires = new Ciudad("Buenos Aires", argentina);
		Ciudad mendoza = new Ciudad("Mendoza", argentina);
		Ciudad laPlata = new Ciudad("La Plata", argentina);
		Ciudad gralBelgrano = new Ciudad("General Belgrano", argentina);
		Ciudad montevideo = new Ciudad("Montevideo", uruguay);
		Ciudad sevilla = new Ciudad("Sevilla", espania);

		Discografica emi = new Discografica();
		Discografica azul = new Discografica();

		Disco signos = new Disco("Signos", 1986);  
		Disco dynamo = new Disco("Dynamo", 1992);
		Disco laGrasa = new Disco("Grasa de las Capitales", 1979);
		Disco agujeroInterior = new Disco("Agujero interior", 1983);
		Disco locura = new Disco("Locura", 1985);
		Disco divididosPorLaFelicidad = new Disco("Divididos por la Felicidad", 1985);
		Disco afterChabon= new Disco("After Chabon", 1985);
		Disco donde= new Disco("Dónde jugarán las niñas", 1997);
		Disco magistral= new Disco("Magistral", 2011);

		Banda soda = new Banda("Soda Stereo", argentina, "pop", 150000);
		Banda seru = new Banda("Serú Girán", argentina, "rock", 42000);
		Banda virus = new Banda("Virus", argentina, "pop", 30000);
		Banda miranda = new Banda("Miranda!", argentina, "pop", 1820);
		Banda divididos = new Banda("Divididos", argentina, "rock", 22000); 
		Banda molotov = new Banda("Molotov", mexico, "rock", 20000);
		Banda sumo = new Banda("Sumo", argentina, "reggae", 25000); 

		Estadio luna = new Estadio(buenosAires, "Luna Park", 20000, 15000);
		Estadio river = new Estadio(buenosAires, "Monumental", 60000, 200000);
		Estadio obras = new Estadio(buenosAires, "Club Obras", 12000, 40000);
		Anfiteatro cineEspaniol = new Anfiteatro(gralBelgrano, "Cine-Teatro Español", 350, 2000);
		Estadio unico = new Estadio(laPlata, "Estadio Unico", 50000, 140000);
		Anfiteatro argentino = new Anfiteatro(laPlata, "Teatro Argentino", 12000, 14000);

		signos.setProductor(emi);
		signos.setCopiasEnPais(argentina, 420000);	
		signos.setCopiasEnPais(chile, 125000);
		signos.setCopiasEnPais(uruguay, 22000);
		dynamo.setProductor(azul);
		dynamo.setCopiasEnPais(argentina, 350000);
		dynamo.setCopiasEnPais(chile, 150000);
		dynamo.setCopiasEnPais(mexico, 70000);
		dynamo.setCopiasEnPais(espania, 2000);
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

		donde.setProductor(azul);
		donde.setCopiasEnPais(espania, 4000);
		donde.setCopiasEnPais(mexico, 30000);
		molotov.addToDiscos(donde);

		magistral.setCopiasEnPais(argentina, 75000);
		magistral.setCopiasEnPais(uruguay, 12000);
		magistral.setCopiasEnPais(espania, 3000);
		miranda.addToDiscos(magistral);

		this.addToBandas(soda, seru, virus);
		this.addToBandas(sumo, divididos, molotov, miranda);
		
		this.addToDiscograficas(azul); this.addToDiscograficas(emi);
	}
	
	public void fillFestivales() {
		Festival megaPopFest = new Festival("Mega festival de pop");
		megaPopFest.addToGenerosIndicados("pop");
		megaPopFest.setOrganizador(this.getCiudadLlamada("La Plata"));
		megaPopFest.setSede(this.getSedeLlamada("Estadio Unico"));
		megaPopFest.addToBandas(this.getBandaLlamada("Soda Stereo"), this.getDiscoLlamado("Dynamo"), 120);
		megaPopFest.addToBandas(this.getBandaLlamada("Virus"), this.getDiscoLlamado("Locura"), 70);
		megaPopFest.addToBandas(this.getBandaLlamada("Miranda!"), this.getDiscoLlamado("Magistral"), 20);
		
		// quiero obtener la discografica que produjo Signos
		Discografica azul = this.getDiscoLlamado("Dynamo").getProductor();
		Festival azulFest = new Festival("Vestido de azul");
		azulFest.addToGenerosIndicados("pop", "rock", "reggae");
		azulFest.setOrganizador(azul);
		azulFest.setSede(this.getSedeLlamada("Teatro Argentino"));
		azulFest.addToBandas(this.getBandaLlamada("Soda Stereo"), this.getDiscoLlamado("Signos"), 90);
		azulFest.addToBandas(this.getBandaLlamada("Sumo"), this.getDiscoLlamado("Divididos por la Felicidad"), 60);
		azulFest.addToBandas(this.getBandaLlamada("Molotov"), this.getDiscoLlamado("Dónde jugarán las niñas"), 40);
	}
	
	public void addToPaises(Pais pais) {
		this.paises.add(pais);
	}

	public void addToBandas(Banda...bandas) {
		for (Banda banda : bandas) {
			this.bandas.add(banda);
		}
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















