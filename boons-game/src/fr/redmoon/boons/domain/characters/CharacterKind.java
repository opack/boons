package fr.redmoon.boons.domain.characters;


/**
 * Les diff�rents types de personnage.
 */
public enum CharacterKind {
	BOON("hero");

	private final String name;

	private CharacterKind(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
