package Control;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import FileManagement.FileSystem;
import Model.Project;
import Model.ProjectManager;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
public class WindowControl /*implements Initializable*/{
	
	@FXML private AnchorPane theInfo;
	@FXML private TextField search;
	private ProjectManager manager;
	private Stage window;
	@FXML
	//private Button newProject;
	private ImageView newProject;
	@FXML 
	public GridPane gp; 
	@FXML 
	public GridPane projectGrid;
	private startProjectControl spc;
	private int nextX;
	private int nextY;
	
	
	@FXML 
   //TableView<Project> table;    
   Project project;
   ObservableList<Project> data;
	
	
	public void makeWindow(Stage window) {
		this.window = window;
		manager = new ProjectManager();
		data = manager.getmyProjects();
		newProject.setImage(new Image("/icons/square_plus.png"));
		data.addListener(new ProjectListListener());//this listener class is at the bottom
		nextX = 1;
		nextY = 0;
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
            //(Edited by Kyle) passed reference to project manager to controller class of new window
            startProjectControl controller = loader.getController();
            controller.addPM(manager);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}
	
//	/**
//	 * @author Reza Amjad
//	 * will add all the existing project to the main window 
//	 */
//	public void LoadProjectsToGrid() {
//		for(int i = 0; i < manager.getmyProjects().size();i++) {
//			add_project_to_Grid(create_button(manager.getmyProjects().get(i).getName()));
//		}	
//	}
	
	/**
	 * 
	 * @param button
	 * will add the projects to the grid on the main window
	 * @param button to represent a project.
	 * by clicking the button user can open the project
	 */
  /*public void add_project_to_Grid(Button button) {
	
	int row = 0;
	int column = 0;
	button.setVisible(true);
	if(column> numOfColumn ) {
		if(row > numOfRow) {
			
			gp.addRow(numOfRow +=1,button);
			column = 0;	
		}
	}else {
		
		gp.add(button,column,row);
		column++;
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
  
  @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	  
	  data = FXCollections.observableArrayList(new Project("Project1"),
		    new Project("Project2"),
		    new Project("Project3"));
	  for(int i = 0; i < data.size();i++) {
		add_project_to_Grid(create_button(data.get(i).getName()));
	  }    
//	    if(manager.getmyProjects() != null) {
//	    	data = FXCollections.observableArrayList(manager.getmyProjects());
//	    	for(int i = 0; i < data.size();i++) {
//				add_project_to_Grid(create_button(data.get(i).getName()));
//		    }    
//	    }  	
	}*/
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
	
	/*
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
            //newProjectWindowControl controller = loader.getController();
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/*@author Kyle Beveridge
	 * 
	 * */
	private class ProjectListListener implements ListChangeListener<Project>{
		@Override
		public void onChanged(Change<? extends Project> c) {
			while(c.next()) {
				if(c.wasAdded()) {
					for(Project p:c.getAddedSubList()) {
						ImageView newpj = new ImageView("/icons/notype.png");
						Label name = new Label(p.getName());
						newpj.setFitWidth(50);
						newpj.setFitHeight(50);
						newpj.setOnMouseClicked(e -> handleOpenProject(p));
						VBox b = new VBox();
						b.setAlignment(Pos.TOP_CENTER);
						b.getChildren().addAll(newpj, name);
						VBox.setMargin(newpj, new Insets(10, 0, 0, 0));
						projectGrid.add(b, nextX, nextY);
						nextX = nextX+1<4 ? nextX+1:0;
						nextY = nextX == 0 ? nextY+1:nextY;
					}
				}
			}
			
		}


	}
}