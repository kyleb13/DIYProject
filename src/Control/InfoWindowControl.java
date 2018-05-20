package Control;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class InfoWindowControl {

	
	@FXML private TextField name;
	@FXML private TextField email;
	@FXML private Button ok;
	@FXML private GridPane grid;
	@FXML private Label errorLabel;
	
	private String info = "";
	
	
	public void finish() {
        ((Stage) (ok.getScene().getWindow())).close();
	}
	public void post() {
		ObservableList<Node> l = grid.getChildren();
		info += ((TextField) l.get(0)).getText();
		info += "\n";
		info += ((TextField) l.get(1)).getText();
		if (validateEmail(((TextField) l.get(1)).getText())) {
			finish();
		}
		errorLabel.setVisible(true);
	}
	public boolean validateEmail(String str) {
		Pattern p = Pattern.compile(".*[0-9]*@.*");
		Matcher m = p.matcher(str);
		return m.matches();
	}
	public String getInfo() {
		return info;
	}
	
}
