# ArchitectureProject2425



FractionalPropertyOwnership/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── propertystake/
│   │   │           ├── FractionalPropertyOwnershipApplication.java  # Classe principale
│   │   │           ├── controller/
│   │   │           │   └── PropertyController.java                  # Contrôleur REST
│   │   │           ├── model/
│   │   │           │   └── Property.java                            # Classe modèle
│   │   │           ├── service/
│   │   │               ├── PropertyService.java                     # Interface du service
│   │   │               └── PropertyServiceImpl.java                 # Implémentation du service
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── propertystake/
│       │           ├── PropertyServiceTests.java                    # Tests unitaires pour le service
│       │           └── PropertyControllerTests.java                 # Tests unitaires pour le contrôleur
│       └── resources/
│           └── application.properties
├── .gitignore
├── pom.xml (ou build.gradle)
└── README.md