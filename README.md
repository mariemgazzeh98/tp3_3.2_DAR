# Activité 3.2 : Serveur Calculatrice Multi-thread avec Synchronisation

## 📋 Description
Ce projet est une application Client-Serveur en Java permettant d'effectuer des opérations arithmétiques de base (+, -, *, /). 
Il a pour but d'illustrer la gestion des **accès concurrents** (Multi-threading) et la **synchronisation** de données partagées.

Le serveur crée un thread dédié pour chaque client connecté et maintient un compteur global synchronisé du nombre total d'opérations effectuées.

## 🛠️ Architecture du projet
Le code est organisé en trois packages :

1.  **`operateurPackage`** : Contient la classe `Operateur` (implémente `Serializable`). C'est l'objet transféré via le réseau contenant les opérandes et l'opérateur.
2.  **`serverPackage`** :
    *   **`Server`** : Écoute sur le port 1234 et accepte les connexions.
    *   **`ClientProcess`** : Thread gérant le calcul pour un client spécifique et la mise à jour sécurisée du compteur global.
3.  **`clientPackage`** : Interface console permettant à l'utilisateur de saisir les données et d'envoyer l'objet au serveur.

## ⚙️ Fonctionnalités Techniques

*   **Communication par Objets** : Utilisation de `ObjectInputStream` et `ObjectOutputStream` pour échanger des instances de la classe `Operateur`.
*   **Multi-threading** : Le serveur peut traiter plusieurs clients simultanément sans blocage.
*   **Synchronisation** : La méthode `Compteur()` est déclarée **`synchronized`** pour incrémenter la variable statique partagée `Compteur_operation` sans risque de conflit entre les threads.

## 🚀 Installation et Exécution

### Prérequis
*   Java JDK 8 ou supérieur.
*   Port **1234** disponible.

### Instructions
1.  **Lancer le Serveur** :
    *   Exécutez la classe `serverPackage.Server`.
    *   *Console :* `Je suis un serveur en attente la connexion d'un client`.

2.  **Lancer le(s) Client(s)** :
    *   Exécutez la classe `clientPackage.Client`.
    *   Saisissez les nombres et l'opérateur demandés.

3.  **Résultats** :
    *   Le client reçoit et affiche le résultat du calcul.
    *   Le serveur affiche le détail de l'opération et le **nombre total d'opérations traitées** (compteur incrémenté).

## ⚠️ Configuration Réseau
Pour tester sur plusieurs machines différentes :
1.  Trouvez l'adresse IP de la machine hébergeant le serveur (ex: `ipconfig`).
2.  Dans `Client.java`, remplacez `"localhost"` par l'adresse IP du serveur :
    ```java
    socket = new Socket("192.168.x.x", 1234);
    ```
