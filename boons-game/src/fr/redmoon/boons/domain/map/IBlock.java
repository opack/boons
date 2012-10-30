package fr.redmoon.boons.domain.map;

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
public interface IBlock {
	/**
	 * Retourne l'identifiant du bloc
	 * @return
	 */
	String getId();
	
	/**
	 * Retourne l'image de fond du bloc
	 * @return
	 */
	String getBackground();
	
	/**
	 * Retourne la liste des entités du bloc.
	 */
	List<IEntity> getEntities();
}
