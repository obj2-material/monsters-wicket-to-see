package ar.edu.unq.ciu.monsters.web.bandCrud;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;

import ar.edu.unq.ciu.monsters.dominio.Banda;

public class UpdateBandPage extends WebPage {
	private static final long serialVersionUID = 5387786891340417709L;

	protected BandUpdateController controller;
	
	public UpdateBandPage(Banda bandToBeUpdated) {
		super();
		this.controller = new BandUpdateController();
		this.controller.setBandToBeUpdated(bandToBeUpdated);
		this.fillForm();
	}

	protected void fillForm() {
		Form<BandEditionController> bandForm = new Form<BandEditionController>("bandForm") {
			private static final long serialVersionUID = -7380319985557184605L;
			@Override
			protected void onSubmit() {
				// business action
				UpdateBandPage.this.controller.doUpdateBand();
				// navigation
				this.setResponsePage(BandListPage.class);
			}
		};
		bandForm.add(new BandEditionPanel("bandFormFields", this.controller, "Confirmar"));
		this.add(bandForm);
	}

}
