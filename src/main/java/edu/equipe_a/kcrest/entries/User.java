package edu.equipe_a.kcrest.entries;

import edu.equipe_a.kcrest.utils.PasswordUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Base64;

/**
 * Classe représentant un utilisateur.
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // Identifiant unique de l'utilisateur
    private String firstName; // Prénom de l'utilisateur
    private String lastName; // Nom de l'utilisateur
    private String email; // Adresse email de l'utilisateur
    private String salt; // Sel utilisé pour le hachage du mot de passe
    private String hashedPassword; // Mot de passe haché

    /**
     * Constructeur par défaut.
     */
    public User() {}

    /**
     * Constructeur avec paramètres.
     * @param firstName Prénom de l'utilisateur
     * @param lastName Nom de l'utilisateur
     * @param email Adresse email de l'utilisateur
     * @param password Mot de passe de l'utilisateur
     */
    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        final byte[] salt = PasswordUtils.generateSalt();
        this.salt = Base64.getEncoder().encodeToString(salt);
        this.hashedPassword = PasswordUtils.hashPassword(password, salt);
    }

    /**
     * Retourne l'identifiant de l'utilisateur.
     * @return Identifiant de l'utilisateur.
     */
    public long getId() {
        return this.id;
    }

    /**
     * Retourne le prénom de l'utilisateur.
     * @return Prénom de l'utilisateur.
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Retourne le nom de l'utilisateur.
     * @return Nom de l'utilisateur.
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Retourne l'adresse email de l'utilisateur.
     * @return Adresse email de l'utilisateur.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Retourne le sel utilisé pour le hachage du mot de passe.
     * @return Sel utilisé pour le hachage du mot de passe.
     */
    public String getSalt() {
        return this.salt;
    }

    /**
     * Retourne le mot de passe haché.
     * @return Mot de passe haché.
     */
    public String getHashedPassword() {
        return hashedPassword;
    }

}
