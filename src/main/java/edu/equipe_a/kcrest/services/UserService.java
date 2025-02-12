/**
 * Projet: R411 - Projet filrouge - Equipe A
 * Auteurs: BONJOUR Corentin, FARCHETTO Lilian, HAMBLI Kenzo
 */
package edu.equipe_a.kcrest.services;

import edu.equipe_a.kcrest.entries.User;
import edu.equipe_a.kcrest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Classe de service regroupant les méthodes de gestion des utilisateurs.
 */
@Service
public class UserService {

    // Injection de dépendance du repository
    @Autowired
    private UserRepository repository;

    /**
     * Méthode permettant de récupérer un utilisateur à partir de son email.
     *
     * @param email L'email de l'utilisateur à récupérer.
     * @return L'utilisateur correspondant à l'email donné.
     */
    @Transactional
    public User getUser(String email) {
        return this.repository.findByEmail(email);
    }

    /** Méthode permettant de récupérer un utilisateur à partir de son id.
     *
     * @param id L'id de l'utilisateur à récupérer.
     * @return L'utilisateur correspondant à l'id donné.
     */
    @Transactional
    public User getUser(long id) {
        return this.repository.findById(id);
    }

    /**
     * Méthode permettant de récupérer tous les utilisateurs.
     *
     * @return La liste de tous les utilisateurs.
     */
    @Transactional
    public List<User> getAllUsers() {
        return this.repository.findAll();
    }

    /**
     * Méthode permettant de créer un utilisateur.
     *
     * @param firstName Le prénom de l'utilisateur.
     * @param lastName Le nom de l'utilisateur.
     * @param email L'email de l'utilisateur.
     * @param password Le mot de passe de l'utilisateur.
     * @return L'utilisateur créé.
     */
    @Transactional
    public User createUser(String firstName, String lastName, String email, String password) {
        if(this.repository.existsByEmail(email))
            return null;
        return this.repository.save(new User(firstName, lastName, email, password));
    }

    /**
     * Méthode permettant de supprimer un utilisateur.
     *
     * @param email Le mail de l'utilisateur à supprimer.
     * @return true si l'utilisateur a été supprimé, false s'il n'existe pas.
     */
    @Transactional
    public boolean deleteUser(String email) {
        return this.repository.deleteByEmail(email);
    }

}
