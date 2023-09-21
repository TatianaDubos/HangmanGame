package jeu.projetpo2;

import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML private ImageView tete, brasD, brasG, corps, jambeG, jambeD, win, lose ;
    @FXML private Label fieldMot, fieldNom, fieldScore, instructions ;
    @FXML private Button lettreA, lettreB,lettreC, lettreD, lettreE, lettreF, lettreG, lettreH, lettreI, lettreJ,
            lettreK,lettreL,lettreM,lettreN,lettreO,lettreP,lettreQ,lettreR,lettreS,lettreT,lettreU,lettreV,
            lettreW,lettreX,lettreY,lettreZ;

    private char[] lettres = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private Session session;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Debuter la session de jeu
        session = new Session(this);
    }
    @FXML
    void lettreAction(ActionEvent event) { // Clic sur une lettre
        Button[] group = {lettreA, lettreB,lettreC, lettreD, lettreE, lettreF, lettreG, lettreH, lettreI, lettreJ,
                lettreK,lettreL,lettreM,lettreN,lettreO,lettreP,lettreQ,lettreR,lettreS,lettreT,lettreU,lettreV,
                lettreW,lettreX,lettreY,lettreZ};

       for(int i=0; i<lettres.length; i++) {
            if (event.getSource() == group[i]) {
                group[i].setDisable(true);
                session.getPartie().nouvelleAction(lettres[i]);
                break;
            }
        }
    }
    void setNom(String nom){fieldNom.setText(nom);}  //Modifier les étiquettes
    void setPointage(int points){fieldScore.setText(String.valueOf(points));}
    void setMot(String mot){fieldMot.setText(mot);}
    void setInstructions(String text){instructions.setText(text);}
    ImageView[] getBonhomme(){
        ImageView[] bonhomme = {tete, corps, brasG, brasD, jambeG, jambeD};
        return bonhomme;
    }

    void partieGagne() { // Partie gagne
        session.updatePointage(5);
        setInstructions("Félicitation, tu as gagné la partie!");
        Button[] group = {lettreA, lettreB,lettreC, lettreD, lettreE, lettreF, lettreG, lettreH, lettreI, lettreJ,
                lettreK,lettreL,lettreM,lettreN,lettreO,lettreP,lettreQ,lettreR,lettreS,lettreT,lettreU,lettreV,
                lettreW,lettreX,lettreY,lettreZ};
        for(int i = 0; i< group.length; i++) group[i].setDisable(true);
        win.setVisible(true);
        transition(win);
    }
    void partiePerdu(){ //Partie perdu
        setInstructions("Malheusement, tu as perdu la partie!");
        Button[] group = {lettreA, lettreB,lettreC, lettreD, lettreE, lettreF, lettreG, lettreH, lettreI, lettreJ,
                lettreK,lettreL,lettreM,lettreN,lettreO,lettreP,lettreQ,lettreR,lettreS,lettreT,lettreU,lettreV,
                lettreW,lettreX,lettreY,lettreZ};
        for(int i = 0; i< group.length; i++) group[i].setDisable(true);
       lose.setVisible(true);
       transition(lose);
    }
    @FXML void btnNouvellePartie(ActionEvent event) { session.nouvellePartie();}  //Nouvelle Partie
    void nouvellePartie(){
        ImageView[] bonhomme = {tete, corps, brasG, brasD, jambeG, jambeD};
        for(int i = 0; i< bonhomme.length; i++) bonhomme[i].setVisible(false);

        Button[] group = {lettreA, lettreB,lettreC, lettreD, lettreE, lettreF, lettreG, lettreH, lettreI, lettreJ,
                lettreK,lettreL,lettreM,lettreN,lettreO,lettreP,lettreQ,lettreR,lettreS,lettreT,lettreU,lettreV,
                lettreW,lettreX,lettreY,lettreZ};
        for(int i = 0; i< group.length; i++) group[i].setDisable(false);

        win.setVisible(false); lose.setVisible(false);
    }
    @FXML void nouvelleSession(ActionEvent event) { // Nouvelle session
        session = new Session(this);
    }
    @FXML void exit(ActionEvent event) { // Quitter
        Alert alert = dialog(Alert.AlertType.CONFIRMATION, "Quitter", "", "Voulez-vous vraiment quitter?");
        ButtonType okButton = new ButtonType("Oui", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("Non", ButtonBar.ButtonData.NO);
        ButtonType cancelButton = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton, noButton, cancelButton);
        alert.showAndWait().ifPresent(type -> {
            if (type == okButton) { System.exit(0);
            }});
    }

    @FXML void infos(ActionEvent event) { // A propos de...
        dialog(Alert.AlertType.INFORMATION, "À propos de...",  "Le jeu du pendu - Instuctions", "Le but du jeu est simple : deviner toute les lettres qui doivent composer un mot," +
                " avec un nombre limité de tentatives. À chaque fois que le joueur devine une lettre," +
                " celle-ci est affichée. Dans le cas contraire, le dessin d’un pendu se met à apparaître…").showAndWait();
    }

    Alert dialog(Alert.AlertType type, String title , String header, String content){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert;
    }
    void transition(ImageView img){
        Duration duration = Duration.millis(1000);
        ScaleTransition scale = new ScaleTransition(duration, img);
        RotateTransition rotate =  new RotateTransition(duration, img);
        rotate.setByAngle(360);
        scale.setAutoReverse(true);
        scale.setByX(0.5);
        scale.setByY(0.5);
        scale.setCycleCount(2);
        scale.play();
        rotate.play();
    }


}
