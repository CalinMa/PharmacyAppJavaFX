package org.example.domain;

import org.example.repository.IRepository;

public class TransactionValidator {

    /**
     * Validates a transactiona
     * @param transaction the transaction to validate
     * @param drugRepository the drugs repository, for validating the transac tion's drug id.
     * @throws Exception if there are any validation errors.
     */
    public void validate(Transaction transaction, IRepository<Drug> drugRepository) throws Exception {
        Drug givenDrug = drugRepository.readOne(transaction.getDrugId());
        if (givenDrug == null) {
            throw new Exception("There is no drug with the given id!");
        }
        if (givenDrug.getNumberOfDrugItems() < transaction.getNumberOfTransactions()) {
            throw new Exception("Cannot add transaction unless number of items from stock are >  number of items from the order");
        }

    }
}
