package spring_study.concertInfo.domain.cond.cocert_search;

import lombok.Data;

@Data
public class ConcertSearchCond {

    private SearchTypeCond searchTypeCond = SearchTypeCond.SHOW;  //SHOW, PLACE
    private GenreCond genreCond = GenreCond.MUSICAL;            //PLAY, MUSICAL, DANCE, CLASSIC, OPERA, GUKAK
    private StatusCond statusCond = StatusCond.PLAYING;          //EXPECTED, PLAYING, COMPLETED

    private String name;

    private String startEndDate;

    private int nowPage = 1;

    public ConcertSearchCond(String name, String startEndDate, int nowPage) {
        this.name = name;
        this.startEndDate = startEndDate;
        this.nowPage = nowPage;
    }

}
