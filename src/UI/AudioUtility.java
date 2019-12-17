package UI;

import javafx.scene.media.AudioClip;

public class AudioUtility {
	private static AudioClip Intro, Start, Click, EndGame, GainScore, LoseLife, freeze, shield;

	static {
		loadResource();
	}

	public static void loadResource() {
		Intro = new AudioClip(ClassLoader.getSystemResource("sound/sound_menu.wav").toString());
		Start = new AudioClip(ClassLoader.getSystemResource("sound/start.wav").toString());
		Click = new AudioClip(ClassLoader.getSystemResource("sound/Click.wav").toString());
		EndGame = new AudioClip(ClassLoader.getSystemResource("sound/sfx_lose.wav").toString());
		GainScore = new AudioClip(ClassLoader.getSystemResource("sound/sfx_gainstar.wav").toString());
		LoseLife = new AudioClip(ClassLoader.getSystemResource("sound/sfx_corride.wav").toString());
		freeze = new AudioClip(ClassLoader.getSystemResource("sound/freezing.wav").toString());
		shield = new AudioClip(ClassLoader.getSystemResource("sound/sfx_zap.wav").toString());
	}

	public static void playSound(String identifier) {

		if (identifier.equalsIgnoreCase("Intro")) {
			Intro.setCycleCount(AudioClip.INDEFINITE);
			Intro.play();
			
		} else if (identifier.equalsIgnoreCase("Start")) {
			Start.setCycleCount(AudioClip.INDEFINITE);
			Start.setVolume(0.2f);
			Start.play();
			
		} else if (identifier.equalsIgnoreCase("Click")) {
			Click.play();
		} else if (identifier.equalsIgnoreCase("EndGame")) {
			EndGame.play();
		} else if (identifier.equalsIgnoreCase("GainScore")) {
			GainScore.setVolume(2);
			GainScore.play();
		} else if (identifier.equalsIgnoreCase("LoseLife")) {
			LoseLife.play();
		} else if (identifier.equalsIgnoreCase("Freeze")) {
			freeze.play();
		} else if (identifier.equalsIgnoreCase("Shield")) {
			shield.play();
		}
	}

	public static void stopSound(String identifier) {
		if (identifier.equalsIgnoreCase("Intro")) {
			Intro.stop();
		} else if (identifier.equalsIgnoreCase("Start")) {
			Start.stop();
		} else if (identifier.equalsIgnoreCase("Click")) {
			Click.stop();
		} else if (identifier.equalsIgnoreCase("EndGame")) {
			EndGame.stop();
		} else if (identifier.equalsIgnoreCase("GainScore")) {
			GainScore.stop();
		} else if (identifier.equalsIgnoreCase("LoseLife")) {
			LoseLife.stop();
		} else if (identifier.equalsIgnoreCase("Freez")) {
			freeze.stop();
		} else if (identifier.equalsIgnoreCase("Shield")) {
			shield.stop();
		}
	}
}
