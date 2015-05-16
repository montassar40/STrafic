package converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import mBeans.PassengerController;
import domain.Passenger;

@FacesConverter("cl")
public class PassengerConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null) {
			return null;
		}
		PassengerController PassengerController = context.getApplication()
				.evaluateExpressionGet(context, "#{PassengerController}",
						PassengerController.class);
		Passenger Passenger = PassengerController.doFindPassengerByName(value);
		System.out.println(Passenger);
		return Passenger;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object object) {
		String string = null;
		if (object instanceof Passenger) {
			string = ((Passenger) object).getName();
		}
		return string;
	}

}
