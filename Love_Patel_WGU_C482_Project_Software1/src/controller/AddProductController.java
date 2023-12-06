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
import model.InHousePart;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This is a controller class for handling the Add Product screen.
 */
public class AddProductController implements Initializable {

    Stage stage;
    Parent scene;

    Product product;

    @FXML
    private TextField productIdTxt;

    @FXML
    private TextField productNameTxt;

    @FXML
    private TextField productInvTxt;

    @FXML
    private TextField productPriceTxt;

    @FXML
    private TextField productMaxTxt;

    @FXML
    private TextField productMinTxt;

    @FXML
    private Button addProductSaveBtn;

    @FXML
    private Button addProductCancelBtn;

    @FXML
    private TextField searchByPartTxt;

    @FXML
    private TableView<Part> addProductTableView1;

    @FXML
    private TableColumn<Part, Integer> addProductId1Col;

    @FXML
    private TableColumn<Part, String> addProductName1Col;

    @FXML
    private TableColumn<Part, Integer> addProductInv1Col;

    @FXML
    private TableColumn<Part, Double> addProductPrice1Col;

    @FXML
    private Button addProductAddBtn;

    @FXML
    private TableView<Part> addProductTableView2;

    @FXML
    private TableColumn<Part, Integer> addProductId2Col;

    @FXML
    private TableColumn<Part, String> addProductName2Col;

    @FXML
    private TableColumn<Part, Integer> addProductInv2Col;

    @FXML
    private TableColumn<Part, Double> addProductPrice2Col;

    @FXML
    private Button addProductRemovePartBtn;

    /**
     * This method handles the Add button on the Add product screen. It associates the part selected to the product that
     * user is adding.
     * @param event Event activates when user selects a part and clicks the add button.
     */
    @FXML
    public void onActionAddProduct(ActionEvent event) {
        Part part = addProductTableView1.getSelectionModel().getSelectedItem();
        if (part == null) {
            return;
        } else {
            product.getAllAssociatedParts().add(part);
        }
    }

    /**
     * This method dissociates the selected part from the product. It also confirms your action.
     * @param event The event activates when user clicks the remove part button.
     */
    @FXML
    public void onActionDeleteProduct(ActionEvent event) {
        Part part = addProductTableView2.getSelectionModel().getSelectedItem();
        if (part==null) {
            return;
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("This will Dissociate this part from the Product.");
            alert.setContentText("Would you like to continue?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                product.deleteAssociatedPart(part);
            }

        }
    }

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
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/main_screen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }

    }

    /**
     * This method handles the saving of data after adding the product. This method also handles data type exception when
     * user enters a wrong data type in the fields. This method also handles the logical errors that may occur when
     * adding a product. It shows the errors necessary to not enter incorrect data and to not miss entering
     * important data.
     * @param event Event activates when user clicks the Save button
     * @throws IOException Exception used for load method. It also handles the IO exception for when user enters
     * wrong data format.
     */
    @FXML
    public void onActionSaveProduct(ActionEvent event) throws IOException {
        try {
            int id = Product.getAndIncrementNextID();
            String name = productNameTxt.getText();
            int inv = Integer.parseInt(productInvTxt.getText());
            Double price = Double.parseDouble(productPriceTxt.getText());
            int max = Integer.parseInt(productMaxTxt.getText());
            int min = Integer.parseInt(productMinTxt.getText());

            if (inv >= min && inv <= max && max > min) {
                Inventory.addProduct(new Product(id, name, price, inv, min, max, product.getAllAssociatedParts()));
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
     * This method handles the searching of the part that needs to be added, using the search bar, on the Add product
     * screen. It selects the part when user searches for it using the part id. It filters the parts when searching for
     * it using the part name.
     * It shows error when the part does not exist
     * @param event The event activates when user searches for a part using the search bar.
     */
    @FXML
    public void onActionSearchProduct(ActionEvent event) {
        if (searchByPartTxt.getText().isEmpty()) {
            addProductTableView1.setItems(Inventory.getAllParts());
        } else if (!searchByPartTxt.getText().trim().isEmpty()) {
            try {
                addProductTableView1.getSelectionModel().select(Inventory.lookupPart(Integer.parseInt(searchByPartTxt.getText())));
            } catch (NumberFormatException e) {
                if (Inventory.lookupPart(searchByPartTxt.getText().trim())==null) {
                    addProductTableView1.setItems(Inventory.getAllParts());
                } else {
                    addProductTableView1.setItems(Inventory.lookupPart(searchByPartTxt.getText().trim()));
                }
            }
        }
    }

    /**
     * This method initializes the two tables with dummy data that was in the main screen.
     * @param url url
     * @param resourceBundle resourcebundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        product = new Product();
        addProductTableView1.setItems(Inventory.getAllParts());
        addProductTableView2.setItems(product.getAllAssociatedParts());

        addProductId1Col.setCellValueFactory(new PropertyValueFactory<>("partID"));
        addProductName1Col.setCellValueFactory(new PropertyValueFactory<>("partName"));
        addProductInv1Col.setCellValueFactory(new PropertyValueFactory<>("partInStock"));
        addProductPrice1Col.setCellValueFactory(new PropertyValueFactory<>("partPrice"));

        addProductId2Col.setCellValueFactory(new PropertyValueFactory<>("partID"));
        addProductName2Col.setCellValueFactory(new PropertyValueFactory<>("partName"));
        addProductInv2Col.setCellValueFactory(new PropertyValueFactory<>("partInStock"));
        addProductPrice2Col.setCellValueFactory(new PropertyValueFactory<>("partPrice"));

    }

}
