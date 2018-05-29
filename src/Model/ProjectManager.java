package Model;

import java.util.ArrayList;

//TODO: add support to read in existing projects from file system
//TODO: javadoc and commenting
public class ProjectManager {
		private ArrayList<Project> myProjects;
		private String myName;
		private String myEmail;
		
		
		public ProjectManager() {
			myProjects = new ArrayList<>();
			myName = "";
			myEmail = "";
			
		}
		
		public ArrayList<Project> getmyProjects() {
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
		public void addUser(String user) {
			
			this.myName = user.substring(0, user.indexOf("\n")+1);
			this.myEmail = user.substring(user.indexOf("\n")+1, user.length());
			
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

}
