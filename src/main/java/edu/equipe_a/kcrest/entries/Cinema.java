package edu.equipe_a.kcrest.entries;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Cinema {

    @JsonProperty("regioncnc")
    private int regionCnc;

    @JsonProperty("ndeg_auto")
    private String ndegAuto;

    @JsonProperty("name")
    private String name;

    @JsonProperty("region")
    private String region;

    @JsonProperty("address")
    private String address;

    @JsonProperty("insee_code")
    private String inseeCode;

    @JsonProperty("district")
    private String district;

    @JsonProperty("district_population")
    private double districtPopulation;

    @JsonProperty("dep")
    private String dep;

    @JsonProperty("ndeguu")
    private String ndegUu;

    @JsonProperty("urban_unity")
    private String urbanUnity;

    @JsonProperty("urban_unity_population")
    private double urbanUnityPopulation;

    @JsonProperty("geographical_situation")
    private String geographicalSituation;

    @JsonProperty("screens")
    private Double screens;

    @JsonProperty("seats")
    private Double seats;

    @JsonProperty("week_activity")
    private Double weekActivity;

    @JsonProperty("sessions")
    private Double sessions;

    @JsonProperty("entries_2022")
    private Double entries2022;

    @JsonProperty("entries_2021")
    private Double entries2021;

    @JsonProperty("entries_upgrade")
    private Double entriesUpgrade;

    @JsonProperty("entry_range")
    private String entryRange;

    @JsonProperty("programmer")
    private String programmer;

    @JsonProperty("ae")
    private String ae;

    @JsonProperty("categorie_art_et_essai")
    private String categorieArtEtEssai;

    @JsonProperty("label_art_et_essai")
    private String labelArtEtEssai;

    @JsonProperty("genre")
    private String genre;

    @JsonProperty("multiplexe")
    private String multiplexe;

    @JsonProperty("zone_de_la_commune")
    private String zoneDeLaCommune;

    @JsonProperty("nombre_de_films_programmes")
    private Double nombreDeFilmsProgrammes;

    @JsonProperty("nombre_de_films_inedits")
    private Double nombreDeFilmsInedits;

    @JsonProperty("nombre_de_films_en_semaine_1")
    private Double nombreDeFilmsEnSemaine1;

    @JsonProperty("pdm_en_entrees_des_films_francais")
    private Double pdmEnEntreesDesFilmsFrancais;

    @JsonProperty("pdm_en_entrees_des_films_americains")
    private Double pdmEnEntreesDesFilmsAmericains;

    @JsonProperty("pdm_en_entrees_des_films_europeens")
    private Double pdmEnEntreesDesFilmsEuropeens;

    @JsonProperty("pdm_en_entrees_des_autres_films")
    private Double pdm_en_entrees_des_autres_films;

    @JsonProperty("films_art_et_essai")
    private Double filmsArtEtEssai;

    @JsonProperty("pdm_en_entrees_des_films_art_et_essai")
    private Double pdmEnEntreesDesFilmsArtEtEssai;

    @JsonProperty("latitude")
    private String latitude;

    @JsonProperty("longitude")
    private String longitude;

    @JsonProperty("geolocalisation")
    private Geolocation geolocation;

    private double distance;

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    public double getLatitude() {
        return Double.parseDouble(latitude);
    }

    public double getLongitude() {
        return Double.parseDouble(longitude);
    }

    @Override
    public String toString() {
        final ObjectMapper mapper = new ObjectMapper();
        final String jsonString;
        try {
            jsonString = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("toString()", e);
        }
        return jsonString;
    }

}
