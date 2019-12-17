package UI.subscene;

import java.util.ArrayList;
import java.util.List;

import UI.AudioUtility;
import UI.CHARACTER;
import UI.InfoLabel;
import UI.HarvestButton;
import UI.CharacterPicker;
import UI.view.GameViewManager;
import UI.view.HarvestSubScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CreateCharacterChooserSubScene extends HarvestSubScene {
	private InfoLabel chooseCharacterLabel;

	List<CharacterPicker> characterList;
	private CHARACTER chooserCharacter;
	private Stage mainStage;

	public CreateCharacterChooserSubScene(CHARACTER chooserCharacter, Stage mainStage) {
		this.chooserCharacter = chooserCharacter;
		this.mainStage = mainStage;
		chooseCharacterLabel = new InfoLabel("CHOOSE CHARACTER");
		chooseCharacterLabel.setLayoutX(110);
		chooseCharacterLabel.setLayoutY(25);
		getPane().getChildren().add(chooseCharacterLabel);
		getPane().getChildren().add(createCharacterToChoose());
		getPane().getChildren().add(createButtonToStart());
	}

	private HBox createCharacterToChoose() {
		HBox box = new HBox();
		box.setSpacing(20);
		characterList = new ArrayList<>();
		for (CHARACTER character : CHARACTER.values()) {
			CharacterPicker characterToPick = new CharacterPicker(character);
			box.getChildren().add(characterToPick);
			characterList.add(characterToPick);
			characterToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {

					for (CharacterPicker character : characterList) {
						character.setIsCircleChoosen(false);
					}
					characterToPick.setIsCircleChoosen(true);
					chooserCharacter = characterToPick.getCharacter();
				}

			});
		}
		box.setLayoutX(300 - (118 * 2));
		box.setLayoutY(150);
		return box;
	}

	private HarvestButton createButtonToStart() {
		HarvestButton startButton = new HarvestButton("START");
		startButton.setLayoutX(350);
		startButton.setLayoutY(300);
		startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if (chooserCharacter != null) {
					GameViewManager gameManager = new GameViewManager();
					AudioUtility.stopSound("Intro");
					gameManager.createNewGame(mainStage, chooserCharacter);
				}
			}

		});
		return startButton;
	}

}
