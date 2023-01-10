package spring_study.concertInfo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class ConcertDetailsResponseDTO {

    private String showId;
    private String placeId;
    private String showName;
    private String stDate;
    private String edDate;
    private String place;
    private String actors;
    private String producers;
    private String runtime;
    private String age;
    private String production;
    private String ticketPrice;
    private String poster_URL;
    private String story;
    private String genre;
    private String status;
    private String openRun;
    private List<String> introImg;
    private String showtime;

    public ConcertDetailsResponseDTO(
            String showId, String placeId, String showName, String stDate,
            String edDate, String place, String actors, String producers,
            String runtime, String age, String production, String ticketPrice,
            String poster_URL, String story, String genre, String status,
            String openRun, List<String> introImg, String showtime) {
        this.showId = showId;
        this.placeId = placeId;
        this.showName = showName;
        this.stDate = stDate;
        this.edDate = edDate;
        this.place = place;
        this.actors = actors;
        this.producers = producers;
        this.runtime = runtime;
        this.age = age;
        this.production = production;
        this.ticketPrice = ticketPrice;
        this.poster_URL = poster_URL;
        this.story = story;
        this.genre = genre;
        this.status = status;
        this.openRun = openRun;
        this.introImg = introImg;
        this.showtime = showtime;
    }
}
