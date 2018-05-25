package Control;

import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class OCRControl {
	@FXML ProgressBar progBar;
	@FXML ImageView img;
	@FXML AnchorPane rootPane;
	
	public void handleProgBar() {
		TranslateTransition tt = 
			 new TranslateTransition(Duration.seconds(1), img);

		tt.setFromX( -(img.getFitWidth()) );
		tt.setToX( rootPane.getPrefWidth() );
		tt.setCycleCount( Timeline.INDEFINITE );
			tt.play();
		progBar.setProgress(.50);
	}
}
