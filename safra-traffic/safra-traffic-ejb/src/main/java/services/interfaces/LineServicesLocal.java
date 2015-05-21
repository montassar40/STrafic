package services.interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import domain.Line;
import domain.Station;

@Local
public interface LineServicesLocal {
	Line findLineById(Integer id);

	Boolean addLine(Line line);

	Boolean createLine(Line line, Map<Integer, Station> stations);

	List<Line> findLinesByStation(Integer idStation);

	List<Line> findAllLines();

	Line findLineByName(String name);

	Boolean updateLine(Integer idLine);

	Boolean deleteLine(Integer idLine);
}
