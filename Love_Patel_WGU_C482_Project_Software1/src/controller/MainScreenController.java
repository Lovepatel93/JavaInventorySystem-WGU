/**
 *
 * @author Love Patel
 */
package controller;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This is a Controller class that handles the Main menu screen when this app is opened.
 */
public class MainScreenController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TextField partSearchBox;

    @FXML
    private Button partAddBtn;

    @FXML
    private Button partModifyBtn;

    @FXML
    private Button partDeleteBtn;

    @FXML
    private TableView<Part> mainPartsTableView;

    @FXML
    private TableColumn<Part, Integer> partsIdCol;

    @FXML
    private TableColumn<Part, String> partsNameCol;

    @FXML
    private TableColumn<Part, Integer> partsInvCol;

    @FXML
    private TableColumn<Part, Double> partsPriceCol;

    @FXML
    private TextField productSearchBox;

    @FXML
    private Button productAddBtn;

    @FXML
    private Button productModifyBtn;

    @FXML
    private Button productDeleteBtn;

    @FXML
    private TableView<Product> mainProductsTableView;

    @FXML
    private TableColumn<Product, Integer> productsIdCol;

    @FXML
    private TableColumn<Product, String> productsNameCol;

    @FXML
    private TableColumn<Product, Integer> productsInvCol;

    @FXML
    private TableColumn<Product, Double> productsPriceCol;

    @FXML
    private Button exitBtn;

    /**
     * This method handles the Add Part button. It takes you to the Add Part Screen where you can enter details of the
     * part you are adding.
     * @param event The event that activates when user clicks the add Part button.
     * @throws IOException exception required for the load method.
     */
    @FXML
    public void onActionAddPart(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/add_part.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    /**
     * This method handles the Add Product button. It takes you to the Add Product Screen where you can enter details
     * of the product you are adding.
     * @param event The event that activates when user clicks the add Product button.
     * @throws IOException exception required for the load method.
     */
    @FXML
    public void onActionAddProduct(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/add_product.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This method handles the deleting of a part. When user selects a part and clicks on delete, it deletes the
     * selected part from the inventory.
     * @param event Event activates when user clicks on the delete Part button.
     */
    @FXML
    public void onActionDeletePart(ActionEvent event) {
        Part selectedPart = mainPartsTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            return;
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("This will delete the selected Part");
            alert.setContentText("Are you sure you want to delete: "+selectedPart.getPartName()+" from the Inventory?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get()==ButtonType.OK) {
                Inventory.deletePart(selectedPart);
            }
        }

    }

    /**
     * This method handles the deleting of a product. When user selects a product and clicks on delete, it deletes the
     * selected product from the inventory if it doesn't have associated parts. If the product has an associated part,
     * It creates and error message telling the user to dissociate the parts first before deleting the product.
     * @param event Event activates when user clicks on the delete Product button.
     */
    @FXML
    public void onActionDeleteProduct(ActionEvent event) {
        Product selectedProduct = mainProductsTableView.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            return;
        } else if (selectedProduct.getAllAssociatedParts().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("This will delete the selected Product");
            alert.setContentText("Are you sure you want to delete: "+selectedProduct.getProductName()+" from the Inventory?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get()==ButtonType.OK) {
                Inventory.deleteProduct(selectedProduct);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("This Product has Associated Parts");
            alert.setContentText("You must modify and remove associated parts to delete this product!");
            alert.showAndWait();
        }
    }

    /**
     * This method closes the application. It also confirms the user action before closing the app.
     * @param event Event activates when user clicks on the exit button.
     */
    @FXML
    public void onActionExitProgram(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("This will Exit the Inventory Management Program");
        alert.setContentText("Would you like to continue?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            System.exit(0);
        }
    }

    /**
     * This method helps with modifying a selected part. It takes you to the modify part screen.
     * @param event Event activates when user selects a part and clicks the modify button.
     * @throws IOException Exception for the load method.
     */
    @FXML
    public void onActionModifyPart(ActionEvent event) throws IOException {
        Part selectedPart = mainPartsTableView.getSelectionModel().getSelectedItem();
        if (selectedPart==null) {
            return;
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/modify_part.fxml"));
        loader.load();

        ModifyPartController PartController = loader.getController();
        PartController.sendPart(selectedPart);

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This method helps with modifying a selected product. It takes you to the modify product screen.
     * @param event Event activates when user selects a product and clicks the modify button.
     * @throws IOException Exception for the load method.
     */
    @FXML
    public void onActionModifyProduct(ActionEvent event) throws IOException {
        Product selectedProduct = mainProductsTableView.getSelectionModel().getSelectedItem();
        if (selectedProduct==null) {
            return;
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/modify_product.fxml"));
        loader.load();

        ModifyProductController ProductController = loader.getController();
        ProductController.sendProduct(selectedProduct);

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This method handles the searching of a part using the search bar, on the main screen. It selects the part
     * when user searches for it using the part id.
     * It filters the parts when searching for it using the part name.
     * It shows error when the part does not exist
     * @param event The event activates when user searches for a part using the search bar.
     */
    @FXML
    public void onActionSearchParts(ActionEvent event) {
        if (partSearchBox.getText().isEmpty()) {
            mainPartsTableView.setItems(Inventory.getAllParts());
        } else if (!partSearchBox.getText().trim().isEmpty()) {
            try {
                mainPartsTableView.getSelectionModel().select(Inventory.lookupPart(Integer.parseInt(partSearchBox.getText())));
            } catch (NumberFormatException e) {
                if (Inventory.lookupPart(partSearchBox.getText().trim())==null) {
                    mainPartsTableView.setItems(Inventory.getAllParts());
                } else {
                    mainPartsTableView.setItems(Inventory.lookupPart(partSearchBox.getText().trim()));
                }
            }
        }
    }

    /**
     * This method handles the searching of the product using the search bar, on the main screen.
     * It selects the product when user searches for it using the product id.
     * It filters the products when searching for it using the product name.
     * It shows error when the product does not exist
     * @param event The event activates when user searches for a product using the search bar ont he main menu screen.
     */
    @FXML
    public void onActionSearchProducts(ActionEvent event) {
        if (productSearchBox.getText().isEmpty()) {
            mainProductsTableView.setItems(Inventory.getAllProducts());
        } else if (!productSearchBox.getText().trim().isEmpty()) {
            try {
                mainProductsTableView.getSelectionModel().select(Inventory.lookupProduct(Integer.parseInt(productSearchBox.getText())));
            } catch (NumberFormatException e) {
                if (Inventory.lookupProduct(productSearchBox.getText().trim())==null) {
                    mainProductsTableView.setItems(Inventory.getAllProducts());
                } else {
                    mainProductsTableView.setItems(Inventory.lookupProduct(productSearchBox.getText().trim()));
                }
            }
        }
    }

    /**
     * This method initializes the two tables with dummy data that's entered in the main method.
     * @param url url
     * @param resourceBundle resourcebundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Populating Part and Product TableViews with Data
        mainPartsTableView.setItems(Inventory.getAllParts());
        mainProductsTableView.setItems(Inventory.getAllProducts());

        partsIdCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partsNameCol.setCellValueFactory(new PropertyValueFactory<>("partName"));
        partsInvCol.setCellValueFactory(new PropertyValueFactory<>("partInStock"));
        partsPriceCol.setCellValueFactory(new PropertyValueFactory<>("partPrice"));

        productsIdCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productsNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        productsInvCol.setCellValueFactory(new PropertyValueFactory<>("productInStock"));
        productsPriceCol.setCellValueFactory(new PropertyValueFactory<>("productPrice"));



    }

}
