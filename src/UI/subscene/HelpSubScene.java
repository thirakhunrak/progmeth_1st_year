package UI.subscene;

import UI.view.HarvestSubScene;
import javafx.scene.image.ImageView;

public class HelpSubScene extends HarvestSubScene {
	ImageView helpImage;

	public HelpSubScene() {
		helpImage = new ImageView(ClassLoader.getSystemResource("image/help.png").toString());
		helpImage.setFitWidth(HARVEST_SUBSCENE_WIDTH - 100);
		helpImage.setFitHeight(HARVEST_SUBSCENE_HEIGHT - 100);
		helpImage.setLayoutX(50);
		helpImage.setLayoutY(25);
		getPane().getChildren().add(helpImage);
	}
}