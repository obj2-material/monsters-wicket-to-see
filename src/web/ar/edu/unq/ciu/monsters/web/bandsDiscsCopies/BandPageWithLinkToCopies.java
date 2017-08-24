package ar.edu.unq.ciu.monsters.web.bandsDiscsCopies;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;

import ar.edu.unq.ciu.monsters.dominio.Banda;
import ar.edu.unq.ciu.monsters.dominio.Disco;
import ar.edu.unq.ciu.monsters.store.MonstersStore;

public class BandPageWithLinkToCopies extends WebPage {
	private static final long serialVersionUID = -5617749468806067306L;

	private BandPageController controller = new BandPageController();
	
	public BandPageWithLinkToCopies() {
		this.controller.setChosenBand(MonstersStore.store().getBandaLlamada("Soda Stereo"));
		
		this.fillBandFile();
		this.fillBandSelectionLinks();
		this.fillAlbumList();
		this.add(new AlbumPanelWithLink("infoDiscoElegido", this.controller));
	}

	public BandPageWithLinkToCopies(Banda band, Disco album) {
		this();
		this.controller.setChosenBand(band);
		this.controller.setChosenAlbum(album);
	}

	protected void fillBandFile() {
		this.add(new Label("nombre", new PropertyModel<>(this.controller, "chosenBand.nombre")));
		this.add(new Label("pais", new PropertyModel<>(this.controller, "chosenBand.pais.nombre")));
		this.add(new Label("genero", new PropertyModel<>(this.controller, "chosenBand.genero")));
		this.add(new Label("copiasVendidas", new PropertyModel<>(this.controller, "chosenBand.totalCopiasVendidas")));
	}


	protected void fillBandSelectionLinks() {
		this.add(new ListView<Banda>("bandSelector", new PropertyModel<>(this.controller, "bandsToShow")) {
			private static final long serialVersionUID = -4547597546545617797L;

			@Override
			protected void populateItem(ListItem<Banda> panel) {
				Banda theBand = panel.getModelObject();
				final Link<String> chooseBandLink = new Link<String>("chooseBand") {
					private static final long serialVersionUID = -5776431313490694323L;
					
					@Override
					public void onClick() {
						BandPageWithLinkToCopies.this.controller.setChosenBand(theBand);
					}
				};
				chooseBandLink.setBody(new PropertyModel<>(theBand, "nombre"));
				panel.add(chooseBandLink);
			}
		});
	}

	
	protected void fillAlbumList() {
		this.add(new ListView<Disco>("discos", new PropertyModel<>(this.controller, "albumsToShow")) {
			private static final long serialVersionUID = -4547597546545617797L;

			@Override
			protected void populateItem(ListItem<Disco> panel) {
				Disco elDisco = panel.getModelObject();
				final Link<String> nombreLink = new Link<String>("nombre") {
					private static final long serialVersionUID = -5776431313490694323L;
					
					@Override
					public void onClick() {
						BandPageWithLinkToCopies.this.controller.setChosenAlbum(elDisco);
					}
				};
				nombreLink.setBody(new PropertyModel<>(elDisco, "nombre"));
				panel.add(nombreLink);

				panel.add(new Label("anio", new PropertyModel<>(elDisco, "anio")));
				panel.add(new Label("copiasVendidas", new PropertyModel<>(elDisco, "totalCopiasVendidas")));				
			}
		});
	}


}
