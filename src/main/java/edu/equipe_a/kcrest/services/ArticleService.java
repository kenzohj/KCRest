/**
 * Projet: R411 - Projet filrouge - Equipe A
 * Auteurs: BONJOUR Corentin, FARCHETTO Lilian, HAMBLI Kenzo
 */
package edu.equipe_a.kcrest.services;

import edu.equipe_a.kcrest.entries.Article;
import edu.equipe_a.kcrest.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Service de gestion des articles
 */
@Service
public class ArticleService {

    // Injection de dépendance du repository
    @Autowired
    private ArticleRepository repository;

    /**
     * Méthode permettant de récupérer un article à partir de l'identifiant de l'utilisateur.
     *
     * @param userId L'id de l'utilisateur.
     * @return Les articles correspondants à l'utilisateur donné.
     */
    @Transactional
    public List<Article> getAllArticles(long userId) {
        return this.repository.findArticlesByUserIdEquals(userId);
    }

    /**
     * Méthode permettant de récupérer les articles restants dans les deux prochaines heures.
     *
     * @param userId L'id de l'utilisateur.
     * @return Les articles restants dans les deux prochaines heures.
     */
    @Transactional
    public List<Article> getAllTwoHoursLeftArticles(long userId) {
        final List<Article> articles = this.repository.findAllBy(PageRequest.of(0, getAllArticles(userId).size(), Sort.by(Sort.Direction.ASC, "filmDate")));
        final List<Article> twoHoursLeftArticles = new ArrayList<>();

        final LocalDateTime currentTime = LocalDateTime.now();
        for (Article article : articles) {
            final LocalDateTime articleDateTime = article.getFilmDate().toInstant().atZone(ZoneId.of("Europe/Paris")).toLocalDateTime();

            final long hoursDifference = ChronoUnit.HOURS.between(currentTime, articleDateTime);
            if (hoursDifference <= 2 && hoursDifference >= 0)
                twoHoursLeftArticles.add(article);
        }

        return twoHoursLeftArticles;
    }

    /**
     * Méthode permettant de créer un article.
     *
     * @param article L'article à créer.
     */
    @Transactional
    public void addArticle(Article article) {
        this.repository.save(article);
    }

    /**
     * Méthode permettant de supprimer un article.
     *
     * @param id L'id de l'article à supprimer.
     */
    @Transactional
    public void removeArticle(long id) {
        this.repository.deleteById(id);
    }

}
