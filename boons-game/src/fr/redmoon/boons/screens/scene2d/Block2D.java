package fr.redmoon.boons.screens.scene2d;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import fr.redmoon.boons.domain.map.Block;

public class Block2D extends Image {
	
	/**
	 * Crée un nouveau block.
	 */
	private Block2D(Vector2 position, Block block, AtlasRegion frame) {
		super(frame);
		setTouchable(Touchable.disabled);
		
		// On place l'image à l'endroit adéquat dans la région
		setX(position.x);
		setY(position.y);
	}
	
	/**
	 * Factory chargée de créer un {@link Bloc2D} 
	 */
	public static Block2D create(Vector2 position, Block block, TextureAtlas textureAtlas) {
		// Chargement des régions du bloc dans l'atlas d'images
		AtlasRegion backgroundFrame = textureAtlas.findRegion("world/block-" + block.getBackground());

		// Création du Boon2D
		return new Block2D(position, block, backgroundFrame);
	}
}
