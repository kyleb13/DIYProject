package Control;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class startProjectControl {

    @FXML
    private Button cancel;

    @FXML
    private GridPane grid;

    @FXML
    private Button finish;

    @FXML
    private TextField text;

    @FXML
    private Label errorLabel;
    
    private String projectName = "";

    @FXML
    void handelCancelButtonAction() {
    	((Stage)(cancel.getScene().getWindow())).close();
    }

    @FXML
    void handelFinishButtonAction() {
    	String title = "";
    	ObservableList<Node> input = grid.getChildren();
    	
    	
		if (nameValidation(((TextField) input.get(0)).getText())) {
			projectName += ((TextField) input.get(0)).getText();
			errorLabel.setVisible(false);
			title += projectName;
			((Stage)(finish.getScene().getWindow())).close();
			setupNewProjectWindow(title);	
		}else {
			errorLabel.setVisible(true);
			((TextField) input.get(0)).clear();
		}
    }

   
    boolean nameValidation(String name) {
    	Pattern p = Pattern.compile("^[A-Za-z][A-Za-z0-9]*$");
		Matcher m = p.matcher(name);
		return m.matches();
    }
    
    private void setupNewProjectWindow(String str) {
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/NewProject.fxml"));
            AnchorPane Ap =  loader.load();
            Stage stage = new Stage();
            stage.setTitle(str);
            Scene window = new Scene(Ap);
            stage.setScene(window);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
       }
    }

}
