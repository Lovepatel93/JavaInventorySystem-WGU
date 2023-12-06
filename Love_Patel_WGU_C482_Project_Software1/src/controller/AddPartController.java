/**
 *
 * @author Love Patel
 */
package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHousePart;
import model.Inventory;
import model.OutsourcedPart;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This is a Controller class for handling the Add Part Screen.
 */
public class AddPartController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private RadioButton outsourcedRBtn;

    @FXML
    private ToggleGroup inHouseOutsourcedTG;

    @FXML
    private RadioButton inHouseRBtn;

    @FXML
    private Label companyLbl;

    @FXML
    private TextField partIdTxt;

    @FXML
    private TextField partNameTxt;

    @FXML
    private TextField partInvTxt;

    @FXML
    private TextField partPriceTxt;

    @FXML
    private TextField partMaxTxt;

    @FXML
    private TextField addVariableTxt;

    @FXML
    private TextField partMinTxt;

    @FXML
    private Button addPartSaveBtn;

    @FXML
    private Button addPartCancelBtn;

    /**
     * This method handles the cancel button and takes you back to the main screen. It also confirms your action.
     * @param event event handling the cancel button.
     * @throws IOException for the load method.
     */
    @FXML
    public void onActionDisplayMainScreen(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Changes are not saved!");
        alert.setContentText("Would you like to continue without saving?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/main_screen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**
     * This method sets the Label based on Radio Button selected
     * @param event The event of choosing one of the radio buttons.
     */
    @FXML
    public void onActionRadioBtn(ActionEvent event) {
        if (inHouseRBtn.isSelected()) {
            companyLbl.setText("Machine ID");
        }
        if (outsourcedRBtn.isSelected()) {
            companyLbl.setText("Company Name");
        }

    }

    /**
     * This method handles the saving of data after adding the part. This method also handles data type exception when
     * user enters a wrong data type in the fields. This method also handles the logical errors that may occur when
     * adding a part. It shows the errors necessary to not enter incorrect data and to not miss entering or selecting
     * important data.
     * @param event Event activates when user clicks the Save button
     * @throws IOException Exception used for load method. It also handles the IO exception for when user enters
     * wrong data format.
     */
    @FXML
    public void onActionAddPartSaveBtn(ActionEvent event) throws IOException{
        if (inHouseRBtn.isSelected()) {
            try {
                int id = main.Main.getAndIncrementNextID();
                String name = partNameTxt.getText();
                int inv = Integer.parseInt(partInvTxt.getText());
                Double price = Double.parseDouble(partPriceTxt.getText());
                int max = Integer.parseInt(partMaxTxt.getText());
                int min = Integer.parseInt(partMinTxt.getText());
                int machineId = Integer.parseInt(addVariableTxt.getText());

                if (inv >= min && inv <= max && max > min) {
                    Inventory.addPart(new InHousePart(id, name, price, inv, min, max, machineId));
                    stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/view/main_screen.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Wrong Data ERROR!");
                    alert.setContentText("Inventory must be between min and max.\nMin must be less than Max.\n");
                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Wrong/Missing Data ERROR!");
                alert.setContentText("Please ensure that all fields have accurate data type. And that they are all filled");
                alert.showAndWait();
            }

        } else if (outsourcedRBtn.isSelected()) {
            try {
                int id = main.Main.getAndIncrementNextID();
                String name = partNameTxt.getText();
                int inv = Integer.parseInt(partInvTxt.getText());
                Double price = Double.parseDouble(partPriceTxt.getText());
                int max = Integer.parseInt(partMaxTxt.getText());
                int min = Integer.parseInt(partMinTxt.getText());
                String companyName = addVariableTxt.getText();

                if (inv >= min && inv <= max && max > min) {
                    Inventory.addPart(new OutsourcedPart(id, name, price, inv, min, max, companyName));
                    stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/view/main_screen.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Wrong Data ERROR!");
                    alert.setContentText("Inventory must be between min and max.\nMin must be less than Max.\n");
                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Wrong Data ERROR!");
                alert.setContentText("Please ensure that all fields have accurate data type.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Part Type not selected!");
            alert.setContentText("Please select whether the Part you are adding is InHouse or OutSourced.");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
