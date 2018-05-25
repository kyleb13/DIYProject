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

/**
 * Main window and application kickoff.
 * 
 * @author Tyler Pitsch
 * @version 1.0 5/21/18
 */
public class MainWindow extends Application {
    
	private Stage primaryStage;
	
	
    @Override
    public void start(Stage primaryStage) {
      this.primaryStage = primaryStage;
      mainWindow();
    }
    
    
    /**
     * Open up the main window and start the application.
     * 
     * @author Tyler Pitsch 
     */
    public void mainWindow() {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/theWindow.fxml"));
    		AnchorPane pane = loader.load();
    	 
    		Scene scene = new Scene(pane);
    		
    		WindowControl control = loader.getController();
    		control.makeWindow(primaryStage,scene);
    		
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