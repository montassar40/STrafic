package mBeans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import services.interfaces.PassagerServiceLocal;
import domain.Passenger;

@ManagedBean
@SessionScoped
public class PassengerController {
	private Passenger passenger;
	private List<Passenger> passengers;
	@EJB
	private PassagerServiceLocal passagerServiceLocal;

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	public Passenger doFindPassengerByName(String value) {
		return passagerServiceLocal.getPassengerByName(value);
	}

}
