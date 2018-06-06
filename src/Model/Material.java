package Model;
/**
 * This class creates a material with the according information. 
 * The class contains height, width, price and name. 
 * @author Emmett Kang
 *
 */
public class Material {
	private double height;
	private double width;
	private double price;
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
	
	
}
