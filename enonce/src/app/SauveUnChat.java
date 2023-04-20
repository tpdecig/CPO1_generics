package app;
import arbres.ArbreBinaire;
import utils.LireFichier;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class SauveUnChat {

    private int positionChat;
    public SauveUnChat(int i) {
        this.positionChat = i;
    }

    // Si l'entier num n'est pas deja une clef dans le dictionnaire
    // alors l'ajoute dans le dictionnaire associé à un Noeud contenant cet élément
    // Dans tous les cas renvoie le Noeud associé à cet élément
    private static ArbreBinaire<Integer> Int2NodeInDico(Map<Integer,ArbreBinaire<Integer>> m, Integer num) {
        if (! m.containsKey(num)) {
            // crée une feuille pour ce noeud, vu pour la premiere fois
            ArbreBinaire<Integer> noeud = new ArbreBinaire<>(num);
            m.put(num,noeud);
        }
        return m.get(num);
    }

    public static ArbreBinaire<Integer> Fichier2ArbreBinInt(String nomFichier) {
      // Lecture des relations de parentés d'un arbre contenant des numéros entiers
      // Le fichier est une liste de lignes
      //    chaque ligne est une liste d'entiers
      List<List<Integer>> parentes;
      // dans cette application on stocke des entiers dans les noeuds
      ArbreBinaire<Integer> noeudRacine = null; 
      parentes = LireFichier.lireArbrBinaire(nomFichier);
      // Utilise une collection pour retrouver les noeuds déjà construits 
      // et aider à l'assemblage de l'arbre
      Map<Integer,ArbreBinaire<Integer>> mesNoeuds = new HashMap<>();

      for (List<Integer> lig : parentes) {
          Integer numPere = lig.remove(0);
          ArbreBinaire<Integer> noeudPere = Int2NodeInDico(mesNoeuds,numPere);
          // le pere de la 1ère ligne est la racine de l'arbre
          if (noeudRacine == null) { noeudRacine = noeudPere; }
          Integer numFilsGauche = lig.remove(0);
          ArbreBinaire<Integer> noeudFilsGauche = Int2NodeInDico(mesNoeuds,numFilsGauche);
          noeudPere.setSag(noeudFilsGauche);            
          // s'il y a un 2ème fils, c'est le fils droit :
          if (! lig.isEmpty()) {
              Integer numFilsDroit = lig.remove(0);
              ArbreBinaire<Integer> noeudFilsDroit = Int2NodeInDico(mesNoeuds,numFilsDroit);
              noeudPere.setSad(noeudFilsDroit);  
          }
      }
      return noeudRacine;
    }

    // Parcourt l'arbre à la recherche du chat et affiche le chemin parcouru
    // depuis la racine quand on le trouve
    public boolean chercheLeChat(ArbreBinaire<Integer> n) {
        if (n.getRacine().equals(this.positionChat)) {
            System.out.print("Trouvé le chat sur le chemin  "+this.positionChat);
            return true;
        }
        if (n.getSag() != null) { 
            if (chercheLeChat(n.getSag())) {
                System.out.print(" "+n.getRacine());
                return true;
            }
        } 
        if (n.getSad() != null) { 
            if (chercheLeChat(n.getSad())) {
                System.out.print(" "+n.getRacine());
                return true;
            }
        } 
        return false;
    }

    public static void main(String[] args) {
        // 1) on lit un arbre depuis un fichier
        ArbreBinaire<Integer> arbreLu = Fichier2ArbreBinInt(args[0]);
        System.out.println("Arbre lu depuis le fichier : " + arbreLu);

        // 2) On demande où est le chat
        Scanner myInput = new Scanner( System.in );
        System.out.print( "Sur quelle noeud est perché le chat ? " );
        int chat = myInput.nextInt();
        myInput.close();
        SauveUnChat jeu = new SauveUnChat(chat);

        // 3) on cherche le chemin pour ramener le chat :
        if (jeu.chercheLeChat(arbreLu)) {
            System.out.println();
        }
        else {
            System.out.println("Chat pas trouvé dans l'arbre");
        }
    }

}