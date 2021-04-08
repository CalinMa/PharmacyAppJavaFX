package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.example.domain.Transaction;
import org.example.service.ServiceTransaction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SearchTransactionsBetweenDates {

    public TableView tblSearchTransactionsBetweenDates;
    public TextField txtDateHourStart;
    public TextField txtDateHourEnd;
    public TableColumn colIdTransaction;
    public TableColumn colIdDrugs;
    public TableColumn colClientCard;
    public TableColumn colNumberOfPieces;
    public TableColumn colDateAndHour;
    public Button btnTransactionDateHourSearch;

    private ServiceTransaction serviceTransaction;
    ObservableList<Transaction> transactionsListResults = FXCollections.observableArrayList();

    public void setTransactionService(ServiceTransaction serviceTransaction ) {
        this.serviceTransaction = serviceTransaction;
    }

    public void btnSearchTransactionsBetweenDates(ActionEvent actionEvent) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime startDate = LocalDateTime.parse(txtDateHourStart.getText(), formatter);
        LocalDateTime endDate = LocalDateTime.parse(txtDateHourEnd.getText(), formatter);

        List<Transaction> transactionListWithinRangeOfDays = this.serviceTransaction.getBetweenTwoDateAndTimes(startDate, endDate);        transactionsListResults.addAll(transactionListWithinRangeOfDays);
        tblSearchTransactionsBetweenDates.setItems(transactionsListResults);
    }
}