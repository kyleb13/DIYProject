package Model;

import java.util.ArrayList;

public class Project {
	private String myType;
	private String myName;
	private int myCost;
	private ArrayList<String> myMaterials;
	private int myHours;
	
	/*
	 * @author Kyle Beveridge
	 * */
	public Project(String theType, String theName, int theCost, int theHours) {
		myType = theType;
		myName = theName;
		myCost = theCost;
		myHours = theHours;
	}
	
	/*
	 * @author Kyle Beveridge
	 * */
	public Project() {
		this("Uninitialized", "None", 0, 0);
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
	

	public void setMaterials(ArrayList<String> theMaterials) {
		myMaterials.addAll(theMaterials);
		//this will likely need to be a deep copy once
		//the material class is made
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
}
