package edu.equipe_a.kcrest.controllers;

import edu.equipe_a.kcrest.utils.CinemaLocator;
import edu.equipe_a.kcrest.KCRestApplication;
import edu.equipe_a.kcrest.entries.Film;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CinemaRestController {

    @GetMapping("/api")
    public String getRest() {
        return KCRestApplication.CINEMAS.toString();
    }

    @GetMapping(value = "/api/closest", produces=MediaType.APPLICATION_JSON_VALUE)
    public String getClosestCinemas(double latitude, double longitude, int n) {
        final CinemaLocator cinemaLocator = new CinemaLocator(KCRestApplication.CINEMAS);
        return cinemaLocator.findClosestCinemas(latitude, longitude, n).toString();
    }

    @GetMapping(value = "/api/fulldatafilm", produces=MediaType.APPLICATION_JSON_VALUE)
    public String getFilms() {
        return KCRestApplication.JSON_FILMS.toString();
    }

    //retourne la liste des films, de la classe Film (qui contient deja les endroit où il est diffusé et les horaires / jours de diffusion)
    @GetMapping(value = "/api/films", produces=MediaType.APPLICATION_JSON_VALUE)
    public String getFilmsInfo() {
        return KCRestApplication.FILMS.toString();
    }

    //recupérer les films qui sont diffusés dans un cinéma
    @GetMapping(value = "/api/filmsbycinema", produces=MediaType.APPLICATION_JSON_VALUE)
    public String getFilmsByCinema(int cinemaId) {
        //on fait une liste vide de film pour le cinema
        final List<Film> films = new ArrayList<>();
        //on parcourt les films
        for (Film film : KCRestApplication.FILMS) {
            //si le film est diffusé dans le cinema
            if (film.getRooms().contains(cinemaId)) {
                //on l'ajoute à la liste
                films.add(film);
            }
        }
        return films.toString();
    }

}
