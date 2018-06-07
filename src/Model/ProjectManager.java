package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//TODO: add support to read in existing projects from file system
//TODO: javadoc and commenting
public class ProjectManager {
		private ObservableList<Project> myProjects;
		private String myName;
		private String myEmail;
		private int meterNumber;
		private ArrayList<Integer> meterNumbers;
		
		
		public ProjectManager() {
			myProjects = FXCollections.observableArrayList();
			myName = "";
			myEmail = "";
			
			meterNumbers = new ArrayList<>();
		}
		
		public ObservableList<Project> getmyProjects() {
			return myProjects;
		}
		
		/**
		 * @author Kyle Beveridge
		 * */
		public void addProject(Project theProject) {
			myProjects.add(theProject);
		}
		
		/**
		 * @author Kyle Beveridge
		 * 
		 * Copies the given project, adds it to the project list,
		 * and returns a reference to the new project
		 * */
		public Project copyProject(Project theProject) {
			int i = 1;
			String oldName = theProject.getName();
			String newName = oldName + "(" + i + ")";
			while(findProjectByName(newName) != -1) {
				i++;
				newName = oldName + "(" + i + ")";
			}
			Project newproj = new Project(theProject.getType(),newName, theProject.getCost(), theProject.getHours());
		//	newproj.setMaterials(theProject.getMaterials());
			myProjects.add(newproj);
			return newproj;
		}
		
		/**
		 * @author Kyle Beveridge
		 * */
		public void deleteProject(Project theProject) {
			myProjects.remove(theProject);
		}
		
		/**
		 * @author Kyle Beveridge
		 * */
		private int findProjectByName(String theName) {
			int idx = -1;
			String temp = "";
			for(int i = 0; i<myProjects.size(); i++) {
				temp = myProjects.get(i).getName();
				if(temp.equals(theName)) {
					idx = i;
					break;
				}
			}
			return idx;
		}
		
		/**
		 * Adds the users information to this set of projects.
		 * 
		 * @author Tyler Pitsch
		 * @param user the information about the user to be saved later. 
		 */
		public void addUser(String user,int meterNum) {
			
			this.myName = user.substring(0, user.indexOf("\n")+1);
			this.myEmail = user.substring(user.indexOf("\n")+1, user.length());
			meterNumber = meterNum;
		}
		public void addMeterMeasure(int val) {
			meterNumbers.add(val);
		}
		
		public String getUser() {
			return myName + myEmail;
		}
		/**
		 * Return the name.
		 * @author Emmett Kang
		 * @return name of the project manager
		 */
		public String getUserName() {
			return myName;
		}
		public String getUserEmail() {
			return myEmail;
		}
		/**
		 * This method finds the text file with materials and reads through the
		 * file content and generate the list with that information and return it.
		 * Runs similar to DataManager's reteriveProjects.
		 * @author Emmett Kang
		 * @return ObservableList object that contains all of the materials.
		 * @throws IOException If the file cannot be found(I.E there's no project manager for the user), throw stuff. 
		 */
		public static ObservableList<Material> createMaterialList() throws IOException {
			ObservableList<Material> materials = FXCollections.observableArrayList();
			String materialString = "";
			FileReader fr;
			BufferedReader br;
			
			File file = new File("material.txt");
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			//Attributes of materials.
			String materialName;
			double materialWidth;
			double materialPrice;
			double materialHeight;
			String[] tokens; //Array of material attributes.
			
			
			//Read file, create material accordingly and add to the materials list.
			while((materialString = br.readLine()) != null) {
				tokens = materialString.split(",");
				materialName = tokens[0];
				materialHeight = Double.parseDouble(tokens[1]);
				materialWidth = Double.parseDouble(tokens[2]);
				materialPrice = Double.parseDouble(tokens[3]);
				Material material = new Material(materialName, materialHeight, materialWidth, materialPrice);
				materials.add(material);
			}
			
			
			fr.close();
			br.close();
			return materials;
		}

		public int getMeterNumber() {
			return meterNumber;
		}
		public ArrayList<Integer> getMeterNumbers() {
			return meterNumbers;
		}
		
		public void setMeterNumber(int theMeterNumber) {
			meterNumber = theMeterNumber;
		}
		
		public void setMeterNumbers(ArrayList<Integer> theList) {
			meterNumbers = theList;
		}


}
