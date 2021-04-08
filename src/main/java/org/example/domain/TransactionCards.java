package org.example.domain;

public class TransactionCards {
    int numberOfTransactions;
    int clientCardNumber;

    public TransactionCards(int numberOfTransactions, int clientCardNumber) {
        this.numberOfTransactions = numberOfTransactions;
        this.clientCardNumber = clientCardNumber;
    }

    public int getNumberOfTransactions() {
        return numberOfTransactions;
    }

    public int getClientCardNumber() {
        return clientCardNumber;
    }

    @Override
    public String toString() {
        return "TransactionCards{" +
                "numberOfTransactions=" + numberOfTransactions +
                ", clientCardNumber=" + clientCardNumber +
                '}';
    }
}
