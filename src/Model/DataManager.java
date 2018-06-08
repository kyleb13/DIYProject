
package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;

public class DataManager {
	
	/**
	 * This method intakes the entire project manager and stores it in text file.
	 * Each project's attributes are comma separated and at the end, new line is added.
	 * @author Emmett Kang
	 * @param PM project manager that contains the projects.
	 * @throws IOException If it fails to open, well, it will throw stuff at you.
	 */
	public static void storeProjects(ProjectManager PM) throws IOException {
		String name = PM.getUserEmail()+".txt";
		File file = new File(name);
		FileWriter filewriter = new FileWriter(file);

		ObservableList<Project> ProjectList = PM.getmyProjects();
		filewriter.write(PM.getMeterNumber()+ "_");
		
		if (PM.getMeterNumbers().size() != 0) {;
			for (int i = 0; i < PM.getMeterNumbers().size(); i++) {
				filewriter.write(String.valueOf(PM.getMeterNumbers().get(i)));
				
				if (i != PM.getMeterNumbers().size()-1) {
					filewriter.write(",");
				}
			}
		}
		filewriter.write(System.getProperty( "line.separator" ));

		
		//Get the projects from the list, store them by attributes into text file.
		for (int i = 0; i < ProjectList.size(); i++) {
			Project project = ProjectList.get(i);
			filewriter.write(project.getName() + "," + project.getCost() + "," 
							+ project.getHours() + "," + project.getType());
			filewriter.write("_");
			for (int j = 0; j < project.getMaterials().size(); j++) {				
				filewriter.write(project.getMaterials().get(j).getName()+ "-" + project.getMaterials().get(j).getQuantity());
				if (j != project.getMaterials().size()-1) {
					filewriter.write(","); 
				} else {
					filewriter.write(System.getProperty( "line.separator" ));   
				}
			}
		}
		filewriter.flush();
		filewriter.close();
	}
	
	
	/**
	 * This method intakes the user info to find the file name and reads through the
	 * file content and generate the projects with that information, and add to project manager
	 * and return the entire project manager.  
	 * @param userinfo User + email via getUser() from project manager class
	 * @return ProjectManager object that contains retrieved projects.
	 * @throws IOException If the file cannot be found(I.E there's no project manager for the user), throw stuff. 
	 */
	public static void retrieveProjects(File file, ProjectManager retrieved) throws IOException {
		retrieved.getmyProjects().clear();
		String projectString = "";
		FileReader fr;
		BufferedReader br;
		
		ObservableList<Material>materialList = ProjectManager.createMaterialList();
		
		fr = new FileReader(file);
		br = new BufferedReader(fr);
		
		//Attributes of projects.
		String projectname;
		String projecttype;
		int projecthour;
		double projectcost;
		String[] tokens; //Array of project attributes.
		String[] info;
		String[] mats;
		
		String tmp = br.readLine();
		String[] meterCollection = tmp.split("_");
		if (meterCollection.length == 2) {
			String[] meterNumArr = meterCollection[1].split(",");
			ArrayList<Integer> meterNumbers = new ArrayList<Integer>();
			for (int i = 0; i < meterNumArr.length; i++) {
				meterNumbers.add(Integer.parseInt(meterNumArr[i]));
			}
		}
		retrieved.setMeterNumber(Integer.parseInt(meterCollection[0]));
		
		
		//Read file, create project accordingly and add to the project manager.
		while((projectString = br.readLine()) != null) {
			info = projectString.split("_");
			tokens = info[0].split(",");
			projectname = tokens[0];
			projectcost = Double.parseDouble(tokens[1]);
			projecthour = Integer.parseInt(tokens[2]);
			projecttype = tokens[3];
			
			Project project = new Project(projecttype, projectname, projectcost, projecthour);
			if(info.length >1) {
				mats = info[1].split(",");
				
				//Create the project with found attribute, add.
				for (int i = 0; i<mats.length; i++) {
					String[] matInfo = mats[i].split("-");
					for (Material m : materialList) {
						if (m.getName().equals(matInfo[0])) {
							Material temp = m;
							temp.setQuantity(Integer.parseInt(matInfo[1]));
							project.addMaterial(temp);
						}
					}
				}
			}
			retrieved.addProject(project);
		} 
		
		fr.close();
		br.close();
	}
	
	/*
	
	public static void main(String[] args) throws IOException {
		ProjectManager pm = new ProjectManager();
		pm.addUser("emmettkang\nelk9516", 100);
		Project p1 = new Project("Mercury", "Emmett1", 36000, 12);
		Project p2 = new Project("Boundless", "Emmett2", 36000, 12);
		ObservableList<Material> mat = ProjectManager.createMaterialList();
		pm.addMeterMeasure(47);
		p1.setMaterials(mat);
		p2.setMaterials(mat);
		pm.addProject(p2);
		pm.addProject(p1);
		pm.setMeterNumber(15);
		storeProjects(pm);
		ProjectManager manager = retrieveProjects("elk9516");
		for (Integer mn : pm.getMeterNumbers()) {
			System.out.println(mn);
		}
		for (Project p : manager.getmyProjects()) {
			System.out.println("new Project");
			System.out.println(p.getName());
			for (Material m : p.getMaterials()) {
				System.out.println(m.getName() + m.getQuantity());
			}
	
		}
		
	}
	*/
		

}
