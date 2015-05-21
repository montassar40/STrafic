package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import services.interfaces.LineServicesLocal;
import services.interfaces.StationServicesLocal;
import domain.Line;
import domain.Station;

@ManagedBean
@ViewScoped
public class LineBean {

	private Line line = new Line();
	// private List<Station> stations = new ArrayList<Station>();
	private DataModel<Station> dataModel = new ListDataModel<>();
	private Map<Integer, Station> stationMap = new HashMap<Integer, Station>();
	private List<Line> lines = new ArrayList<>();
	private String erreur500 = "/admin/BusMan/ViewLines?faces-redirect=true";
	private String lista = "/erreur?faces-redirect=true";
	private String navigateUpdate = "/admin/BusMan/updateLine?faces-redirect=true";

	public String getLista() {
		return lista;
	}

	public void setLista(String lista) {
		this.lista = lista;
	}

	public String getNavigateUpdate() {
		return navigateUpdate;
	}

	public void setNavigateUpdate(String navigateUpdate) {
		this.navigateUpdate = navigateUpdate;
	}

	public String getErreur500() {
		return erreur500;
	}

	public void setErreur500(String erreur500) {
		this.erreur500 = erreur500;
	}

	@EJB
	private StationServicesLocal stationServicesLocal;

	@EJB
	private LineServicesLocal lineServicesLocal;

	@PostConstruct
	public void initModel() {
		lines = lineServicesLocal.findAllLines();
	}

	public String doSelect() {
		try {
			return navigateUpdate;
		} catch (Exception e) {
			return erreur500;
		}
	}

	public String doDelete() {
		try {
			lineServicesLocal.deleteLine(line.getId());
			System.out.println("-Delete-" + line.getName());
			return lista;
		} catch (Exception e) {
			return erreur500;
		}
	}

	public String doUpdate() {
		try {
			lineServicesLocal.updateLine(line.getId());
			System.out.println("-Update-" + line.getName());
			return lista;
		} catch (Exception e) {
			return erreur500;
		}
	}

	public String doCreateLine() {
		try {
			stationServicesLocal.createLine(line, stationMap);
			return lista;
		} catch (Exception e) {
			return erreur500;
		}

	}

	public String doDo() {
		System.out.println(line.getName());
		return "";
	}

	public String doSelectStationForCurrentLine() {
		Integer i = stationMap.size();
		stationMap.put(i, dataModel.getRowData());
		System.out.println(stationMap.get(i).getName() + " size "
				+ stationMap.size());
		return "";
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public List<Station> getStations() {
		return stationServicesLocal.findAllStations();
	}

	// public void setStations(List<Station> stations) {
	// this.stations = stations;
	// }

	public DataModel<Station> getDataModel() {
		dataModel.setWrappedData(stationServicesLocal.findAllStations());
		return dataModel;
	}

	public void setDataModel(DataModel<Station> dataModel) {
		this.dataModel = dataModel;
	}

	public Map<Integer, Station> getStationMap() {
		return stationMap;
	}

	public void setStationMap(Map<Integer, Station> stationMap) {
		this.stationMap = stationMap;
	}

	public List<Line> getLines() {
		return lines;
	}

	public void setLines(List<Line> lines) {
		this.lines = lines;
	}

}
