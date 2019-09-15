package br.com.api.utils.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;

import br.com.api.model.MessageModel;

public class EmailValidator implements Validator {
	
	private MessageModel message = new MessageModel();

	public void validate(FacesContext context, UIComponent component, Object toValidate) {
		boolean isValid = true;
		String value = null;

		if ((context == null) || (component == null)) {
			throw new NullPointerException();
		}
		
		if (!(component instanceof UIInput)) {
			return;
		}
		
		if (null == toValidate) {
			return;
		}
		
		value = toValidate.toString();
		int atIndex = value.indexOf('@');
		
		if (atIndex < 0) {
			isValid = false;
		} else if (value.lastIndexOf('.') < atIndex) {
			isValid = false;
		}
		
		if ( !isValid ) {
			context.addMessage("EmailValidator", new FacesMessage(message.getEmailNotValid()));
		}
	}
}
