package spring_study.concertInfo.web.controller;

import lombok.RequiredArgsConstructor;
import org.jdom2.JDOMException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import spring_study.concertInfo.domain.dto.ConcertResponseDTO;
import spring_study.concertInfo.web.service.ConcertService;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ConcertAPITestController {
    private final ConcertService concertService;

    @GetMapping("/api/v1/concert/{keyword}")
    public List<ConcertResponseDTO> get(@PathVariable String keyword) throws IOException, JDOMException {
        return concertService.findByKeywordAndDate("20221101", "20230101", keyword);
    }
}
