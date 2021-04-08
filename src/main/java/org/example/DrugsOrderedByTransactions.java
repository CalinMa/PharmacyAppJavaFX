package org.example;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.example.domain.DrugWithNumberOfTransactions;
import org.example.service.ServiceDrug;

public class DrugsOrderedByTransactions {

    public TableView tblDrugsWithTransactions;
    public TableColumn colIdDrugByTransactions;
    private ServiceDrug serviceDrug;

    public void setServiceDrugs(ServiceDrug serviceDrug) {
        this.serviceDrug = serviceDrug;
    }
    @FXML
    private void initialize() {
        Platform.runLater(() -> {
            ObservableList<DrugWithNumberOfTransactions> drugsOrderedByTransactions = FXCollections.observableArrayList();
            drugsOrderedByTransactions.addAll(serviceDrug.getDrugsOrderedByNumberOfTransactions());
            tblDrugsWithTransactions.setItems(drugsOrderedByTransactions);
        });
    }
}

