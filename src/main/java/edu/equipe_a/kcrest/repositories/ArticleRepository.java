package edu.equipe_a.kcrest.repositories;

import edu.equipe_a.kcrest.entries.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface permettant de gérer les requêtes SQL sur la table Article.
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    /**
     * Méthode permettant de récupérer les articles d'un utilisateur.
     *
     * @param userId l'identifiant de l'utilisateur.
     * @return les articles de l'utilisateur.
     */
    List<Article> findArticlesByUserIdEquals(long userId);

    /**
     * Méthode permettant de récupérer tous les articles.
     *
     * @param pageable pagination.
     * @return les articles.
     */
    List<Article> findAllBy(Pageable pageable);

}
