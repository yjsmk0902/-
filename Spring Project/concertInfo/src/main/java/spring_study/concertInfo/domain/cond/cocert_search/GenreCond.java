package spring_study.concertInfo.domain.cond.cocert_search;

public enum GenreCond {
    PLAY("연극"), MUSICAL("뮤지컬"), DANCE("무용"),
    CIRCUS("서커스"), CLASSIC("클래식"), PUBLIC_DANCING("대중 무용"),
    PUBLIC_MUSIC("대중 음악"), GUKAK("국악"), COMPLEX("복합");

    private final String description;


    GenreCond(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
