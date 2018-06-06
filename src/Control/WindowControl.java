package Control;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ConcurrentModificationException;
import java.util.Optional;
import FileManagement.FileSystem;
import Model.Project;
import Model.ProjectManager;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
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
	@FXML
	//private Button newProject;
	private ImageView newProject;
	@FXML 
	public GridPane gp; 
	@FXML 
	public GridPane projectGrid;
	private startProjectControl spc;
	
	
	@FXML 
   //TableView<Project> table;    
   Project project;
   ObservableList<Project> data;
	
	public void makeWindow(Stage window,Scene windowScene) {
		this.window = window;
		manager = new ProjectManager();
		this.windowScene = windowScene;
		data = manager.getmyProjects();
		newProject.setImage(new Image("/icons/square_plus.png"));
		data.addListener(new ProjectListListener());//this listener class is at the bottom
	}
	
	/**
	 * Takes care of the file/exit option.  Pops up a box  to confirm or cancel exiting the program. 
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
    		manager.addUser(cont.getInfo(),cont.getMeterNumber());
    		
    		
			
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
            //(Edited by Kyle) passed reference to project manager to controller class of new window
            startProjectControl controller = loader.getController();
            controller.addPM(manager);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
/**
 * OCR for user to enter their electric bills, most operations are handeled on the back by
 * Tesseract but the information and saving of information is handeled here and in Project Manager
 * @author Tyler Pitsch
 * @throws IOException bad files
 */
	public void handleBill() throws IOException {
		
		FileSystem f = new FileSystem();
 		
 		File file = f.getBill();//grabs the bill the user wants to import
 		//if the files is null then we can just terminate
 		if(file != null) {
	 		ITesseract instance = new Tesseract();  // JNA Interface Mapping
	        // ITesseract instance = new Tesseract1(); // JNA Direct Mapping
	        instance.setDatapath("./tessdata");
	
	        //attempt to do OCR on the given file
	        try {
	        	
	            String result = instance.doOCR(file);
	            try {
	            	//from the generated string grab the important information
	            	int meterNumber = Integer.parseInt(result.substring(result.indexOf("Meter Number: ")+14,result.indexOf("Meter Number: " )+21));
		            String thisPeriod = result.substring(result.indexOf("this period:")+13,result.indexOf("Same period")-1);
		           
		            //depending on the information we can upload or throw out the information
		            int x = Integer.parseInt(thisPeriod);
		            if(manager.getMeterNumber() != meterNumber) {
		            	Alert alert = new Alert(AlertType.INFORMATION);
		        		alert.setTitle("This bill is invalid for the current meter, please update the information");
		        		alert.setHeaderText(null);
		        		alert.setContentText("bad things");
		        		alert.showAndWait();
		            }else {
		            	manager.addMeterMeasure(x);
		            	Alert alert = new Alert(AlertType.INFORMATION);
		        		alert.setTitle("Information Added");
		        		alert.setHeaderText(null);
		        		alert.setContentText("Success");
		        		alert.showAndWait();
		            }
		            
	            }catch(Exception e){
	            	Alert alert = new Alert(AlertType.INFORMATION);
	        		alert.setTitle("File No Good");
	        		alert.setHeaderText(null);
	        		alert.setContentText("The file you selected is invalid, please check the information");
	        		alert.showAndWait();
	            }
	        } catch (TesseractException e) {}
 		}
 		
 		
	}
	
	/**
	 * @author Kyle Beveridge
	 * */
	public void handleOpenProject(Project theProject) {
		try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/NewProject.fxml"));
            AnchorPane Ap =  loader.load();
            Stage stage = new Stage();
            stage.setTitle(theProject.getName());
            Scene window = new Scene(Ap);
            stage.setScene(window);
            //uncomment next line if you want to pass something to the controller class

            newProjectWindowControl controller = loader.getController();
            controller.addProject(theProject);
            controller.setupAvailibleMaterials();
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void handleContextMenu(double x, double y, Project p) {
		ContextMenu menu = new ContextMenu();
		MenuItem delete = new MenuItem("Delete");
		MenuItem copy = new MenuItem("Copy");
		delete.setOnAction(e-> manager.deleteProject(p));
		copy.setOnAction(e-> manager.copyProject(p));
		menu.getItems().addAll(delete, copy);
		menu.show(window, x, y);
	}
	
	/**
	 * @author Kyle Beveridge
	 * 
	 * */
	private class ProjectListListener implements ListChangeListener<Project>{
		@Override
		public void onChanged(Change<? extends Project> c) {
			while(c.next()) {
				if(c.wasAdded()) {//New project added
					for(Project p:c.getAddedSubList()) {//iterate through added projects
						ImageView newpj = new ImageView("/icons/notype.png");
						Label name = new Label(p.getName());
						newpj.setFitWidth(50);
						newpj.setFitHeight(50);
						newpj.setOnMouseClicked(e -> {
							if(e.getButton().toString() == "PRIMARY") {
								handleOpenProject(p);//open project edit window on left click
							} else if(e.getButton().toString() == "SECONDARY"){
								handleContextMenu(e.getScreenX(), e.getScreenY(), p);//bring up context menu on right click
							}
						});
						addToGrid(newpj, name);
					}
				} else if(c.wasRemoved()) {
					for(Project p:c.getRemoved()) {
						for(Node child:projectGrid.getChildren()) {
							if(child.getClass() == VBox.class) {
								VBox current = (VBox) child;
								if(current.getChildren().size() !=0 && ((Label)current.getChildren().get(1)).getText() == p.getName()) {
									current.getChildren().clear();
									break;
								}
							}
						}
					}
				}
			}
			
		}
		
		
		private void addToGrid(ImageView img, Label name) {
			for(Node n:projectGrid.getChildren()) {
				if(n.getClass() == VBox.class) {
					VBox b = ((VBox) n);
					if(b.getChildren().size() == 0) {
						b.getChildren().addAll(img, name);
						VBox.setMargin(img, new Insets(10, 0, 0, 0));
						break;
					}
				}
			}
		}


	}
}