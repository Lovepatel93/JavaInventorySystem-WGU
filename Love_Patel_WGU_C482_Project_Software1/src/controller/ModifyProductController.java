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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This is a Controller class that handles the Modify Product screen.
 */
public class ModifyProductController implements Initializable {
    Stage stage;
    Parent scene;

    Product product;

    @FXML
    private TextField modifyProductIdTxt;

    @FXML
    private TextField modifyProductNameTxt;

    @FXML
    private TextField modifyProductInvTxt;

    @FXML
    private TextField modifyProductPriceTxt;

    @FXML
    private TextField modifyProductMaxTxt;

    @FXML
    private TextField modifyProductMinTxt;

    @FXML
    private Button modifyProductSaveBtn;

    @FXML
    private Button modifyProductCancelBtn;

    @FXML
    private TextField modifyProductSearchByPartTxt;

    @FXML
    private TableView<Part> modifyProductTableView1;

    @FXML
    private TableColumn<Part, Integer> modifyProductId1Col;

    @FXML
    private TableColumn<Part, String> modifyProductName1Col;

    @FXML
    private TableColumn<Part, Integer> modifyProductInv1Col;

    @FXML
    private TableColumn<Part, Double> modifyProductPrice1Col;

    @FXML
    private Button modifyProductAddBtn;

    @FXML
    private TableView<Part> modifyProductTableView2;

    @FXML
    private TableColumn<Part, Integer> modifyProductId2Col;

    @FXML
    private TableColumn<Part, String> modifyProductName2Col;

    @FXML
    private TableColumn<Part, Integer> modifyProductInv2Col;

    @FXML
    private TableColumn<Part, Double> modifyProductPrice2Col;

    @FXML
    private Button modifyProductRemovePartBtn;

    /**
     * This method handles the cancel button and takes you back to the main screen. It also confirms your action.
     * @param event Event handling the cancel button.
     * @throws IOException Exception required for the load method.
     */
    @FXML
    public void onActionDisplayMainScreen(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Changes are not saved!");
        alert.setContentText("Would you like to continue without saving?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/main_screen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**
     * This method handles the Add button on the Modify product screen. It associates the part selected to the
     * product that user is modifying.
     * @param event Event activates when user selects a part and clicks the add button.
     */
    @FXML
    public void onActionModifyProductAdd(ActionEvent event) {
        Part part = modifyProductTableView1.getSelectionModel().getSelectedItem();
        if (part == null) {
            return;
        } else {
            product.getAllAssociatedParts().add(part);
        }
    }

    /**
     * This method handles the saving of data after modifying the product. This method also handles data type exception when
     * user enters a wrong data type in the fields. This method also handles the logical errors that may occur when
     * modifying a product. It shows the errors necessary to not enter incorrect data and to not miss entering
     * important data.
     * @param event Event activates when user clicks the Save button
     * @throws IOException Exception used for load method. It also handles the IO exception for when user enters
     * wrong data format.
     */
    @FXML
    public void onActionModifyProductSave(ActionEvent event) throws IOException {
        try {
            int id = Integer.parseInt(modifyProductIdTxt.getText());
            String name = modifyProductNameTxt.getText();
            int inv = Integer.parseInt(modifyProductInvTxt.getText());
            Double price = Double.parseDouble(modifyProductPriceTxt.getText());
            int max = Integer.parseInt(modifyProductMaxTxt.getText());
            int min = Integer.parseInt(modifyProductMinTxt.getText());

            if (inv >= min && inv <= max && max > min) {
                Inventory.updateProduct(getIndex(id), (new Product(id, name, price, inv, min, max, product.getAllAssociatedParts())));
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
    }

    /**
     * This method handles the searching of the part that needs to be associated, using the search bar, on the
     * Modify product screen. It selects the part when user searches for it using the part id.
     * It filters the parts when searching for it using the part name.
     * It shows error when the part does not exist
     * @param event The event activates when user searches for a part using the search bar.
     */
    @FXML
    public void onActionModifyProductSearch(ActionEvent event) {
        if (modifyProductSearchByPartTxt.getText().isEmpty()) {
            modifyProductTableView1.setItems(Inventory.getAllParts());
        } else if (!modifyProductSearchByPartTxt.getText().trim().isEmpty()) {
            try {
                modifyProductTableView1.getSelectionModel().select(Inventory.lookupPart(Integer.parseInt(modifyProductSearchByPartTxt.getText())));
            } catch (NumberFormatException e) {
                if (Inventory.lookupPart(modifyProductSearchByPartTxt.getText().trim())==null) {
                    modifyProductTableView1.setItems(Inventory.getAllParts());
                } else {
                    modifyProductTableView1.setItems(Inventory.lookupPart(modifyProductSearchByPartTxt.getText().trim()));
                }
            }
        }
    }
    /**
     * This method dissociates the selected part from the product. It also confirms your action.
     * @param event The event activates when user clicks the remove associated part button.
     */
    @FXML
    public void onActionRemoveAssociatedPart(ActionEvent event) {
        Part selectedPart = modifyProductTableView2.getSelectionModel().getSelectedItem();
        if (selectedPart==null) {
            return;
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("This will Dissociate this part from the Product.");
            alert.setContentText("Would you like to continue?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                product.deleteAssociatedPart(selectedPart);
            }

        }
    }
    /**
     * This method sends old data of the product to the modify product screen.
     * @param product1 Selected product that needs to be modified.
     */
    public void sendProduct(Product product1) {
        modifyProductIdTxt.setText(String.valueOf(product1.getProductID()));
        modifyProductNameTxt.setText(product1.getProductName());
        modifyProductInvTxt.setText(String.valueOf(product1.getProductInStock()));
        modifyProductPriceTxt.setText(String.valueOf(product1.getProductPrice()));
        modifyProductMaxTxt.setText(String.valueOf(product1.getProductMax()));
        modifyProductMinTxt.setText(String.valueOf(product1.getProductMin()));

        product.getAllAssociatedParts().addAll(product1.getAllAssociatedParts());

    }
    /**
     * This method gets the Index of the product that user is trying to modify from the Observable list of products.
     * @param ID Id of the selected product that needs to be modified.
     * @return Returns the Index of the selected product that is sent to the modify product screen.
     */
    public int getIndex (int ID) {
        for (Product product : Inventory.getAllProducts()) {
            if (product.getProductID() == ID) {
                return Inventory.getAllProducts().indexOf(product);
            }
        }
        return -1;
    }


    /**
     * This method initializes the two tables with dummy data that was in the main screen.
     * @param url url
     * @param resourceBundle resourcebundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        product = new Product();
        modifyProductTableView1.setItems(Inventory.getAllParts());
        modifyProductTableView2.setItems(product.getAllAssociatedParts());

        modifyProductId1Col.setCellValueFactory(new PropertyValueFactory<>("partID"));
        modifyProductName1Col.setCellValueFactory(new PropertyValueFactory<>("partName"));
        modifyProductInv1Col.setCellValueFactory(new PropertyValueFactory<>("partInStock"));
        modifyProductPrice1Col.setCellValueFactory(new PropertyValueFactory<>("partPrice"));

        modifyProductId2Col.setCellValueFactory(new PropertyValueFactory<>("partID"));
        modifyProductName2Col.setCellValueFactory(new PropertyValueFactory<>("partName"));
        modifyProductInv2Col.setCellValueFactory(new PropertyValueFactory<>("partInStock"));
        modifyProductPrice2Col.setCellValueFactory(new PropertyValueFactory<>("partPrice"));

    }

}
