package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class LireFichier {

    // Reads a tree from a file:
    //   each line has between two or three space separated integers
    //   the first one is a parent and the other ones its children
    public static List<List<Integer>> lireArbrBinaire(String nomFichier) {
        List<List<Integer>> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nomFichier))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                String[] valeurs = ligne.split(" ");
                if (valeurs.length < 2 || valeurs.length>3) {
                    System.err.println("Erreur de format dans la ligne : " + ligne);
                    continue;
                }
                List<Integer> liens = new LinkedList<>();
                for (String s : valeurs) {
                    liens.add(Integer.parseInt(s));
                }
                result.add(liens);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Main de la classe ArbreBinaire
    public static void main(String[] args) {
        List<List<Integer>> parentes = LireFichier.lireArbrBinaire("bel_arbre.txt");
        for (List<Integer> lig : parentes) {
            Integer pere = lig.remove(0);
            System.out.print(pere);
            System.out.println(" a pour fils : " + lig);
        }
    }

}