package fr.redmoon.boons.domain.map;

/**
 * Construit une Region en assemblant des RegionParts.
 * @author Yed
 *
 */
public class RegionBuilder {
	private final Region region;
	private int nextBlockId;
	
	public RegionBuilder(){
		region = new Region();
		nextBlockId = 0;
	}
	
	public RegionBuilder appendBlock(String background){
		Block block = new Block(String.valueOf(nextBlockId), background);
		region.addBlock(block);
		return this;
	}
	
	public Region build(){
		return region;
	}
}
