package UI;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class CharacterPicker extends VBox {

	private ImageView circleImage;
	private ImageView characterImage;

	private String circleNotChoosen = ClassLoader.getSystemResource("image/grey_circle.png").toString();
	private String circleChoosen = ClassLoader.getSystemResource("image/circle_choosen.png").toString();

	private CHARACTER character;
	private boolean isCircleChoosen;

	public CharacterPicker(CHARACTER character) {
		circleImage = new ImageView(circleNotChoosen);
		characterImage = new ImageView(character.geturl());
		characterImage.setFitHeight(100);
		characterImage.setFitWidth(100);
		this.character = character;
		isCircleChoosen = false;
		this.setAlignment(Pos.CENTER);
		this.getChildren().add(circleImage);
		this.getChildren().add(characterImage);
	}

	public CHARACTER getCharacter() {
		return character;
	}

	public boolean getIsCircleChoosen() {
		return isCircleChoosen;
	}

	public void setIsCircleChoosen(boolean isCircleChoosen) {
		this.isCircleChoosen = isCircleChoosen;
		String ImageToSet = this.isCircleChoosen ? circleChoosen : circleNotChoosen;
		circleImage.setImage(new Image(ImageToSet));
	}
}
