package FileManagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Model.ProjectManager;
import javafx.stage.FileChooser;

public class FileSystem {

	private ProjectManager manager;
	
	public FileSystem() {
		manager = new ProjectManager();
	}
	public FileSystem(ProjectManager manager) {
		this.manager = manager;
	}
	
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
	
	private void saveFile(File file){
        try {
            FileWriter fileWriter = new FileWriter(file);
            
            fileWriter.write(manager.getUser());
            fileWriter.close();
            
        } catch (IOException ex) {
        	
        }
	}
}
