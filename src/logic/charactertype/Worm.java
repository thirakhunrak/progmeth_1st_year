package logic.charactertype;

import javafx.scene.image.Image;
import logic.Character;
import logic.CharacterProperties;

public class Worm extends Character implements CharacterProperties {
	
	public Worm(String url) {

		setVelocity();
		setLifepoint();
		setImage(new Image(url));
		setConfig();
		this.type = "worm";


	}

	@Override
	public void setVelocity() {
		this.velocity = 5;
	}

	@Override
	public void setLifepoint() {
		this.life = 5;

	}

	@Override
	public void setConfig() {
		
		setFitHeight(100);
		setFitWidth(100);
		setLayoutX(300);
		setLayoutY(500);

	}


}
