package fr.redmoon.boons.domain.entity.actions;

public class AttackAction implements IEntityAction {
	
//	private final Weapon weapon;
	private final int damage;
	
	public AttackAction(final int damage){
//		this.weapon = weapon;
		this.damage = damage;
	}

	@Override
	public ActionType getType() {
		return ActionType.ATTACK;
	}

	/**
	 * Retourne l'arme utilis�e
	 */
//TODO	Weapon getWeapon();
	
	/**
	 * Retourne les d�g�ts caus�s
	 */
	int getDamage() {
		return damage;
	}
}
