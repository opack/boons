package fr.redmoon.boons.domain.characters;

import fr.redmoon.boons.domain.map.World;

/**
 * Un Boon.
 * @author Yed
 *
 */
public class Boon extends Character {
	private World world;
	private Genre genre;
	private int age;
	private int deathDay;
	public World getWorld() {
		return world;
	}
	public void setWorld(World world) {
		this.world = world;
	}
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getDeathDay() {
		return deathDay;
	}
	public void setDeathDay(int deathDay) {
		this.deathDay = deathDay;
	}
}
