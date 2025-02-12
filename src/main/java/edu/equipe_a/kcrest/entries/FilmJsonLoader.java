package edu.equipe_a.kcrest.entries;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FilmJsonLoader {
    /*
    Data example :
    {
        "backdrop_path": "/tmU7GeKVybMWFButWEGl2M4GeiP.jpg",
        "title": "Le Parrain",
        "original_language": "en",
        "original_title": "The Godfather",
        "overview": "La Seconde Guerre mondiale vient de s'achever. À New York, le « parrain » Don Corleone, l'un des chefs respectés de la mafia, se sent vieillir. Il refuse de s'adapter à son temps et de se lancer, comme ses pairs, dans le trafic de drogue. Une frilosité qui entrave la bonne marche des affaires des autres « familles » et qui lui vaut d'être la cible d'un attentat. Don Corleone survit à ses blessures, mais reste très diminué. Mike, son plus jeune fils, qui jusque-là se tenait à l'écart des affaires de son père, devient le plus dévoué de ses héritiers. Plus efficace que ses frères, Sonny et Fredo, il venge son père et organise l'élimination de ses adversaires…",
        "poster_path": "/wnDNKCeBQzioXYQrXcSyrmRHVxf.jpg",
        "runtime": 175,
        "release_date": "1972-03-14"
    },
    */

    @JsonProperty("backdrop_path")
    private String backdropPath;

    @JsonProperty("title")
    private String title;

    @JsonProperty("original_language")
    private String originalLanguage;

    @JsonProperty("original_title")
    private String originalTitle;

    @JsonProperty("overview")
    private String overview;

    @JsonProperty("poster_path")
    private String posterPath;

    @JsonProperty("runtime")
    private int runtime;

    @JsonProperty("release_date")
    private String releaseDate;

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getReleaseDate() {
        return releaseDate;
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
