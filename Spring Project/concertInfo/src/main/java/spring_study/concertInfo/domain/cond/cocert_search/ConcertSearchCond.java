package spring_study.concertInfo.domain.cond.cocert_search;

import lombok.Data;

@Data
public class ConcertSearchCond {

    private SearchTypeCond searchTypeCond = SearchTypeCond.SHOW;  //SHOW, PLACE
    private GenreCond genreCond = GenreCond.MUSICAL;            //PLAY, MUSICAL, DANCE, CLASSIC, OPERA, GUKAK
    private StatusCond statusCond = StatusCond.PLAYING;          //EXPECTED, PLAYING, COMPLETED

    private String name;

    private String startEndDate;


    public ConcertSearchCond(String name, String startEndDate) {
        this.name = name;
        this.startEndDate = startEndDate;
    }
}
