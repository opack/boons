package fr.redmoon.boons.screens;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import fr.redmoon.boons.Boons;
import fr.redmoon.boons.domain.characters.Boon;
import fr.redmoon.boons.domain.characters.CharacterKind;
import fr.redmoon.boons.domain.map.RegionBuilder;
import fr.redmoon.boons.screens.scene2d.Character2D;
import fr.redmoon.boons.screens.scene2d.Region2D;
import fr.redmoon.boons.utils.VectorUtils;

public class LevelScreen extends AbstractScreen {
	private Character2D character2d;
	private Region2D region2D;
	
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
		
	// Cr�ation de la r�gion puis ajout � la sc�ne
		RegionBuilder builder = new RegionBuilder();
		builder.generateBlocks("test");
		builder.appendBlock("cave-night")
			.appendBlock("cave")
			.appendBlock("forest")
			.appendBlock("prairie")
			.appendBlock("dungeon")
			.appendBlock("shop");
		region2D = new Region2D(builder.build(), getAtlas());
		region2D.addBlocksToStage(stage);

	// Cr�ation du Boon puis ajout � la sc�ne
		Boon testBoon = new Boon();
		testBoon.setKind(CharacterKind.BOON);
		character2d = Character2D.create(testBoon, getAtlas());
		character2d.setInitialPosition((stage.getWidth() / 2 - character2d.getWidth() / 2), 0);
		character2d.setMaxPositionX(region2D.getWidth());
		stage.addActor(character2d);

	// Ajoute un effet de fondu � la sc�ne
		stage.getRoot().getColor().a = 0f;
		stage.getRoot().addAction(Actions.fadeIn(0.5f));
	}
	
	@Override
	protected void updateCamera(Camera camera) {
		// On d�place la cam�ra avec le joueur si n�cessaire
		float dx = character2d.getX() - camera.position.x;
		float dy = character2d.getY() - camera.position.y;
		camera.translate(dx, dy, 0);
		
		// On bloque la cam�ra aux limites de la r�gion
		VectorUtils.adjustByRangeX(camera.position, camera.viewportWidth / 2, region2D.getWidth() - camera.viewportWidth / 2);
	}
}
