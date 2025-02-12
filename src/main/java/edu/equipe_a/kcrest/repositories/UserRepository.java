package edu.equipe_a.kcrest.repositories;

import edu.equipe_a.kcrest.entries.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface permettant de gérer les requêtes SQL sur la table User.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Méthode permettant de vérifier si un utilisateur existe en base de données.
     *
     * @param id l'identifiant de l'utilisateur.
     * @return true si l'utilisateur existe, false sinon.
     */
    boolean existsById(long id);

    /**
     * Méthode permettant de récupérer un utilisateur par son identifiant.
     *
     * @param id l'identifiant de l'utilisateur.
     * @return l'utilisateur correspondant à l'identifiant.
     */
    User findById(long id);

    /**
     * Méthode permettant de vérifier si un utilisateur existe en base de données.
     *
     * @param email l'email de l'utilisateur.
     * @return true si l'utilisateur existe, false sinon.
     */
    boolean existsByEmail(String email);

    /**
     * Méthode permettant de récupérer un utilisateur par son email.
     * @param email l'email de l'utilisateur.
     * @return l'utilisateur correspondant à l'email.
     */
    User findByEmail(String email);

    boolean deleteByEmail(String email);

}
