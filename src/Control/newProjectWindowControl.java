
package Control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Model.Material;
import Model.Project;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WritableDoubleValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener.Change;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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

	private ArrayList<Material> allMaterials;
	
	private Project project;
	
	public newProjectWindowControl() {
		allMaterials = new ArrayList<Material>();
	}
	
	public void setupAvailibleMaterials() {
		allMaterials.add(new Material("Wood", 3, 5, 5.99));
		allMaterials.add(new Material("Carpet", 6, 6, 12.99));
		allMaterials.add(new Material("Marble", 4, 8, 75.99));
		allMaterials.add(new Material("Metal", 4, 4, 20.99));
		allMaterials.add(new Material("Dirt", 10, 10, 1.99));
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

	/*public double getProjectHeight() {
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
	}*/
	
	@FXML
	private void saveClicked() {
		
		
	}
	
	@FXML
	private void calculateClicked() {
		
		
	}
	
	@FXML
	private void cancelClicked() {
		((Stage)(cancel.getScene().getWindow())).close();
	}
	
	public void addProject(Project theProject) {
		project = theProject;
		theProject.getMaterials().addListener(new MaterialListListener());
		project.addMaterial(new Material("TEST", 0, 0, 0));
	}
	
	private class MaterialListListener implements ListChangeListener<Material>{
		int prevQ = 0;
		@Override
		public void onChanged(Change<? extends Material> c) {
			while(c.next()) {
				if(c.wasAdded()) {
					for(Material m:c.getAddedSubList()) {
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
						CheckBox cb = new CheckBox();
						box.getChildren().addAll(l1, l2, l3,l4, q, cb);
						addedList.getItems().add(box);
					}
				}
			} 
		}
	}
}
