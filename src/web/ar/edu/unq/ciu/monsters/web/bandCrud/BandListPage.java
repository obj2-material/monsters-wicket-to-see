package ar.edu.unq.ciu.monsters.web.bandCrud;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;

import ar.edu.unq.ciu.monsters.dominio.Banda;
import ar.edu.unq.ciu.monsters.store.MonstersStore;

public class BandListPage extends WebPage {
	private static final long serialVersionUID = 8376105337564285484L;

	public BandListPage() {
		super();
		this.fillBandTable();
		this.fillAddBandButton();
	}

	protected void fillAddBandButton() {
		Link<String> addBandAction = new Link<String>("addBand") {
			private static final long serialVersionUID = -901862819067967823L;

			@Override
			public void onClick() {
				this.setResponsePage(EditBandInitialPage.class);
				
			}
		}; 
		this.add(addBandAction);
	}

	protected void fillBandTable() {
		ListView<Banda> listTable = new ListView<Banda>("bands", new PropertyModel<>(this, "bands")) {
			private static final long serialVersionUID = 2471303321870848346L;

			@Override
			protected void populateItem(ListItem<Banda> panel) {
				CompoundPropertyModel<Banda> bandModel = new CompoundPropertyModel<>(panel.getModelObject());
				panel.add(new Label("name", bandModel.bind("nombre")));
				panel.add(new Label("musicalGenre", bandModel.bind("genero")));
				panel.add(new Label("country", bandModel.bind("pais.nombre")));
				panel.add(new Label("cachet", bandModel.bind("cachet")));				
			}
			
		};
		this.add(listTable);
	}
	
	public List<Banda> getBands() {
		return MonstersStore.store().getBandas().stream()
				.sorted(Comparator.comparing(Banda::getNombre))
				.collect(Collectors.toList());
	}

	
}
