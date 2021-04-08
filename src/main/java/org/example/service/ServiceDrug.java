package org.example.service;

import org.example.domain.*;
import org.example.repository.IRepository;

import java.util.*;

public class ServiceDrug {
    private IRepository<Drug> drugIRepository;
    private IRepository<Transaction> transactionIRepository;
    private DrugValidator drugValidator;
    private UndoRedoManager undoRedoManager;

    public ServiceDrug(IRepository<Drug> drugIRepository,IRepository<Transaction> transactionIRepository, DrugValidator drugValidator, UndoRedoManager undoRedoManager) {
        this.drugIRepository = drugIRepository;
        this.transactionIRepository = transactionIRepository;
        this.drugValidator = drugValidator;
        this.undoRedoManager = undoRedoManager;
    }

    /**
     * ...
     * @param drugId
     * @param drugPrice
     * @param numberOfDrugItems
     * @param drugName
     * @param drugProducer
     * @param drugNeedsRecipe
     */
    public void addDrug(int drugId, int drugPrice, int numberOfDrugItems, String drugName, String drugProducer, boolean drugNeedsRecipe ) throws Exception {
        Drug drug = new Drug(drugId, drugPrice, numberOfDrugItems, drugName, drugProducer,drugNeedsRecipe);
        this.drugValidator.validate(drug);
        this.drugIRepository.create(drug);
        undoRedoManager.addToUndo(new UndoRedoAddOperation<>(this.drugIRepository,drug));
    }

    public void updateDrug(int drugId, int drugPrice, int numberOfDrugItems, String drugName, String drugProducer, boolean drugNeedsRecipe) throws Exception {
        Drug drug = new Drug(drugId, drugPrice, numberOfDrugItems, drugName, drugProducer,drugNeedsRecipe);
        this.drugValidator.validate(drug);
        this.drugIRepository.update(drug);
        // TODO: add update undo redo operation to undoRedoManager
    }

    public void delete(int idDrug) throws Exception {
        Drug drug = this.drugIRepository.readOne(idDrug);
        this.drugIRepository.delete(idDrug);
        undoRedoManager.addToUndo(new UndoRedoDeleteOperation<>(this.drugIRepository,drug));
    }
    public List<Drug> getDrugsCheaperThanPrice (int maxPrice){
        List<Drug> drugs = new ArrayList<>();
        for (Drug drug: this.getAll()) {
            if (maxPrice > drug.getDrugPrice())
                drugs.add(drug);
        }
        return drugs;
    }
    public List<Drug> searchDrugs(String searchText) {
        List<Drug> results = new ArrayList<>();
        // List<String> searchList = new ArrayList<String>();
        for (Drug drug: this.getAll()) {
            if (drug.getDrugName().contains(searchText) ||
                    drug.getDrugProducer().contains(searchText) ||
                    String.valueOf(drug.getIdEntity()).contains(searchText) ||
                    String.valueOf(drug.getDrugPrice()).contains(searchText) ||
                    String.valueOf(drug.getNumberOfDrugItems()).contains(searchText) ||
                    String.valueOf(drug.getDrugNeedsRecipe()).contains(searchText)) {
                results.add(drug);
            }
        }

        return results;
    }
    public List<DrugWithNumberOfTransactions> getDrugsOrderedByNumberOfTransactions(){
        Map<Integer, Integer> drugsWithNUmberOfTransactions = new HashMap<>();
        for (Transaction t : this.transactionIRepository.read()) {
            int idDrug = t.getDrugId();
            if (!drugsWithNUmberOfTransactions.containsKey(idDrug)) {
                drugsWithNUmberOfTransactions.put(idDrug, 1);
            } else {
                drugsWithNUmberOfTransactions.put(idDrug,drugsWithNUmberOfTransactions.get(idDrug) + 1);
            }
        }
        List<DrugWithNumberOfTransactions> results = new ArrayList<>();
        for (Map.Entry<Integer,Integer> entry: drugsWithNUmberOfTransactions.entrySet() ){
            int idDrug = entry.getKey();
            int numberOfTransactions = entry.getValue();
            Drug drug = this.drugIRepository.readOne(idDrug);
            results.add( new DrugWithNumberOfTransactions(idDrug, drug.getDrugName(),numberOfTransactions) );
        }
        results.sort(Comparator.comparing(DrugWithNumberOfTransactions::getNumberOfTransactions).reversed());
        return results;
    }
    public void updateDrugsWithPercentage(float minPrice, float percentageIncrease) throws Exception {
        for (Drug drug : getAll()) {
            if (drug.getDrugPrice() < minPrice) {
                float result = drug.getDrugPrice() + drug.getDrugPrice() * percentageIncrease / 100;
                updateDrug(drug.getIdEntity(), (int) result,drug.getNumberOfDrugItems(),drug.getDrugName(),drug.getDrugProducer(),drug.getDrugNeedsRecipe());
            }
        }
    }

    public List<Drug> getAll() {
        return this.drugIRepository.read();
    }
}
