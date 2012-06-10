package forms.dynamicforms;

import play.data.validation.Constraints.Required;

public class Delete {

	@Required
	public boolean isConfirmed = false;
	
	public String validate() {
		String result = null;
		if(!isConfirmed)
			result = "Poistoa ei vahvistettu!";
		return result;
	}
}
