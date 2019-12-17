package UI.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


import UI.AudioUtility;
import UI.CHARACTER;
import UI.DecreaseLifeException;
import UI.SmallInfoLabel;
import UI.TimerGUI;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import logic.Character;
import logic.charactertype.Beetle;
import logic.charactertype.Dragonfly;
import logic.charactertype.Ladybug;
import logic.charactertype.Worm;

public class GameViewManager {
	private AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;

	private static final int GAME_WIDTH = 1024;
	private static final int GAME_HEIGHT = 650;

	private Stage menuStage;
	private Character character;
	private CHARACTER choosenCharacter;

	private boolean isLeftKeyPressed;
	private boolean isRightKeyPressed;
	private boolean isPoison = false;
	private int freezecount = 0;
	private int angle;
	private AnimationTimer gameTimer;

	private GridPane gridPane1;
	private GridPane gridPane2;
	private final static String BACKGROUND_IMAGE = ClassLoader.getSystemResource("image/background_game3.png").toString();

	private final static String OBSTACLE_1 = ClassLoader.getSystemResource("image/obstacle.png").toString();
	private final static String OBSTACLE_2 = ClassLoader.getSystemResource("image/obstacle_1.png").toString();
	private final static String POISON = ClassLoader.getSystemResource("image/poison/poison.png").toString();
	private final static String SHIELD = ClassLoader.getSystemResource("image/shield/shield.png").toString();
	private final static String CHARSHIELD = ClassLoader.getSystemResource("image/shield/charactershield.png").toString();
	private final static String STRAWBERRY_IMAGE = ClassLoader.getSystemResource("image/Strawberry.png").toString();

	private ImageView[] obstacle1;
	private ImageView[] obstacle2;
	private ImageView[] characterLifes;
	private ImageView[] shieldLifes;

	Random randomPositionGenerator;

	private ImageView strawberry;
	private ImageView poison;
	private ImageView shield;
	private ImageView charactershield;
	private SmallInfoLabel pointsLabel;

	private int characterLife;
	private int shieldLife;
	private int points;

	private final static int STRAWBERRY_RADIUS = 25;
	private final static int POISON_RADIUS = 50;
	private final static int SHIELD_RADIUS = 50;
	private final static int OBSTACLE1_RADIUS = 40;
	private final static int OBSTACLE2_RADIUS = 35;
	private final static int CHARACTER_RADIUS = 30;
	private Thread thread;
	private TimerGUI timerGUI;

	private int hour;
	private int minute;
	private int second;

	public GameViewManager() {

		initializeStage();
		createKeyListener();
		randomPositionGenerator = new Random();

		charactershield = new ImageView(new Image(CHARSHIELD));

	}

	private void createKeyListener() {
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {

				if (event.getCode() == KeyCode.LEFT) {
					isLeftKeyPressed = true;
				
				} else if (event.getCode() == KeyCode.RIGHT) {
					isRightKeyPressed = true;
					
				}
				
			}

		});
		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.LEFT) {
					isLeftKeyPressed = false;
				} else if (event.getCode() == KeyCode.RIGHT) {
					isRightKeyPressed = false;
				}
		
			}

		});

	}

	private void initializeStage() {
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
		gameStage = new Stage();
		gameStage.setResizable(false);
		gameStage.setScene(gameScene);

	}

	public void createNewGame(Stage menuStage, CHARACTER choosenCharacter) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		this.choosenCharacter = choosenCharacter;
		AudioUtility.playSound("Start");
		createBackground();
		createCharacter(choosenCharacter);
		createGameElements(choosenCharacter);
		createGameLoop();

		gameStage.show();
	}

	private void createGameElements(CHARACTER choosenCharacter) {
		characterLife = character.getLifepoint() - 1;
		shieldLife = 0;
		strawberry = new ImageView(STRAWBERRY_IMAGE);
		strawberry.setFitHeight(50);
		strawberry.setFitWidth(50);
		setNewElementPosition(strawberry);
		gamePane.getChildren().add(strawberry);

		poison = new ImageView(POISON);
		poison.setFitHeight(100);
		poison.setFitWidth(100);
		setNewElementPosition(poison);
		gamePane.getChildren().add(poison);

		shield = new ImageView(SHIELD);
		shield.setFitHeight(100);
		shield.setFitWidth(100);
		setNewElementPosition(shield);
		gamePane.getChildren().add(shield);

		pointsLabel = new SmallInfoLabel("POINTS : 00");
		pointsLabel.setLayoutX(460);
		pointsLabel.setLayoutY(20);

		gamePane.getChildren().add(pointsLabel);

		timerGUI = new TimerGUI();
		threadInitialize();
		timerGUI.setLayoutX(50);
		timerGUI.setLayoutY(15);
		resetHandle();
		gamePane.getChildren().add(timerGUI);
		thread.start();

		shieldLifes = new ImageView[5];

		characterLifes = new ImageView[characterLife + 1];
		for (int i = 0; i < characterLifes.length; i++) {
			characterLifes[i] = new ImageView(choosenCharacter.getUrlLife());
			characterLifes[i].setFitHeight(40);
			characterLifes[i].setFitWidth(45);
			characterLifes[i].setLayoutX(555 - (i * 50));
			characterLifes[i].setLayoutY(80);
			gamePane.getChildren().add(characterLifes[i]);
		}

		obstacle1 = new ImageView[9];
		for (int i = 0; i < obstacle1.length; i++) {
			ImageView ob1 = new ImageView(OBSTACLE_1);
			ob1.setFitHeight(100);
			ob1.setFitWidth(100);
			obstacle1[i] = ob1;
			setNewElementPosition(obstacle1[i]);
			gamePane.getChildren().add(obstacle1[i]);
		}
		obstacle2 = new ImageView[9];
		for (int i = 0; i < obstacle2.length; i++) {
			ImageView ob2 = new ImageView(OBSTACLE_2);
			ob2.setFitHeight(80);
			ob2.setFitWidth(80);
			obstacle2[i] = ob2;
			setNewElementPosition(obstacle2[i]);
			gamePane.getChildren().add(obstacle2[i]);
		}
	}

	private void moveGameElements() {
		strawberry.setLayoutY(strawberry.getLayoutY() + 5);
		shield.setLayoutY(shield.getLayoutY() + 5);
		poison.setLayoutY(poison.getLayoutY() + 5);
		for (int i = 0; i < obstacle1.length; i++) {
			obstacle1[i].setLayoutY(obstacle1[i].getLayoutY() + character.getVelocity()+(second/1000));
		}
		for (int i = 0; i < obstacle2.length; i++) {
			obstacle2[i].setLayoutY(obstacle2[i].getLayoutY() + character.getVelocity()+(second/1000));
		}
	}

	private void generatePassedElement() {
		if (strawberry.getLayoutY() > 800) {
			setNewElementPosition(strawberry);
		}
		if (poison.getLayoutY() > 900) {
			setNewElementPosition(poison);
		}
		if (shield.getLayoutY() > 900) {
			setNewElementPosition(shield);
		}

		for (int i = 0; i < obstacle1.length; i++) {
			if (obstacle1[i].getLayoutY() > 900) {
				setNewElementPosition(obstacle1[i]);
			}

		}
		for (int i = 0; i < obstacle2.length; i++) {
			if (obstacle2[i].getLayoutY() > 900) {
				setNewElementPosition(obstacle2[i]);
			}
		}
	}

	private void setNewElementPosition(ImageView image) {
		image.setLayoutX(randomPositionGenerator.nextInt(800)+100);
		image.setLayoutY(-(randomPositionGenerator.nextInt(3200) + 600));
	}

	private void createCharacter(CHARACTER choosenCharacter) {
		String type = choosenCharacter.gettypeVehicle();
		if (type.equals("LADYBUG")) {
			character = new Ladybug(choosenCharacter.geturl());
		} else if (type.equals("WORM")) {
			character = new Worm(choosenCharacter.geturl());
		} else if (type.equals("DRAGONFLY")) {
			character = new Dragonfly(choosenCharacter.geturl());
		} else if (type.equals("BEETLE")) {
			character = new Beetle(choosenCharacter.geturl());
		}
		character.setLayoutX(450);
		character.setLayoutY(550);
		charactershield = new ImageView(new Image(CHARSHIELD));
		charactershield.setLayoutX(425);
		charactershield.setLayoutY(530);
		charactershield.setFitHeight(150);
		charactershield.setFitWidth(150);
		charactershield.setVisible(false);

		gamePane.getChildren().addAll(character,charactershield);
	}

	private void createGameLoop() {
		gameTimer = new AnimationTimer() {

			@Override
			public void handle(long arg0) {
				moveBackground();
				moveGameElements();
				try {
					checkIfElementCollide();
				} catch (DecreaseLifeException e) {
					e.printStackTrace();
				}
				generatePassedElement();
				second++;
				updateGUI();
				moveCharacter();

			}

		};
		gameTimer.start();
	}

	private void moveCharacter() {
		if (!isPoison) {
			if (isLeftKeyPressed && !isRightKeyPressed) {
				if (angle > -30) {
					angle -= 5;
				}
				character.setRotate(angle);
				if (character.getLayoutX() > 100) {
					character.setLayoutX(character.getLayoutX() - character.getVelocity()/2);
					charactershield.setLayoutX(charactershield.getLayoutX() - character.getVelocity()/2);
				}
			}
			if (isRightKeyPressed && !isLeftKeyPressed) {
				if (angle < 30) {
					angle += 5;
				}
				character.setRotate(angle);
				if (character.getLayoutX() < 819) {
					character.setLayoutX(character.getLayoutX() + character.getVelocity()/2);
					charactershield.setLayoutX(charactershield.getLayoutX() + character.getVelocity()/2);
				}
			}
			if (isLeftKeyPressed && isRightKeyPressed) {
				if (angle < 0) {
					angle += 5;
				} else if (angle > 0) {
					angle -= 5;
				}
				character.setRotate(angle);
			}
			if (!isLeftKeyPressed && !isRightKeyPressed) {
				if (angle < 0) {
					angle += 5;
				} else if (angle > 0) {
					angle -= 5;
				}
				character.setRotate(angle);
			}
		}

	}

	private void createBackground() {
		gridPane1 = new GridPane();
		gridPane2 = new GridPane();

		for (int i = 0; i < 12; i++) {
			ImageView BackgroundImage1 = new ImageView(BACKGROUND_IMAGE);
			ImageView BackgroundImage2 = new ImageView(BACKGROUND_IMAGE);
			BackgroundImage1.setFitHeight(GAME_HEIGHT);
			BackgroundImage1.setFitWidth(GAME_WIDTH);
			BackgroundImage2.setFitHeight(GAME_HEIGHT);
			BackgroundImage2.setFitWidth(GAME_WIDTH);
			GridPane.setConstraints(BackgroundImage1, i % 3, i / 3);
			GridPane.setConstraints(BackgroundImage2, i % 3, i / 3);
			gridPane1.getChildren().add(BackgroundImage1);
			gridPane2.getChildren().add(BackgroundImage2);
		}
		gridPane2.setLayoutY(-1024);
		gamePane.getChildren().addAll(gridPane1, gridPane2);
	}

	private void moveBackground() {
		gridPane1.setLayoutY(gridPane1.getLayoutY() + character.getVelocity()+(second/1000));
		gridPane2.setLayoutY(gridPane2.getLayoutY() + character.getVelocity()+(second/1000));
		if (gridPane1.getLayoutY() >= 1024) {
			gridPane1.setLayoutY(-1024);
		}
	
		if (gridPane2.getLayoutY() >= 1024) {
			gridPane2.setLayoutY(-1024);
		}
	}

	private void checkIfElementCollide() throws DecreaseLifeException {
		if (CHARACTER_RADIUS + STRAWBERRY_RADIUS > calculateDistance(character.getLayoutX() + 49, strawberry.getLayoutX() + 15,
				character.getLayoutY() + 37, strawberry.getLayoutY() + 15)) {
			setNewElementPosition(strawberry);
			points += 10;
			increaseLife();
			AudioUtility.playSound("GainScore");
			String textToSet = "POINTS: ";
			if (points < 10) {
				textToSet = textToSet + "0";
			}
			pointsLabel.setText(textToSet + points);
		}
		if (isPoison) {
			freezecount++;
		}
		if (freezecount == 100) {
			isPoison = false;
			character.setImage(new Image(choosenCharacter.geturl()));
			freezecount = 0;
			AudioUtility.stopSound("Freeze");
		}
		if (CHARACTER_RADIUS + POISON_RADIUS > calculateDistance(character.getLayoutX() + 49, poison.getLayoutX() + 15,
				character.getLayoutY() + 37, poison.getLayoutY() + 15)) {
			isPoison = true;
			setNewElementPosition(poison);
			AudioUtility.playSound("Freeze");
			character.setImage(new Image(choosenCharacter.getfreezVehicle()));
		}
		if (CHARACTER_RADIUS + SHIELD_RADIUS > calculateDistance(character.getLayoutX() + 49, shield.getLayoutX() + 15,
				character.getLayoutY() + 37, shield.getLayoutY() + 15)) {

			shieldLife++;
			charactershield.setVisible(true);
			shieldLifes[shieldLife] = new ImageView(new Image(SHIELD));
			shieldLifes[shieldLife].setFitHeight(40);
			shieldLifes[shieldLife].setFitWidth(45);
			shieldLifes[shieldLife].setLayoutX(605 - (shieldLife * 50));
			shieldLifes[shieldLife].setLayoutY(140);
			gamePane.getChildren().add(shieldLifes[shieldLife]);
			setNewElementPosition(shield);
			AudioUtility.playSound("Shield");

		}
		for (int i = 0; i < obstacle1.length; i++) {
			if (CHARACTER_RADIUS + OBSTACLE1_RADIUS > calculateDistance(character.getLayoutX()+10,
					obstacle1[i].getLayoutX() +10, character.getLayoutY() + 37, obstacle1[i].getLayoutY() + 20)) {
				if (shieldLife > 0) {
					removeShield();
				} else {
					removeLife();
				}
				if (shieldLife <= 0) {
					charactershield.setVisible(false);
				}

				setNewElementPosition(obstacle1[i]);
				AudioUtility.playSound("LoseLife");
			}
		}
		for (int i = 0; i < obstacle2.length; i++) {
			if (CHARACTER_RADIUS + OBSTACLE2_RADIUS > calculateDistance(character.getLayoutX() + 10,
					obstacle2[i].getLayoutX() + 20, character.getLayoutY() + 37, obstacle2[i].getLayoutY() + 20)) {
				
				if (shieldLife > 0) {
					removeShield();
				} else {
					removeLife();
				}
				if (shieldLife <= 0) {
					charactershield.setVisible(false);
				}
				setNewElementPosition(obstacle2[i]);
				AudioUtility.playSound("LoseLife");
			}
		}
	}

	private void removeShield() throws DecreaseLifeException {
		if (shieldLife == -1) {
			throw new DecreaseLifeException();
		}
		gamePane.getChildren().remove(shieldLifes[shieldLife]);
		shieldLife--;
	}

	private void removeLife() throws DecreaseLifeException{
		if (shieldLife == -1) {
			throw new DecreaseLifeException();
		}
		gamePane.getChildren().remove(characterLifes[characterLife]);
		characterLife--;
		if (characterLife < 0) {
			AudioUtility.playSound("EndGame");
			gameStage.close();
			gameTimer.stop();
			AudioUtility.stopSound("Start");
			ScoreViewManager score = new ScoreViewManager(points, menuStage, choosenCharacter);
			score.createSaveScoreScene();
		}
	}

	private void increaseLife() {
		if (characterLife < characterLifes.length - 1) {
			characterLife++;
			gamePane.getChildren().add(characterLifes[characterLife]);
		}

	}

	private double calculateDistance(Double x1, Double x2, Double y1, Double y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}

	private void threadInitialize() {

		thread = new Thread(new Runnable() {
			public void run() {
				// TODO Auto-generated method stub

				try {
					updateTime();
					updateGUI();
					Thread.sleep(1000);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

	}

	private void updateGUI() {

		// implement your code here
		Platform.runLater(() -> {

			timerGUI.update(getTimeString());

		});
	}

	@SuppressWarnings("deprecation")
	private String getTimeString() {
		// implement your code here
		return (new SimpleDateFormat("HH:mm:ss")).format(new Date(0, 0, 0, hour, minute, second));
	}

	private void updateTime() {

		// implement your code here
		if (second == 60) {
			minute++;
			second = 0;
		}
		if (minute == 60) {
			hour++;
			minute = 0;
		}
		if (hour == 24) {
			hour = 0;
		}
	}

	private void resetHandle() {

		hour = minute = second = 0;
		updateGUI();
	}

}
