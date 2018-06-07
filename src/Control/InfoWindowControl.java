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


/**
 * Controller class for the info window.  Handled by FXML file infoWindow.fxml.
 * 
 * @author Tyler Pitsch
 * @version 1.0 5/21/18
 *
 */
public class InfoWindowControl {

	@FXML private Button ok;
	@FXML private GridPane grid;
	@FXML private Label errorLabel;
	
	private String info = "";
	private int meterNumber = 0;
	
	/**
	 * Final actions to be taken by the window before being closed.
	 * @author Tyler Pitsch
	 */
	public void finish() {
        ((Stage) (ok.getScene().getWindow())).close();
	}
	
	/**
	 * Grabs the name and email the user enters.  If the email the user enters is invaild 
	 * the error message will be displayed and will continue to wait for valid input.
	 * @author Tyler Pitsch
	 */
	public void post() {
		ObservableList<Node> l = grid.getChildren();
		info += ((TextField) l.get(0)).getText();
		info += "\n";
		info += ((TextField) l.get(1)).getText();
		String m = ((TextField) l.get(5)).getText();
		meterNumber = Integer.parseInt(m);
		
		if (validateEmail(((TextField) l.get(1)).getText())) {
			finish();
		}
		errorLabel.setVisible(true);
	}
	
	/**
	 * Validates the given string using regular expression.
	 * 
	 * @author Tyler Pitsch
	 * @param str the email that needs to be validated
	 * @return true if the email is valid; here valid means anything that is similar
	 * to something@website.com
	 */
	public boolean validateEmail(String str) {
		Pattern p = Pattern.compile(".*[0-9]*@.*");
		Matcher m = p.matcher(str);
		return m.matches();
	}
	/**
	 * Gives back the info the user typed.
	 * @return Tyler Pitsch
	 * @return the string that the user entered.
	 */
	public String getInfo() {
		return info;
	}
	public int getMeterNumber() {
		return meterNumber;
	}
	
}
