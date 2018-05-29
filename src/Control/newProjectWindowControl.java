package Control;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class newProjectWindowControl {
	
	@FXML  private TabPane tab;
	@FXML  private Tab noneElectericTab;
	@FXML  private Tab ElectericTab;
	@FXML  private GridPane leftGrid;
	@FXML  private GridPane righttGrid;
	@FXML  private TextField height;
	@FXML  private TextField width;
	@FXML  private TextField depth;
	@FXML  private MenuButton material;
	@FXML  private MenuButton product;
	@FXML  private MenuButton electericProduct;
	@FXML  private MenuButton Brand;
	@FXML  private MenuButton type;
	@FXML  private TextField noneElectericPrice;
	@FXML  private TextField electericPrice;
	@FXML  private Button cancel;
	@FXML  private Button save;
	@FXML  private Button calculate;
	
	@FXML  private Label error;

	
	private double projectHeight;
	private double projectWidth;
	private double projectDepth;
	
	 
	 @FXML
	 private void checkHeight() {
	       try {
	    	   setProjectWidth(Double.parseDouble(height.getText()));
			}catch (NumberFormatException e) {
				error.setVisible(true);
				height.clear();
		       }					
	 }
	 
	 @FXML
	 private void checkWidth() {
		 try {
	    	   setProjectWidth(Double.parseDouble(width.getText()));
			}catch (NumberFormatException e) {
				error.setVisible(true);
				width.clear();
		       }
	 }
	 
	 @FXML
	 private void checkDepth() {
		 try {
	    	   setProjectWidth(Double.parseDouble(depth.getText()));
			}catch (NumberFormatException e) {
				error.setVisible(true);
				depth.clear();
		       }
	 }

	public double getProjectHeight() {
		return projectHeight;
	}

	public void setProjectHeight(double projectHeight) {
		this.projectHeight = projectHeight;
	}

	public double getProjectWidth() {
		return projectWidth;
	}

	public void setProjectWidth(double projectWidth) {
		this.projectWidth = projectWidth;
	}

	public double getProjectDepth() {
		return projectDepth;
	}

	public void setProjectDepth(double projectDepth) {
		this.projectDepth = projectDepth;
	}
	
	@FXML
	private void saveClicked() {
		checkHeight();
		checkWidth();
		checkDepth();
	}
	
	@FXML
	private void calculateClicked() {
		checkHeight();
		checkWidth();
		checkDepth();
		
	}
	
	@FXML
	private void cancelClicked() {
		((Stage)(cancel.getScene().getWindow())).close();
	}
}
