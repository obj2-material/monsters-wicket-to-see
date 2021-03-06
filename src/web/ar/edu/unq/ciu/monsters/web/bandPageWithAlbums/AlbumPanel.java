package ar.edu.unq.ciu.monsters.web.bandPageWithAlbums;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

import ar.edu.unq.ciu.monsters.dominio.CopiasVendidas;

public class AlbumPanel extends Panel {
	private static final long serialVersionUID = 6005102500922455262L;

	private BandPageController controller;
	
	public AlbumPanel(String id, BandPageController pageController) {
		super(id);
		this.controller = pageController;

		this.add(new Label("nombreDiscoElegido", new PropertyModel<>(this.controller, "chosenAlbum.nombre")));

		this.add(new ListView<CopiasVendidas>("copiasPorPais", new PropertyModel<>(this.controller, "chosenAlbum.copiasPorPais")) {
			private static final long serialVersionUID = -4589934709866990333L;

			@Override
			protected void populateItem(ListItem<CopiasVendidas> panel) {
				panel.add(new Label("pais", new PropertyModel<>(panel.getModelObject(), "pais.nombre")));
				panel.add(new Label("cantidadCopias", new PropertyModel<>(panel.getModelObject(), "cantidad")));				
			}
		});
	}

	@Override
	public boolean isVisible() {
		return super.isVisible() && this.controller.hasChosenAlbum();
	}
	

}
