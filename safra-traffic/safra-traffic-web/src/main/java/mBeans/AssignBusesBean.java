package mBeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import services.interfaces.StationServicesLocal;
import domain.Bus;
import domain.Line;

@ManagedBean
@ViewScoped
public class AssignBusesBean {

	@EJB
	StationServicesLocal stationservicesLocal;

	private Integer idSelectedLine;
	private List<SelectItem> items;
	private List<Line> lines;

	private Line line = new Line();
	private List<Bus> buses = new ArrayList<Bus>();
	private DataModel<Bus> dataModel = new ListDataModel<>();
	private Map<Bus, Boolean> busesMap = new HashMap<Bus, Boolean>();

	public Map<Bus, Boolean> getBusesMap() {
		return busesMap;
	}

	public void setBusesMap(Map<Bus, Boolean> busesMap) {
		this.busesMap = busesMap;
	}

	public String doAssign() {
		checked();
		System.out.println(busesMap.size());
		stationservicesLocal.assignBusesToLine(buses,
				stationservicesLocal.findLineById(idSelectedLine));
		busesMap.clear();
		buses.clear();
		
		return "";
	}

	public String displaySelectedLine() {
		System.out.println(stationservicesLocal.findLineById(idSelectedLine)
				.getName());
		return "";
	}

	public List<SelectItem> getItems() {
		lines = stationservicesLocal.findAllLines();
		items = new ArrayList<SelectItem>(lines.size() + 1);
		items.add(new SelectItem(-1, "Please select a line"));
		for (Line l : lines) {
			items.add(new SelectItem(l.getId(), l.getName()));
		}
		return items;
	}

	public void setItems(List<SelectItem> items) {
		this.items = items;
	}

	public String doAssignBusesToLine() {
		stationservicesLocal.assignBusesToLine(buses, line);
		return "";
	}

	public boolean checked() {
		for (Entry<Bus, Boolean> entry : busesMap.entrySet()) {
			if (entry.getValue()) {
				buses.add(entry.getKey());
			}
		}
		return true;
	}

	public String doSelectBusForCurrentLine() {
		buses.add(dataModel.getRowData());
		System.out.println("Bus selectionné !! "
				+ dataModel.getRowData().getNum() + " size :" + buses.size());
		return "";
	}

	public List<Line> getLines() {
		return lines;
	}

	public void setLines(List<Line> lines) {
		this.lines = lines;
	}

	public Integer getIdSelectedLine() {
		return idSelectedLine;
	}

	public void setIdSelectedLine(Integer idSelectedLine) {
		this.idSelectedLine = idSelectedLine;
	}

	public DataModel<Bus> getDataModel() {
		dataModel.setWrappedData(stationservicesLocal.findAvailableBuses());
		return dataModel;
	}

	public void setDataModel(DataModel<Bus> dataModel) {
		this.dataModel = dataModel;
	}
}
