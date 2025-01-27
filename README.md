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






































