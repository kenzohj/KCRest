package edu.equipe_a.kcrest.controllers;

import edu.equipe_a.kcrest.entries.Article;
import edu.equipe_a.kcrest.services.ArticleService;
import edu.equipe_a.kcrest.utils.DateParser;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe ArticleRestController
 * Cette classe est un contrôleur REST qui gère les requêtes HTTP relatives aux articles.
 * Elle utilise le service ArticleService pour effectuer les opérations demandées.
 */
@RestController
@RequestMapping("/article") // URL de base pour les requêtes relatives aux articles
public class ArticleRestController {

    @Autowired
    private ArticleService service;

    /**
     * Cette méthode permet de récupérer tous les articles.
     *
     * @param userId Identifiant de l'utilisateur.
     * @return ResponseEntity<List<Article>> Réponse HTTP contenant la liste des articles.
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retrieve all articles"), // Récupérer tous les articles
            @ApiResponse(responseCode = "204", description = "No articles retrieved"), // Aucun article trouvé
    })
    @RequestMapping(
            value = "/all", // URL relative pour la requête
            method = RequestMethod.GET, // Méthode HTTP utilisée
            produces = "application/json" // Format de la réponse
    )
    public ResponseEntity<List<Article>> getArticles(
            @RequestParam(value = "userId") long userId
    ) {
        final List<Article> articles = this.service.getAllArticles(userId);
        if(articles.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(articles);
    }

    /**
     * Cette méthode permet de récupérer tous les articles dont leur date de projection est prévue dans moins de 2h.
     *
     * @param userId Identifiant de l'utilisateur.
     * @return ResponseEntity<List<Article>> Réponse HTTP contenant la liste des articles.
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retrieve all articles"), // Récupérer tous les articles
            @ApiResponse(responseCode = "204", description = "No articles retrieved"), // Aucun article trouvé
    })
    @RequestMapping(
            value = "/notif", // URL relative pour la requête
            method = RequestMethod.GET, // Méthode HTTP utilisée
            produces = "application/json" // Format de la réponse
    )
    public ResponseEntity<List<Article>> getArticlesNotification(
            @RequestParam(value = "userId") long userId
    ) {
        final List<Article> articles = this.service.getAllTwoHoursLeftArticles(userId);
        if(articles.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(articles);
    }

    /**
     * Cette méthode permet d'ajouter un article.
     *
     * @param userId Identifiant de l'utilisateur.
     * @param filmName Nom du film.
     * @param filmDate Date de la séance.
     * @param filmDuration Durée du film.
     */
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Article added"), // Article ajouté
    })
    @RequestMapping(
            value = "/add", // URL relative pour la requête
            method = RequestMethod.GET, // Méthode HTTP utilisée
            produces = "application/json" // Format de la réponse
    )
    public ResponseEntity<List<Article>> addArticle(
            @RequestParam(value = "userId") long userId,
            @RequestParam(value = "filmName") String filmName,
            @RequestParam(value = "filmDate") String filmDate, // yyyy-MM-dd hh:mm
            @RequestParam(value = "filmDuration") int filmDuration
    ) {
        this.service.addArticle(new Article(userId, filmName, DateParser.parse(filmDate), filmDuration));
        return ResponseEntity.status(HttpStatusCode.valueOf(HttpStatus.CREATED.value())).body(new ArrayList<>());
    }

    /**
     * Cette méthode permet de supprimer un article.
     *
     * @param id Identifiant de l'article à supprimer.
     */
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Article removed"), // Article supprimé
    })
    @RequestMapping(
            value = "/remove", // URL relative pour la requête
            method = RequestMethod.GET, // Méthode HTTP utilisée
            produces = "application/json" // Format de la réponse
    )
    public ResponseEntity<List<Article>> removeArticle(
            @RequestParam(value = "id") long id
    ) {
        this.service.removeArticle(id);
        return ResponseEntity.status(HttpStatusCode.valueOf(HttpStatus.CREATED.value())).body(new ArrayList<>());
    }

}
