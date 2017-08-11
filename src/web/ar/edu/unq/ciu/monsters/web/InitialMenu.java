package ar.edu.unq.ciu.monsters.web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

import ar.edu.unq.ciu.monsters.web.simpleBandPage.SimpleBandPage;

public class InitialMenu extends WebPage {
	private static final long serialVersionUID = 2458823768222945201L;

	public InitialMenu() {
		super();
		this.add(new Link<String>("simpleBandPage") {
			private static final long serialVersionUID = -3690151360490159036L;

			@Override
			public void onClick() {
				this.setResponsePage(SimpleBandPage.class);
			}
		});
	}
	

}
