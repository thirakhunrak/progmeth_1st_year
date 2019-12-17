package UI.subscene;


import UI.view.HarvestSubScene;
import javafx.scene.image.ImageView;


public class CreditSubScene extends HarvestSubScene {
	ImageView creditImage;

	public CreditSubScene() {
		
		creditImage = new ImageView(ClassLoader.getSystemResource("image/credit.png").toString());
		creditImage.setFitWidth(HARVEST_SUBSCENE_WIDTH-50);
		creditImage.setFitHeight(HARVEST_SUBSCENE_HEIGHT-50);
		creditImage.setLayoutX(10);
		creditImage.setLayoutY(10);
		getPane().getChildren().add(creditImage);
	}
}
