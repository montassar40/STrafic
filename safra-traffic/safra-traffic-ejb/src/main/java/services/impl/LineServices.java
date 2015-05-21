package services.impl;

import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import services.interfaces.LineServicesLocal;
import domain.Line;
import domain.Station;
import domain.Type;

@Stateless
public class LineServices implements LineServicesLocal {

	@PersistenceContext
	private EntityManager entityManager;

	public LineServices() {
	}

	@Override
	public Line findLineById(Integer id) {
		try {
			return entityManager.find(Line.class, id);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Boolean addLine(Line line) {
		Boolean b = false;
		try {
			entityManager.persist(line);
			b = true;
		} catch (Exception e) {
		}
		return b;
	}

	@Override
	public Boolean createLine(Line line, Map<Integer, Station> stations) {
		Boolean b = false;
		try {
			entityManager.persist(line);

			for (int i = 0; i < stations.size(); i++) {
				String typeName = "";
				if (i == 0)
					typeName = "Depart";
				else if (i == stations.size() - 1)
					typeName = "Arrival";
				else
					typeName = "Intermediate";
				Type type = new Type(typeName, i, stations.get(i), line);
				entityManager.persist(type);
			}
			b = true;
		} catch (Exception e) {
		}
		return b;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Line> findLinesByStation(Integer idStation) {
		try {
			Station station = entityManager.find(Station.class, idStation);
			String jpql = "select l from Line l " + "join l.types ls "
					+ "where ls.station= :param1";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("param1", station);

			List<Line> lines = query.getResultList();
			return lines;

		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Line> findAllLines() {
		try {
			return entityManager.createQuery("Select l from Line l")
					.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Line findLineByName(String name) {
		try {
			return (Line) entityManager
					.createQuery("select l from Line l where l.name = :param1")
					.setParameter("param1", name).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Boolean updateLine(Integer idLine) {
		try {
			if (entityManager.merge(findLineById(idLine)) != null)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean deleteLine(Integer idLine) {
		Boolean b = false;
		try {
			entityManager.remove(findLineById(idLine));
			b = true;
			return b;
		} catch (Exception e) {
			return b;
		}
	}
}
