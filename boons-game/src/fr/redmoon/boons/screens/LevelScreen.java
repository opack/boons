package fr.redmoon.boons.screens;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import fr.redmoon.boons.Boons;
import fr.redmoon.boons.domain.Boon;
import fr.redmoon.boons.domain.CharacterKind;
import fr.redmoon.boons.screens.scene2d.Boon2D;

public class LevelScreen extends AbstractScreen {
	private Boon2D boon2d;

	public LevelScreen(Boons game) {
		super(game);
	}

	@Override
	protected boolean isGameScreen() {
		return true;
	}

	@Override
	public void show() {
		super.show();

		// Création du Boon puis ajout à la scène
		Boon testBoon = new Boon();
		testBoon.setKind(CharacterKind.HERO);
		boon2d = Boon2D.create(testBoon, getAtlas());

		// Centre le boon horizontallement
		boon2d.setInitialPosition(
				(stage.getWidth() / 2 - boon2d.getWidth() / 2),
				0);

		// Ajoute le boon à la scène
		stage.addActor(boon2d);

		// Ajoute un effet de fondu à la scène
		stage.getRoot().getColor().a = 0f;
		stage.getRoot().addAction(Actions.fadeIn(0.5f));
	}
}
