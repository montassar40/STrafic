package services.utilities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import services.interfaces.StationServicesLocal;
import domain.Bus;
import domain.BusMan;
import domain.Driver;
import domain.Line;
import domain.Passenger;
import domain.Station;
import domain.Stop;

/**
 * Session Bean implementation class PopulateDataBase
 */
@Singleton
@Startup
public class PopulateDataBase {
	@EJB
	private StationServicesLocal stationServicesLocal;
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public PopulateDataBase() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void populateDb() {
		Bus bus1 = new Bus("Bus01");
		Bus bus2 = new Bus("Bus02");
		Bus bus3 = new Bus("Bus03");
		Bus bus4 = new Bus("Bus04");

		Line line1 = new Line("10 Décembre - Ariana El Soghra");
		Line line2 = new Line("Passage - Ariana");

		Station station10 = new Station("10 Décembre");
		Station station11 = new Station("Magasin Magro");
		Station station12 = new Station("Enkhilette");
		Station station13 = new Station("Pole Technologique El Ghazela");
		Station station14 = new Station("Ariana El Soghra");

		Station station21 = new Station("Passage");
		Station station22 = new Station("Cité el Khathra");
		Station station23 = new Station("Lycée Technique Arina");
		Station station24 = new Station("Ariana");

		List<Bus> listBuses1 = new ArrayList<>();
		List<Bus> listBuses2 = new ArrayList<>();

		listBuses1.add(bus1);
		listBuses1.add(bus2);

		listBuses2.add(bus3);
		listBuses2.add(bus4);

		line1.linkBusesToThisLine(listBuses1);
		line2.linkBusesToThisLine(listBuses2);

		Driver driver1 = new Driver(1, "Matin", "Mohamed", "med@driver.tn",
				"0000");
		entityManager.persist(driver1);

		Driver driver2 = new Driver(1, "Matin", "Saleh", "saleh@driver.tn",
				"0000");
		entityManager.persist(driver2);

		Driver driver3 = new Driver(1, "Aprés midi", "Nidhal",
				"nidhal@driver.tn", "0000");
		entityManager.persist(driver3);

		Driver driver4 = new Driver(1, "Aprés midi", "Samir",
				"samir@driver.tn", "0000");
		entityManager.persist(driver4);

		BusMan busMan = new BusMan(5, "Seif", "seif@man.tn", "0000");
		entityManager.persist(busMan);

		BusMan busMan2 = new BusMan(5, "Amine", "amine@man.tn", "0000");
		entityManager.persist(busMan2);

		try {
			Passenger passenger = new Passenger((Double) 120.0,
					new SimpleDateFormat("MM/dd/yyyy").parse("11/25/1991"),
					true, "Patricia", "patricia@passenger.tn", "0000");
			entityManager.persist(passenger);
		} catch (Exception e) {
		}

		// Creation of a line

		Map<Integer, Station> stations1 = new HashMap();
		Map<Integer, Station> stations2 = new HashMap();

		stations1.put(0, station10);
		stations1.put(1, station11);
		stations1.put(2, station12);
		stations1.put(2, station13);
		stations1.put(2, station14);

		stations2.put(0, station21);
		stations2.put(1, station22);
		stations2.put(2, station23);
		stations2.put(1, station24);

		entityManager.persist(line1);
		entityManager.persist(line2);

		entityManager.persist(station10);
		entityManager.persist(station11);
		entityManager.persist(station12);
		entityManager.persist(station13);
		entityManager.persist(station14);
		entityManager.persist(station21);
		entityManager.persist(station22);
		entityManager.persist(station23);
		entityManager.persist(station24);

		entityManager.persist(bus1);
		entityManager.persist(bus2);
		entityManager.persist(bus3);
		entityManager.persist(bus4);

		stationServicesLocal.createLine(line1, stations1);

		Stop stop1 = new Stop(0, stationServicesLocal.findBusByName("Bus01"),
				stationServicesLocal.findStationByName("10 Décembre"));

		entityManager.persist(stop1);

	}

}
