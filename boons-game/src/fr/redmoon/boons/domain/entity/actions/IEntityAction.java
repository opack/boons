package fr.redmoon.boons.domain.entity.actions;


/**
 * Une action qui est effectu�e par un IEntity.
 * <p>
 * Elle est compos�e d'un type d'action et d'informations compl�mentaires
 * (d�finies au niveau des impl�mentations).
 * 
 * @author didem93n
 * 
 */
public interface IEntityAction {
	/**
	 * Le type g�n�rique de l'action
	 */
	ActionType getType();
}
