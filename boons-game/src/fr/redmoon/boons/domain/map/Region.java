package fr.redmoon.boons.domain.map;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente une région du monde. Une région est linéaire : c'est un assemblage
 * de blocs (Block) les uns à la suite des autres.
 * <p>
 * Le code de génération permet de déterminer quelles RegionPart sont créés.
 * 
 * @author Yed
 * 
 */
public class Region {
	private String generationCode;
	private List<Block> blocks;
	
	public Region() {
		blocks = new ArrayList<Block>();
	}
	
	public void addBlock(Block block) {
		blocks.add(block);
	}
	
	public List<Block> getBlocks() {
		return blocks;
	}
}
