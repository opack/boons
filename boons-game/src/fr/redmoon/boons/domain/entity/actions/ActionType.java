package fr.redmoon.boons.domain.entity.actions;

/**
 * Regroupe les actions qui peuvent être réalisées dans le jeu. Le regroupement
 * des actions par type d'entité est effectué au niveau des implémentations des
 * IEntity.
 * 
 * @author didem93n
 * 
 */
public enum ActionType {
	/**
	 * Action générique
	 */
	ACT,
	/**
	 * Ouverture (de porte, de trésor...)
	 */
	OPEN,
	/**
	 * Attaque
	 */
	ATTACK;
}
