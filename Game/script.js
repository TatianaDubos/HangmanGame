// Création de notre mot aléatoire
const mots = new Array("printemps", "ami", "personne", "livre", "soleil", "musique", "escargot", "facile", "maison", "enfant", "saison", "froid", "automne", "pluie", "neige", "devoir", "vent", "hibernation", "polaire", "ange", "chaud", "gentil", "humide", "hivernal", "marmotte", "compagnon", "montagne", "oceanique", "olympique", "continental", "sport", "tropical", "thermique", "agreable", "desertique", "semaine", "herbe", "saisonnier", "ecureuil", "cigarette", "antarctique", "estival", "station", "lune", "athlete", "brume", "chaleur", "orage", "skieur", "montagnard", "climat", "mangeoire", "maximum", "mensuel", "glacial", "hivernation", "humide", "plumage", "nuit", "orange", "patinoire", "neige", "celsius", "feuille", "brouillard", "calculatrice", "douceur", "saison", "rose", "crayon", "confortable", "fruit", "fourrure", "effacer", "oiseau", "cannabis", "manger", "application", "animal", "musique", "chien", "voiture", "tuque", "chambre", "feu", "eau", "terre", "air", "avatar", "intelligence", "fumer", "hopital");
let nombreAléatoire = Math.floor(Math.random()*mots.length);
let motAléatoire = mots[nombreAléatoire];


// Fonction pour afficher le nombre de lettre que le mot contient
function SelectionMot() 
{ 
    document.getElementById("NbrDeLettres").textContent +=  motAléatoire.length + " lettres.";

    for (compteur = 0; compteur < motAléatoire.length; compteur++) 
    {
        document.getElementById("Reponse").textContent += "_ ";
    }
} 


let saisie = document.getElementById("Saisie");
let reponse;
let err = 0;
let utilise;
let regex = /[a-zàéèêç]/ig;
let regex2 = /\d\s/g;

let reponseModifie;
let idx;
let indices;


//Fonction pour chercher si la lettre saisie est présente dans notre mot
function Recherche(e)
{  
    
    saisie = document.getElementById("Saisie").value.toLowerCase();
    reponse = document.getElementById("Reponse").textContent;
    //Lettres utilisées:
    utilise = document.getElementById("LettresUtilise").textContent;

    //Si ce n'est pas une lettre
    if (!saisie.match(regex)) {
        document.getElementById("Bravo").innerHTML = "Ce n'est pas une lettre";
        document.getElementById("Bravo").style.color = "gold";
        e.preventDefault();
    
    } else if (saisie.match(regex2)) {
        document.getElementById("Bravo").innerHTML = "Ce n'est pas une lettre";
        document.getElementById("Bravo").style.color = "gold";
        e.preventDefault();
    }
    //Si la lettre est déjà utilisée:
    if (utilise.includes(saisie)) 
    { 
        document.getElementById("Bravo").innerHTML = "Lettre déjà utilisée";
        document.getElementById("Bravo").style.color = "yellow";
        e.preventDefault();
    }
    else
    {
        document.getElementById("LettresUtilise").innerHTML += saisie + " - ";
    }

    
    //Pour faire fondre le bonhomme de neige si Erreur
    if(motAléatoire.includes(saisie))
    {
        document.getElementById("Bravo").innerHTML = "Bravo!";
        document.getElementById("Bravo").style.color = "green";

        //Pour afficher les lettres au bon endroit
        indices = [];
        idx = motAléatoire.indexOf(saisie);
        reponseModifie = document.getElementById("Reponse").textContent.split(' ');
    
        while (idx != -1) {
        indices.push(idx);
        idx = motAléatoire.indexOf(saisie, idx + 1);
        } 

        for (compteur = 0; compteur < indices.length; compteur++) {
            reponseModifie[indices[compteur]] = saisie;
            reponse.textContent = reponseModifie;
        }
        document.getElementById("Reponse").innerHTML = reponseModifie.join(' ');
    
    }
    else {
        document.getElementById("Bravo").innerHTML = "Erreur";
        document.getElementById("Bravo").style.color = "red";

        ++err
        switch (err) {
            case 1:
                 document.getElementById("snowman").src = "Images/snowman2.png";
                break;
            case 2:
                document.getElementById("snowman").src = "Images/snowman3.png";
                break;
            case 3:
                document.getElementById("snowman").src = "Images/snowman4.png";
                break;
            case 4:
                document.getElementById("snowman").src = "Images/snowman5.png";
                break;
            case 5:
                document.getElementById("snowman").src = "Images/snowman6.png";
                break;
            case 6:
                document.getElementById("snowman").src = "Images/snowman7.png";
                break;
            case 7:
                document.getElementById("snowman").src = "Images/snowman8.png";
                document.getElementById("Bravo").innerHTML = "Il vous reste une chance!";
                document.getElementById("Bravo").style.color = "blue";
                
                break;
            case 8:
                document.getElementById("snowman").src = "Images/snowman9.png";
                document.getElementById("Chercher").disabled = true;
                document.getElementById("GameOver").src = "Images/game_over.png";
                document.getElementById("GameOver").style.animation = "slide 1s";
                break;
            default: 
                break;
            
        }
    }

    reponse = document.getElementById("Reponse").textContent;
    if (!reponse.includes("_")) {
        document.getElementById("Chercher").disabled = true;
        document.getElementById("YouWin").src = "Images/you_win.png";
        document.getElementById("YouWin").style.animation = "slide1 1s";
    }
    document.getElementById("Saisie").value = "";
}
// Fonction pour le bouton Nouvelle Partie
function Actualiser()
{
    window.location.reload();
}


