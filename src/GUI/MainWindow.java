package GUI;
import java.io.IOException;
import Control.WindowControl;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class MainWindow extends Application {
    
	private Stage primaryStage;
	
	
    @Override
    public void start(Stage primaryStage) {
      this.primaryStage = primaryStage;
      mainWindow();
    }
    
    public void mainWindow() {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/theWindow.fxml"));
    		AnchorPane pane = loader.load();
    	 
    		Scene scene = new Scene(pane);
    		
    		WindowControl control = loader.getController();
    		control.makeWindow(primaryStage);
    		
    		primaryStage.setScene(scene);
    		primaryStage.show();
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    
    }
    
 public static void main(String[] args) {
        launch(args);
    }
}