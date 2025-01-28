# ArchitectureProject2425

1ere etape : dans le dossier resources src/main/resources excute docker-compose up -d
ensuite lancer l'application puis 👍
test :
Use Case 1 : Gestion des Propriétés par l'Agent
1.1 Créer un Agent :
POst : 
http://localhost:8080/agents
{
  "firstname": "Alice",
  "lastname": "Dupont",
  "email": "alice.agent@example.com",
  "password": "mysecretpassword",
  "agencyName": "Magic Agency"
}

1.2 Ajouter une Propriété
POST
http://localhost:8080/properties
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

1.3 Mettre à Jour une Propriété
PUT
http://localhost:8080/properties/{propertyId}
{
  "name": "Updated Super Apartment",
  "price": 95000.0,
  "fundingDeadline": "2025-11-30",
  "fundingOpen": true,
  "rentalIncomePercentage": 6.5,
  "appreciationRate": 2.5,
  "type": "apartment"
}

1.4 Supprimer une Propriété
DELETE
http://localhost:8080/properties/{propertyId}

1.5 Lister Toutes les Propriétés
GET
http://localhost:8080/properties


Use Case 2 : Listing des Propriétés Ouvertes pour les Utilisateurs Finaux
2.1 Lister les Propriétés Ouvertes (Maximum 6)
GET
http://localhost:8080/properties/open



Use case 3 : The end users can consult their portfolio (List of shares in different properties they already acquired), “Prior to any investment, the investor customer must create a profile with needed personal information required by the Capital Market Authority.”
3-1-GET /investors/{id}
3-2-POST /investors crée l’investor, et si vous tentez d’investir avec un investorId inexistant, le code lève une exception.

Use case 4 : For every investor customer, we need to put in place a wallet. This Wallet is used to provision
Money internally in the system before funding any property, and it is used to receive the
monthly rental income from properties already in the user’s portfolio.

4-1- Add money before any invest 
POST /payments 
{
  "investorId": "UUID",
  "amount": 5000.0,
  "status": "SUCCESS"
}
Si status = SUCCESS, la méthode createTransaction(...) crédite le wallet de l’investor

4-2-Monthly Rental Income
pour tester  : 
cree une proporite avec un agent existant 
POST /properties
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

cree un investor : 
POST /investors
{
  "firstname": "kill",
  "lastname": "kam",
  "email": "killakam@example.com",
  "password": "secret14523"
}

cree un 2e investor  : 
{
  "firstname": "Alice",
  "lastname": "Investor",
  "email": "alice@example.com",
  "password": "secret123"
}

Alice invest 20000$
POST /investments/invest
{
  "investorId": "c034d7f7-dd7c-4d83-a4e2-8c7fe6cbb3c0",
  "propertyId": "bc60861d-df8c-4e64-bb50-f868780efede",
  "amount": 20000
}

kill kam invest 30000 : 
{
  "investorId": "58ca4974-c737-431e-b5e1-7c207af14a10",
  "propertyId": "90926908-d8ba-490b-bd5d-f36c352ff8e7",
  "amount": 20000
}

distribute mensual rent : 
http://localhost:8080/rental/distribute
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

see all the wallets : to see the amount they gain 
GET /investors

Use case 5 : Each property that is open for funding must meet a funding deadline: if the property is not 
fully funded within 2 months after the launching of the funding process, the funding stops, 
and the investors are refunded the money they spent on that property. The money is 
returned into their Wallet. 

Test :  Create un agent
Créer une Propriété avec un fundingDeadline proche (ex. LocalDate.now().plusDays(1)) pour simuler un délai court.
POST /properties
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
creer un Investor 
faire un investment 

Simuler l’échec du funding



























