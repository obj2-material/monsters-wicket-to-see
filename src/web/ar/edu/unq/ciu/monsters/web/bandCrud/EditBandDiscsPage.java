package ar.edu.unq.ciu.monsters.web.bandCrud;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.CompoundPropertyModel;

public class EditBandDiscsPage extends WebPage {
	private static final long serialVersionUID = -315270021272334416L;
	
	private BandEditionController controller;

	public EditBandDiscsPage(BandEditionController _controller) {
		super();
		this.controller = _controller;
		this.fillMainData();
	}

	protected void fillMainData() {
		CompoundPropertyModel<BandEditionController> controllerModel = new CompoundPropertyModel<>(this.controller);
		this.add(new Label("name", controllerModel.bind("name")));
		this.add(new Label("country", controllerModel.bind("country.nombre")));
		this.add(new Label("cachet", controllerModel.bind("cachet")));
		this.add(new Label("musicalGenre", controllerModel.bind("musicalGenre")));
	}
}
