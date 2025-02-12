package edu.equipe_a.kcrest.entries;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Film {

    // un film a une liste d'ints de 1 à 20 (qui représente les salles où il est diffusé)
    private final List<Integer> rooms;
    private final List<Integer> days;
    //hashmap pour chaque salle, une liste de jours et d'horaires de chaque jour
    private final HashMap<Integer, HashMap<Integer, List<Integer>>> scheduleByRoom;
    private final String title;
    private final int duration;
    private final String imageLink;
    private final String description;
    private final String releaseDate;
    private final String originalLanguage;

    public Film(String title, int duration, String imageLink, String description, String releaseDate, String originalLanguage) {
        this.rooms = new ArrayList<>();
        this.days = new ArrayList<>();
        this.scheduleByRoom = new HashMap<>();
        this.generateRooms();
        this.generateDays();
        this.generateSchedule();
        this.title = title;
        this.duration = duration;
        this.imageLink = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + imageLink;
        this.description = description;
        this.releaseDate = releaseDate;
        this.originalLanguage = originalLanguage;
    }

    public void generateRooms() {
        // 40% de chance que le film soit diffusé dans une salle
        for (int i = 0; i < 20; i++) {
            if (Math.random() < 0.4) {
                this.rooms.add(i);
            }
        }
    }

    public void generateSchedule() {
        // pour chaque salle, on génère jusqu'a 3 horaire pour chaque jour de la liste
        for (int room : this.rooms) {
            for (int day : this.days) {
                //on genere jusqu'a 3 horaires pour chaque jour
                List<Integer> hours = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    // 40% de chance que le film soit diffusé à cet horaire
                    if (Math.random() < 0.4) {
                        //on genere un horaire entre 10h et 22h
                        int hour = (int) (Math.random() * 12) + 10;
                        hours.add(hour);
                    }
                }
                if (!hours.isEmpty()) {
                    this.scheduleByRoom.put(room, new HashMap<>());
                    this.scheduleByRoom.get(room).put(day, hours);
                }
            }
        }
    }

    public void generateDays() {
        for (int i = 0; i < 7; i++) {
            if (Math.random() < 0.4) {
                this.days.add(i);
            }
        }
    }

    public List<Integer> getRooms() {
        return rooms;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public String getImageLink() {
        return imageLink;
    }

    public String getDescription() {
        return description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public List<Integer> getDays() {
        return days;
    }

    public HashMap<Integer, HashMap<Integer, List<Integer>>> getScheduleByRoom() {
        return scheduleByRoom;
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
