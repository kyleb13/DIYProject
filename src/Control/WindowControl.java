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
	private Scene windowScene;
	
	public void makeWindow(Stage window,Scene windowScene) {
		this.window = window;
		manager = new ProjectManager();
		this.windowScene = windowScene;
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
	public void handleSettings(){
		
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
	
	public void handleBill() throws IOException {
		FileSystem f = new FileSystem();
		
		f.getBill();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/OCRLoading.fxml"));
 		AnchorPane pane = loader.load();
 		Scene scene = new Scene(pane);
 		
 		OCRControl cont = loader.getController();
 		
 		//Stage s = new Stage();
 		window.setScene(scene);
 		
 		
	}
	
	/**
	 * Makes a copy of the selected projects.
	 * @author Tyler Pitsch
	 * 
	 * ***************DONT TOUCH THIS OCR CODE, I WILL FIX IT IN A WHILE
	 * @throws IOException 
	 */
	public void handleCopy() throws IOException {
		
		File imageFile = new File("C:\\Users\\Ty\\eclipse-workspace\\DIYProject\\SeattleBill.gif");
        //ITesseract instance = new Tesseract();  // JNA Interface Mapping
         ITesseract instance = new Tesseract1(); // JNA Direct Mapping
         instance.setDatapath("C:\\Users\\Ty\\eclipse-workspace\\DIYProject\\tessdata");
         
         /*
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/OCRLoading.fxml"));
 		AnchorPane pane = loader.load();
 		Scene scene = new Scene(pane);
 		
 		OCRControl cont = loader.getController();
 		
 		Stage s = new Stage();
 		window.setScene(scene);
 		*/
        try {
        	
            String result = instance.doOCR(imageFile);
            String thisPeriod = result.substring(result.indexOf("this period:")+13,result.indexOf("Same period")-1);
            int x = Integer.parseInt(thisPeriod);
            String lastPeriod = result.substring(result.indexOf("Same period",1596));
            System.out.println(result);
            if(result.contains("Meter Number: ")) {
            	System.out.println("valid bill");
            }
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
 		
        
        //window.setScene(windowScene);
    }
	
}