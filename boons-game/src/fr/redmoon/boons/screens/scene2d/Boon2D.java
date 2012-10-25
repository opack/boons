package fr.redmoon.boons.screens.scene2d;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

import fr.redmoon.boons.domain.Boon;

/**
 * The ship's 2D representation.
 */
public class Boon2D extends Image {
	/**
	 * La vitesse de déplacement du boon, en mètres par seconde. En se basant
	 * sur une vitesse de marche de 5km/h, on obtient 1.4m/s.
	 */
	private static final float SPEED = 1.4f;

	private final Vector2 position;
	
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
	 * Crée un nouveau Boon {@link Boon2D}.
	 */
	private Boon2D(Boon boon, Array<AtlasRegion> walkAnimationFrames) {
		super(walkAnimationFrames.get(0));
		position = new Vector2();
		velocity = new Vector2();

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
	 * Factory chargée de créer un {@link Boon2D} 
	 */
	public static Boon2D create(Boon boon, TextureAtlas textureAtlas) {
		// Chargement des régions du bonhomme dans l'atlas d'images
		Array<AtlasRegion> regions = textureAtlas.findRegions("hero/" + boon.getBoonModel().getSimpleName());

		// Création du Boon2D
		return new Boon2D(boon, regions);
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
		moveShip(delta);
		tiltShip(delta);
	}

	/**
	 * Moves the ship around the screen.
	 */
	private void moveShip(float delta) {
		// check the input and calculate the acceleration
		if (Gdx.input.isPeripheralAvailable(Peripheral.Accelerometer)) {

			// set the acceleration base on the accelerometer input; notice the
			// inverted axis because the game is displayed in landscape mode
			acceleration.set(Gdx.input.getAccelerometerY(),
					Gdx.input.getAccelerometerX());

			// set the acceleration bounds
			VectorUtils.adjustByRange(acceleration, -2, 2);

			// set the input deadzone
			if (!VectorUtils.adjustDeadzone(acceleration, 1f, 0f)) {
				// we're out of the deadzone, so let's adjust the acceleration
				// (2 is 100% of the max acceleration)
				acceleration.x = (acceleration.x / 2 * MAX_ACCELERATION);
				acceleration.y = (-acceleration.y / 2 * MAX_ACCELERATION);
			}

		} else {
			// when the keys aren't pressed the acceleration will be zero, so
			// the ship's velocity won't be affected by it
			acceleration.x = (Gdx.input.isKeyPressed(Input.Keys.LEFT) ? -MAX_ACCELERATION
					: (Gdx.input.isKeyPressed(Input.Keys.RIGHT) ? MAX_ACCELERATION
							: 0));
			acceleration.y = (Gdx.input.isKeyPressed(Input.Keys.UP) ? MAX_ACCELERATION
					: (Gdx.input.isKeyPressed(Input.Keys.DOWN) ? -MAX_ACCELERATION
							: 0));
		}

		// if there is no acceleration and the ship is moving, let's calculate
		// an appropriate deceleration
		if (acceleration.len() == 0f && velocity.len() > 0f) {

			// horizontal deceleration
			if (velocity.x > 0) {
				acceleration.x = -MAX_DECELERATION;
				if (velocity.x - acceleration.x < 0) {
					acceleration.x = -(acceleration.x - velocity.x);
				}
			} else if (velocity.x < 0) {
				acceleration.x = MAX_DECELERATION;
				if (velocity.x + acceleration.x > 0) {
					acceleration.x = (acceleration.x - velocity.x);
				}
			}

			// vertical deceleration
			if (velocity.y > 0) {
				acceleration.y = -MAX_DECELERATION;
				if (velocity.y - acceleration.y < 0) {
					acceleration.y = -(acceleration.y - velocity.y);
				}
			} else if (velocity.y < 0) {
				acceleration.y = MAX_DECELERATION;
				if (velocity.y + acceleration.y > 0) {
					acceleration.y = (acceleration.y - velocity.y);
				}
			}

		}

		// modify and check the ship's velocity
		velocity.add(acceleration);
		VectorUtils.adjustByRange(velocity, -MAX_SPEED, MAX_SPEED);

		// modify and check the ship's position, applying the delta parameter
		position.add(velocity.x * delta, velocity.y * delta);

		// we can't let the ship go off the screen, so here we check the new
		// ship's position against the stage's dimensions, correcting it if
		// needed and zeroing the velocity, so that the ship stops flying in the
		// current direction
		if (VectorUtils.adjustByRangeX(position, 0,
				(getStage().getWidth() - getWidth())))
			velocity.x = 0;
		if (VectorUtils.adjustByRangeY(position, 0,
				(getStage().getHeight() - getHeight())))
			velocity.y = 0;

		// update the ship's actual position
		setX(position.x);
		setY(position.y);
	}

	/**
	 * Tilts the ship to the direction its moving.
	 */
	private void tiltShip(float delta) {
		// the animation's frame to be shown
		TextureRegion frame;

		// find the appropriate frame of the tilt animation to be drawn
		if (velocity.x < 0) {
			frame = walkAnimation.getKeyFrame(walkAnimationStateTime += delta,
					false);
			if (frame.getRegionWidth() < 0)
				frame.flip(true, false);
		} else if (velocity.x > 0) {
			frame = walkAnimation.getKeyFrame(walkAnimationStateTime += delta,
					false);
			if (frame.getRegionWidth() > 0)
				frame.flip(true, false);
		} else {
			walkAnimationStateTime = 0;
			frame = walkAnimation.getKeyFrame(0, false);
		}

		// there is no performance issues when setting the same frame multiple
		// times as the current region (the call will be ignored in this case)
		setDrawable(walkAnimationDrawables.get(frame));
	}

	static final String TAG = Boon2D.class.getSimpleName();
}
