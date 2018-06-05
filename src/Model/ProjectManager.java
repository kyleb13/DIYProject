package Model;

import java.util.ArrayList;
import java.util.List;

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
		
		/*
		 * @author Kyle Beveridge
		 * */
		public void addProject(Project theProject) {
			myProjects.add(theProject);
		}
		
		/*
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
			newproj.setMaterials(theProject.getMaterials());
			myProjects.add(newproj);
			return newproj;
		}
		
		/*
		 * @author Kyle Beveridge
		 * */
		public void deleteProject(Project theProject) {
			myProjects.remove(theProject);
		}
		
		/*
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
		public int getMeterNumber() {
			return meterNumber;
		}
		public ArrayList<Integer> getMeterNumbers() {
			return meterNumbers;
		}

}
