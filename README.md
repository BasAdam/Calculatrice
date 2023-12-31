# Calculatrice en Java

Ce projet est une simple calculatrice graphique écrite en Java. Il utilise Swing pour l'interface utilisateur et suit le modèle MVC (Modèle-Vue-Contrôleur).

## Structure du projet

Le projet est organisé en plusieurs classes Java, chacune ayant un rôle spécifique :

- `MainCalculator` : C'est le point d'entrée de l'application. Il crée une instance de l'interface utilisateur de la calculatrice.

- `CalculatorUI` : Cette classe gère l'interface utilisateur de la calculatrice. Elle crée et organise les composants Swing, et gère l'affichage des résultats.

- `CalculatorLogic` : Cette classe gère la logique de calcul de la calculatrice. Elle effectue les opérations arithmétiques et stocke le résultat.

- `ButtonActionListener` : Cette classe est un écouteur d'action qui est appelé lorsque l'utilisateur clique sur un bouton de l'interface utilisateur. Elle détermine quelle action effectuer en fonction du bouton cliqué.

## Comment exécuter le projet

Pour exécuter ce projet, vous devez avoir Java installé sur votre machine. Vous pouvez ensuite compiler et exécuter le projet à l'aide de Maven :


```sh
mvn compile
mvn exec:java -Dexec.mainClass="com.mycompany.calculatrice.MainCalculator"
```


## Fonctionnalités de la calculatrice

- **Opérations arithmétiques de base :**
  - Addition
  - Soustraction
  - Multiplication
  - Division

- **Bouton de réinitialisation :**
  - Permet de réinitialiser l'affichage et le calcul en cours.

Lorsqu'une opération est en cours, l'affichage est remplacé par le chiffre entré par l'utilisateur. Si aucune opération n'est en cours, le chiffre est ajouté à l'affichage.

## Limitations et améliorations possibles

Actuellement, la calculatrice présente quelques limitations :

- **Nombres négatifs :**
  - La gestion des nombres négatifs n'est pas implémentée.

- **Entrées claviers :**
  -  La calculatrice ne gère pas les entrées claviers, il faut obligatoirement cliquer sur les boutons.

Ces limitations pourraient être traitées dans de futures mises à jour pour améliorer la fonctionnalité globale de la calculatrice.
