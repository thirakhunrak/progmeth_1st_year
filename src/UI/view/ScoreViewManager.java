package UI.view;

import UI.AudioUtility;
import UI.CHARACTER;
import UI.HarvestButton;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ScoreViewManager {

	private Stage mainStage;
	private Stage scoreStage;

	private Scene scene;

	private static final int GAME_WIDTH = 1024;
	private static final int GAME_HEIGHT = 650;
	private final static String BACKGROUND_IMAGE = ClassLoader.getSystemResource("image/back_farm.jpg").toString();
	private final String FONT_PATH = ClassLoader.getSystemResource("font/kenvector_future.ttf").toString();

	private VBox vbox;
	private HBox hbox;

	private Label gameoverLabel;
	private Label scoreLabel;

	private HarvestButton playAgainButton;
	private HarvestButton mainMenuButton;

	private CHARACTER chooserCharacter;

	public ScoreViewManager(int score, Stage mainStage, CHARACTER chooserCharacter) {
		this.mainStage = mainStage;
		this.chooserCharacter = chooserCharacter;
		DropShadow ds = new DropShadow();
		ds.setOffsetY(3.0f);
		ds.setColor(Color.color(0.4f, 0.4f, 0.4f));

		gameoverLabel = new Label("GAME OVER!!");
		gameoverLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		gameoverLabel.setEffect(ds);
		gameoverLabel.setTextFill(Color.YELLOW);
		scoreLabel = new Label("Your score is : " + score);
		scoreLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		scoreLabel.setTextFill(Color.WHITE);

		playAgainButton = new HarvestButton("Play again");
		mainMenuButton = new HarvestButton("Main menu");

		playAgainButton.setFont(Font.loadFont(FONT_PATH, 20));
		mainMenuButton.setFont(Font.loadFont(FONT_PATH, 20));
		hbox = new HBox();
		hbox.setSpacing(50);
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(playAgainButton, mainMenuButton);

		vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(50);
		vbox.getChildren().addAll(gameoverLabel, scoreLabel, hbox);

		scoreStage = new Stage();
		scoreStage.setResizable(false);

		scene = new Scene(vbox, GAME_WIDTH, GAME_HEIGHT);
		scoreStage.setScene(scene);
		setEventOnButton();
		createBackground();
		
		

	}

	public void createSaveScoreScene() {
		scoreStage.show();

	}

	private void setEventOnButton() {
		playAgainButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				scoreStage.close();
				GameViewManager gameManager = new GameViewManager();
				gameManager.createNewGame(mainStage, chooserCharacter);
			}

		});
		mainMenuButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				scoreStage.close();
				mainStage.show();
				AudioUtility.playSound("Intro");
			}

		});
	}

	private void createBackground() {
		Image backgroundImage = new Image(BACKGROUND_IMAGE, 1040, 660, false, true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		vbox.setBackground(new Background(background));
	}

}
