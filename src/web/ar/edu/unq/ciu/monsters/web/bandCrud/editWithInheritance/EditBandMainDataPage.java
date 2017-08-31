package ar.edu.unq.ciu.monsters.web.bandCrud.editWithInheritance;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;

public abstract class EditBandMainDataPage extends WebPage {
	private static final long serialVersionUID = -4932440411621013914L;
	
	protected BandEditionController controller;
	
	public EditBandMainDataPage() {
		super();
		this.controller = new BandEditionController();
		this.add(new Label("formTitle", this.getFormTitle()));
		this.fillForm();
	}
	
	public EditBandMainDataPage(BandEditionController _controller) {
		this();
		this.controller = _controller;
	}
	
	protected void fillForm() {
		Form<BandEditionController> bandMainDataForm = this.buildForm();
		
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
		bandMainDataForm.add(new Label("confirmButtonLabel", this.getConfirmButtonLabel()));
		
		this.addCancelButtonTo(bandMainDataForm);
		this.add(bandMainDataForm);
	}

	protected abstract Form<BandEditionController> buildForm();
	
	protected abstract String getConfirmButtonLabel(); 
	
	protected abstract String getFormTitle();
	
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
