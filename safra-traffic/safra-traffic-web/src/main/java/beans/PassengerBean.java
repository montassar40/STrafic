package beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import services.interfaces.PassagerServiceLocal;
import domain.Passenger;

@ManagedBean
@ViewScoped
public class PassengerBean {
	private Passenger passenger = new Passenger();
	private List<Passenger> passengers = new ArrayList<>();
	private Boolean visibility = false;

	@EJB
	private PassagerServiceLocal passagerServiceLocal;

	public void doCreatePass(Passenger passenger) {
		passagerServiceLocal.addPassager(passenger);
	}

	public void doSelect() {
		setVisibility(true);
	}

	public String doSaveOrUpdate() {

		passagerServiceLocal.editPassager(passenger.getId());
		setVisibility(false);
		return "";
	}

	public String doDeletePass() {
		passagerServiceLocal.deletePassager(passenger.getId());
		setVisibility(false);
		return "";
	}

	public String doAddBus() {
		passagerServiceLocal.addPassager(passenger);
		;
		setVisibility(false);
		return "";
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public List<Passenger> getPassengers() {
		passengers = passagerServiceLocal.findAllPassangers();
		for (Passenger passenger : passengers) {
			System.out.println(passenger.getName());
		}
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	public Boolean getVisibility() {
		return visibility;
	}

	public void setVisibility(Boolean visibility) {
		this.visibility = visibility;
	}

}
