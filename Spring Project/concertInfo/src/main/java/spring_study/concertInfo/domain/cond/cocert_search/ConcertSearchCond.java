package spring_study.concertInfo.domain.cond.cocert_search;

import lombok.Data;

@Data
public class ConcertSearchCond {

    private GenreCond genreCond = GenreCond.MUSICAL;            //PLAY, MUSICAL, DANCE, CLASSIC, OPERA, GUKAK
    private StatusCond statusCond = StatusCond.PLAYING;          //EXPECTED, PLAYING, COMPLETED

    private String showName;
    private String showPlace;

    private String startEndDate;

    public ConcertSearchCond(String showName, String showPlace, String startEndDate) {
        this.showName = showName;
        this.showPlace = showPlace;
        this.startEndDate = startEndDate;
    }
}
