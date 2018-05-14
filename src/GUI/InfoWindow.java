package GUI;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InfoWindow extends Stage{
	
	
	public void infoWindow() {
		try {
			FXMLLoader loader = new FXMLLoader(MainWindow.class.getResource("/infoWindow.fxml"));
    		AnchorPane pane = loader.load();
    	
    		Scene scene = new Scene(pane);
    		setScene(scene);
    		show();
			
		}catch(IOException e) {
    		e.printStackTrace();
		}
	}
}
