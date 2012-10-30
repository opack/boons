package fr.redmoon.boons.domain.map;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

/**
 * Repr�sente une carte du monde. Chaque Boon a sa carte du monde.
 * Un monde regroupe plusieurs r�gions, chacune ayant une position
 * dans le monde.
 * @author Yed
 *
 */
public class World {
	private Map<Point, Region> regions;
	
	public World() {
		regions = new HashMap<Point, Region>();
	}
	
	public void addRegion(Point position, Region region) {
		regions.put(position, region);
	}
}
