package fr.redmoon.boons.domain;

import java.awt.Point;
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
}
