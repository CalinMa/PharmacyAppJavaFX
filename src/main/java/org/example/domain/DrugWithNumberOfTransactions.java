package org.example.domain;

public class DrugWithNumberOfTransactions {
    private int idDrug;
    private String name;
    private int numberOfTransactions;

    public DrugWithNumberOfTransactions(int idDrug, String name, int numbersOfTransactions) {
        this.idDrug = idDrug;
        this.name = name;
        this.numberOfTransactions = numbersOfTransactions;
    }

    public int getIdDrug() {
        return idDrug;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfTransactions() {
        return numberOfTransactions;
    }

    @Override
    public String toString() {
        return "DrugWithNumberOfTransactions{" +
                "idDrug=" + idDrug +
                ", name='" + name + '\'' +
                ", numberOfTransactions=" + numberOfTransactions +
                '}';
    }
}
