package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.domain.Drug;
import org.example.domain.DrugValidator;
import org.example.domain.Transaction;
import org.example.domain.TransactionValidator;
import org.example.repository.IRepository;
import org.example.repository.InMemoryRepository;
import org.example.service.ServiceDrug;
import org.example.service.ServiceTransaction;
import org.example.service.UndoRedoManager;

/**
 * JavaFX App
 */

public class App extends Application {

    private static Scene scene;
    @Override
    public void start(Stage stage) throws Exception {
//        scene = new Scene(loadFXML("primary"), 640, 480);
//        stage.setScene(scene);
//        stage.show();
       // root = FXMLLoader.load(getClass().getResource("project-3.fxml"));
       // root.get().add(getClass().getResource("your_stylesheet.css").toExternalFo‌​rm());

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("primary.fxml"));
      //  fxmlLoader.ge().add(getClass().getResource("style.css").toString());
        Parent parentFxml = fxmlLoader.load();
       // fxmlLoader.load();
        this.scene =  new Scene(parentFxml,1000,800);

        IRepository<Drug> drugRepository= new InMemoryRepository<Drug>();
        IRepository<Transaction> transactionIRepository = new InMemoryRepository<Transaction>();
        UndoRedoManager undoRedoManager = new UndoRedoManager();
        DrugValidator drugValidator = new DrugValidator();
        TransactionValidator transactionValidator = new TransactionValidator();
        ServiceDrug serviceDrug = new ServiceDrug(drugRepository,transactionIRepository,drugValidator,undoRedoManager);
        ServiceTransaction serviceTransaction = new ServiceTransaction(transactionIRepository, drugRepository , transactionValidator,undoRedoManager);

        serviceDrug.addDrug(1, 5, 3, "Aspirine", "Bayern", false);
        serviceDrug.addDrug(2, 10, 2, "Paracetamol", "Modena", false);
        serviceDrug.addDrug(3, 15, 3, "Ibuprofen", "Pfizer", true);
        serviceDrug.addDrug(4, 25, 5, "AAAA", "XXX", false);
        serviceDrug.addDrug(5, 30, 10, "BBB", "YYY", false);
        serviceDrug.addDrug(6, 35, 15, "CCC", "ZZZ", true);

        serviceTransaction.addTransaction(1, 1, 1234, 1, "12.03.2020 12:00");
        serviceTransaction.addTransaction(2, 5, 1534, 5, "12.03.2028 12:00");
        serviceTransaction.addTransaction(3, 5, 1234, 1, "12.03.2017 12:00");
        PrimaryController primaryController =  fxmlLoader.getController();
        primaryController.setServices(serviceDrug,serviceTransaction,undoRedoManager);

        stage.setTitle("Pharmacy manager");
        stage.setScene(this.scene);
        stage.show();

    }

//    static void setRoot(String fxml) throws IOException {
//        scene.setRoot(loadFXML(fxml));
//    }
//
//    private static Parent loadFXML(String fxml) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
//        return fxmlLoader.load();
//    }

    public static void main(String[] args) {
        launch();
    }

}