package fr.redmoon.boons.domain.entity.actions;

public class OpenAction implements IEntityAction {

	@Override
	public ActionType getType() {
		return ActionType.OPEN;
	}

}
