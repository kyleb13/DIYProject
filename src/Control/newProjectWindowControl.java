
package Control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Model.Material;
import Model.Project;
import Model.ProjectManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class newProjectWindowControl {
	
	@FXML  private TextField price; 
	@FXML  private Button cancel;
	@FXML  private Button save;
	@FXML  private Button calculate;
	
	@FXML  private Label error;
	
	@FXML private ListView<HBox> addedList;
	@FXML private ListView<HBox> availibleList;
	
	@FXML private TextField totalQuantity;
	@FXML private TextField totalArea;
	
	@FXML public Button construction;
	@FXML public Button floors;
	@FXML public Button bathroom;
	@FXML public Button kitchen;
	@FXML public Button garden;

	private List<Material> allMaterials;
	
	private Project project;
	
	private ImageView pjImage;
	
	/*
	 * @author Kyle Beveridge
	 * Constructor for controller class. Gets the master list of materials
	 * */
	public newProjectWindowControl() {
		try {
			allMaterials = ProjectManager.createMaterialList();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * @author Kyle Beveridge
	 * Set up the buttons for changing project type
	 * */
	public void setupTypeButtons() {
		construction.setOnAction(e -> handleTypeChange(construction.getText()));
		floors.setOnAction(e -> handleTypeChange(floors.getText()));
		bathroom.setOnAction(e -> handleTypeChange(bathroom.getText()));
		kitchen.setOnAction(e -> handleTypeChange(kitchen.getText()));
		garden.setOnAction(e -> handleTypeChange(garden.getText()));
	}
	
	/*
	 * @author Kyle Beveridge
	 * Add the materials availible to projects to a list view
	 * */
	public void setupAvailibleMaterials() {
		for(Material m: allMaterials) {
			HBox box = new HBox();//hbox for storing material info in a row
			box.setSpacing(15);
			Label l1 = new Label(m.getName());
			Label l2 = new Label(m.getWidth() + "'");
			Label l3 = new Label(m.getHeight()+ "'");
			Label l4 = new Label("$" + m.getPrice());
			Button add = new Button("+");
			add.setOnAction(e -> {//lambda for adding material to the project
				HBox parent = ((HBox) add.getParent());
				Label n = (Label) parent.getChildren().get(0);
				int i = project.findMaterial(n.getText());
				if(i>=0) {
					//if material exists in project, increment its quantity
					//a listener attached to the quantity's value will change
					//the gui value
					project.getMaterials().get(i).incrementQuantity();
				} else {
					//find and add a new material to project
					for(Material w:allMaterials) {
						if(w.getName() == n.getText()) {
							Material addin = new Material(w);
							addin.incrementQuantity();
							project.addMaterial(addin);
						}
					}
				}
			});
			box.getChildren().addAll(l1, l2, l3,l4,add);
			availibleList.getItems().add(box);
		}
	}
	
	/*
	 * @author Kyle Beveridge
	 * If the project already has added materials, add those to the gui
	 * */
	public void setUpAddedMaterials() {
		for(Material m: project.getMaterials()) {
			HBox box = new HBox();
			box.setSpacing(15);
			Label l1 = new Label(m.getName());
			Label l2 = new Label(m.getWidth() + "'");
			Label l3 = new Label(m.getHeight()+ "'");
			Label l4 = new Label("$" + m.getPrice());
			TextField q = new TextField(m.getQuantity() + "");
			q.setMaxWidth(20);
			//add a listener to the quantity variable, so the textfield
			//is updated when it changes
			m.quantityProperty().addListener(new ChangeListener<Number>() {

				@Override
				public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
					q.setText(arg2.toString());
				}
			});
			q.setMaxWidth(50);
			Button delete = new Button("x");
			delete.setOnAction(e-> handleDeleteMaterial(m));
			box.getChildren().addAll(l1, l2, l3,l4, q, delete);
			addedList.getItems().add(box);
		
		}
	}
	
	/*
	 * @author Kyle Beveridge
	 * finds the next empty empty entry in the gui to add a material
	 * */
	private int getEmptyRow() {
		int i = -1;
		int cnt = 0;
		for(HBox box:addedList.getItems()) {
			if(box.getChildren().size() == 0) {
				i = cnt;
				break;
			}
			cnt++;
		}
		return i;
	}
	
	/*
	 * @author Kyle Beveridge
	 * */
	@FXML
	private void saveClicked() {
		calculateClicked();
		cancelClicked();
	}
	
	/*
	 * @author Kyle Beveridge
	 * make project calculations
	 * */
	@FXML
	private void calculateClicked() {
		int totalQ= 0;
		double totalA= 0;
		double totalP = 0;
		for(Material m:project.getMaterials()) {
			totalQ+=m.getQuantity();
			totalA+=m.getHeight() * m.getWidth();
			totalP+=m.getPrice() * m.getQuantity();
		}
		if(totalP != 0) {
			totalQuantity.setText(totalQ + "");
			totalArea.setText(totalA + " ft^2");
			String pstring = "$" + totalP;
			pstring = pstring.substring(0, pstring.indexOf(".") + 3);
			price.setText(pstring);
			project.setCost(totalP);
		}
	}
	
	/*
	 * @author Kyle Beveridge
	 * */
	public void addImage(ImageView img) {
		pjImage = img;
	}
	
	/*
	 * @author Kyle Beveridge
	 * */
	private void handleDeleteMaterial(Material m) {
		project.removeMaterial(m);
	}
	
	@FXML
	private void cancelClicked() {
		((Stage)(cancel.getScene().getWindow())).close();
	}
	
	/*
	 * @author Kyle Beveridge
	 * */
	private void handleTypeChange(String type) {
		pjImage.setImage(new Image("/icons/" + type + ".png"));
		project.setType(type);
	}
	
	/*
	 * @author Kyle Beveridge
	 * */
	public void addProject(Project theProject) {
		project = theProject;
		theProject.getMaterials().addListener(new MaterialListListener());
	}
	
	/*
	 * @author Kyle Beveridge
	 * Update the gui when a material is added to or removed from project's material list
	 * */
	private class MaterialListListener implements ListChangeListener<Material>{
		@Override
		public void onChanged(Change<? extends Material> c) {
			while(c.next()) {
				//handle an added material
				if(c.wasAdded()) {
					for(Material m:c.getAddedSubList()) {
						int idx = getEmptyRow();
						HBox box;
						if(idx>=0) {
							//get box to place new gui elements in
							box = addedList.getItems().get(idx);
						} else{
							//make new box if there are no empty rows
							box = new HBox();
						}
						box.setSpacing(15);
						Label l1 = new Label(m.getName());
						Label l2 = new Label(m.getWidth() + "'");
						Label l3 = new Label(m.getHeight()+ "'");
						Label l4 = new Label("$" + m.getPrice());
						TextField q = new TextField(m.getQuantity() + "");
						m.quantityProperty().addListener(new ChangeListener<Number>() {

							@Override
							public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
								q.setText(arg2.toString());
							}
						});
						q.setMaxWidth(30);
						Button delete = new Button("x");
						delete.setOnAction(e-> handleDeleteMaterial(m));
						box.getChildren().addAll(l1, l2, l3,l4, q, delete);
						if(idx<0) {
							//add box if new one was made
							addedList.getItems().add(box);
						}
					}
				} else if(c.wasRemoved()) {
					for(Material m:c.getRemoved()) {
						for(HBox h:addedList.getItems()) {
							//The second part of this if statement might be confusing, so here is a quick explanation:
							//This statement checks if the row we are looking at corresponds to the material that was
							//deleted, so we get the first element of the children of the current hbox, which should
							//be a label with the material name, and checks that it is the same as the deleted
							//material's name
							if(h.getChildren().size() !=0 && ((Label) h.getChildren().get(0)).getText() == m.getName()) {
								h.getChildren().clear();
								break;
							}
						}
					}
				}
			} 
		}
	}
}
