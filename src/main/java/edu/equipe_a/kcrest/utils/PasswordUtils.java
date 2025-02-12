/**
 * Projet: R411 - Projet filrouge - Equipe A
 * Auteurs: BONJOUR Corentin, FARCHETTO Lilian, HAMBLI Kenzo
 */
package edu.equipe_a.kcrest.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Classe utilitaire pour la gestion des utilisateurs.
 */
public abstract class PasswordUtils {

    /**
     * Génère un sel aléatoire pour le hachage du mot de passe.
     * @return Sel aléatoire.
     */
    public static byte[] generateSalt() {
        // On utilise un générateur de nombres aléatoires sécurisé pour générer un sel aléatoire.
        final SecureRandom random = new SecureRandom();
        final byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    /**
     * Hache le mot de passe de l'utilisateur.
     * @param password Mot de passe de l'utilisateur.
     * @param salt Sel aléatoire.
     * @return Mot de passe haché.
     */
    public static String hashPassword(String password, byte[] salt) {
        try {
            // On utilise l'algorithme SHA-256 pour hacher le mot de passe de l'utilisateur.
            final MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(salt);
            final byte[] hashedPassword = messageDigest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erreur lors du hachage du mot de passe.", e);
        }
    }

}
