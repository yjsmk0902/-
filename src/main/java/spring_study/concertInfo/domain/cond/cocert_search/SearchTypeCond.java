package spring_study.concertInfo.domain.cond.cocert_search;

public enum SearchTypeCond {
    SHOW("공연"), PLACE("장소");

    private final String description;

    SearchTypeCond(String description) {
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
}
