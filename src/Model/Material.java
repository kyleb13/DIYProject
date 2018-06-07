package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * This class creates a material with the according information. 
 * The class contains height, width, price and name. 
 * @author Emmett Kang
 *
 */

//TODO: add tostring method
public class Material {
	private double height;
	private double width;
	private double price;
	private IntegerProperty quantity;
	private String name;
	
	/**
	 * Constructors are there for creating a materials.
	 * @param theName name of the material.
	 * @param theHeight height in inch.
	 * @param theWidth width in inch.
	 * @param thePrice price of the material. 
	 */
	public Material(String theName, double theHeight, double theWidth, double thePrice) {
		name = theName;
		height = theHeight;
		width = theWidth;
		price = thePrice;
		quantity = new SimpleIntegerProperty(0);
	}
	
	public Material(Material theMaterial) {
		this(theMaterial.getName(), theMaterial.getHeight(), theMaterial.getWidth(), theMaterial.getPrice());
		quantity.set(theMaterial.getQuantity());
	}
	
	/*
	 * Getters and setters.
	 */
	public double getHeight() {
		return height;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getWidth() {
		return width;
	}
	
	public void setWidth(double width) {
		this.width = width;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getQuantity() {
		return quantity.get();
	}


	public void setQuantity(int quant) {
		quantity.set(quant);
	}
	
	public void incrementQuantity() {
		quantity.set(quantity.get() + 1);
	}
	
	public IntegerProperty quantityProperty() {
		return quantity;
	}
	
}
