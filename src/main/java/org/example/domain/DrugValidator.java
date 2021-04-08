package org.example.domain;

public class DrugValidator {
    /**
     * Validation on number of days, that has to be strictly positive;
     * @param drug
     * @throws Exception ...
     */
    public void validate(Drug drug) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        if (drug.getDrugPrice() <= 0) {
            stringBuilder.append("The price must be > 0\n");
        }


        if (stringBuilder.length() > 0) {
            throw new Exception(stringBuilder.toString());
        }
    }
}
