module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;

    opens org.example to javafx.fxml;
    exports org.example;

    opens org.example.domain to javafx.base;

}