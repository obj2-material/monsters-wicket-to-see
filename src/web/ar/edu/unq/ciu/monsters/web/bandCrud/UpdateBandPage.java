package ar.edu.unq.ciu.monsters.web.bandCrud;

import org.apache.wicket.markup.html.form.Form;

import ar.edu.unq.ciu.monsters.dominio.Banda;

public class UpdateBandPage extends EditBandMainDataPage {
	private static final long serialVersionUID = 5387786891340417709L;

	public UpdateBandPage(Banda bandToBeUpdated) {
		super();
		this.controller.setBandToBeUpdated(bandToBeUpdated);
	}

	protected Form<BandEditionController> buildForm() {
		return new Form<BandEditionController>("bandForm") {
			private static final long serialVersionUID = -7380319985557184605L;
			@Override
			protected void onSubmit() {
				// business action
				UpdateBandPage.this.controller.doUpdateBand();
				// navigation
				this.setResponsePage(BandListPage.class);
			}
		};
	}

	protected String getConfirmButtonLabel() { return "Confirmar"; } 

	protected String getFormTitle() { return "Modificar datos de banda"; }
	
}
