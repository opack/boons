package fr.redmoon.boons.domain.map;

import java.util.ArrayList;
import java.util.List;

import fr.redmoon.boons.domain.entity.IEntity;

/**
 * Une petite zone composant une région. Chaque IBlock est composé d'un
 * identifiant, d'une image de fond, et d'entités (avec lesquelles
 * il est possible d'interagir). 
 * 
 * @author Yed
 * 
 */
public class Block {
	private final String id;
	private final String background;
	private final List<IEntity> entities;
	
	public Block(String id, String background) {
		this.id = id;
		this.background = background;
		entities = new ArrayList<IEntity>();
	}
	
	/**
	 * Retourne l'identifiant du bloc
	 * @return
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Retourne l'image de fond du bloc
	 * @return
	 */
	public String getBackground(){
		return background;
	}
	
	/**
	 * Retourne la liste des entités du bloc.
	 */
	public List<IEntity> getEntities() {
		return entities;
	}
}
