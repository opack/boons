package fr.redmoon.boons.domain;

/**
 * Un Boon.
 * @author Yed
 *
 */
public class Boon {
	private World world;
	private Genre genre;
	private int age;
	private int deathDay;
	private CharacterKind kind;
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
	public CharacterKind getKind() {
		return kind;
	}
	public void setKind(CharacterKind kind) {
		this.kind = kind;
	}
}
