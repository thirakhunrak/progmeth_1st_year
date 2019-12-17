package logic.charactertype;

import javafx.scene.image.Image;
import logic.Character;
import logic.CharacterProperties;

public class Dragonfly extends Character implements CharacterProperties {
	
	public Dragonfly(String url) {

		setVelocity();
		setLifepoint();
		setImage(new Image(url));
		setConfig();
		this.type = "dragonfly";


	}

	@Override
	public void setVelocity() {
		this.velocity = 8;
	}

	@Override
	public void setLifepoint() {
		this.life = 3;

	}

	@Override
	public void setConfig() {
		
		setFitHeight(100);
		setFitWidth(100);
		setLayoutX(300);
		setLayoutY(500);

	}

}
