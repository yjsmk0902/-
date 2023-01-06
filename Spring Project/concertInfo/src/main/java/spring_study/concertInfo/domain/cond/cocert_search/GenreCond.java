package spring_study.concertInfo.domain.cond.cocert_search;

public enum GenreCond {
    PLAY("연극"), MUSICAL("뮤지컬"), DANCE("무용"),
    CLASSIC("클래식"), OPERA("오페라"),
    GUKAK("국악"), COMPLEX("복합");

    private final String description;


    GenreCond(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
