package fr.redmoon.boons.domain.entity;

/**
 * Les diff�rents types d'entit� qui peuvent �tre rencontr�es dans le jeu.
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
	 * Un personnage contr�l� par le jeu
	 */
	NPC,
	/**
	 * Un ennemi
	 */
	ENNEMY,
	/**
	 * Un objet comme une porte, un coffre au tr�sor, un trou...
	 */
	OBJECT;
}
