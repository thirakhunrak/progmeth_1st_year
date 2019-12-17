package logic.charactertype;

import javafx.scene.image.Image;
import logic.Character;
import logic.CharacterProperties;

public class Beetle extends Character implements CharacterProperties {

	public Beetle(String url) {

		setVelocity();
		setLifepoint();
		setImage(new Image(url));
		setConfig();
		this.type = "beetle";

	}

	@Override
	public void setVelocity() {
		this.velocity = 7;
	}

	@Override
	public void setLifepoint() {
		this.life = 4;

	}

	@Override
	public void setConfig() {

		setFitHeight(100);
		setFitWidth(100);
		setLayoutX(300);
		setLayoutY(500);

	}

}
