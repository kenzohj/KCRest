package edu.equipe_a.kcrest.entries;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Geolocation {

    @JsonProperty("lon")
    private double lon;

    @JsonProperty("lat")
    private double lat;

}
