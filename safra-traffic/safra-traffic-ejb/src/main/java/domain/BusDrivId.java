package domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class BusDrivId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idBus;
	private Integer idDriver;
	private Date date;
	

	public BusDrivId() {
		super();
	}

	
	
	public BusDrivId(Integer idBus, Integer idDriver, Date date) {
		super();
		this.idBus = idBus;
		this.idDriver = idDriver;
		this.date = date;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((idBus == null) ? 0 : idBus.hashCode());
		result = prime * result
				+ ((idDriver == null) ? 0 : idDriver.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BusDrivId other = (BusDrivId) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (idBus == null) {
			if (other.idBus != null)
				return false;
		} else if (!idBus.equals(other.idBus))
			return false;
		if (idDriver == null) {
			if (other.idDriver != null)
				return false;
		} else if (!idDriver.equals(other.idDriver))
			return false;
		return true;
	}



	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
