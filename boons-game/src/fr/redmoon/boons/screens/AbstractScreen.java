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
	// Les dimensions de l'écran. Le ratio est de 1.6.
	// Les écrans de menu auront une meilleure définition.
	// Pour les écrans de jeu, tout est défini en mètres.
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
	// (1) Réaliser la game logic
		// Mettre à jour les acteurs
		stage.act(delta);

		// Mettre à jour la caméra
		updateCamera(stage.getCamera());

	// (2) Dessiner le résultat
		// Effacer l'écran
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Dessiner les acteurs
		stage.draw();
	}

	/**
	 * C'est ici qu'on met à jour la position de la caméra
	 * @param camera
	 */
	protected void updateCamera(Camera camera) {
	}

	@Override
	public void show() {
		Gdx.app.log(Boons.LOG, "Showing screen: " + getName());

		// Définit le stage comme étant l'input processor
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void hide() {
		Gdx.app.log(Boons.LOG, "Hiding screen: " + getName());

		// Quand on quitte l'écran, on libère les ressources associées.
		// La méthode dispose() n'est pas appelée automatiquement par le
		// framework, donc on doit déterminer quand il est approprié de
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