package logic.charactertype;

import javafx.scene.image.Image;
import logic.Character;
import logic.CharacterProperties;

public class Ladybug extends Character implements CharacterProperties {
	
	public Ladybug(String url) {

		setVelocity();
		setLifepoint();
		setImage(new Image(url));
		setConfig();
		this.type ="ladybug";

	}

	@Override
	public void setVelocity() {
		this.velocity = 10;
	}

	@Override
	public void setLifepoint() {
		this.life = 2;

	}

	@Override
	public void setConfig() {
		
		setFitHeight(100);
		setFitWidth(100);
		setLayoutX(300);
		setLayoutY(500);

	}

}
