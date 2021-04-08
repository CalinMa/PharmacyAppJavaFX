package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.example.domain.Drug;
import org.example.service.ServiceDrug;
import java.util.List;

public class IncreaseDrugPrice {

    public TextField txtIncreasePercentage;
    public TextField txtDrugPrice;
    public TableView tblIncreasePrice;

    private ServiceDrug serviceDrug;
    ObservableList<Drug> drugsListResults = FXCollections.observableArrayList();

    public void setServiceDrugs(ServiceDrug drugService) {
        this.serviceDrug = drugService;
    }

    public void btnIncreasePriceClick(ActionEvent actionEvent) throws Exception {
        float percentage = Float.parseFloat(txtIncreasePercentage.getText());
        float drugPrice = Float.parseFloat(txtDrugPrice.getText());

        this.serviceDrug.updateDrugsWithPercentage(percentage, drugPrice);
        List<Drug> drugsIncreaseResults = this.serviceDrug.getAll();
        drugsListResults.addAll(drugsIncreaseResults);
        tblIncreasePrice.setItems(drugsListResults);
    }
}
