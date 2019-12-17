package UI.view;

import java.util.ArrayList;
import java.util.List;

import UI.AudioUtility;
import UI.CHARACTER;
import UI.HarvestButton;
import UI.subscene.CreateCharacterChooserSubScene;
import UI.subscene.CreditSubScene;
import UI.subscene.HelpSubScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;

public class MenuViewManager {

	private static final int HEIGHT = 650;
	private static final int WIDTH = 1024;

	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;

	private HarvestSubScene creditsSubScene;
	private HarvestSubScene helpSubScene;
	private HarvestSubScene characterChooserScene;
	private HarvestSubScene screenToHide;

	private final static int MENU_BUTTONS_START_X = 100;
	private final static int MENU_BUTTONS_START_Y = 220;

	List<HarvestButton> menuButtons;
	private CHARACTER chooserCharacter;

	public MenuViewManager() {
		menuButtons = new ArrayList<>();
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		mainStage = new Stage();

		AudioUtility.playSound("Intro");
		createSubScene();
		createButton();
		createBackground();
		mainStage.setScene(mainScene);
		mainStage.setResizable(false);
		mainStage.setTitle("HARVESTPOINT");
		mainStage.getIcons().add(new Image(ClassLoader.getSystemResource("image/shield/shield.png").toString()));
		

	}

	private void createSubScene() {
		creditsSubScene = new CreditSubScene();
		helpSubScene = new HelpSubScene();
		characterChooserScene = new CreateCharacterChooserSubScene(chooserCharacter, mainStage);
		mainPane.getChildren().addAll(characterChooserScene, creditsSubScene, helpSubScene);

	}

	private void createButton() {
		createStartButton();
		createHelpButton();
		creatCreditsButton();
		createExitButton();

	}

	private void showSubScene(HarvestSubScene subScene) {
		if (screenToHide != null) {

			screenToHide.moveSubScene();
		}
		AudioUtility.playSound("Click");
		subScene.moveSubScene();
		screenToHide = subScene;
	}

	private void createStartButton() {
		HarvestButton startButton = new HarvestButton("PLAY");
		addMenuButton(startButton);

		startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showSubScene(characterChooserScene);

			}

		});
	}

	private void createHelpButton() {
		HarvestButton helpButton = new HarvestButton("HELP");
		addMenuButton(helpButton);
		helpButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				showSubScene(helpSubScene);
			}

		});
	}

	private void creatCreditsButton() {
		HarvestButton creditsButton = new HarvestButton("CREDITS");
		addMenuButton(creditsButton);

		creditsButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				showSubScene(creditsSubScene);

			}

		});
	}

	private void createExitButton() {
		HarvestButton exitButton = new HarvestButton("EXIT");
		addMenuButton(exitButton);
		exitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				mainStage.close();
			}

		});
	}

	private void addMenuButton(HarvestButton button) {
		button.setLayoutX(MENU_BUTTONS_START_X);
		button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size() * 80);
		menuButtons.add(button);
		mainPane.getChildren().add(button);
	}

	public Stage getMainStage() {
		return mainStage;

	}

	private void createBackground() {
		Image backgroundImage = new Image(ClassLoader.getSystemResource("image/back_farm.jpg").toString(), 1040, 660,
				false, true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(background));
	}

}
