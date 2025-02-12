plugins {
	id("java") // Ajout du plugin java
	id("org.springframework.boot") version "3.2.3" // Ajout du plugin Spring Boot
	id("io.spring.dependency-management") version "1.1.4" // Ajout du plugin de gestion des dépendances Spring
}

group = "edu.equipe_a" // Définition du groupe du projet
version = "0.1-SNAPSHOT" // Définition de la version du projet

repositories {
	/* Ajout des dépôts Maven */
	mavenCentral()
}

dependencies {
	/* Dépendance pour la prise en charge de création d'API REST */
	implementation("org.springframework.boot:spring-boot-starter-web:3.2.3")
	/* Dépendance pour la prise en charge de la persistance des données (avec JPA) */
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.2.3")
	/* Dépendance pour la prise en charge de la gestion des données (avec json) */
	implementation("org.springframework.boot:spring-boot-starter-json:3.2.3")
	/* Dépendance pour rendre la prise en main de Spring plus simple */
	implementation("org.springframework.boot:spring-boot-devtools:3.2.3")

	/* Dépendance pour l'autogénération de la documentation de l'API */
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.4.0")

	/* Dépendance pour SQLite */
	implementation("org.xerial:sqlite-jdbc:3.45.2.0")

	/* Dépendance pour Hibernate */
	implementation("org.hibernate.orm:hibernate-community-dialects:6.4.4.Final")

	/* Dépendance pour Jackson */
	implementation("org.milyn:jackson:0.9.2")
}

java {
	/* Définition de la version de java utilisée */
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
	sourceCompatibility = JavaVersion.VERSION_17
	targetCompatibility = JavaVersion.VERSION_17
}

/* Ajout des options d'encodages et de compatibilité à la compilation */
tasks.withType<JavaCompile> {
	options.encoding = "UTF-8"
	options.isIncremental = true
	sourceCompatibility = "17"
}
