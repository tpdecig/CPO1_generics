# Les génériques à la rescousse !
## 1 - Etat des lieux
1) Compilez et exécutez la classe `ArbreBinaireChaine`.
Examinez son code pour voir si vous comprenez bien ce que permet cette classe et comment elle le fait. 
En particulier, êtes-vous capable d'expliquer pourquoi une modification d'un noeud d'un arbre fait que le contenu 
de l'autre arbre est aussi modifié (était-ce le cas pour l'implémentation d'arbre binaire en Swift sous forme d'`enum` ?).

## 2 - Interfacez-vous
2) Créer une interface `ArbreBinaireChaine` depuis les méthodes principales de la classe `ArbreBinaireNonGenerique`
Indiquer que cette classe implémente l'interface.
Recompilez et exécutez la classe `ArbreBinaireNonGenerique`, celle-ci doit s'exécuter de façon inchangée à ce que nous avions précédemment
(seule l'architecture du code a été complétée, pas ses fonctionnalités).

## 3 - Une interface plus utile
Si on veut faire un arbre contenant des `Clients` ou des entiers, on est embêté car les classes et interfaces ci-dessus ne sont pas assez *génériques*.

Justement, proposez une interface `ArbreBinaire` où cette fois le type n'est pas imposé : on doit pouvoir stocker aux noeuds un type autre que `String` (par exemple `Integer`, `Client`, `Animal`, ...). Cependant imposez que le même type soit stocké à tous les noeuds ("*on ne mélange pas les torchons et les serviettes*").

## 4 - La classe Mathias !
Par transformation de la classe `ArbreBinaireString`, proposez une classe `ArbreBinaireChaine` qui implémente l'interface `ArbreBinaire`
Adaptez aussi les déclarations d'arbres dans les exemples de la méthode `main` de façon à pouvoir exécuter cette classe et qu'elle produise le même résultat que précédemment. En effet : on vient de rendre notre architecture de code plus générique / réutilisable, il n'y a pas de raison qu'en faisant ça on ne puisse pas avoir le même service qu'avant, simplement maintenant il faut préciser que vous voulez des arbres stockant des `String` (dans les exemples d'arbres créés ici).

## 5 - Sauvons un chat
- L'application `app.SauveUnChat` lit un arbre stocké sous forme de fichier. Le nom du fichier est donné en paramètre au lancement du programme. Ce fichier contient des lignes d'entiers. Chaque ligne commence par le numéro d'un noeud suivi de la liste des numéros de ses noeuds fils. 
- Une fois l'arbre chargé en mémoire sous la forme d'un `ArbreBinaire`, l'app demande à l'utilisateur d'indiquer sur quelle noeud il a vu un chat perché. 
- Ce chat ne sait pas descendre de l'arbre, il faut donc aller le chercher et pour ça savoir par quels noeuds passer. C'est l'objectif de la fonction `chercheLeChat` qui affiche la liste des noeuds par lesquels il faut passer pour aller chercher le chat.

Compilez et exécutez cette classe puis indiquez un noeud où pourrait se percher le chat.
Vérifiez que le chemin indiqué est bien correct.

----
# Etudiants en avance : 

## 1 - Une situation exceptionnelle
vous noterez que quand on lance l'exécution de la classe arbres.ArbreBinaire, on obtient une erreur si on n'indique pas de fichier d'arbre comme paramètre. 

Modifiez le code de cette classe pour qu'elle lève une exception plus significative que celle levée actuellement pour expliquer d'où vient le problème.

## 2 - Remise dans l'ordre
Vous avez forcément remarqué que le chemin affiché par la fonction  `chercheLeChat` ne s'affiche pas dans le bon ordre. Pour cela, il faudrait modifier cette fonction afin qu'elle retourne une collection dans laquelle sont stockés les noeuds rencontrés sur le chemin menant au chat. 
- Quelle collection faut-il utiliser, et comment l'utiliser, pour que l'on puisse afficher de retour dans le `main` le chemin menant jusqu'au chat en commençant par le noeud racine et en terminant par le noeud où est perché le chat ?
- Sauriez-vous implémenter les modifs nécessaires pour parvenir à ce résultat ?