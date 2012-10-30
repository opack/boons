package fr.redmoon.boons.domain.entity.actions;

/**
 * Regroupe les actions qui peuvent �tre r�alis�es dans le jeu. Le regroupement
 * des actions par type d'entit� est effectu� au niveau des impl�mentations des
 * IEntity.
 * 
 * @author didem93n
 * 
 */
public enum ActionType {
	/**
	 * Action g�n�rique
	 */
	ACT,
	/**
	 * Ouverture (de porte, de tr�sor...)
	 */
	OPEN,
	/**
	 * Attaque
	 */
	ATTACK;
}
