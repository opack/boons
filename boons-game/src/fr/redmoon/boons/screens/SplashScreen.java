package fr.redmoon.boons.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;

import fr.redmoon.boons.Boons;

public class SplashScreen extends AbstractScreen {

	private Image splashImage;

	public SplashScreen(Boons game) {
		super(game);
	}

	@Override
	public void show() {
		super.show();

		// R�cup�ration de la r�gion de l'image depuis l'atlas
		AtlasRegion splashRegion = getAtlas().findRegion("splash-screen/splash-screen");
		Drawable splashDrawable = new TextureRegionDrawable(splashRegion);

		// Cr�ation de l'Actor splash image. Sa taille est d�finie quand
		// la m�thode resize() est appel�e.
		splashImage = new Image(splashDrawable, Scaling.stretch);
		splashImage.setFillParent(true);

		// Ceci est n�cessaire pour que l'effet de fondu d'ouverture fonctionne
		// correctement ; on se contente de mettre l'image compl�tement transparente
		// au d�but.
		splashImage.getColor().a = 0f;

		// Configuration des effets de fondu et de l'action en fin de fondu
		splashImage.addAction(sequence(fadeIn(0.75f), delay(1.75f),fadeOut(0.75f), new Action(){
			@Override
			public boolean act(float delta) {
				game.setNextScreen(new LevelScreen(game));
				return true;
			}
		}
		));

		// Ajoute l'acteur � la sc�ne
		stage.addActor(splashImage);
	}
}