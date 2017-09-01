package ar.edu.unq.ciu.monsters.web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

import ar.edu.unq.ciu.monsters.web.bandCrud.editWithInheritance.BandListPage;
import ar.edu.unq.ciu.monsters.web.bandPageWithAlbums.BandPageWithAlbums;
import ar.edu.unq.ciu.monsters.web.bandsDiscsCopies.BandPageWithLinkToCopies;
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
		this.add(new Link<String>("bandPageWithAlbums") {
			private static final long serialVersionUID = -5714398010396372891L;

			@Override
			public void onClick() {
				this.setResponsePage(BandPageWithAlbums.class);
			}
		});
		this.add(new Link<String>("bandsDiscsCopies") {
			private static final long serialVersionUID = 8622777156833620290L;

			@Override
			public void onClick() {
				this.setResponsePage(BandPageWithLinkToCopies.class);
			}
		});
		this.add(new Link<String>("bandCrud") {
			private static final long serialVersionUID = 3805388359507614302L;

			@Override
			public void onClick() {
				this.setResponsePage(BandListPage.class);
			}
		});
	}
	

}
