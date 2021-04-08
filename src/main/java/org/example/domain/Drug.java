package org.example.domain;

public class Drug  extends Entity{
    private final int drugPrice;
    private final int numberOfDrugItems;
    private final String drugName;
    private final String DrugProducer;
    boolean drugNeedsRecipe;



    public Drug(int idEntity, int drugPrice, int numberOfDrugItems, String drugName, String drugProducer, boolean drugNeedsRecipe) {
        super(idEntity);
        this.drugPrice = drugPrice;
        this.numberOfDrugItems = numberOfDrugItems;
        this.drugName = drugName;
        this.drugNeedsRecipe=drugNeedsRecipe;
        DrugProducer = drugProducer;
    }
// we already  have a getter on idEntity in the parent class
 /*    public int getDrugId() {
        return drugId;
    }*/

    public int getDrugPrice() {
        return drugPrice;
    }

    public int getNumberOfDrugItems() {
        return numberOfDrugItems;
    }

    public String getDrugName() {
        return drugName;
    }

    public String getDrugProducer() {
        return DrugProducer;
    }

    public boolean getDrugNeedsRecipe() {
        return drugNeedsRecipe;
    }

    @Override
    public String toString() {
        return "Drug{" +
                "drugId=" + getIdEntity() +
                ", drugPrice=" + drugPrice +
                ", numberOfDrugItems=" + numberOfDrugItems +
                ", drugName='" + drugName + '\'' +
                ", DrugProducer='" + DrugProducer + '\'' +
                ", drugNeedsRecipe=" + drugNeedsRecipe +
                '}';
    }
}
