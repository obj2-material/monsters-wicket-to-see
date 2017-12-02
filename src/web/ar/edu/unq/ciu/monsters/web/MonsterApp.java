package ar.edu.unq.ciu.monsters.web;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceReference;

import ar.edu.unq.ciu.monsters.api.MonstersRestResource;

public class MonsterApp extends WebApplication {

	@Override
	public Class<? extends Page> getHomePage() {
		return InitialMenu.class;
	}

	/*
	 * Paso 3: agregar el método init() en la clase que extiende WebApplication
	 * (que es la referenciada desde web.xml)
	 * 
	 */
	@Override
	protected void init() {
		super.init();

		// aca "/api" es la primer parte de la URL, va a ser todo
		// ...:8080/api/...
		// MonstersRestResource es la clase del paso 1
		// el resto del código lo copié miserablemente
		this.mountResource("/api", new ResourceReference("restReference") {
			MonstersRestResource resource = new MonstersRestResource();
			@Override
			public IResource getResource() {
				return resource;
			}

		});
}

	

}
