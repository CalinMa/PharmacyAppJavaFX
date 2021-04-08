package org.example.domain;

public class Transaction extends Entity{
    private int  drugId, clientCardNumber, numberOfTransactions;
    private String dateAndHour;

    public Transaction(int idEntity, int drugId, int clientCardNumber, int numberOfTransactions, String dateAndHour) {
        super(idEntity);
        this.drugId = drugId;
        this.clientCardNumber = clientCardNumber;
        this.numberOfTransactions = numberOfTransactions;
        this.dateAndHour = dateAndHour;
    }
// same as in Drug class, we have the getter in the parent class Entity
   /* public int getTransactionId() {
        return transactionId;
    }*/

    public int getDrugId() {
        return drugId;
    }

    public int getClientCardNumber() {
        return clientCardNumber;
    }

    public int getNumberOfTransactions() {
        return numberOfTransactions;
    }

    public String getDateAndHour() {
        return dateAndHour;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + getIdEntity() +
                ", drugId=" + drugId +
                ", clientCardNumber=" + clientCardNumber +
                ", numberOfItems=" + numberOfTransactions +
                ", dateAndHour='" + dateAndHour + '\'' +
                '}';
    }
}
