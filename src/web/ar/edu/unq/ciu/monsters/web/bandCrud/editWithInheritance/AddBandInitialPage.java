package ar.edu.unq.ciu.monsters.web.bandCrud.editWithInheritance;

import org.apache.wicket.markup.html.form.Form;

public class AddBandInitialPage extends EditBandMainDataPage {
	private static final long serialVersionUID = 3828105452372618867L;

	public AddBandInitialPage() { super(); }

	public AddBandInitialPage(BandEditionController _controller) {
		super(_controller);
	}

	protected Form<BandEditionController> buildForm() {
		return new Form<BandEditionController>("bandForm") {
			private static final long serialVersionUID = -7380319985557184605L;
			@Override
			protected void onSubmit() {
				this.setResponsePage(new EditBandAlbumsPage(AddBandInitialPage.this.controller));
			}
		};
	}

	protected String getConfirmButtonLabel() { return "Discos"; } 
	
	protected String getFormTitle() { return "Nueva banda"; }

}
