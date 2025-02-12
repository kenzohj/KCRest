/**
 * Projet: R411 - Projet filrouge - Equipe A
 * Auteurs: BONJOUR Corentin, FARCHETTO Lilian, HAMBLI Kenzo
 */
package edu.equipe_a.kcrest.utils;

import edu.equipe_a.kcrest.entries.Cinema;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Cette classe permet de localiser les cinémas les plus proches d'un point donné.
 */
public class CinemaLocator {

    private final List<Cinema> cinemas; // Liste des cinémas

    /**
     * Constructeur de la classe CinemaLocator.
     *
     * @param cinemas Liste des cinémas
     */
    public CinemaLocator(List<Cinema> cinemas) {
        this.cinemas = cinemas;
    }

    /**
     * Retourne les n cinémas les plus proches du point donné.
     *
     * @param latitude Latitude du point
     * @param longitude Longitude du point
     * @param n Nombre de cinémas à retourner
     * @return Liste des n cinémas les plus proches
     */
    public List<Cinema> findClosestCinemas(double latitude, double longitude, int n) {
        final List<Cinema> closestCinemas = new ArrayList<>(this.cinemas);

        // Calcul de la distance entre le point donné et chaque cinéma
        for (Cinema cinema : closestCinemas)
            cinema.setDistance(calculateDistance(latitude, longitude, cinema.getLatitude(), cinema.getLongitude()));

        // Tri des cinémas par distance
        closestCinemas.sort(Comparator.comparingDouble(Cinema::getDistance));

        // Retourne les n premiers cinémas les plus proches
        return closestCinemas.subList(0, Math.min(n, closestCinemas.size()));
    }

    /**
     * Calcule la distance entre deux points géographiques.
     *
     * @param lat1 Latitude du premier point
     * @param lon1 Longitude du premier point
     * @param lat2 Latitude du deuxième point
     * @param lon2 Longitude du deuxième point
     * @return Distance entre les deux points
     */
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final double earthRadius = 6371; // Rayon de la Terre en kilomètres
        /* Formule de Haversine */
        final double dLat = Math.toRadians(lat2 - lat1);
        final double dLon = Math.toRadians(lon2 - lon1);

        final double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                Math.sin(dLon / 2) * Math.sin(dLon / 2);

        final double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadius * c; // Distance in kilometers
    }

}
