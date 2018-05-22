package FileManagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Model.ProjectManager;
import javafx.stage.FileChooser;



/**
 * Takes care of all file operations that the project may need. 
 * @author Tyler Pitsch
 * @version 1.0 5/21/18
 *
 */
public class FileSystem {

	private ProjectManager manager;
	
	/**
	 * Default constructor.  Doesn't matter the operation to be performed will hold nothing until
	 * a later option is chosen.
	 * @author Tyler Pitsch
	 */
	public FileSystem() {
		manager = new ProjectManager();
	}
	
	
	/**
	 * Constructor with the desired projects to save or use.
	 * 
	 * @author Tyler Pitsch
	 * @param manager ProjectManager to be used for file operations.
	 */
	public FileSystem(ProjectManager manager) {
		this.manager = manager;
	}
	
	
	/**
	 * Opens a file chooser for the user to select the projects they wish to import.
	 * 
	 * @return the projects and settings contained within the file that was chosen.
	 * @throws IOException
	 * @author Tyler Pitsch
	 */
	public ProjectManager openNewFile() throws IOException {
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open DIY File");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("DIY", "*.DIY*"));
		File f = fileChooser.showOpenDialog(null);
		if(f != null) {
			openFile(f);
		}
		return manager;
	}
	
	/**
	 * Digests the file that was given. Reconstructs the information.
	 * 
	 * @author Tyler Pitsch
	 * @param file the file that needs to be opened.
	 * @throws IOException
	 */
	public void openFile(File file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String user = "";
		user += br.readLine();
		user += br.readLine();
		manager.addUser(user);
		String line = br.readLine();
			while (line != null) {
			    line = br.readLine();
		   }
		br.close();
		
	}
	
	
	/**
	 * Opens a file chooser for the user to save the current projects and settings.
	 * 
	 * @author Tyler Pitsch 
	 */
	public void saveCurrent() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save DIY File");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("DIY", "*.DIY*"));
		File f = fileChooser.showSaveDialog(null);
		if(f != null) {
			f = new File(f.getAbsolutePath()+ ".DIY");
			saveFile(f);
		}
		
	}
	
	
	/**
	 * Once the file has been chosen to save it will save all the infromation to that chosen file.
	 * 
	 * @author Tyler Pitsch
	 * @param file the file to save all the information to.
	 */
	private void saveFile(File file){
        try {
            FileWriter fileWriter = new FileWriter(file);
            
            fileWriter.write(manager.getUser());
            fileWriter.close();
            
        } catch (IOException ex) {
        	
        }
	}
}
