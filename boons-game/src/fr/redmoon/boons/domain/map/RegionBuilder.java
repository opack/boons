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
	
	/**
	 * Ajoute des blocs à partir du code de génération et de la clé spécifiés.
	 * <p>
	 * Le code est utilisé comme graine dans chacun des algorithmes chargés
	 * de déterminer le contenu du bloc (background, nombre d'entités,
	 * entités, nombre d'objets, objets...).
	 * La clé est en réalité une version qui permet à l'algorithme de gérer
	 * les différentes évolutions du contenu, en conservant une compatibilité
	 * ascendante. Ainsi, un couple code/clé donnera le même résultat même
	 * si entre temps du nouveau contenu est ajouté. Par exemple, le background
	 * peut être choisi parmi 3 backgrounds différents en version 1, et parmi
	 * 5 backgrounds différents en version 2. Mais quand on reçoit ce code
	 * pour la clé 1, on sait (même si on est en version 2) qu'on doit retourner
	 * l'un des 3 backgrounds gérés dans la version 1.
	 * @param code
	 * @return
	 */
	public RegionBuilder generateBlocks(String code, String key) {
		// Détermination du nombre de blocs à créer
		int nbBlocks = code.length();
		
		return this;
	}
	
	public Region build(){
		return region;
	}
}
