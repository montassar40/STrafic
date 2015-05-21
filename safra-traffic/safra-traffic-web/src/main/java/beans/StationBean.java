package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import services.interfaces.StationServicesLocal;
import domain.Station;

@ManagedBean
@ViewScoped
public class StationBean {

	@EJB
	StationServicesLocal stationServicesLocal;

	private Station station;
	private List<Station> stations;
	private Boolean visibility = false;

	@PostConstruct
	public void initModel() {
		station = new Station();
		stations = new ArrayList<Station>();
		stations = stationServicesLocal.findAllStations();
	}

	public String doSaveOrUpdate() {
		stationServicesLocal.updateStation(station);
		stations = stationServicesLocal.findAllStations();
		return "";
	}

	public String doSelectStation() {
		setVisibility(true);
		return "";
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public List<Station> getStations() {
		return stations;
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}

	public Boolean getVisibility() {
		return visibility;
	}

	public void setVisibility(Boolean visibility) {
		this.visibility = visibility;
	}
}
