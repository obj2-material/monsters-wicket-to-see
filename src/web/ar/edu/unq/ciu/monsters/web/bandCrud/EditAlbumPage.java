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

public class EditAlbumPage extends WebPage {
	private static final long serialVersionUID = 2856047083765823166L;
	
	private AlbumEditionController controller;
	
	public EditAlbumPage(BandAddController _parentController) {
		super();
		this.controller = new AlbumEditionController(_parentController);
		this.fillForm();
	}
	
	protected void fillForm() {
		Form<AlbumEditionController> albumDataForm = new Form<AlbumEditionController>("albumForm") {
			private static final long serialVersionUID = 7912894019225928872L;

			@Override
			protected void onSubmit() {
				// accion de negocio
				EditAlbumPage.this.controller.accept();
				// navegacion
				this.setResponsePage(new EditBandAlbumsPage(EditAlbumPage.this.controller.getParentController()));
			}
		};
		
		CompoundPropertyModel<AlbumEditionController> controllerModel = new CompoundPropertyModel<>(this.controller);
		albumDataForm.add(new TextField<>("name", controllerModel.bind("name")));
		albumDataForm.add(new DropDownChoice<>(
				"company"   // id
				, controllerModel.bind("company")   // value binding
				, controllerModel.bind("companiesToShow")   // list-of-options binding
				, new ChoiceRenderer<>("nombre")    // what to show for each option   
		));
		albumDataForm.add(new NumberTextField<>("year", controllerModel.bind("year")));
		albumDataForm.add(new NumberTextField<>("copiesInArgentina", controllerModel.bind("copiesInArgentina")));
		
		this.addCancelButtonTo(albumDataForm);
		this.add(albumDataForm);
	}

	protected void addCancelButtonTo(WebMarkupContainer where) {
		Link<String> cancelAction = new Link<String>("cancelAction") {
			private static final long serialVersionUID = 6343539539239975497L;

			@Override
			public void onClick() {
				this.setResponsePage(new EditBandAlbumsPage(EditAlbumPage.this.controller.getParentController()));
			}
		}; 
		where.add(cancelAction);
	}


}
