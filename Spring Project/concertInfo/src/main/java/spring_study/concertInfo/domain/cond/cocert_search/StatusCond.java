package spring_study.concertInfo.domain.cond.cocert_search;

public enum StatusCond {
    EXPECTED("공연예정"), PLAYING("공연중"), COMPLETED("공연완료");

    private final String description;

    StatusCond(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}
