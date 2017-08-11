package ar.edu.unq.ciu.monsters.web;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

public class MonsterApp extends WebApplication {

	@Override
	public Class<? extends Page> getHomePage() {
		return InitialMenu.class;
	}
	

}
