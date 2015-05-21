package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import domain.BusMan;
import domain.Driver;
import domain.Passenger;

@ManagedBean(name = "identity")
@SessionScoped
public class IdentityBean {

	private Object object;

	public IdentityBean() {
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public Boolean hasRole(String role) {
		Boolean response = false;
		switch (role) {
		case "Driver":
			response = object instanceof Driver;
			break;
		case "BusMan":
			response = object instanceof BusMan;
			break;
		case "Passenger":
			response = object instanceof Passenger;
			break;
		}
		return response;
	}

}
