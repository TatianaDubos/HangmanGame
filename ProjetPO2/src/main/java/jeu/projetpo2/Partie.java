package jeu.projetpo2;

public class Partie {
        public Partie(Controller c, Session s){ // Une partie dans une session
            view = c;
            session = s;
            pendu = new Bonhomme(c, this);

            mot = listeMots[(int)(Math.random() * listeMots.length)].toLowerCase(); // Mot aléatoire
            System.out.println("Mot mystere : "+ mot);
            for(int i = 0; i < mot.length(); i++){

                if(mot.charAt(i) == '-' ) {  // Charactere espace et trait : visible
                    motCache +="-";}
                else if(mot.charAt(i) == ' ') {
                    motCache +=" ";
                } else if (mot.charAt(i) == '\'') {
                    motCache +="'";
                }
                else{motCache +="*";}  // lettre : caché
            }
            c.setMot(motCache);
            c.setInstructions("Devinez une lettre dans le mot caché");
        }

        void nouvelleAction(char lettre){ // Clic sur une lettre : recherche de la lettre dans le mot
            char[] tabMot = motCache.toCharArray();
            boolean  special = false;

            for(int i=0; i<mot.length(); i++ ){
                // Cas charactère avec accents
                if(mot.contains("é") ||   mot.contains("è")   ||   mot.contains("à")  ||  mot.contains("ç")) {
                    special = true;
                    if (lettre == 'a') {
                        if (compare(mot, 'à', tabMot, i)) continue;
                    }
                    if (lettre == 'c') {
                        if (compare(mot, 'ç', tabMot, i)) continue;
                    }
                    if (lettre == 'e') {
                        if (compare(mot, 'é', tabMot, i)) continue;
                        else if (compare(mot,'è', tabMot, i)) continue;
                    }
                }

                if(mot.indexOf(lettre) != -1 || special) { // Vérifier si la lettre est présente dans le mot
                     compare(mot, lettre, tabMot, i);
                    view.setInstructions("Bravo! Tu as deviné une lettre.");
                }
                else{
                    pendu.ajoute_membre();
                    view.setInstructions("Oups...Cette lettre n'est pas présente dans le mot!");
                    break;
                }

            }
            tabMot[0] =  String.valueOf(tabMot[0]).toUpperCase().charAt(0); // Première lettre en majuscule
            motCache = String.valueOf(tabMot);
            view.setMot(motCache); // re-afficher le mot mystere


            if(motCache.indexOf("*") == -1) view.partieGagne(); //Partie gagné

            if(perdu) view.partiePerdu(); //Partie perdu
        }
        void setPerdu(){perdu = true;}
        boolean compare(String mot, char lettre, char[] tab, int pos){ // Comparer une lettre avec une autre
            if (mot.charAt(pos) == lettre) {
                tab[pos] = lettre;  // mettre la lettre à la bonne position dans le mot caché
                session.updatePointage(1);
               return true;
            }
            return false;
        }

    private String mot;
    private String motCache = "";
    private Bonhomme pendu;
    private Controller view;
    private Session session;
    private boolean perdu = false;
    public static String[] listeMots = {"animal", "humain", "ordinateur", "insecte", "poisson", "objet", "montagne", "rose", "orange", "soleil", "lune", "chien",
            "chat", "souris", "hamster", "rongeur", "musique", "arc-en-ciel", "eclipse", "montréal"};
}
