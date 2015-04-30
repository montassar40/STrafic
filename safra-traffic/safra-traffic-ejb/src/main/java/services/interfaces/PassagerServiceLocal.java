package services.interfaces;

import java.util.List;

import javax.ejb.Local;

import domain.Passenger;

@Local
public interface PassagerServiceLocal {

	void addPassager(Passenger passager);

	void editPassager(int id);

	Passenger findPassagerById(int id);

	List<Passenger> findAllPassangers();

	void deletePassager(int id);
}
