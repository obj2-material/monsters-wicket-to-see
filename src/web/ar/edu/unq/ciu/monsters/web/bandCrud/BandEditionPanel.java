package ar.edu.unq.ciu.monsters.web.bandCrud;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

public class BandEditionPanel extends Panel {
	private static final long serialVersionUID = -6107787921892324724L;
	
	protected BandEditionController controller;
	protected String submitButtonLabel;
	
	public BandEditionPanel(String id, BandEditionController _controller, String _submitButtonLabel) {
		super(id);
		this.controller = _controller;
		this.submitButtonLabel = _submitButtonLabel;
		this.fillPanel();
	}

	protected void fillPanel() {
		CompoundPropertyModel<BandEditionController> controllerModel = new CompoundPropertyModel<>(this.controller);
		
		// fields
		this.add(new TextField<>("name", controllerModel.bind("name")));
		this.add(new DropDownChoice<>(
				"country"   // id
				, controllerModel.bind("country")   // value binding
				, controllerModel.bind("countriesToShow")   // list-of-options binding
				, new ChoiceRenderer<>("nombre")    // what to show for each option   
		));
		this.add(new NumberTextField<>("cachet", controllerModel.bind("cachet")));
		this.add(new TextField<>("musicalGenre", controllerModel.bind("musicalGenre")));

		// submit button label
		this.add(new Label("submitButtonLabel", this.submitButtonLabel));
		
		// cancel button
		Link<String> cancelAction = new Link<String>("cancelAction") {
			private static final long serialVersionUID = 1113100660455388359L;
			@Override
			public void onClick() {
				this.setResponsePage(BandListPage.class);
			}
		}; 
		this.add(cancelAction);
	}

}
