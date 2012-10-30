package fr.redmoon.boons.domain.entity.actions;


/**
 * Une action qui est effectuée par un IEntity.
 * <p>
 * Elle est composée d'un type d'action et d'informations complémentaires
 * (définies au niveau des implémentations).
 * 
 * @author didem93n
 * 
 */
public interface IEntityAction {
	/**
	 * Le type générique de l'action
	 */
	ActionType getType();
}
