# ArchitectureProject2425

## Étape 1 : Démarrer le Docker

Dans le dossier `src/main/resources`, exécutez:
```bash
docker-compose up -d
```
Ensuite, lancez l'application.

## Tests :

### Use Case 1 : Gestion des Propriétés par l'Agent

#### 1.1 Créer un Agent
**POST**: `http://localhost:8080/agents`
```json
{
  "firstname": "Alice",
  "lastname": "Dupont",
  "email": "alice.agent@example.com",
  "password": "mysecretpassword",
  "agencyName": "Magic Agency"
}
```

#### 1.2 Ajouter une Propriété
**POST**: `http://localhost:8080/properties`
```json
{
  "name": "Super Apartment",
  "price": 100000.0,
  "fundingDeadline": "2025-12-31",
  "fundingOpen": true,
  "rentalIncomePercentage": 6.0,
  "appreciationRate": 2.0,
  "type": "apartment",
  "agent": {
    "id": "3f2c7bb6-0fa1-4ce6-99ac-05b3f0aaa115"  // Remplacez par l'UUID de votre Agent
  }
}
```

#### 1.3 Mettre à Jour une Propriété
**PUT**: `http://localhost:8080/properties/{propertyId}`
```json
{
  "name": "Updated Super Apartment",
  "price": 95000.0,
  "fundingDeadline": "2025-11-30",
  "fundingOpen": true,
  "rentalIncomePercentage": 6.5,
  "appreciationRate": 2.5,
  "type": "apartment"
}
```

#### 1.4 Supprimer une Propriété
**DELETE**: `http://localhost:8080/properties/{propertyId}`

#### 1.5 Lister Toutes les Propriétés
**GET**: `http://localhost:8080/properties`

### Use Case 2 : Listing des Propriétés Ouvertes pour les Utilisateurs Finaux

#### 2.1 Lister les Propriétés Ouvertes (Maximum 6)
**GET**: `http://localhost:8080/properties/open`

### Use Case 3 : Consultation du Portefeuille par les Utilisateurs Finaux

3-1-**GET**: `/investors/{id}`

3-2-**POST**: `/investors` 
Crée l’investor, et si vous tentez d’investir avec un `investorId` inexistant, le code lève une exception.

### Use Case 4 : Gestion du Porte-Monnaie pour les Investisseurs

#### 4.1 Ajouter de l'Argent avant tout Investissement
**POST**: `/payments`
```json
{
  "investorId": "UUID",
  "amount": 5000.0,
  "status": "SUCCESS"
}
```
Si `status` = `SUCCESS`, la méthode `createTransaction(...)` crédite le wallet de l’investor.

#### 4.2 Revenu Mensuel de Location
Pour tester :
1. Créer une propriété avec un agent existant
2. **POST**: `/properties`
```json
{
  "name": "Duplex For Rent",
  "price": 100000,
  "fundingDeadline": "2025-12-31",
  "fundingOpen": true,
  "rentalIncomePercentage": 6.0, 
  "appreciationRate": 2.0,
  "type": "apartment",
  "agent": {
    "id": "12c88dea-623d-4cdc-9e44-bc4eded52d1a"
  }
}
```
3. Créer un investor :
**POST**: `/investors`
```json
{
  "firstname": "kill",
  "lastname": "kam",
  "email": "killakam@example.com",
  "password": "secret14523"
}
```
4. Créer un deuxième investor :
**POST**: `/investors`
```json
{
  "firstname": "Alice",
  "lastname": "Investor",
  "email": "alice@example.com",
  "password": "secret123"
}
```
5. Alice investit 20000$
**POST**: `/investments/invest`
```json
{
  "investorId": "c034d7f7-dd7c-4d83-a4e2-8c7fe6cbb3c0",
  "propertyId": "bc60861d-df8c-4e64-bb50-f868780efede",
  "amount": 20000
}
```
6. Kill Kam investit 30000$ :
**POST**: `/investments/invest`
```json
{
  "investorId": "58ca4974-c737-431e-b5e1-7c207af14a10",
  "propertyId": "90926908-d8ba-490b-bd5d-f36c352ff8e7",
  "amount": 20000
}
```
7. Distribuer le loyer mensuel :
**POST**: `http://localhost:8080/rental/distribute`
```json
[
  {
    "investorId": "c034d7f7-dd7c-4d83-a4e2-8c7fe6cbb3c0",
    "propertyId": "bc60861d-df8c-4e64-bb50-f868780efede",
    "amountDistributed": 50.0
  },
  {
    "investorId": "58ca4974-c737-431e-b5e1-7c207af14a10",
    "propertyId": "90926908-d8ba-490b-bd5d-f36c352ff8e7",
    "amountDistributed": 75.0
  }
]
```
8. Voir tous les wallets pour voir le montant qu'ils ont gagné :
**GET**: `/investors`

### Use Case 5 : Gestion des Délais de Financement

Chaque propriété ouverte au financement doit respecter une date limite de financement : si la propriété n'est pas entièrement financée dans les 2 mois suivant le lancement du processus de financement, le financement s'arrête et les investisseurs sont remboursés de l'argent qu'ils ont dépensé pour cette propriété. L'argent est retourné dans leur wallet.

Pour tester :
1. Créer un agent
2. Créer une propriété avec un `fundingDeadline` proche (ex. `LocalDate.now().plusDays(1)`) pour simuler un délai court.
**POST**: `/properties`
```json
{
  "name": "Short Deadline Property",
  "price": 100000.0,
  "fundingDeadline": "2025-02-01",  // ou +1 jour
  "fundingOpen": true,
  "rentalIncomePercentage": 6.0,
  "appreciationRate": 2.0,
  "type": "apartment",
  "agent": {
    "id": "ID-AGENT"
  }
}
```
3. Créer un investor
4. Faire un investment
5. Simuler l’échec du financement










