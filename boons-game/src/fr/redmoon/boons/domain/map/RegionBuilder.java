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
	 * Ajoute des blocs � partir du code de g�n�ration et de la cl� sp�cifi�s.
	 * <p>
	 * Le code est utilis� comme graine dans chacun des algorithmes charg�s
	 * de d�terminer le contenu du bloc (background, nombre d'entit�s,
	 * entit�s, nombre d'objets, objets...).
	 * La cl� est en r�alit� une version qui permet � l'algorithme de g�rer
	 * les diff�rentes �volutions du contenu, en conservant une compatibilit�
	 * ascendante. Ainsi, un couple code/cl� donnera le m�me r�sultat m�me
	 * si entre temps du nouveau contenu est ajout�. Par exemple, le background
	 * peut �tre choisi parmi 3 backgrounds diff�rents en version 1, et parmi
	 * 5 backgrounds diff�rents en version 2. Mais quand on re�oit ce code
	 * pour la cl� 1, on sait (m�me si on est en version 2) qu'on doit retourner
	 * l'un des 3 backgrounds g�r�s dans la version 1.
	 * @param code
	 * @return
	 */
	public RegionBuilder generateBlocks(String code, String key) {
		// D�termination du nombre de blocs � cr�er
		int nbBlocks = code.length();
		
		return this;
	}
	
	public Region build(){
		return region;
	}
}
