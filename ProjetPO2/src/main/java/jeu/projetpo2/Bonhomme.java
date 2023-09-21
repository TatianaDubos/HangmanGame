package jeu.projetpo2;

import javafx.animation.ScaleTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Bonhomme {
    private ImageView tete, brasD, brasG, corps, jambeG, jambeD ;
    private int compteur = 0;

    private Partie partie;
    public Bonhomme(Controller c, Partie p){
        ImageView[] bonhomme = c.getBonhomme();
        tete = bonhomme[0];
        corps = bonhomme[1];
        brasG = bonhomme[2] ;
        brasD = bonhomme[3] ;
        jambeG = bonhomme[4];
        jambeD = bonhomme[5];

        partie = p;
    }
    void ajoute_membre(){

        switch (compteur){
            case 0 : tete.setVisible(true); transition(tete); compteur++;
                break;
            case 1 :  corps.setVisible(true); transition(corps); compteur++;
                break;
            case 2 : brasG.setVisible(true); transition(brasG); compteur++;
                break;
            case 3 : brasD.setVisible(true); transition(brasD); compteur++;
                break;
            case 4 : jambeG.setVisible(true); transition(jambeG); compteur++;
                break;
            case 5 : jambeD.setVisible(true); transition(jambeD); compteur++; partie.setPerdu();
                break;
        }
    }

    void transition(ImageView img){
        Duration duration = Duration.millis(500);
        ScaleTransition scale = new ScaleTransition(duration, img);
            scale.setAutoReverse(true);
            scale.setByX(0.8);
            scale.setByY(0.8);
            scale.setCycleCount(2);
        scale.play();
    }
}
