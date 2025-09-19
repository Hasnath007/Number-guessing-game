module com.example.numbergussinggame {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.numbergussinggame to javafx.fxml;
    exports com.example.numbergussinggame;
}