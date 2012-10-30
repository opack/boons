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
 * Un bonhomme qui peut se d�placer en marchant.
 */
public class Character2D extends Image {
	/**
	 * La vitesse de d�placement du boon, en m�tres par seconde. En se basant
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
	 * Temps de chaque �tat de l'animation.
	 * <p>
	 * Cette information permet de d�terminer la trame � afficher.
	 */
	private float walkAnimationStateTime;

	/**
	 * Les drawables de l'animation de marche.
	 * <p>
	 * C'est un cache des drawables pour �viter de les cr�er � la demande.
	 */
	private Map<TextureRegion, Drawable> walkAnimationDrawables;

	/**
	 * Le drawable utilis� lorsque le Boon ne bouge pas.
	 */
	private final Drawable standFrame;
	
	/**
	 * Cr�e un nouveau personnage {@link Character2D}.
	 */
	private Character2D(Character character, AtlasRegion standFrame, Array<AtlasRegion> walkAnimationFrames) {
		super(walkAnimationFrames.get(0));
		setTouchable(Touchable.disabled);
		
		position = new Vector2();
		velocity = new Vector2();

		this.standFrame = new TextureRegionDrawable(standFrame);
		
		// Cr�ation de l'animation de marche. Chaque frame sera affich�e
		// pendant 0.15s quand l'animation sera active.
		this.walkAnimation = new Animation(0.15f, walkAnimationFrames);

		// Cr�ation du cache de drawables de l'animation
		this.walkAnimationDrawables = new HashMap<TextureRegion, Drawable>();
		for (AtlasRegion region : walkAnimationFrames) {
			walkAnimationDrawables.put(region, new TextureRegionDrawable(region));
		}
	}

	/**
	 * Factory charg�e de cr�er un {@link Character2D} 
	 */
	public static Character2D create(Character character, TextureAtlas textureAtlas) {
		// Chargement des r�gions du bonhomme dans l'atlas d'images
		String charKind = character.getKind().getName();
		AtlasRegion standRegion = textureAtlas.findRegion("world/" + charKind + "-stand");
		Array<AtlasRegion> walkRegions = textureAtlas.findRegions("world/" + charKind + "-walk");

		// Cr�ation du Boon2D
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
	 * D�place le Boon dans l'�cran
	 */
	private void move(float delta) {
		// Modifie la vitesse de d�placement suivant qu'on
		// appuie sur gauche, droite ou rien.
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			velocity.x = - SPEED;
		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			velocity.x = SPEED;
		} else {
			velocity.x = 0;
		}

		// Modification et v�rification de la position, en appliquant le param�tre delta
		position.add(velocity.x * delta, velocity.y * delta);

		// On ne laisse pas le boon aller hors de l'�cran, donc on v�rifie la
		// nouvelle position par rapport aux dimensions du stage. Si n�cessaire, 
		// on corrige la position et on remet la v�locit� � 0, afin que le boon
		// arr�te son d�placement.
		if (VectorUtils.adjustByRangeX(position, 0,	maxX - getWidth())) {
			velocity.x = 0;
		}

		// Mise � jour de la position r�elle du boon
		setX(position.x);
		setY(position.y);
	}

	/**
	 * Joue l'animation faisant marcher le boon dans la direction appropri�e.
	 */
	private void animateWalk(float delta) {
		// La frame de l'animation � afficher
		TextureRegion frame;

		// Recherche de la frame de l'animation de marche � afficher
		Drawable drawable;
		if (velocity.x < 0) {
			frame = walkAnimation.getKeyFrame(walkAnimationStateTime += delta, true);
			// On se dirige vers la gauche. Les images allant vers la droite, il faut
			// les retourner. Cela dit, une fois que c'est fait, on ne le refait que
			// si la frame a �t� retourn�e entre temps.
			if (!frame.isFlipX()) {
				frame.flip(true, false);
			}
			drawable = walkAnimationDrawables.get(frame);
		} else if (velocity.x > 0) {
			frame = walkAnimation.getKeyFrame(walkAnimationStateTime += delta, true);
			// Raisonnement inverse du cas pr�c�dent : on veut s'assurer que le mec
			// est tourn� vers la gauche avant de faire le flip pour le remettre
			// vers la droite
			if (frame.isFlipX()) {
				frame.flip(true, false);
			}
			drawable = walkAnimationDrawables.get(frame);
		} else {
			walkAnimationStateTime = 0;
			drawable = standFrame;
		}

		// Pas de soucis de performance si on d�finit la m�me frame plusieurs
		// fois car la demande sera ignor�e dans ce cas.
		setDrawable(drawable);
	}

	public void setMaxPositionX(float maxX) {
		this.maxX = maxX;
	}
}
