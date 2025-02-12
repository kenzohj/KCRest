/**
 * Projet: R411 - Projet filrouge - Equipe A
 * Auteurs: BONJOUR Corentin, FARCHETTO Lilian, HAMBLI Kenzo
 */
package edu.equipe_a.kcrest.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Classe utilitaire pour formatter les dates.
 */
public abstract class DateParser {

    /**
     * Formate une date au format "yyyy-MM-dd hh:mm" en objet Date.
     * @param dateString La date à formatter.
     * @return L'objet Date correspondant à la date passée en paramètre.
     */
    public static Date parse(String dateString) {
        try {
            final Date parsedDate = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(dateString);
            final Calendar calendar = Calendar.getInstance();
            calendar.setTime(parsedDate);
            calendar.add(Calendar.HOUR_OF_DAY, -2);
            return calendar.getTime();
        } catch (ParseException ignored) {
            return null;
        }
    }

}
