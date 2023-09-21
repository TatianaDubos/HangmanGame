package jeu.projetpo2;

import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class Session {  //Session du jeu
    public Session(Controller c ){
        view = c;

        while(true){
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Jeu du pendu");
        dialog.setHeaderText("Veuillez saisir votre nom");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
          nomJoueur = result.get();
          break;
        }}
        c.setNom(nomJoueur);
        c.setPointage(pointage);

        partie = new Partie(c, this);
    }

    void updatePointage(int points){
        pointage += points;
        view.setPointage(pointage);
    }
    void nouvellePartie(){
        partie = new Partie(view, this);
        view.nouvellePartie();
    }

    Partie getPartie(){
        return partie;
    }
    private String nomJoueur;
    private int pointage = 0;
    private Controller view;
    private Partie partie;



}
