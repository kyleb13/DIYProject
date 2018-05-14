package GUI;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class InfoWindowControl {

	@FXML private TextField name;
	@FXML private TextField email;
	@FXML private Button ok;
	
	private String info = "";
	
	public void handleUserName() {
		String userName = name.getText();
		info +=userName;
		
		
	}
	public void handleUserEmail() {
		String userEmail = email.getText();
		info+=userEmail;
	}
	
	public void finish() {
        ((Stage) (ok.getScene().getWindow())).close();
	}
	public void post() {
		System.out.println(info);
		finish();
	}
}
