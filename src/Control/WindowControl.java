package Control;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import FileManagement.FileSystem;
import Model.ProjectManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class WindowControl{
	
	@FXML private AnchorPane theInfo;
	@FXML private TextField search;
	private ProjectManager manager;
	private Stage window;
	
	public void makeWindow(Stage window) {
		this.window = window;
		manager = new ProjectManager();
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
		search.clear();
	}
	
	public void handleAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Team Information");
		alert.setHeaderText(null);
		alert.setContentText("Tyler Pitsch\nEmmett Kang\nKyle Beveridge\nReza Amjad\nMarcus Cheung");
		alert.showAndWait();
	}
	
	public void handleSettings() {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/infoWindow.fxml"));
    		AnchorPane pane = loader.load();
    		Scene scene = new Scene(pane);
    		
    		InfoWindowControl cont = loader.getController();
    		
    		Stage s = new Stage();
    		s.setScene(scene);
    		s.showAndWait();
    		manager.addUser(cont.getInfo());
    		
			
		}catch(IOException e) {
    		e.printStackTrace();
		}
		
	}
	
	
	public void handleImport() throws IOException {
		
		FileSystem sys = new FileSystem();
		manager = sys.openNewFile();
		
	}
	
	
	public void handleExport() {

		FileSystem sys = new FileSystem(manager);
		sys.saveCurrent();
		
		
	}
	
	
	public void handleCopy() {
		System.out.println("COPY");
	}
	
	
	
	
	
	
	
	
	
	/*
	private void saveFile(String content, File file){
        try {
            FileWriter fileWriter = new FileWriter(file);
            
            fileWriter.write(content);
            fileWriter.close();
            
        } catch (IOException ex) {
        	
        }
        
	}
	*/
	
	
	
}
