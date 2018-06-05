package Model;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;

public class Project {
	private String myType;
	private String myName;
	private int myCost;
	private ArrayList<String> myMaterials;
	private int myHours;
	private Button myButton;
	
	/*
	 * @author Kyle Beveridge
	 * */
	public Project(String theType, String theName, int theCost, int theHours) {
		myType = theType;
		myName = theName;
		myCost = theCost;
		myHours = theHours;
		myMaterials = new ArrayList<String>();

	}
	
	/*
	 * @author Kyle Beveridge
	 * edited by Reza
	 * */
	public Project(String theName) {
		this("None", theName, 0, 0);
		
	}
	
	/*
	 * @author Kyle Beveridge
	 * */
	public void addMaterial(String theMat) {
		myMaterials.add(theMat);
	}
	
	/*
	 * @author Kyle Beveridge
	 * */
	public void removeMaterial(String theMat) {
		int idx = myMaterials.indexOf(theMat);
		if(idx != -1) {
			myMaterials.remove(idx);
		}
	}
	
	public ArrayList<String> getMaterials() {
		ArrayList<String> copyList = new ArrayList<String>();
		copyList.addAll(myMaterials);
		//this will likely need to be a deep copy once
		//the material class is made
		return myMaterials;
	}
	

	public void setMaterials(ObservableList<Material> theMaterials) {
		for (int i = 0; i < theMaterials.size(); i++) {
			myMaterials.add(theMaterials.get(i).getName());
		}
	} 
	
	/*
	 * @author Kyle Beveridge
	 * */
	public String getType() {
		return myType;
	}
	
	/*
	 * @author Kyle Beveridge
	 * */
	public void setType(String myType) {
		this.myType = myType;
	}
	
	/*
	 * @author Kyle Beveridge
	 * */
	public String getName() {
		return myName;
	}
	
	/*
	 * @author Kyle Beveridge
	 * */
	public void setName(String myName) {
		this.myName = myName;
	}

	/*
	 * @author Kyle Beveridge
	 * */
	public int getCost() {
		return myCost;
	}

	/*
	 * @author Kyle Beveridge
	 * */
	public void setCost(int myCost) {
		this.myCost = myCost;
	}

	/*
	 * @author Kyle Beveridge
	 * */
	public int getHours() {
		return myHours;
	}

	/*
	 * @author Kyle Beveridge
	 * */
	public void setHours(int myHours) {
		this.myHours = myHours;
	}
	
	public Button getButton() {
        return myButton;
    }
 
    public void setRemark(Button theButton) {
        this.myButton = theButton;
    }
}
