package ar.edu.unq.ciu.monsters.web.bandPageWithAlbums;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;

import ar.edu.unq.ciu.monsters.dominio.Disco;
import ar.edu.unq.ciu.monsters.store.MonstersStore;

public class BandPageWithAlbums extends WebPage {
	private static final long serialVersionUID = -5617749468806067306L;

	private BandPageController controller = new BandPageController();
	
	public BandPageWithAlbums() {
		this.controller.setChosenBand(MonstersStore.store().getBandaLlamada("Soda Stereo"));
		
		this.fillBandFile();
		this.fillBandSelectionLinks();
		this.fillAlbumList();
		this.add(new AlbumPanel("infoDiscoElegido", this.controller));
	}


	protected void fillBandFile() {
		this.add(new Label("nombre", new PropertyModel<>(this.controller, "chosenBand.nombre")));
		this.add(new Label("pais", new PropertyModel<>(this.controller, "chosenBand.pais.nombre")));
		this.add(new Label("genero", new PropertyModel<>(this.controller, "chosenBand.genero")));
		this.add(new Label("copiasVendidas", new PropertyModel<>(this.controller, "chosenBand.totalCopiasVendidas")));
	}


	protected void fillBandSelectionLinks() {
		this.add(new Link<String>("chooseSoda") {
			private static final long serialVersionUID = -7850955536756349914L;

			@Override
			public void onClick() {
				BandPageWithAlbums.this.controller.setChosenBand(MonstersStore.store().getBandaLlamada("Soda Stereo"));
			}
		});

		this.add(new Link<String>("chooseVirus") {
			private static final long serialVersionUID = -963918703168170592L;

			@Override
			public void onClick() {
				BandPageWithAlbums.this.controller.setChosenBand(MonstersStore.store().getBandaLlamada("Virus"));
			}
		});

		this.add(new Link<String>("chooseMolotov") {
			private static final long serialVersionUID = 1122141821886909322L;

			@Override
			public void onClick() {
				BandPageWithAlbums.this.controller.setChosenBand(MonstersStore.store().getBandaLlamada("Molotov"));
			}
		});
	}

	
	protected void fillAlbumList() {
		this.add(new ListView<Disco>("discos", new PropertyModel<>(this.controller, "chosenBand.discos")) {
			private static final long serialVersionUID = -4547597546545617797L;

			@Override
			protected void populateItem(ListItem<Disco> panel) {
				final Link<String> nombreLink = new Link<String>("nombre") {
					private static final long serialVersionUID = -5776431313490694323L;

					@Override
					public void onClick() {
						BandPageWithAlbums.this.controller.setChosenAlbum(panel.getModelObject());
					}
				};
				nombreLink.setBody(new PropertyModel<>(panel.getModelObject(), "nombre"));
				panel.add(nombreLink);

				panel.add(new Label("anio", new PropertyModel<>(panel.getModelObject(), "anio")));
				panel.add(new Label("copiasVendidas", new PropertyModel<>(panel.getModelObject(), "totalCopiasVendidas")));				
			}
		});
	}


}
