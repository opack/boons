package fr.redmoon.boons.domain.entity;

/**
 * Les différents types d'entité qui peuvent être rencontrées dans le jeu.
 * 
 * @author didem93n
 * 
 */
public enum EntityType {
	/**
	 * Le joueur ou un autre joueur
	 */
	PLAYER,
	/**
	 * Un personnage contrôlé par le jeu
	 */
	NPC,
	/**
	 * Un ennemi
	 */
	ENNEMY,
	/**
	 * Un objet comme une porte, un coffre au trésor, un trou...
	 */
	OBJECT;
}
