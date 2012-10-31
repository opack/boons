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
		
	// Création de la région puis ajout à la scène
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

	// Création du Boon puis ajout à la scène
		Boon testBoon = new Boon();
		testBoon.setKind(CharacterKind.BOON);
		character2d = Character2D.create(testBoon, getAtlas());
		character2d.setInitialPosition((stage.getWidth() / 2 - character2d.getWidth() / 2), 0);
		character2d.setMaxPositionX(region2D.getWidth());
		stage.addActor(character2d);

	// Ajoute un effet de fondu à la scène
		stage.getRoot().getColor().a = 0f;
		stage.getRoot().addAction(Actions.fadeIn(0.5f));
	}
	
	@Override
	protected void updateCamera(Camera camera) {
		// On déplace la caméra avec le joueur si nécessaire
		float dx = character2d.getX() - camera.position.x;
		float dy = character2d.getY() - camera.position.y;
		camera.translate(dx, dy, 0);
		
		// On bloque la caméra aux limites de la région
		VectorUtils.adjustByRangeX(camera.position, camera.viewportWidth / 2, region2D.getWidth() - camera.viewportWidth / 2);
	}
}
