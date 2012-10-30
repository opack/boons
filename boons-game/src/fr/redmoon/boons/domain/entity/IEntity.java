package fr.redmoon.boons.domain.entity;

import fr.redmoon.boons.domain.entity.actions.IEntityAction;

/**
 * Une entité avec laquelle le joueur ou les ennemis peuvent interagir.
 * <p>
 * Une entité sensible accepte des actions et réagit en conséquence.
 * 
 * @author didem93n
 *
 */
public interface IEntity {

	/**
	 * Retourne le type de l'entité
	 * @return
	 */
	EntityType getType();
	
	/**
	 * Réagit à l'action spécifiée
	 * @param performer L'entité qui effectue l'action
	 * @param action L'action effectuée
	 */
	void reactTo(IEntity performer, IEntityAction action);
}
