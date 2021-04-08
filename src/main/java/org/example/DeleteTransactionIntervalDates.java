package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.example.domain.Transaction;
import org.example.service.ServiceTransaction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DeleteTransactionIntervalDates {
    public TextField txtDateStart;
    public TextField txtDateEnd;
    public TableView tblTransactionsDelete;
    public TableColumn colIdTransaction;
    public TableColumn colIdDrugs;
    public TableColumn colClientCard;
    public TableColumn colNumberOfPieces;
    public TableColumn colDateAndHour;

    private ServiceTransaction serviceTransaction;
    ObservableList<Transaction> transactionsListResults = FXCollections.observableArrayList();

    public void setServiceTransactions(ServiceTransaction serviceTransaction) {
        this.serviceTransaction = serviceTransaction;
    }

    public void btnTransactionDateDeleteClick(ActionEvent actionEvent) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
      LocalDateTime startDate = LocalDateTime.parse(txtDateStart.getText(),formatter);
        LocalDateTime endDate = LocalDateTime.parse(txtDateEnd.getText(),formatter);

        this.serviceTransaction.deleteBetweenTwoDateAndTimes(startDate, endDate);
        List<Transaction> transactionListWithinRangeOfDays = this.serviceTransaction.getAll();
        transactionsListResults.addAll(transactionListWithinRangeOfDays);
        tblTransactionsDelete.setItems(transactionsListResults);
    }
}