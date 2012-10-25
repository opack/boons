package fr.redmoon.boons;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;

import fr.redmoon.boons.screens.AbstractScreen;
import fr.redmoon.boons.screens.SplashScreen;

public class Boons extends Game {
	public static final String LOG = Boons.class.getSimpleName();
	
	public static int PIXELS_PER_METER = 16;
	
	private FPSLogger fpsLogger;
	private AbstractScreen nextScreen;

	public SplashScreen getSplashScreen() {
		return new SplashScreen(this);
	}

	@Override
	public void create() {
		Gdx.app.log(LOG, "Creating game");
		fpsLogger = new FPSLogger();
		setScreen(getSplashScreen());
	}

	@Override
	public void render() {
		super.render();

		// output the current FPS
		fpsLogger.log();
		
		// Affichage du prochain écran, si demandé
		if (nextScreen != null) {
			setScreen(nextScreen);
			nextScreen = null;
		}
	}

	@Override
	public void pause() {
		super.pause();
		Gdx.app.log(LOG, "Pausing game");
	}

	@Override
	public void resume() {
		super.resume();
		Gdx.app.log(LOG, "Resuming game");
	}

	@Override
	public void dispose() {
		super.dispose();
		Gdx.app.log(LOG, "Disposing game");
	}

	public void setNextScreen(AbstractScreen nextScreen) {
		this.nextScreen = nextScreen;
	}
}
