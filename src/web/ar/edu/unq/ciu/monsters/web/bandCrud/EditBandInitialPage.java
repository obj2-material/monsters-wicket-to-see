package ar.edu.unq.ciu.monsters.web.bandCrud;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;

public class EditBandInitialPage extends WebPage {
	private static final long serialVersionUID = 1709591341263118983L;
	
	private BandEditionController controller;
	
	public EditBandInitialPage() {
		super();
		this.controller = new BandEditionController();
		this.fillForm();
	}

	protected void fillForm() {
		Form<BandEditionController> bandMainDataForm = new Form<BandEditionController>("bandForm") {
			private static final long serialVersionUID = -7380319985557184605L;
			@Override
			protected void onSubmit() {
				this.setResponsePage(new EditBandDiscsPage(EditBandInitialPage.this.controller));
			}
		};
		
		CompoundPropertyModel<BandEditionController> controllerModel = new CompoundPropertyModel<>(this.controller);
		bandMainDataForm.add(new TextField<>("name", controllerModel.bind("name")));
		bandMainDataForm.add(new DropDownChoice<>(
				"country"   // id
				, controllerModel.bind("country")   // value binding
				, controllerModel.bind("countriesToShow")   // list-of-options binding
				, new ChoiceRenderer<>("nombre")    // what to show for each option   
		));
		bandMainDataForm.add(new NumberTextField<>("cachet", controllerModel.bind("cachet")));
		bandMainDataForm.add(new TextField<>("musicalGenre", controllerModel.bind("musicalGenre")));
		
		this.addCancelButtonTo(bandMainDataForm);
		this.add(bandMainDataForm);
	}

	protected void addCancelButtonTo(WebMarkupContainer where) {
		Link<String> cancelAction = new Link<String>("cancelAction") {
			private static final long serialVersionUID = 1113100660455388359L;

			@Override
			public void onClick() {
				this.setResponsePage(BandListPage.class);
			}
		}; 
		where.add(cancelAction);
	}

}
