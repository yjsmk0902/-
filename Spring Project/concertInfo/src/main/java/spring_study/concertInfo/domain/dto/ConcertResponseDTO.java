package spring_study.concertInfo.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
public class ConcertResponseDTO {
    private String id;
    private String title;
    private String stDate;
    private String edDate;
    private String place;
    private String poster_URL;
    private String genre;
    private String status;
    private String openRun;

    public ConcertResponseDTO(
            String id,
            String title,
            String stDate,
            String edDate,
            String place,
            String poster_URL,
            String genre,
            String status,
            String openRun) {
        this.id = id;
        this.title = title;
        this.stDate = stDate;
        this.edDate = edDate;
        this.place = place;
        this.poster_URL = poster_URL;
        this.genre = genre;
        this.status = status;
        this.openRun = openRun;
    }
}
