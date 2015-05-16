package services.impl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import services.interfaces.PassagerServiceLocal;
import services.interfaces.PassagerServiceRemote;
import domain.Passenger;

/**
 * Session Bean implementation class PassagerServices
 */
@Stateless
@LocalBean
public class PassagerServices implements PassagerServiceLocal,
		PassagerServiceRemote {

	/**
	 * Default constructor.
	 */
	@PersistenceContext
	private EntityManager entityManager;

	public PassagerServices() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addPassager(Passenger passager) {
		entityManager.persist(passager);
	}

	@Override
	public void editPassager(int id) {
		entityManager.merge(findPassagerById(id));
	}

	@Override
	public Passenger findPassagerById(int id) {
		return entityManager.find(Passenger.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Passenger> findAllPassangers() {
		return entityManager.createQuery(
				"select p from User p where DTYPE= Passenger ").getResultList();
	}

	@Override
	public void deletePassager(int id) {
		entityManager.remove(findPassagerById(id));
	}

	@Override
	public Passenger getPassengerByName(String value) {
		return (Passenger) entityManager.createQuery(
				"select p from User p where DTYPE= Passenger and name = "
						+ value).getSingleResult();
	}

}
