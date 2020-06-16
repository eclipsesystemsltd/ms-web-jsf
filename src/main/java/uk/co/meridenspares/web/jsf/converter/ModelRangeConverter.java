package uk.co.meridenspares.web.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("modelRangeConverter")
public class ModelRangeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String modelRangeName) {
		Object obj = null;
		
		if (!modelRangeName.equalsIgnoreCase("NONE")) {
			obj = ModelRangeFactory.findModelRangeByName(modelRangeName);
		}
		
		return obj;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object modelRange) {
		return modelRange != null ? modelRange.toString() : "NONE";
	}

}
