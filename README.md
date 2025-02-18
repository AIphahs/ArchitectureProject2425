# ArchitectureProject2425

## Étape 1 : Démarrer le Docker

A la racine du projet, exécutez:
```bash
cd .\src\main\resources\
docker-compose up -d
```

## Étape 2 : Lancer l'application

A la racine du projet, exécutez:
```bash
mvn clean install
mvn spring-boot:run
```

## Étape 3 : Importer le projet postman

Dans postman importer le JSON ```PropertyStakeAPI.postman_colletion.JSON``` se situant à la racine du projet.


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



### Use Case 6: Concurrency Management in Funding Sessions

#### 6.1 Multiple Investors Funding the Same Property Simultaneously
To manage concurrency when multiple investors are funding the same property at the same time, you need to implement a locking mechanism to prevent race conditions.
**Example Implementation:**
1. Lock the property record before processing any funding transaction.
2. Check the current funded amount and the remaining amount required.
3. Process the funding transaction if there is still room for the investment.
4. Release the lock after updating the property record.

**Sample API Call:**
**POST**: `/investments/concurrent-fund`
```json
{
  "investorId": "uuid-of-investor",
  "propertyId": "uuid-of-property",
  "amount": 10000.0 // Amount to invest
}
```
**Response:**
```json
{
  "status": "SUCCESS",
  "message": "Investment processed successfully."
}
```

### Use Case 7: External Payment Gateway Integration (Stripe)

#### 7.1 Funding Wallet via Credit Card
Integrate Stripe to handle credit card payments and top up the wallet.
**Example Implementation:**
1. Create a payment intent with Stripe.
2. Confirm the payment with the provided payment method.
3. On successful payment, credit the investor's wallet and send a receipt.

**Sample API Call:**
**POST**: `/payments/stripe`
```json
{
  "investorId": "uuid-of-investor",
  "amount": 5000.0, // Amount to add to wallet
  "paymentMethodId": "pm_card_visa" // Stripe payment method ID
}
```
**Response:**
```json
{
  "status": "SUCCESS",
  "message": "Payment processed successfully, wallet credited."
}
```

### Use Case 8: Sending Receipts to Investors

#### 8.1 Email Receipts After Successful Transactions
After a successful funding transaction, send a receipt to the investor's email.
**Example Implementation:**
1. Integrate an email service (like SendGrid) to send receipts.
2. Trigger the email sending function after a successful transaction.

**Sample API Call:**
**POST**: `/receipts/send`
```json
{
  "investorId": "uuid-of-investor",
  "transactionId": "uuid-of-transaction",
  "email": "investor@example.com"
}
```
**Response:**
```json
{
  "status": "SUCCESS",
  "message": "Receipt sent to investor's email."
}
```

### Use Case 9: Scalability for High Traffic

#### 9.1 Scaling the System for High Transaction Volumes
Ensure the system is capable of handling high volumes of transactions during peak times.

**Example Implementation:**
1. Use a load balancer to distribute incoming requests across multiple servers.
2. Implement horizontal scaling to add more servers when needed.
3. Optimize database queries and use caching to reduce load on the database.

**Configuration:**
- Use AWS Elastic Load Balancing (ELB) or similar services.
- Set up auto-scaling groups in your cloud provider (e.g., AWS, Azure).
- Use Redis or Memcached for caching frequently accessed data.

### Example Scenarios for Testing

#### 9.2 Test Scenario: High Concurrency Funding
1. Simulate multiple investors funding the same property at the same time.
2. Ensure no race conditions occur and all transactions are processed accurately.

**Sample API Call:**
**POST**: `/investments/concurrent-fund`
```json
{
  "investorId": "uuid-of-investor",
  "propertyId": "uuid-of-property",
  "amount": 5000.0 // Amount to invest
}
```

#### 9.3 Test Scenario: High Volume Payments
1. Simulate a high number of wallet top-up requests via Stripe.
2. Ensure the payment gateway handles the load and all wallets are credited correctly.

**Sample API Call:**
**POST**: `/payments/stripe`
```json
{
  "investorId": "uuid-of-investor",
  "amount": 1000.0, // Amount to add to wallet
  "paymentMethodId": "pm_card_visa" // Stripe payment method ID
}
```
