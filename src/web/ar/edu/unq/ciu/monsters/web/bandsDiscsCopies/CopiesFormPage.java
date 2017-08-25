package ar.edu.unq.ciu.monsters.web.bandsDiscsCopies;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;

import ar.edu.unq.ciu.monsters.dominio.Banda;
import ar.edu.unq.ciu.monsters.dominio.Disco;
import ar.edu.unq.ciu.monsters.store.MonstersStore;

public class CopiesFormPage extends WebPage {
	private static final long serialVersionUID = 8263570456720739583L;
	
	CopiesFormController controller;
	
	public CopiesFormPage(Banda band, Disco album) {
		super();
		this.controller = new CopiesFormController(band, album);
		this.add(this.buildForm());
	}

	public Form<CopiesFormController> buildForm() {
		Form<CopiesFormController> newForm = new Form<CopiesFormController>("addCopiesForm") {
			private static final long serialVersionUID = 9176124418022888414L;

			@Override
			protected void onSubmit() {
				// acción de dominio
				CopiesFormPage.this.controller.doAddCopies();
				// navegación
				this.setResponsePage(new BandPageWithLinkToCopies(
						CopiesFormPage.this.controller.getBand(),
						CopiesFormPage.this.controller.getAlbum()
				));
			}
			
		};
		newForm.add(new DropDownChoice<>(
				// id
				"country", 		
				// binding del campo, acá va a ir la opción seleccionada
				new PropertyModel<>(this.controller, "country"),
				// binding de la lista de items
				new PropertyModel<>(MonstersStore.store(), "paisesOrdenados"),
				// qué se muestra de cada item
				new ChoiceRenderer<>("nombre") 
		));
		newForm.add(new TextField<>("copyCount", new PropertyModel<>(this.controller, "copiesToAdd")));
		return newForm;
	}


}
