package fr.redmoon.boons.domain.map;

import java.util.ArrayList;
import java.util.List;

/**
 * Repr�sente une r�gion du monde. Une r�gion est lin�aire : c'est un assemblage
 * de blocs (Block) les uns � la suite des autres.
 * <p>
 * Le code de g�n�ration permet de d�terminer quelles RegionPart sont cr��s.
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
