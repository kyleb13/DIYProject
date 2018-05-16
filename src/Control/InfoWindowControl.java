package Control;

import java.util.List;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class InfoWindowControl {

	//private static final Node TextField = null;
	@FXML private TextField name;
	@FXML private TextField email;
	@FXML private Button ok;
	@FXML private GridPane grid;
	
	
	private static String info = "";
	
	public void handleUserName() {
		//String userName = name.getText();
		//info +=name.getText();
		
		
	}
	public void handleUserEmail() {
		//String userEmail = email.getText();
		//info+=email.getText();
	}
	
	public void finish() {
        ((Stage) (ok.getScene().getWindow())).close();
	}
	public void post() {
		ObservableList<Node> l = grid.getChildren();
		info += ((TextField) l.get(0)).getText();
		info += ((TextField) l.get(1)).getText();
		System.out.println(info);
		//TODO: Call file out here to save the name and email with the projects
		
		
		finish();
	}
}
