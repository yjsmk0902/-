package spring_study.concertInfo.domain.cond.cocert_search;

public enum GenreCond {
    TOTAL("종합"), PLAY("연극"), MUSICAL("뮤지컬"), DANCE_POPULAR("대중무용"),
    DANCE("무용"), CLASSIC("클래식"), CIRCUS("서커스/마술"),
    //    OPERA("오페라"),
    MUSIC("대중음악"), GUKAK("국악"), COMPLEX("복합");


    private final String description;


    GenreCond(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
