# Description textuelle des cas d'utilisations

## Acteurs

- Administrateur : Utilisateur technique avec un accès complet à toutes les fonctionnalités.
- Conseillers bancaires : Employé de la banque qui gère les comptes et les opérations pour les clients.

## Droits d'accès :

- Administrateur : Utilisateur technique avec un accès complet à toutes les fonctionnalités.
- Conseillers bancaires : Employé de la banque qui gère les comptes et les opérations pour les clients.

## Authentification

Conseiller Bancaire :
- Doit se connecter avec un identifiant et un mot de passe pour accéder à l'application.

Admin Système :
- Accès direct sans authentification (accès technique réservé aux opérations de maintenance).

### Cas d'utilisation 1 :

---

| Elément                       | Description                                                                                                                                                                                                                  |
|-------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| __Nom des cas d’utilisation__ | Cas 1 : Créer un compte bancaire                                                                                                                                                                                             |
| __But / Objectif__            | Permet de créer un nouveau compte bancaire pour un client.                                                                                                                                                                   |
| __Acteur principal__          | Admin Système, Conseiller Bancaire.                                                                                                                                                                                          |
| __Préconditions__             | - Le numéro de compte doit être unique et valide (regex). <br /> - Le solde initial doit être positif ou nul.                                                                                                                |
| __Scénario principal__        | 1 Saisir le numéro de compte (format FR-XXXX-XXXX).<br/> 2 Saisir le nom du titulaire. <br /> 3 Définir le solde initial (≥ 0). <br /> 4 Choisir le type de compte (standard ou avec plafond). <br /> 5 Valider la création. |
| __Scénario alternatif__       |                                                                                                                                                                                                                              |
| __Postconditions__            | - InvalidAccountNumberException : Si le format du numéro est invalide. <br /> - DuplicateAccountException : Si le numéro de compte existe déjà.                                                                              |

### Cas d'utilisation 2 :

---

| Elément                       | Description                                                                       |
|-------------------------------|-----------------------------------------------------------------------------------|
| __Nom des cas d’utilisation__ | Cas 2 : Consulter un compte                                                       |
| __But / Objectif__            | Affiche les informations d'un compte existant (solde, titulaire, type de compte). |
| __Acteur principal__          | Admin Système, Conseiller Bancaire.                                               |
| __Préconditions__             | Le compte doit exister                                                            |
| __Scénario principal__        | 1 Saisir le numéro de compte.<br /> 2 Afficher les détails du compte.             |
| __Scénario alternatif__       |                                                                                   |
| __Postconditions__            | - AccountNotFoundException : Si le compte n'existe pas                            |

### Cas d'utilisation 3

---

| Elément                       | Description                                                                                                      |
|-------------------------------|------------------------------------------------------------------------------------------------------------------|
| __Nom des cas d’utilisation__ | Cas 3 : Effectuer un dépôt                                                                                       |
| __But / Objectif__            | Crédite un compte d'un montant spécifié.                                                                         |
| __Acteur principal__          | Admin Système, Conseiller Bancaire.                                                                              |
| __Préconditions__             | - Le compte doit exister<br/>- Le montant doit être strictement positif.                                         |
| __Scénario principal__        | 1 Saisir le numéro de compte.<br /> 2 Saisir le montant (> 0). <br /> 3 Valider l'opération.                     |
| __Scénario alternatif__       |                                                                                                                  |
| __Postconditions__            | - InvalidAmountException : Si le montant est ≤ 0. <br /> - AccountNotFoundException : Si le compte n'existe pas. |

### Cas d'utilisation 4

---

| Elément                       | Description                                                                                                                                                         |
|-------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| __Nom des cas d’utilisation__ | Cas 4 : Effectuer un retrait                                                                                                                                        |
| __But / Objectif__            | Débite un compte d'un montant spécifié.                                                                                                                             |
| __Acteur principal__          | Admin Système, Conseiller Bancaire.                                                                                                                                 |
| __Préconditions__             | - Le solde doit rester ≥ 0 après le retrait.<br/> - Pour les comptes avec plafond, le montant ne doit pas dépasser le plafond.                                      |
| __Scénario principal__        | 1 Saisir le numéro de compte.<br /> 2 Saisir le montant (> 0). <br /> 3 Valider l'opération.                                                                        |
| __Scénario alternatif__       |                                                                                                                                                                     |
| __Postconditions__            | - InsufficientBalanceException  : Si le solde est insuffisant. <br /> - LimitExceededException  : Si le montant dépasse le plafond (pour les comptes avec plafond). |


Cas 5 : Effectuer un virement
Cas 6 : Consulter l'historique des opérations
Cas 7 : Modifier le plafond d'un compte
Cas 8 : Supprimer un compte
Cas 9 : Se connecter

## 4. Règles Métiers 

### Validation des données :

- Tous les montants doivent être strictement positifs (sauf pour les soldes, qui peuvent être nuls).
- Les numéros de compte doivent respecter le format FR-XXXX-XXXX.

### Persistance des données :

- Toutes les opérations doivent être enregistrées en base de données (MariaDB).
- L'historique des transactions doit être mis à jour en temps réel.

### Gestion des exceptions :

- Toutes les exceptions doivent être affichées de manière claire à l'utilisateur.
- Les erreurs doivent être journalisées pour le débogage.

