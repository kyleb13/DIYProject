package Control;

import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class OCRControl {
	
	@FXML private Button exit;
	@FXML private Button retry;
	@FXML private Label errorLabel;
	@FXML private Label loading;
	
	@FXML private AnchorPane rootPane;
	
	public void handleInvalidBill() {
		loading.setVisible(false);
		errorLabel.setVisible(true);
		
	}
	
	public void handleValidBill() {
		
	}
	
	public void handleExit() {
		
	}
	
	
	
}
