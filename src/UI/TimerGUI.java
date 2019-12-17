package UI;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TimerGUI extends VBox {

	// implement your code here
	private Label displayPart;

	public TimerGUI() {

		
		DropShadow ds = new DropShadow();
		ds.setOffsetY(10f);
		ds.setColor(Color.color(0.4f, 0.4f, 0.4f));


		
		
		setAlignment(Pos.CENTER);
		setSpacing(10);
		setPadding(new Insets(10));
		displayPart = new Label();
		displayPart.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		displayPart.setEffect(ds);
		displayPart.setTextFill(Color.YELLOW);
		getChildren().add(displayPart);

	}




	// Generate getters
	public void update(String text) {

		// implement your code here
		displayPart.setText(text);

	}

}
