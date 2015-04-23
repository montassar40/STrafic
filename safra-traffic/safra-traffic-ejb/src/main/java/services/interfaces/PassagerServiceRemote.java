package services.interfaces;

import java.util.List;

import javax.ejb.Remote;

import domain.Passenger;

@Remote
public interface PassagerServiceRemote {

	void addPassager(Passenger passager);

	void editPassager(int id);

	Passenger findPassagerById(int id);

	List<Passenger> findAllPassangers();

	void deletePassager(int id);

}
