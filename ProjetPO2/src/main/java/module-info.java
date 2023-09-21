module jeu.projetpo2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;

    opens jeu.projetpo2 to javafx.fxml;
    exports jeu.projetpo2;
}