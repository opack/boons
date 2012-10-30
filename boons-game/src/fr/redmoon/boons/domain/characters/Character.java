package fr.redmoon.boons.domain.characters;

/**
 * Un personnage.
 * @author Yed
 *
 */
public class Character {
	private CharacterKind kind;
	
	public CharacterKind getKind() {
		return kind;
	}
	public void setKind(CharacterKind kind) {
		this.kind = kind;
	}
}
