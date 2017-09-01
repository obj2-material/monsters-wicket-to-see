package ar.edu.unq.ciu.monsters.web.bandCrud;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;

public class AddBandInitialPage extends WebPage {
	private static final long serialVersionUID = -7607228896939415658L;
	
	protected BandAddController controller;

	public AddBandInitialPage() { 
		super();
		this.controller = new BandAddController();
		this.fillForm();
	}

	public AddBandInitialPage(BandAddController _controller) {
		super();
		this.controller = _controller;
		this.fillForm();
	}

	protected void fillForm() {
		Form<BandEditionController> bandMainDataForm = new Form<BandEditionController>("bandForm") {
			private static final long serialVersionUID = -7380319985557184605L;
			@Override
			protected void onSubmit() {
				this.setResponsePage(new EditBandAlbumsPage(AddBandInitialPage.this.controller));
			}
		};
		bandMainDataForm.add(new BandEditionPanel("bandFormFields", this.controller, "Discos"));
		this.add(bandMainDataForm);
	}

}
