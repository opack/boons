package fr.redmoon.boons.screens.scene2d;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

import fr.redmoon.boons.domain.map.Block;
import fr.redmoon.boons.domain.map.Region;

/**
 * Représente une région qui peut être rendue dans un stage par exemple.
 * La région en elle-même n'est pas dessinable, mais elle sait dessiner
 * les blocs qu'elle contient sur un stage.
 * @author Yed
 *
 */
public class Region2D {
	private final Region region;
	private final TextureAtlas textureAtlas;
	private List<Block2D> blocks2D;
	private float width;
	
	/**
	 * Crée une nouvelle région.
	 * @param textureAtlas 
	 */
	public Region2D(Region region, TextureAtlas textureAtlas) {
		this.region = region;
		this.textureAtlas = textureAtlas;
	}

	/**
	 * Ajoute chacun des blocs de la région dans le stage
	 * @param stage
	 */
	public void addBlocksToStage(Stage stage) {
		// Création de la liste de blocs
		if (blocks2D == null) {
			blocks2D = new ArrayList<Block2D>();
		} else {
			blocks2D.clear();
		}
		
		// Création des blocs et ajout à la liste (pour utilisation future)
		// et au stage
		Block2D curBlock2D;
		Vector2 curPos = new Vector2(0, 0);
		for (Block block : region.getBlocks()) {
			// Création du block2D
			curBlock2D = Block2D.create(curPos, block, textureAtlas);
			
			// Ajout à la liste et au Stage
			blocks2D.add(curBlock2D);
			stage.addActor(curBlock2D);
			
			// Mise à jour de la position courante, pour le prochain bloc
			curPos.add(curBlock2D.getWidth(), 0);
		}
		
		// Mise à jour de la taille totale de la région
		width = curPos.x;
	}

	public float getWidth() {
		return width;
	}
}
