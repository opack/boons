import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2.Settings;

/**
 * Packs single images into image atlases.
 */
public class BoonsTexturePacker {
	private static final String INPUT_DIR = "../boons-game/etc/images";
	private static final String OUTPUT_DIR = "../boons-android/assets/image-atlases";
	private static final String PACK_FILE = "pages";

	public static void main(String[] args) {
		// create the packing's settings
		Settings settings = new Settings();

		// adjust the padding settings
		settings.paddingX = 0;
		settings.paddingY = 0;
		settings.edgePadding = false;

		// set the maximum dimension of each image atlas
		settings.maxWidth = 512;
		settings.maxWidth = 512;

		// pack the images
		TexturePacker2.process(settings, INPUT_DIR, OUTPUT_DIR, PACK_FILE);
	}
}