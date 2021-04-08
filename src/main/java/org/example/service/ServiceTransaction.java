package org.example.service;

import org.example.domain.*;
import org.example.repository.IRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ServiceTransaction {
    private final IRepository<Transaction> repositoryTransaction;
    private final IRepository<Drug> repository;
    private final TransactionValidator transactionValidator;
    private final UndoRedoManager undoRedoManager;

    public ServiceTransaction(IRepository<Transaction> repositoryTransaction,
                              IRepository<Drug> repository,
                              TransactionValidator transactionValidator,
                              UndoRedoManager undoRedoManager) {
        this.repositoryTransaction = repositoryTransaction;
        this.repository = repository;
        this.transactionValidator = transactionValidator;
        this.undoRedoManager = undoRedoManager;
    }

    // Transacțion can be performed only if:
    //		number of drugs > number of transactions.
    public void addTransaction(int transactionId, int drugId, int clientCardNumber, int numberOfDrugItems, String dateAndHour) throws Exception {
        Transaction transaction = new Transaction(transactionId, drugId, clientCardNumber, numberOfDrugItems, dateAndHour);
        this.transactionValidator.validate(transaction, this.repository);
        this.repositoryTransaction.create(transaction);
        this.undoRedoManager.addToUndo(new UndoRedoAddOperation<>(this.repositoryTransaction,transaction));
    }

    public void updateTransaction(int transactionId, int drugId, int clientCardNumber, int numberOfTransactions, String dateAndHour) throws Exception {
        Transaction transaction = new Transaction(transactionId, drugId, clientCardNumber, numberOfTransactions, dateAndHour);
        this.transactionValidator.validate(transaction, this.repository);
        this.repositoryTransaction.update(transaction);
        //TODO UndoRedoManager
    }

    public void delete(int transactionId) throws Exception {
        this.repositoryTransaction.delete(transactionId);
    }

    public List<Transaction> getAll() {
        return this.repositoryTransaction.read();
    }

    public List<Transaction> searchTransaction(String searchText) {
        List<Transaction> results = new ArrayList<>();

        for (Transaction transaction : this.getAll()) {
            if (transaction.getDateAndHour().contains(searchText) ||
                    String.valueOf(transaction.getIdEntity()).contains(searchText) ||
                    String.valueOf(transaction.getDrugId()).contains(searchText) ||
                    String.valueOf(transaction.getClientCardNumber()).contains(searchText) ||
                    String.valueOf(transaction.getNumberOfTransactions()).contains(searchText)) {
                results.add(transaction);
            }
        }

        return results;
    }

    /**
     * Return all the transactions between two dates
     * @param start start date
     * @param end end date
     * @return list with all the transactions with date between start and end
     */
    public List<Transaction> getBetweenTwoDateAndTimes(LocalDateTime start, LocalDateTime end) {
        List<Transaction> results = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        for (Transaction t: this.getAll()){
            String stringDate = t.getDateAndHour();
            LocalDateTime typedDate = LocalDateTime.parse(stringDate, formatter);
            if (start.isBefore(typedDate) && typedDate.isBefore(end)){
                results.add(t);
            }
        }
        return results;
    }
    // delete transactions between two dates

    public List<Transaction> deleteBetweenTwoDateAndTimes(LocalDateTime start, LocalDateTime end) throws Exception {
        List<Transaction> results = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        for (Transaction t: this.getAll()){
            int idTransaction = t.getIdEntity();
            String stringDate = t.getDateAndHour();
            LocalDateTime typedDate = LocalDateTime.parse(stringDate, formatter);
            if (start.isBefore(typedDate) && typedDate.isBefore(end)){
                this.repositoryTransaction.delete(idTransaction);
                results.add(t);
            }
        }
        return results;
    }
    // Show cards sorted descending by number of transactions

    public List<TransactionCards> getCardsOrderedByNumberOfTransactions(){
        Map<Integer, Integer> cardsWithNUmberOfTransactions = new HashMap<>();
        for (Transaction t : this.repositoryTransaction.read()) {
            int cardNumber = t.getClientCardNumber();
            if (!cardsWithNUmberOfTransactions.containsKey(cardNumber)) {
                cardsWithNUmberOfTransactions.put(cardNumber, 1);
            } else {
                cardsWithNUmberOfTransactions.put(cardNumber,cardsWithNUmberOfTransactions.get(cardNumber) + 1);
            }
        }
        List<TransactionCards> results = new ArrayList<>();
        for (Map.Entry<Integer,Integer> entry: cardsWithNUmberOfTransactions.entrySet() ){
            int cardNumber = entry.getKey();
            int numberOfTransactions = entry.getValue();
            results.add( new TransactionCards(numberOfTransactions,cardNumber ));
        }
        results.sort(Comparator.comparing(TransactionCards::getNumberOfTransactions).reversed());
        return results;
    }

    //Search by keyword

    public List<Entity> searchEntitiesByKeyword(String stringInput){
        List<Entity> getAllEntitiesByStringInput = new ArrayList<>();

        for (Drug drug : this.repository.read()) {
            if (drug.getDrugName().contains(stringInput)) {
                getAllEntitiesByStringInput.add(repository.readOne(drug.getIdEntity()));
            } else if (drug.getDrugProducer().contains(stringInput)){
                getAllEntitiesByStringInput.add(repository.readOne(drug.getIdEntity()));
            } else if (String.valueOf(drug.getDrugPrice()).contains(stringInput)) {
                getAllEntitiesByStringInput.add(repository.readOne(drug.getIdEntity()));
            } else if (String.valueOf(drug.getNumberOfDrugItems()).contains(stringInput)) {
                getAllEntitiesByStringInput.add(repository.readOne(drug.getIdEntity()));
            }
        }

        for(Transaction transaction : this.getAll()) {
            if (String.valueOf(transaction.getClientCardNumber()).contains(stringInput)) {
                getAllEntitiesByStringInput.add(repositoryTransaction.readOne(transaction.getIdEntity()));
            } else if (String.valueOf(transaction.getNumberOfTransactions()).contains(stringInput)) {
                getAllEntitiesByStringInput.add(repositoryTransaction.readOne(transaction.getIdEntity()));
            } else if (transaction.getDateAndHour().contains(stringInput)) {
                getAllEntitiesByStringInput.add(repositoryTransaction.readOne(transaction.getIdEntity()));
            }
        }
        return getAllEntitiesByStringInput;
    }
}
