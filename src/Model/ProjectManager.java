package Model;

import java.util.ArrayList;

public class ProjectManager {
		private ArrayList<Project> projects;
		private String name;
		private String email;
		
		
		public ProjectManager() {
			projects = new ArrayList<>();
			name = "";
			email = "";
			
		}
		
		public ArrayList<Project> getProjects() {
			return projects;
		} 
		
		public void addUser(String user) {
			
			this.name = user.substring(0, user.indexOf("\n")+1);
			this.email = user.substring(user.indexOf("\n")+1, user.length());
			
		}
		public String getUser() {
			return name + email;
		}
		
}
