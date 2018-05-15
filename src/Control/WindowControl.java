package Control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Optional;

import GUI.InfoWindow;
import GUI.MainWindow;
import Model.ProjectManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class WindowControl{
	
	
	@FXML private TextField search;
	//@FXML private AnchorPane pane;
	
	private Window window;
	
	
	private ProjectManager proj;
	
	public void makeWindow(Window window) {
		this.window = window;
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
	
	public void handleSettings()  {
		
		InfoWindow w = new InfoWindow();
		w.infoWindow();
		
	}
	
	
	public void handleImport() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("DIY", "*.DIY*"));
		File f = fileChooser.showOpenDialog(window);
		if(f != null) {
			openFile(f);
		}
	}
	public void handleExport() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("DIY", "*.DIY*"));
		File f = fileChooser.showSaveDialog(window);
		if(f != null) {
			f = new File(f.getAbsolutePath()+".DIY");
			saveFile("newProj",f);
		}
		
	}
	private void saveFile(String content, File file){
        try {
            FileWriter fileWriter = null;
             
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
        	
        }
	}
	
	private void openFile(File file) {
		
	}

	
	
}
