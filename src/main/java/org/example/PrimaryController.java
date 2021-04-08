package org.example;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.domain.Drug;
import org.example.domain.Transaction;
import org.example.service.ServiceDrug;
import org.example.service.ServiceTransaction;
import org.example.service.UndoRedoManager;

import java.io.IOException;

public class PrimaryController {

    public TextField txtDrugId;
    public TextField txtDrugPrice;
    public TextField txtNumberOfDrugItems;
    public TextField txtDrugName;
    public TextField txtDrugProducer;
    public CheckBox chkDrugNeedsRecipe;
    public TableView tblDrugs;
    public TableColumn colDrugId;
    public TableColumn colDrugPrice;
    public TableColumn colNumberOfItems;
    public TableColumn colName;
    public TableColumn colProducer;
    public TableColumn colNeedsRecipe;

    public TableView tblTransactions;
    public TableColumn colTransactionId;
    public TableColumn colDrugIdTransaction;
    public TableColumn colClientCardNumber;
    public TableColumn colNumberOfTransactions;
    public TableColumn colDateAndHour;
    public TextField txtTransactionID;
    public TextField txtDrugIdTransaction;
    public TextField txtClientCardNumber;
    public TextField txtNumberOfTransactions;
    public TextField txtDateAndHour;

    public TextField txtTransactionByKeyword;

    public TextField txtTransactionStartDate;
    public TextField txtTransactionEndDate;
    public Button btnSearchTransactionByTime;
    public Button btnDeleteTransactionByTime;
    public TextField txtDeleteTransactionEndDate;
    public TextField txtDeleteTransactionStartDate;



    //    @FXML
//    private void switchToSecondary() throws IOException {
//        App.setRoot("secondary");
//    }
    private ServiceDrug serviceDrug;
    private ServiceTransaction serviceTransaction;
    private UndoRedoManager undoRedoManager;
    private final ObservableList<Drug> drugs = FXCollections.observableArrayList();
    private final ObservableList<Transaction> transactions = FXCollections.observableArrayList();

    @FXML
    private void initialize(){

        Platform.runLater(() ->{
            drugs.addAll((serviceDrug.getAll()));
            transactions.addAll((serviceTransaction.getAll()));
            tblTransactions.setItems(transactions);
            tblDrugs.setItems(drugs);
        });
    }

     public void setServices(ServiceDrug serviceDrug, ServiceTransaction serviceTransaction, UndoRedoManager undoRedoManager){
         this.serviceDrug = serviceDrug;
         this.serviceTransaction = serviceTransaction;
         this.undoRedoManager = undoRedoManager;

     }

    public void btnAddDrugClick(ActionEvent actionEvent) throws Exception {
         try{
            int id = Integer.parseInt(txtDrugId.getText());
            int drugPrice = Integer.parseInt(txtDrugPrice.getText());
            int numberOfItems = Integer.parseInt(txtNumberOfDrugItems.getText());
            String drugName = txtDrugName.getText();
            String drugProducer = txtDrugProducer.getText();
            Boolean drugNeedsRecipe = chkDrugNeedsRecipe.isSelected();

            serviceDrug.addDrug(id,drugPrice,numberOfItems,drugName,drugProducer,drugNeedsRecipe);
            drugs.clear();;
            drugs.addAll(serviceDrug.getAll());
         }
         catch (RuntimeException rex) {
            Common.showErrorMessage(rex.getMessage());
         } {

        }
    }

    public void btnUpdateDrugClick(ActionEvent actionEvent) throws Exception {
        try{
            int drugId = Integer.parseInt(txtDrugId.getText());
            int drugPrice = Integer.parseInt(txtDrugPrice.getText());
            int numberOfDrugItems = Integer.parseInt(txtNumberOfDrugItems.getText());
            String drugName = txtDrugName.getText();
            String drugProducer = txtDrugProducer.getText();
            Boolean drugNeedsRecipe = chkDrugNeedsRecipe.isSelected();



            serviceDrug.updateDrug(drugId,drugPrice,numberOfDrugItems,drugName,drugProducer,drugNeedsRecipe);
            refreshDrugList();
        }
        catch (RuntimeException rex) {
            Common.showErrorMessage(rex.getMessage());
        }
        {

        }
    }

    public void btnDeleteDrugClick(ActionEvent actionEvent) throws Exception {
        Drug selectedDrug = (Drug) tblDrugs.getSelectionModel().getSelectedItem();
        if (selectedDrug != null ){
            serviceDrug.delete(selectedDrug.getIdEntity());
            refreshDrugList();
        }
    }
    private void refreshDrugList () {
        drugs.clear();;
        drugs.addAll(serviceDrug.getAll());
    }
    private void refreshTransactionList () {
        transactions.clear();;
       transactions.addAll(serviceTransaction.getAll());
    }


    public void btnUndoClick(ActionEvent actionEvent) {

            try {
                refreshDrugList();
                undoRedoManager.doUndo();
                refreshDrugList();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }


    public void btnRedoClick(ActionEvent actionEvent) {
        try {

            refreshDrugList();
            undoRedoManager.doRedo();
            refreshDrugList();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    //Transactions


    public void btnAddTransactionClick(ActionEvent actionEvent) throws Exception {
        try{
            int transactionID = Integer.parseInt(txtTransactionID.getText());
            int drugID = Integer.parseInt(txtDrugIdTransaction.getText());
            int clientCardNumber = Integer.parseInt(txtClientCardNumber.getText());
            int numberOfTransactions = Integer.parseInt(txtNumberOfTransactions.getText());
            String dateAndHour = txtDateAndHour.getText();


            serviceTransaction.addTransaction(transactionID,drugID,clientCardNumber,numberOfTransactions,dateAndHour);
            transactions.clear();
            transactions.addAll(serviceTransaction.getAll());
        }
        catch (RuntimeException rex) {
            Common.showErrorMessage(rex.getMessage());
        } {

        }
    }

    public void btnUpdateTransactionClick(ActionEvent actionEvent) {
        try{
            int transactionID = Integer.parseInt(txtTransactionID.getText());
            int drugID = Integer.parseInt(txtDrugIdTransaction.getText());
            int clientCardNumber = Integer.parseInt(txtClientCardNumber.getText());
            int numberOfTransactions = Integer.parseInt(txtNumberOfTransactions.getText());
            String dateAndHour = txtDateAndHour.getText();


            serviceTransaction.updateTransaction(transactionID,drugID,clientCardNumber,numberOfTransactions,dateAndHour);
           refreshTransactionList();



        } catch (Exception exception) {
            Alert a = new Alert (Alert.AlertType.ERROR);
            a.setHeaderText(exception.getMessage());
            a.show();
        }
    }

    public void btnDeleteTransactionClick(ActionEvent actionEvent) throws Exception {
        Transaction selectedTransaction = (Transaction) tblTransactions.getSelectionModel().getSelectedItem();
        if (selectedTransaction != null ){
            serviceTransaction.delete(selectedTransaction.getIdEntity());
            refreshTransactionList();
        }
    }

    public void btnUndoTransactionClick(ActionEvent actionEvent) {
        try {

            refreshTransactionList();
            undoRedoManager.doUndo();
            refreshTransactionList();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void btnRedoTransactionClick(ActionEvent actionEvent) {
        try {

            refreshTransactionList();
            undoRedoManager.doRedo();
            refreshTransactionList();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    public void btnSortDrugs(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("drugsOrderedByTransactions.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 400);
            Stage stage = new Stage();

            //ii dam acces la controller
            DrugsOrderedByTransactions resultsController = fxmlLoader.getController();
            resultsController.setServiceDrugs(this.serviceDrug);

            stage.setTitle("Drug sorted by number of transactions:");
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException iex) {
            Common.showErrorMessage(iex.getMessage());        }
    }


    public void btnSortCards(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("transactionCards.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 400);
            Stage stage = new Stage();

            //ii dam acces la controller
            CardsOrderedByTransactions resultsController = fxmlLoader.getController();
            resultsController.setServiceTransactions(this.serviceTransaction);

            stage.setTitle("Client Card with transaction results");
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException iex) {
            Common.showErrorMessage(iex.getMessage());        }
    }


    public void btnSearchEntitiesByKeyword(ActionEvent actionEvent) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("searchEntitiesByKeyword.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 400);
            Stage stage = new Stage();

            //ii dam acces la controller
            SearchEntitiesByKeyword resultsController = fxmlLoader.getController();
            resultsController.setTransactionService(this.serviceTransaction);

            stage.setTitle("Entities display by string input");
            stage.setScene(scene);
            stage.showAndWait();    //ca sa nu se poata lucra cu ambele ferestre in acelasi timp

        } catch (RuntimeException | IOException rex) {
            Common.showErrorMessage(rex.getMessage());
        }
    }

    public void btnSearchForTransactionsBetweenDates(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("searchTransactionsBetweenDates.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 400);
            Stage stage = new Stage();

            //ii dam acces la controller
            SearchTransactionsBetweenDates resultsController = fxmlLoader.getController();
            resultsController.setTransactionService(this.serviceTransaction);

            stage.setTitle("Search");
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException iex) {
            Common.showErrorMessage(iex.getMessage());
        }
    }
    public void btnDeleteForTransactionsBetweenDates(ActionEvent actionEvent) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("deleteTransactionIntervalDates.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 400);
            scene.getStylesheets().add("org.example/CSS/style.css");
            Stage stage = new Stage();

            //ii dam acces la controller
            DeleteTransactionIntervalDates resultsController = fxmlLoader.getController();
            resultsController.setServiceTransactions(this.serviceTransaction);

            stage.setTitle("Transaction results after delete option:");
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException iex) {
            Common.showErrorMessage(iex.getMessage());        }
    }

    public void btnIncreasePrice(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("increaseDrugPrice.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 400);
            Stage stage = new Stage();

            //ii dam acces la controller
            IncreaseDrugPrice resultsController = fxmlLoader.getController();
            resultsController.setServiceDrugs(this.serviceDrug);

            stage.setTitle("Drug results after increased price:");
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException iex) {
            Common.showErrorMessage(iex.getMessage());        }
    }
}

