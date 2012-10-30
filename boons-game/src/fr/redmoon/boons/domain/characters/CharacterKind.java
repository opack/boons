package fr.redmoon.boons.domain.characters;


/**
 * Les diff�rents types de personnage.
 */
public enum CharacterKind {
	HERO("hero");

	private final String name;

	private CharacterKind(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
