package Control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Optional;

import GUI.MainWindow;
import Model.ProjectManager;
import javafx.event.EventDispatcher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class WindowControl{
	
	@FXML private AnchorPane theInfo;
	@FXML private TextField search;
	private ProjectManager manager;
	private Stage window;
	private String user;
	private String email;
	
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
	
	public void handleSettings() {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/infoWindow.fxml"));
		 
    		AnchorPane pane = loader.load();
    	
    		Scene scene = new Scene(pane);
    		
    		InfoWindowControl cont = loader.getController();
    		
    		Stage s = new Stage();
    		s.setScene(scene);
    		s.showAndWait();
    		user = cont.getInfo();
    		
			
		}catch(IOException e) {
    		e.printStackTrace();
		}
		
	}
	
	
	public void handleImport() throws IOException {
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
			f = new File(f.getAbsolutePath()+ ".DIY");
			saveFile(user,f);
		}
		
	}
	private void saveFile(String content, File file){
        try {
            FileWriter fileWriter = new FileWriter(file);
            
            fileWriter.write(content);
            fileWriter.close();
            
        } catch (IOException ex) {
        	
        }
	}
	
	private void openFile(File file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		user = br.readLine();
		email = br.readLine();
		br.close();
		
	}

	
	
}
