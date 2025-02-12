package edu.equipe_a.kcrest.controllers;

import edu.equipe_a.kcrest.entries.User;
import edu.equipe_a.kcrest.services.UserService;
import edu.equipe_a.kcrest.utils.PasswordUtils;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Classe UserRestController
 * Cette classe est un contrôleur REST qui gère les requêtes HTTP relatives aux utilisateurs.
 * Elle utilise le service UserService pour effectuer les opérations demandées.
 */
@RestController
@RequestMapping("/user") // URL de base pour les requêtes relatives aux utilisateurs
public class UserRestController {

    // Service UserService
    @Autowired
    private UserService service;

    /**
     * Cette méthode permet de récupérer un utilisateur par son adresse mail.
     *
     * @param email Adresse mail de l'utilisateur.
     * @return ResponseEntity<User> Réponse HTTP contenant l'utilisateur.
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found"), // Utilisateur trouvé
            @ApiResponse(responseCode = "204", description = "User not found"), // Utilisateur non trouvé
    })
    @RequestMapping(
            value = "/by-email", // URL relative pour la requête
            method = RequestMethod.GET, // Méthode HTTP utilisée
            produces = "application/json" // Format de la réponse
    )
    public ResponseEntity<User> getUserByEmail(
        @RequestParam(value = "email") String email
    ) {
        final User user = this.service.getUser(email);
        if(user == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(user);
    }

    /**
     * Cette méthode permet de récupérer un utilisateur par son identifiant.
     *
     * @param id Identifiant de l'utilisateur.
     * @return ResponseEntity<User> Réponse HTTP contenant l'utilisateur.
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found"), // Utilisateur trouvé
            @ApiResponse(responseCode = "204", description = "User not found"), // Utilisateur non trouvé
    })
    @RequestMapping(
            value = "/by-id", // URL relative pour la requête
            method = RequestMethod.GET, // Méthode HTTP utilisée
            produces = "application/json" // Format de la réponse
    )
    public ResponseEntity<User> getUserById(
            @RequestParam(value = "id") long id
    ) {
        final User user = this.service.getUser(id);
        if(user == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(user);
    }

    /**
     * Cette méthode permet de vérifier les identifiants de l'utilisateur.
     *
     * @param email Mail de l'utilisateur.
     * @param password Mot de passe de l'utilisateur.
     * @return ResponseEntity<User> Réponse HTTP contenant l'utilisateur.
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found"), // Les identifiants sont corrects
            @ApiResponse(responseCode = "204", description = "User not found"), // Les identifiants sont incorrects
    })
    @RequestMapping(
            value = "/verify", // URL relative pour la requête
            method = RequestMethod.GET, // Méthode HTTP utilisée
            produces = "application/json" // Format de la réponse
    )
    public ResponseEntity<List<User>> verifyUser(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password
    ) {
        final User user = this.service.getUser(email);
        if(user == null)
            return ResponseEntity.noContent().build();
        if(!user.getHashedPassword().equals(PasswordUtils.hashPassword(password, Base64.getDecoder().decode(user.getSalt()))))
            return ResponseEntity.noContent().build();
        final List<User> u = new ArrayList<>();
        u.add(user);
        return ResponseEntity.ok(u);
    }

    /**
     * Cette méthode permet de récupérer tous les utilisateurs.
     *
     * @return ResponseEntity<List<User>> Réponse HTTP contenant la liste des utilisateurs.
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retrieve all users"), // Récupérer tous les utilisateurs
            @ApiResponse(responseCode = "204", description = "No users retrieved"), // Aucun utilisateur trouvé
    })
    @RequestMapping(
            value = "/all", // URL relative pour la requête
            method = RequestMethod.GET, // Méthode HTTP utilisée
            produces = "application/json" // Format de la réponse
    )
    public ResponseEntity<List<User>> getUsers() {
        final List<User> users = this.service.getAllUsers();
        if(users.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }

    /**
     * Cette méthode permet de créer un utilisateur.
     *
     * @param email Adresse mail de l'utilisateur.
     * @param firstName Prénom de l'utilisateur.
     * @param lastName Nom de l'utilisateur.
     * @param password Mot de passe de l'utilisateur.
     * @return ResponseEntity<User> Réponse HTTP contenant l'utilisateur créé.
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User created"), // Utilisateur créé
            @ApiResponse(responseCode = "204", description = "User with this mail already exists") // Utilisateur avec cette adresse mail existe déjà
    })
    @RequestMapping(
            value = "/create", // URL relative pour la requête
            method = RequestMethod.GET, // Méthode HTTP utilisée
            produces = "application/json" // Format de la réponse
    )
    public ResponseEntity<List<User>> createUser(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName,
            @RequestParam(value = "password") String password
    ) {
        final User user = this.service.createUser(firstName, lastName, email, password);
        if(user == null)
            return ResponseEntity.noContent().build();
        final List<User> u = new ArrayList<>();
        u.add(user);
        return ResponseEntity.ok(u);
    }

    /**
     * Cette méthode permet de supprimer un utilisateur.
     * @param email Mail de l'utilisateur.
     * @return boolean true si l'utilisateur a été supprimé, false sinon.
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User deleted"), // Utilisateur supprimé
            @ApiResponse(responseCode = "204", description = "User not found"), // Utilisateur non trouvé
    })
    @RequestMapping(
            value = "/delete", // URL relative pour la requête
            method = RequestMethod.GET, // Méthode HTTP utilisée
            produces = "application/json" // Format de la réponse
    )
    public boolean deleteUser(
            @RequestParam(value = "email") String email
    ) {
        return this.service.deleteUser(email);
    }

}
