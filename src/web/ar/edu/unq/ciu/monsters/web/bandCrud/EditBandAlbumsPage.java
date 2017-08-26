package ar.edu.unq.ciu.monsters.web.bandCrud;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;

public class EditBandAlbumsPage extends WebPage {
	private static final long serialVersionUID = -315270021272334416L;
	
	private BandEditionController controller;

	public EditBandAlbumsPage(BandEditionController _controller) {
		super();
		this.controller = _controller;
		this.fillMainData();
		this.fillAlbumTable();
		this.fillAddAlbumButton();
		this.fillOkCancelButtons();
	}

	protected void fillMainData() {
		CompoundPropertyModel<BandEditionController> controllerModel = new CompoundPropertyModel<>(this.controller);
		this.add(new Label("name", controllerModel.bind("name")));
		this.add(new Label("country", controllerModel.bind("country.nombre")));
		this.add(new Label("cachet", controllerModel.bind("cachet")));
		this.add(new Label("musicalGenre", controllerModel.bind("musicalGenre")));
	}

	protected void fillAlbumTable() {
		ListView<AlbumEditionController> albumTable = new ListView<AlbumEditionController>("albums", new PropertyModel<>(this.controller, "albums")) {
			private static final long serialVersionUID = 1747585278831915473L;

			@Override
			protected void populateItem(ListItem<AlbumEditionController> albumPanel) {
				CompoundPropertyModel<AlbumEditionController> albumModel = new CompoundPropertyModel<>(albumPanel.getModelObject());
				albumPanel.add(new Label("name", albumModel.bind("name")));
				albumPanel.add(new Label("company", albumModel.bind("company.nombre")));
				albumPanel.add(new Label("year", albumModel.bind("year")));
				albumPanel.add(new Label("copiesInArgentina", albumModel.bind("copiesInArgentina")));
			}
		};
		this.add(albumTable);
	}

	protected void fillAddAlbumButton() {
		Link<String> addBandAction = new Link<String>("addAlbum") {
			private static final long serialVersionUID = -532723191007540515L;

			@Override
			public void onClick() {
				this.setResponsePage(new EditAlbumPage(EditBandAlbumsPage.this.controller));
				
			}
		}; 
		this.add(addBandAction);
	}

	protected void fillOkCancelButtons() {
		Link<String> cancelAction = new Link<String>("cancelAction") {
			private static final long serialVersionUID = 3251048626635072477L;

			@Override
			public void onClick() {
				this.setResponsePage(new EditBandInitialPage(EditBandAlbumsPage.this.controller));
				
			}
		}; 
		this.add(cancelAction);

		Link<String> confirmAction = new Link<String>("doAddBand") {
			private static final long serialVersionUID = 6693007657356760682L;

			@Override
			public void onClick() {
				EditBandAlbumsPage.this.controller.doAddBand();
				this.setResponsePage(BandListPage.class);
				
			}
		}; 
		this.add(confirmAction);

	}


}
