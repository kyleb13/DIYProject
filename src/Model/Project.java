package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

public class Project {
	private String myType;
	private String myName;
	private double myCost;
	private ObservableList<Material> myMaterials;
	private int myHours;
	private Button myButton;
	
	/*
	 * @author Kyle Beveridge
	 * */
	public Project(String theType, String theName, double theCost, int theHours) {
		myType = theType;
		myName = theName;
		myCost = theCost;
		myHours = theHours;
		myMaterials = FXCollections.observableArrayList();

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
	public void addMaterial(Material theMat) {
		myMaterials.add(theMat);
	}
	
	/*
	 * @author Kyle Beveridge
	 * */
	public void removeMaterial(Material theMat) {
		int idx = myMaterials.indexOf(theMat);
		if(idx != -1) {
			myMaterials.remove(idx);
		}
	}
	
	public ObservableList<Material> getMaterials() {
		return myMaterials;
	}
	

	public void setMaterials(ObservableList<Material> theMaterials) {
		myMaterials = theMaterials;
	}
	
	
	public int findMaterial(String name) {
		int i = -1;
		for(Material m:myMaterials) {
			if(m.getName() == name) {
				i = myMaterials.indexOf(m);
				break;
			}
		}
		return i;
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
	public double getCost() {
		return myCost;
	}

	/*
	 * @author Kyle Beveridge
	 * */
	public void setCost(double myCost) {
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
