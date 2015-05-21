package domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Entity implementation class for Entity: Bus
 *
 */
@Entity
public class Bus implements Serializable {

	private Integer id;
	private String num;
	private static final long serialVersionUID = 1L;

	private List<Stop> stops;
	private Line line;
	private List<BusDriv> busDrivers;

	public Bus() {
		super();
	}

	public Bus(String num) {
		super();
		this.num = num;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(unique = true)
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	@OneToMany(mappedBy = "bus")
	@JsonIgnore
	public List<Stop> getStops() {
		return stops;
	}

	public void setStops(List<Stop> stops) {
		this.stops = stops;
	}

	@ManyToOne
	@JsonIgnore
	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	@Override
	public String toString() {
		return "Bus [num=" + num + "]";
	}

	@OneToMany(mappedBy = "bus")
	@JsonIgnore
	public List<BusDriv> getBusDrivers() {
		return busDrivers;
	}

	public void setBusDrivers(List<BusDriv> busDrivers) {
		this.busDrivers = busDrivers;
	}

}
