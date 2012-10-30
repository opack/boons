package fr.redmoon.boons.screens;

import static fr.redmoon.boons.Boons.PIXELS_PER_METER;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;

import fr.redmoon.boons.Boons;

public abstract class AbstractScreen implements Screen {
	// Les dimensions de l'�cran. Le ratio est de 1.6.
	// Les �crans de menu auront une meilleure d�finition.
	// Pour les �crans de jeu, tout est d�fini en m�tres.
	public static final int GAME_VIEWPORT_WIDTH = 8 * PIXELS_PER_METER;
	public static final int GAME_VIEWPORT_HEIGHT = 5 * PIXELS_PER_METER;
	public static final int MENU_VIEWPORT_WIDTH = 800;
	public static final int MENU_VIEWPORT_HEIGHT = 480;

	protected final Boons game;
	protected Stage stage;
	private SpriteBatch batch;
	private TextureAtlas atlas;

	public AbstractScreen(Boons game) {
		this.game = game;
		int width = (isGameScreen() ? GAME_VIEWPORT_WIDTH : MENU_VIEWPORT_WIDTH);
		int height = (isGameScreen() ? GAME_VIEWPORT_HEIGHT : MENU_VIEWPORT_HEIGHT);
		stage = new Stage(width, height, true);
	}

	public String getName() {
		return getClass().getSimpleName();
	}

	public SpriteBatch getBatch() {
		if (batch == null) {
			batch = new SpriteBatch();
		}
		return batch;
	}

	public TextureAtlas getAtlas() {
		if (atlas == null) {
			atlas = new TextureAtlas(Gdx.files.internal("image-atlases/pages.atlas"));
		}
		return atlas;
	}

	protected boolean isGameScreen() {
		return false;
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log(Boons.LOG, "Resizing screen: " + getName() + " to: " + width + " x " + height);
	}
	
	@Override
	public void render(float delta) {
	// (1) R�aliser la game logic
		// Mettre � jour les acteurs
		stage.act(delta);

		// Mettre � jour la cam�ra
		updateCamera(stage.getCamera());

	// (2) Dessiner le r�sultat
		// Effacer l'�cran
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Dessiner les acteurs
		stage.draw();
	}

	/**
	 * C'est ici qu'on met � jour la position de la cam�ra
	 * @param camera
	 */
	protected void updateCamera(Camera camera) {
	}

	@Override
	public void show() {
		Gdx.app.log(Boons.LOG, "Showing screen: " + getName());

		// D�finit le stage comme �tant l'input processor
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void hide() {
		Gdx.app.log(Boons.LOG, "Hiding screen: " + getName());

		// Quand on quitte l'�cran, on lib�re les ressources associ�es.
		// La m�thode dispose() n'est pas appel�e automatiquement par le
		// framework, donc on doit d�terminer quand il est appropri� de
		// l'appeler.
		dispose();
	}

	@Override
	public void pause() {
		Gdx.app.log(Boons.LOG, "Pausing screen: " + getName());
	}

	@Override
	public void resume() {
		Gdx.app.log(Boons.LOG, "Resuming screen: " + getName());
	}

	@Override
	public void dispose() {
		Gdx.app.log(Boons.LOG, "Disposing screen: " + getName());

		if (stage != null) {
			stage.dispose();
			stage = null;
		}
				
		if (batch != null) {
			batch.dispose();
			batch = null;
		}

		if (atlas != null) {
			atlas.dispose();
			atlas = null;
		}
	}
}