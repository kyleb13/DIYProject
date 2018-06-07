
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
	
	public newProjectWindowControl() {
		try {
			allMaterials = ProjectManager.createMaterialList();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setupTypeButtons() {
		construction.setOnAction(e -> handleTypeChange(construction.getText()));
		floors.setOnAction(e -> handleTypeChange(floors.getText()));
		bathroom.setOnAction(e -> handleTypeChange(bathroom.getText()));
		kitchen.setOnAction(e -> handleTypeChange(kitchen.getText()));
		garden.setOnAction(e -> handleTypeChange(garden.getText()));
	}
	
	public void setupAvailibleMaterials() {
		for(Material m: allMaterials) {
			HBox box = new HBox();
			box.setSpacing(20);
			Label l1 = new Label(m.getName());
			Label l2 = new Label(m.getWidth() + "'");
			Label l3 = new Label(m.getHeight()+ "'");
			Label l4 = new Label("$" + m.getPrice());
			Button add = new Button("+");
			add.setOnAction(e -> {
				HBox parent = ((HBox) add.getParent());
				Label n = (Label) parent.getChildren().get(0);
				int i = project.findMaterial(n.getText());
				if(i>=0) {
					project.getMaterials().get(i).incrementQuantity();
				} else {
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
	
	public void setUpAddedMaterials() {
		for(Material m: project.getMaterials()) {
			HBox box = new HBox();
			box.setSpacing(20);
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
			q.setMaxWidth(50);
			Button delete = new Button("x");
			delete.setOnAction(e-> handleDeleteMaterial(m));
			box.getChildren().addAll(l1, l2, l3,l4, q, delete);
			addedList.getItems().add(box);
		
		}
	}
	
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
	
	@FXML
	private void saveClicked() {
		calculateClicked();
		cancelClicked();
	}
	
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
	
	public void addImage(ImageView img) {
		pjImage = img;
	}
	

	private void handleDeleteMaterial(Material m) {
		project.removeMaterial(m);
	}
	
	@FXML
	private void cancelClicked() {
		((Stage)(cancel.getScene().getWindow())).close();
	}
	

	private void handleTypeChange(String type) {
		pjImage.setImage(new Image("/icons/" + type + ".png"));
	}
	
	public void addProject(Project theProject) {
		project = theProject;
		theProject.getMaterials().addListener(new MaterialListListener());
	}
	
	private class MaterialListListener implements ListChangeListener<Material>{
		@Override
		public void onChanged(Change<? extends Material> c) {
			while(c.next()) {
				if(c.wasAdded()) {
					for(Material m:c.getAddedSubList()) {
						int idx = getEmptyRow();
						HBox box;
						if(idx>=0) {
							box = addedList.getItems().get(idx);
						} else{
							box = new HBox();
						}
						box.setSpacing(20);
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
						q.setMaxWidth(50);
						Button delete = new Button("x");
						delete.setOnAction(e-> handleDeleteMaterial(m));
						box.getChildren().addAll(l1, l2, l3,l4, q, delete);
						if(idx<0) {
							addedList.getItems().add(box);
						}
					}
				} else if(c.wasRemoved()) {
					for(Material m:c.getRemoved()) {
						for(HBox h:addedList.getItems()) {
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
