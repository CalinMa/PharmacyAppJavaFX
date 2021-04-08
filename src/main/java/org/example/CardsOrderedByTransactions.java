package org.example;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import org.example.domain.TransactionCards;
import org.example.service.ServiceTransaction;

public class CardsOrderedByTransactions {
    public TableView tblTransactionsCards;
    private ServiceTransaction serviceTransaction;
  

    public void setServiceTransactions(ServiceTransaction serviceTransaction) {
        this.serviceTransaction = serviceTransaction;
    }

    @FXML
    private void initialize() {
        Platform.runLater(() -> {
            ObservableList<TransactionCards> cardsWithTransactions = FXCollections.observableArrayList();
            cardsWithTransactions.addAll(serviceTransaction.getCardsOrderedByNumberOfTransactions());
            tblTransactionsCards.setItems(cardsWithTransactions);
        });
    }
}
