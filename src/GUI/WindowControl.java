package GUI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

public class WindowControl{
	
	
	//@FXML private Button exitButton;
	@FXML private TextField search;
	
	
	//private MainWindow main;
	
	public void setMain(MainWindow main) {
		//this.main = main;
	}
	
	public void handleExitButton() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Caution!");
		alert.setHeaderText("Are you sure you wish to exit?");
		alert.setContentText("");
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == ButtonType.OK){
		   System.exit(0);
		}
	}
	
	public void handleSearch() {
		String text = search.getText();
		//System.out.println(text);
		search.clear();
	}
	
	public void handleAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Team Information");
		alert.setHeaderText(null);
		alert.setContentText("Tyler Pitsch\nEmmett Kang\nKyle Beveridge\nReza Amjad\nMarcus Cheung");
		alert.showAndWait();
	}
	
	public void handleExport() throws FileNotFoundException {
		
		//File outFile = new File("userData.txt");
		//FileOutputStream fos = new FileOutputStream(outFile);
		//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		
		InfoWindow w = new InfoWindow();
		w.infoWindow();
		
		
		
		
	}
	
	
	public void handleImport() {
		System.out.println("Read the shit");
	}

	
	
}
