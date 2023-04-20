package arbres;

import java.util.Iterator;
import java.util.Stack;

public class ArbreBinaireNonGenerique implements Iterable<String> {
    private String info;
    private ArbreBinaireNonGenerique sag;
    private ArbreBinaireNonGenerique sad;

    // Construit une feuille
    public ArbreBinaireNonGenerique(String info) {
        this.info = info;
        this.sag = null;
        this.sad = null;
    }
    // Construit un noeud avec ses fils
    public ArbreBinaireNonGenerique(String info, ArbreBinaireNonGenerique sag, ArbreBinaireNonGenerique sad) {
        this.info = info;
        this.sag = sag; 
        this.sad = sad;
    }
    public String getRacine() {
        return this.info;
    }
    public void setRacine(String info) {
        this.info = info;
    }
    public ArbreBinaireNonGenerique getSag() {
        return this.sag;
    }
    public void setSag(ArbreBinaireNonGenerique sag) {
        this.sag = sag;
    }
    public ArbreBinaireNonGenerique getSad() {
        return this.sad;
    }
    public void setSad(ArbreBinaireNonGenerique sad) {
        this.sad = sad;
    }
    public boolean isLeaf() { return (this.sag == null) && (this.sad == null); }

    public Iterator<String> iterator() {
        return new ArbreBinaireIterator(this);
    }

    // Renvoie les éléments de l'arbre dans l'ordre d'un certain parcours de l'arbre
    public class ArbreBinaireIterator implements Iterator<String> {
        
        private Stack<ArbreBinaireNonGenerique> pile; // utilise une collection pour le parcours
        
        public ArbreBinaireIterator(ArbreBinaireNonGenerique tree) {
            pile = new Stack<ArbreBinaireNonGenerique>();
            remplirPile(tree);
        }
        private void remplirPile(ArbreBinaireNonGenerique n) {
            while (n != null) {
                pile.push(n);
                n = n.getSag();
            }
        }
        // y a-t-il encore des éléments non parcourus
        public boolean hasNext() {
            return !pile.isEmpty();
        }
        // renvoie le prochain élément stocké dans l'arbre
        public String next() {
            ArbreBinaireNonGenerique n = pile.pop();
            remplirPile(n.getSad());
            return n.getRacine();
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // Représente un arbre comme l'ensemble de ses noeuds ordonnés suivant un certain parcours
    public String toString() {
        String str = "[";
        ArbreBinaireIterator iter = new ArbreBinaireIterator(this);
        while (iter.hasNext()) {
            str += iter.next();
            if (iter.hasNext()) {
                str += ", ";
            }
        }
        return str+"]";
    }

    // Main de la classe pour des essais
    public static void main(String[] args) {
        // Arbre vide
        ArbreBinaireNonGenerique arbreVide = null;

        // Arbre avec trois noeuds
        ArbreBinaireNonGenerique arbre = new ArbreBinaireNonGenerique("3");
        ArbreBinaireNonGenerique noeudGauche = new ArbreBinaireNonGenerique("2");
        ArbreBinaireNonGenerique noeudDroit = new ArbreBinaireNonGenerique("20");

        arbre.setSag(noeudGauche);
        arbre.setSad(noeudDroit);
        System.out.println("Un arbre non vide : " + arbre);
        System.out.println("Un arbre vide : " + arbreVide);

        // Construction d'autre arbre ayant un noeud en commun avec l'arbre précédent
        ArbreBinaireNonGenerique autreArbre = new ArbreBinaireNonGenerique("40");
        autreArbre.setSag(noeudDroit);
        System.out.println("Un autre arbre non vide : " + autreArbre);

        // On change la valeur d'un seul noeud, mais référencé dans les deux arbres :
        noeudDroit.setRacine("33");
        System.out.println("Après avoir mis 33 dans un noeud, voici ce que sont devenus les deux arbres :");
        System.out.println(arbre);
        System.out.println(autreArbre);
    }

}









