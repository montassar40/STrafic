package domain;

import java.io.Serializable;





import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
public class BusDriv implements Serializable{

	private BusDrivId busDrivId;
	
	private static final long serialVersionUID = 1L;
	
	private Bus bus;
	private Driver driver;
	
	
	public BusDriv() {
		super();
	}
	
	
	public BusDriv(BusDrivId busDrivId, Bus bus, Driver driver) {
		super();
		this.busDrivId = busDrivId;
		this.bus = bus;
		this.driver = driver;
	}

	@EmbeddedId
	public BusDrivId getBusDrivId() {
		return busDrivId;
	}
	public void setBusDrivId(BusDrivId busDrivId) {
		this.busDrivId = busDrivId;
	}
	
	@ManyToOne
	@JoinColumn(name = "idBus", referencedColumnName = "id", insertable = false, updatable = false)
	@JsonIgnore
	public Bus getBus() {
		return bus;
	}
	public void setBus(Bus bus) {
		this.bus = bus;
	}
	
	@ManyToOne
	@JoinColumn(name = "idDriver", referencedColumnName = "id", insertable = false, updatable = false)
	@JsonIgnore
	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	
}
