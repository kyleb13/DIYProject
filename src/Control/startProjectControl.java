package Control;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Model.Project;
import Model.ProjectManager;
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
	//private WindowControl main;
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

	
	private ProjectManager pm ;
	private WindowControl main;
	Project project;

    @FXML
    void handelCancelButtonAction() {
    	((Stage)(cancel.getScene().getWindow())).close();
    }

    @FXML
    void handelFinishButtonAction() {
    	
    	ObservableList<Node> input = grid.getChildren();
		if (nameValidation(((TextField) input.get(0)).getText())) {
			projectName += ((TextField) input.get(0)).getText();
			errorLabel.setVisible(false);
			pm.addProject(project = new Project(projectName));
			add_new_project_to_grid(create_button(projectName));
			((Stage)(finish.getScene().getWindow())).close();
		}else {
			errorLabel.setVisible(true);
			((TextField) input.get(0)).clear();
		}
    }
    boolean nameValidation(String name) {
    	Pattern p = Pattern.compile(".*[0-9]*$");
		Matcher m = p.matcher(name);
		return m.matches();
    }
    
 
    
public void add_new_project_to_grid(Button button) {
	ObservableList<Node> index = main.gp.getChildren();
	int i = 0;
	if(index.get(i) == null) {
		main.add_project_to_Grid(button);
	}else {
		while(index.get(i) != null) {
			i++;
		}
		main.add_project_to_Grid(button);
	}
}
    
   public Button create_button(String name) {	
	
		Button button = new Button(name);
		System.out.println(button.getText());
    	button.setOnAction(e->{
    		 try {
    	            FXMLLoader loader= new FXMLLoader(getClass().getResource("/NewProject.fxml"));
    	            AnchorPane Ap =  loader.load();
    	            Stage stage = new Stage();
    	            Scene window = new Scene(Ap);
    	            stage.setScene(window);
    	            stage.show();
    	        }
    	        catch (IOException e1) {
    	            e1.printStackTrace();
    	       }
    	    
    	});
    	return button;
    }   
    
}
