package edu.equipe_a.kcrest.entries;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // Identifiant unique de l'article
    private long userId; // Identifiant de l'utilisateur ayant réservé l'article
    private String filmName; // Nom du film
    private Date filmDate; // Date de la séance
    private int filmDuration; // Durée du film

    /**
     * Constructeur par défaut.
     */
    public Article() {}

    /**
     * Constructeur avec paramètres.
     *
     * @param userId Identifiant de l'utilisateur ayant réservé l'article.
     * @param filmName Nom du film.
     * @param filmDate Date de la séance.
     * @param filmDuration Durée du film.
     */
    public Article(long userId, String filmName, Date filmDate, int filmDuration) {
        this.userId = userId;
        this.filmName = filmName;
        this.filmDate = filmDate;
        this.filmDuration = filmDuration;
    }

    /**
     * Retourne l'identifiant de l'article.
     * @return Identifiant de l'article.
     */
    public long getId() {
        return this.id;
    }

    /**
     * Retourne l'identifiant de l'utilisateur ayant réservé l'article.
     * @return Identifiant de l'utilisateur ayant réservé l'article.
     */
    public long getUserId() {
        return this.userId;
    }

    /**
     * Retourne le nom du film.
     * @return Nom du film.
     */
    public String getFilmName() {
        return this.filmName;
    }

    /**
     * Retourne la date de la séance.
     * @return Date de la séance.
     */
    public Date getFilmDate() {
        return this.filmDate;
    }

    /**
     * Retourne la durée du film.
     * @return Durée du film.
     */
    public int getFilmDuration() {
        return this.filmDuration;
    }

}
