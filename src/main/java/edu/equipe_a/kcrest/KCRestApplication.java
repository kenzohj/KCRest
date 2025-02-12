/**
 * Projet: R411 - Projet filrouge - Equipe A
 * Auteurs: BONJOUR Corentin, FARCHETTO Lilian, HAMBLI Kenzo
 */
package edu.equipe_a.kcrest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.equipe_a.kcrest.entries.Cinema;
import edu.equipe_a.kcrest.entries.Film;
import edu.equipe_a.kcrest.entries.FilmJsonLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe principale de l'application.
 * Elle permet de charger les fichiers json et de les transformer en objets java.
 * Elle permet également de lancer l'application.
 */
@SpringBootApplication
public class KCRestApplication {

	public static List<Cinema> CINEMAS; // Liste des cinémas
	public static List<FilmJsonLoader> JSON_FILMS; // Liste des films (json)
	public static List<Film> FILMS; // Liste des films

	/**
	 * Méthode principale de l'application.
	 * @param args Arguments de la ligne de commande.
	 */
	public static void main(String[] args) {
		FILMS = new ArrayList<>(); // Initialisation de la liste des films

		/* Récupération des fichiers de données json */
		final InputStream inputStreamCinema = KCRestApplication.class.getClassLoader().getResourceAsStream("etablissements-cinematographiques.json");
		if(inputStreamCinema == null)
			throw new RuntimeException("Impossible de récupérer le fichier json !");

		final ObjectMapper mapper = new ObjectMapper();
		try {
			CINEMAS = mapper.readValue(inputStreamCinema, new TypeReference<>() {});
		} catch (IOException e) {
			throw new RuntimeException("", e);
		}

		final InputStream inputStreamFilm = KCRestApplication.class.getClassLoader().getResourceAsStream("films.json");
		if(inputStreamFilm == null)
			throw new RuntimeException("Impossible de récupérer le fichier json !");
		try {
			JSON_FILMS = mapper.readValue(inputStreamFilm, new TypeReference<>() {});
		} catch (IOException e) {
			throw new RuntimeException("", e);
		}

		// Transformation des films en objets java
		for (FilmJsonLoader filmJsonLoader : JSON_FILMS) {
			final Film film = new Film(filmJsonLoader.getTitle(), filmJsonLoader.getRuntime(), filmJsonLoader.getPosterPath(), filmJsonLoader.getOverview(), filmJsonLoader.getReleaseDate(), filmJsonLoader.getOriginalLanguage());
			FILMS.add(film);
		}

		SpringApplication.run(KCRestApplication.class, args); // Lancement de l'application
	}

}
