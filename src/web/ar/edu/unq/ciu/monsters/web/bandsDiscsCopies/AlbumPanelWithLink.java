package ar.edu.unq.ciu.monsters.web.bandsDiscsCopies;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

import ar.edu.unq.ciu.monsters.dominio.CopiasVendidas;

public class AlbumPanelWithLink extends Panel {
	private static final long serialVersionUID = 6005102500922455262L;

	private BandPageController controller;
	
	public AlbumPanelWithLink(String id, BandPageController pageController) {
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
		
        final Link<String> addCopiesButton = new Link<String>("addCopies") {
			private static final long serialVersionUID = 3138145803437736720L;

			@Override
			public void onClick() {
				this.setResponsePage(new CopiesFormPage(
						AlbumPanelWithLink.this.controller.getChosenBand(), 
						AlbumPanelWithLink.this.controller.getChosenAlbum()
				));
			}
		};
		this.add(addCopiesButton);
	}

	@Override
	public boolean isVisible() {
		return super.isVisible() && this.controller.hasChosenAlbum();
	}
	

}
