package Control;

import java.io.File;
import java.io.IOException;

import java.util.Optional;

import FileManagement.FileSystem;
import Model.ProjectManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.Tesseract1;
import net.sourceforge.tess4j.TesseractException;




/**
 * Main window controller class.  Tied to the file theWindow.xml.  Handles all actions of 
 * the main window. 
 * @author Tyler Pitsch
 * @version 1.0 5/21/18
 *
 */
public class WindowControl{
	
	@FXML private AnchorPane theInfo;
	@FXML private TextField search;
	private ProjectManager manager;
	private Stage window;
	@FXML
	private Button newProject;
	
	public void makeWindow(Stage window) {
		this.window = window;
		manager = new ProjectManager();
	}
	
	/**
	 * Takes care of the file/exit option.  Pops up a box to confirm or cancel exiting the program. 
	 * @author Tyler Pitsch
	 */
	public void handleExitButton() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Caution!");
		alert.setHeaderText("Are you sure you wish to exit?");
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == ButtonType.OK){
		   System.exit(0);
		}
	}
	
	/**
	 * Takes care of the search bar
	 * @author Tyler Pitsch 
	 */
	public void handleSearch() {
		String text = search.getText();
		search.clear();
	}
	
	/**
	 * Takes care of the about menu option.  Basic about pop-up window with team info.
	 * @author Tyler Pitsch
	 */
	public void handleAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Team Information");
		alert.setHeaderText(null);
		alert.setContentText("Tyler Pitsch\nEmmett Kang\nKyle Beveridge\nReza Amjad\nMarcus Cheung");
		alert.showAndWait();
	}
	
	/**
	 * Takes care of the info window.  Creates a new window where the user can enter their 
	 * information
	 * @author Tyler Pitsch
	 */
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
	
	/**
	 * Will handle the import option. 
	 * @author Tyler Pitsch
	 */
	public void handleImport()  {
		
		FileSystem sys = new FileSystem();
		try {
			manager = sys.openNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Takes care of the export option, exporting current projects and settings
	 * @author Tyler Pitsch
	 */
	public void handleExport() {

		FileSystem sys = new FileSystem(manager);
		sys.saveCurrent();
		
		
	}
	/**
	 * @author Reza Amjad
	 * This function will open a new window so user can create a new project
	 */
	@FXML
	private void handelNewProject() {
		try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/CreateNewProject.fxml"));
            AnchorPane Ap =  loader.load();
            Stage stage = new Stage();
            stage.setTitle("Create Project");
            Scene window = new Scene(Ap);
            stage.setScene(window);
            stage.show();
            // Hide this current window (if this is what you want)
           //(((Node) event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Makes a copy of the selected projects.
	 * @author Tyler Pitsch
	 * 
	 * ***************DONT TOUCH THIS OCR CODE, I WILL FIX IT IN A WHILE
	 */
	public void handleCopy() {
		//Edited by Kyle: removed hard coded paths
		File imageFile = new File("SeattleBill.gif");
        ITesseract instance = new Tesseract();  // JNA Interface Mapping
        // ITesseract instance = new Tesseract1(); // JNA Direct Mapping
        instance.setDatapath("./tessdata");

        try {
        	
            String result = instance.doOCR(imageFile);
            //System.out.println(result);
            String thisPeriod = result.substring(result.indexOf("this period:")+13,result.indexOf("Same period")-1);
            int x = Integer.parseInt(thisPeriod);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
        

    }
	
}