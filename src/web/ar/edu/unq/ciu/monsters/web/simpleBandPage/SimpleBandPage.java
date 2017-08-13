package ar.edu.unq.ciu.monsters.web.simpleBandPage;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.PropertyModel;

import ar.edu.unq.ciu.monsters.store.MonstersStore;

public class SimpleBandPage extends WebPage {
	private static final long serialVersionUID = -5617749468806067306L;

	private SimpleBandPageController controller = new SimpleBandPageController();
	
	public SimpleBandPage() {
		this.controller.setChosenBand(MonstersStore.store().getBandaLlamada("Soda Stereo"));
		
		this.fillBandFile();
		this.fillBandSelectionLinks();
	}


	protected void fillBandFile() {
		this.add(new Label("nombre", new PropertyModel<>(this.controller, "chosenBand.nombre")));
		// en Arena:
		// 		new Label(panel).bindValueToProperty("chosenBand.nombre");
		// (suponiendo que el controller sea el modelo del panel)
		this.add(new Label("pais", new PropertyModel<>(this.controller, "chosenBand.pais.nombre")));
		this.add(new Label("genero", new PropertyModel<>(this.controller, "chosenBand.genero")));
		this.add(new Label("copiasVendidas", new PropertyModel<>(this.controller, "chosenBand.totalCopiasVendidas")));
	}


	protected void fillBandSelectionLinks() {
		this.add(new Link<String>("chooseSoda") {
			private static final long serialVersionUID = -7850955536756349914L;

			@Override
			public void onClick() {
				SimpleBandPage.this.controller.setChosenBand(MonstersStore.store().getBandaLlamada("Soda Stereo"));
			}
		});

		this.add(new Link<String>("chooseVirus") {
			private static final long serialVersionUID = -963918703168170592L;

			@Override
			public void onClick() {
				SimpleBandPage.this.controller.setChosenBand(MonstersStore.store().getBandaLlamada("Virus"));
			}
		});

		this.add(new Link<String>("chooseMolotov") {
			private static final long serialVersionUID = 1122141821886909322L;

			@Override
			public void onClick() {
				SimpleBandPage.this.controller.setChosenBand(MonstersStore.store().getBandaLlamada("Molotov"));
			}
		});
	}

}
