package fr.redmoon.boons.screens.scene2d;

import static fr.redmoon.boons.Boons.PIXELS_PER_METER;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

import fr.redmoon.boons.domain.characters.Character;
import fr.redmoon.boons.utils.VectorUtils;

/**
 * Un bonhomme qui peut se déplacer en marchant.
 */
public class Character2D extends Image {
	/**
	 * La vitesse de déplacement du boon, en mètres par seconde. En se basant
	 * sur une vitesse de marche de 5km/h, on obtient 1.4m/s.
	 */
	private static final float SPEED = 1.4f * PIXELS_PER_METER;
	
	private final Vector2 position;
	
	/**
	 * Position maximale en X
	 */
	private float maxX;
	
	/**
     * La vitesse actuelle du boon.
     */
    private final Vector2 velocity;
    
	/**
	 * L'animation de marche du boon
	 */
	private final Animation walkAnimation;

	/**
	 * Temps de chaque état de l'animation.
	 * <p>
	 * Cette information permet de déterminer la trame à afficher.
	 */
	private float walkAnimationStateTime;

	/**
	 * Les drawables de l'animation de marche.
	 * <p>
	 * C'est un cache des drawables pour éviter de les créer à la demande.
	 */
	private Map<TextureRegion, Drawable> walkAnimationDrawables;

	/**
	 * Le drawable utilisé lorsque le Boon ne bouge pas.
	 */
	private final Drawable standFrame;
	
	/**
	 * Crée un nouveau personnage {@link Character2D}.
	 */
	private Character2D(Character character, AtlasRegion standFrame, Array<AtlasRegion> walkAnimationFrames) {
		super(walkAnimationFrames.get(0));
		setTouchable(Touchable.disabled);
		
		position = new Vector2();
		velocity = new Vector2();

		this.standFrame = new TextureRegionDrawable(standFrame);
		
		// Création de l'animation de marche. Chaque frame sera affichée
		// pendant 0.15s quand l'animation sera active.
		this.walkAnimation = new Animation(0.15f, walkAnimationFrames);

		// Création du cache de drawables de l'animation
		this.walkAnimationDrawables = new HashMap<TextureRegion, Drawable>();
		for (AtlasRegion region : walkAnimationFrames) {
			walkAnimationDrawables.put(region, new TextureRegionDrawable(region));
		}
	}

	/**
	 * Factory chargée de créer un {@link Character2D} 
	 */
	public static Character2D create(Character character, TextureAtlas textureAtlas) {
		// Chargement des régions du bonhomme dans l'atlas d'images
		String charKind = character.getKind().getName();
		AtlasRegion standRegion = textureAtlas.findRegion("world/" + charKind + "-stand");
		Array<AtlasRegion> walkRegions = textureAtlas.findRegions("world/" + charKind + "-walk");

		// Création du Boon2D
		return new Character2D(character, standRegion, walkRegions);
	}

	/**
	 * Sets the ship's initial position.
	 */
	public void setInitialPosition(float x, float y) {
		position.set(x, y);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		move(delta);
		animateWalk(delta);
	}

	/**
	 * Déplace le Boon dans l'écran
	 */
	private void move(float delta) {
		// Modifie la vitesse de déplacement suivant qu'on
		// appuie sur gauche, droite ou rien.
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			velocity.x = - SPEED;
		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			velocity.x = SPEED;
		} else {
			velocity.x = 0;
		}

		// Modification et vérification de la position, en appliquant le paramètre delta
		position.add(velocity.x * delta, velocity.y * delta);

		// On ne laisse pas le boon aller hors de l'écran, donc on vérifie la
		// nouvelle position par rapport aux dimensions du stage. Si nécessaire, 
		// on corrige la position et on remet la vélocité à 0, afin que le boon
		// arrête son déplacement.
		if (VectorUtils.adjustByRangeX(position, 0,	maxX - getWidth())) {
			velocity.x = 0;
		}

		// Mise à jour de la position réelle du boon
		setX(position.x);
		setY(position.y);
	}

	/**
	 * Joue l'animation faisant marcher le boon dans la direction appropriée.
	 */
	private void animateWalk(float delta) {
		// La frame de l'animation à afficher
		TextureRegion frame;

		// Recherche de la frame de l'animation de marche à afficher
		Drawable drawable;
		if (velocity.x < 0) {
			frame = walkAnimation.getKeyFrame(walkAnimationStateTime += delta, true);
			// On se dirige vers la gauche. Les images allant vers la droite, il faut
			// les retourner. Cela dit, une fois que c'est fait, on ne le refait que
			// si la frame a été retournée entre temps.
			if (!frame.isFlipX()) {
				frame.flip(true, false);
			}
			drawable = walkAnimationDrawables.get(frame);
		} else if (velocity.x > 0) {
			frame = walkAnimation.getKeyFrame(walkAnimationStateTime += delta, true);
			// Raisonnement inverse du cas précédent : on veut s'assurer que le mec
			// est tourné vers la gauche avant de faire le flip pour le remettre
			// vers la droite
			if (frame.isFlipX()) {
				frame.flip(true, false);
			}
			drawable = walkAnimationDrawables.get(frame);
		} else {
			walkAnimationStateTime = 0;
			drawable = standFrame;
		}

		// Pas de soucis de performance si on définit la même frame plusieurs
		// fois car la demande sera ignorée dans ce cas.
		setDrawable(drawable);
	}

	public void setMaxPositionX(float maxX) {
		this.maxX = maxX;
	}
}
