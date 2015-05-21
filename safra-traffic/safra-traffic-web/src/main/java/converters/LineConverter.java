package converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import services.interfaces.LineServicesLocal;
import domain.Line;

@FacesConverter("cl")
public class LineConverter implements Converter {

	@Inject
	private LineServicesLocal lineServicesLocal;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Line line = null;
		if (!value.trim().equals("")) {
			line = lineServicesLocal.findLineByName(value);
		}
		return line;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		String eqString = null;
		if (value == null || value.equals("")) {
			eqString = "";
		} else {
			eqString = ((Line) value).getName();
		}
		return eqString;
	}

}
