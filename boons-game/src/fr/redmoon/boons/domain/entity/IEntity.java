package fr.redmoon.boons.domain.entity;

import fr.redmoon.boons.domain.entity.actions.IEntityAction;

/**
 * Une entit� avec laquelle le joueur ou les ennemis peuvent interagir.
 * <p>
 * Une entit� sensible accepte des actions et r�agit en cons�quence.
 * 
 * @author didem93n
 *
 */
public interface IEntity {

	/**
	 * Retourne le type de l'entit�
	 * @return
	 */
	EntityType getType();
	
	/**
	 * R�agit � l'action sp�cifi�e
	 * @param performer L'entit� qui effectue l'action
	 * @param action L'action effectu�e
	 */
	void reactTo(IEntity performer, IEntityAction action);
}
